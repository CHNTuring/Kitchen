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
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("食材名称：");
	private JLabel labelDescription = new JLabel("食材描述：");
	private JLabel labelFoodType = new JLabel("食材种类：");
	private JLabel labelPrice = new JLabel("食材价格：");
	private JLabel labelCount = new JLabel("食材数量：");
	private JLabel labelSpecification = new JLabel("食材规格：");
	private JLabel labelImage = new JLabel("食材图片：");
	private Button button = new Button("请选择要上传的图片...");

	JTextField edtName = new JTextField(20);
	JTextArea edtDescription = new JTextArea();
	JTextField edtPrice = new JTextField(20);
	JTextField edtCount = new JTextField(20);
	JTextField edtSpecification = new JTextField(20);

	private Map<String, BeanFoodType> foodTypeMap_name = null;
	private JComboBox cmbFoodtype = null;

//	private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
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
		// 屏幕居中显示
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
			GifFileFilter gifFilter = new GifFileFilter(); // gif过滤器
			JpgFileFilter jpgFilter = new JpgFileFilter(); // jpg过滤器
			jfc.addChoosableFileFilter(jpgFilter); // 加载jpg文件过滤器
			jfc.addChoosableFileFilter(gifFilter); // 加载gif文件过滤器
			jfc.setApproveButtonText("确定"); // 定义“确定“按钮”
			jfc.setDialogTitle("打开文件"); // 定义文件选择框标题
			result = jfc.showOpenDialog(this); // 显示打开对话框
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
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
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
				JOptionPane.showMessageDialog(null, "请填写食材名称", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (rt == null) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (price == null || "".equals(price)) {
				JOptionPane.showMessageDialog(null, "请填写食材价格", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (count == null || "".equals(count)) {
				JOptionPane.showMessageDialog(null, "请填写食材数量", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (specifications == null || "".equals(specifications)) {
				JOptionPane.showMessageDialog(null, "请填写食材规格", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (description == null || "".equals(description)) {
				JOptionPane.showMessageDialog(null, "请填写食材描述", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.foodManager.modify(food, rt, name, Double.parseDouble(price),
						Double.parseDouble(count), description, specifications, filepath);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
