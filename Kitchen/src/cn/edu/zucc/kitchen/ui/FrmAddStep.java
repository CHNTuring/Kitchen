package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmAddStep extends JDialog implements ActionListener {

	private String filepath = null;
	public BeanMenu menu = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelStepOrder = new JLabel("������ţ�");
	private JLabel labelStepDescription = new JLabel("����������");
	private JLabel labelImage = new JLabel("����ͼƬ��");
	private Button button = new Button("��ѡ��Ҫ�ϴ���ͼƬ...");

	private JComboBox cmbReadertype = null;

	private JTextArea edtDescription = new JTextArea();

	public FrmAddStep(JFrame f, String s, boolean b) {
		super(f, s, b);

		String[] order = new String[16];
		order[0] = "";
		for (int i = 1; i < 16; i++) {
			order[i] = i + "";
		}
		cmbReadertype = new JComboBox(order);

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelStepOrder.setBounds(10, 5, 70, 20);// new
		cmbReadertype.setBounds(80, 5, 70, 20);
//		edtName.setBounds(80, 5, 200, 20);// new
//		edtName.setBorder(new LineBorder(null, 1));
		labelStepDescription.setBounds(10, 30, 70, 20);
		edtDescription.setBounds(80, 30, 200, 150);
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		labelImage.setBounds(10, 190, 70, 20);
		button.setBounds(80, 190, 200, 20);
		workPane.add(labelImage);
		workPane.add(labelStepDescription);
		workPane.add(labelStepOrder);
		workPane.add(edtDescription);
		workPane.add(button);
		workPane.add(cmbReadertype);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 300);
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
			if("".equals((String) this.cmbReadertype.getSelectedItem())||((String) this.cmbReadertype.getSelectedItem())==null) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int orderId = Integer.parseInt((String) this.cmbReadertype.getSelectedItem());
			String description = this.edtDescription.getText();
			if("".equals(description)||(description)==null) {
				JOptionPane.showMessageDialog(null, "���Ĳ���ȱ����Ӧ�Ĳ�������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if("".equals(filepath)||(filepath)==null) {
				JOptionPane.showMessageDialog(null, "����û���ϴ�����ͼƬ\n��֮������ڲ����޸����ϴ�ͼƬ","��ʾ",JOptionPane.INFORMATION_MESSAGE); 
			}
			try {
				KitchenAssistantUtil.menuStepManager.add(menu, description, orderId, filepath);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		}

	}

}
