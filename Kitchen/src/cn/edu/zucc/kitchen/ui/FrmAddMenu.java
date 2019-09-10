package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javax.swing.JButton;
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
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;

class JpgFileFilter extends FileFilter {
	public String getDescription() {
		return "*.jpg";
	}

	public boolean accept(File file) {
		String name = file.getName();
		return name.toLowerCase().endsWith(".jpg");
	}
}

class GifFileFilter extends FileFilter {
	public String getDescription() {
		return "*.gif";
	}

	public boolean accept(File file) {
		String name = file.getName();
		return name.toLowerCase().endsWith(".gif");
	}
}

public class FrmAddMenu extends JDialog implements ActionListener {

	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelName = new JLabel("�������ƣ�");
	private JLabel labelDescription = new JLabel("����������");
	private JLabel labelImage = new JLabel("����ͼƬ��");
	private Button button = new Button("��ѡ��Ҫ�ϴ���ͼƬ...");

	private JTextField edtName = new JTextField(20);
	private JTextArea edtDescription = new JTextArea();

	public FrmAddMenu(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelName.setBounds(10, 5, 70, 20);// new
		edtName.setBounds(80, 5, 200, 20);// new
		edtName.setBorder(new LineBorder(null, 1));
		labelDescription.setBounds(10, 30, 70, 20);
		edtDescription.setBounds(80, 30, 200, 150);
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		labelImage.setBounds(10, 190, 70, 20);
		button.setBounds(80, 190, 200, 20);
//		fc.setBounds(80, 190, 70, 50);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelDescription);
		workPane.add(edtDescription);
		workPane.add(labelImage);
		workPane.add(button);
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
			String name = this.edtName.getText();
			String description = this.edtDescription.getText();
			if("".equals(name)||(name)==null||"".equals(description)||(description)==null) {
				JOptionPane.showMessageDialog(null, "����д�����Ĳ�����Ϣ\n����ͼƬ������֮���ϴ�","����",JOptionPane.ERROR_MESSAGE); 
				return;
			}
			if("".equals(filepath)||(filepath)==null) {
				JOptionPane.showMessageDialog(null, "����û���ϴ�����ͼƬ\n��֮�������_�޸�_���ϴ�ͼƬ","��ʾ",JOptionPane.INFORMATION_MESSAGE); 
			}
			try {
				KitchenAssistantUtil.menuManager.add(BeanUser.currentLoginUser, name, description, filepath);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
