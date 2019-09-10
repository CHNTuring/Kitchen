package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmFoodManager_ModifyFood extends JDialog implements ActionListener {

	public BeanFood food = null;
	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelName = new JLabel("ʳ�����ƣ�");
	private JLabel labelDescription = new JLabel("ʳ��������");
	private JLabel labelFoodType = new JLabel("ʳ�����ࣺ");
	private JLabel labelPrice = new JLabel("ʳ�ļ۸�");
	private JLabel labelCount = new JLabel("ʳ��������");
	private JLabel labelSpecification = new JLabel("ʳ�Ĺ��");
	private JLabel labelImage = new JLabel("ʳ��ͼƬ��");
	private Button button = new Button("��ѡ��Ҫ�ϴ���ͼƬ...");

	JTextField edtName = new JTextField(20);
	JTextArea edtDescription = new JTextArea();
	JTextField edtPrice = new JTextField(20);
	JTextField edtCount = new JTextField(20);
	JTextField edtSpecification = new JTextField(20);

	private Map<String, BeanFoodType> foodTypeMap_name = null;
	private JComboBox cmbFoodtype = null;

//	private JComboBox cmbUsertype= new JComboBox(new String[] { "����Ա", "����Ա"});
	public FrmFoodManager_ModifyFood(JDialog f, String s, boolean b, Map<String, BeanFoodType> ftMap) {
		super(f, s, b);
		this.foodTypeMap_name = ftMap;
		String[] strTypes = new String[this.foodTypeMap_name.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanFoodType> itFt = this.foodTypeMap_name.values().iterator();
		int i = 1;
		while (itFt.hasNext()) {
			strTypes[i] = itFt.next().getFoodTypeName();
			i++;
		}
		cmbFoodtype = new JComboBox(strTypes);

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelName.setBounds(25, 5, 70, 20);
		edtName.setBounds(95, 5, 250, 20);
		edtName.setBorder(new LineBorder(null, 1));
		labelFoodType.setBounds(25, 30, 70, 20);
		cmbFoodtype.setBounds(95, 30, 250, 20);
		labelPrice.setBounds(25, 55, 70, 20);
		edtPrice.setBounds(95, 55, 250, 20);
		edtPrice.setBorder(new LineBorder(null, 1));
		labelCount.setBounds(25, 80, 70, 20);
		edtCount.setBounds(95, 80, 250, 20);
		edtCount.setBorder(new LineBorder(null, 1));
		labelSpecification.setBounds(25, 105, 70, 20);
		edtSpecification.setBounds(95, 105, 250, 20);
		edtSpecification.setBorder(new LineBorder(null, 1));
		labelDescription.setBounds(25, 130, 70, 20);
		edtDescription.setBounds(95, 130, 250, 150);
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		labelImage.setBounds(25, 285, 70, 20);
		button.setBounds(95, 285, 250, 20);

		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelImage);
		workPane.add(button);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		workPane.add(labelCount);
		workPane.add(edtCount);
		workPane.add(labelSpecification);
		workPane.add(edtSpecification);
		workPane.add(labelDescription);
		workPane.add(edtDescription);
		workPane.add(labelImage);
		workPane.add(button);
		workPane.add(cmbFoodtype);
		workPane.add(labelFoodType);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(400, 400);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = 0;
		if (e.getSource() == this.button) {
			JFileChooser jfc = new JFileChooser();
			GifFileFilter gifFilter = new GifFileFilter(); // gif������
			JpgFileFilter jpgFilter = new JpgFileFilter(); // jpg������
			jfc.addChoosableFileFilter(jpgFilter); // ����jpg�ļ�������
			jfc.addChoosableFileFilter(gifFilter); // ����gif�ļ�������
			jfc.setApproveButtonText("ȷ��"); // ���塰ȷ������ť��
			jfc.setDialogTitle("���ļ�"); // �����ļ�ѡ������
			result = jfc.showOpenDialog(this); // ��ʾ�򿪶Ի���
			if (result == JFileChooser.APPROVE_OPTION) {
				filepath = jfc.getSelectedFile().getPath();
			}
			button.setLabel(filepath);
		}

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			if (this.cmbFoodtype.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String name = this.edtName.getText();
			String description = this.edtDescription.getText();
			String price = this.edtPrice.getText();
			String count = this.edtCount.getText();
			String specifications = this.edtSpecification.getText();
			String ftName = this.cmbFoodtype.getSelectedItem().toString();
			BeanFoodType rt = this.foodTypeMap_name.get(ftName);
			if (name == null || "".equals(name)) {
				JOptionPane.showMessageDialog(null, "����дʳ������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (rt == null) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (price == null || "".equals(price)) {
				JOptionPane.showMessageDialog(null, "����дʳ�ļ۸�", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (count == null || "".equals(count)) {
				JOptionPane.showMessageDialog(null, "����дʳ������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (specifications == null || "".equals(specifications)) {
				JOptionPane.showMessageDialog(null, "����дʳ�Ĺ��", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (description == null || "".equals(description)) {
				JOptionPane.showMessageDialog(null, "����дʳ������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.foodManager.modify(food, rt, name, Double.parseDouble(price),
						Double.parseDouble(count), description, specifications, filepath);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
