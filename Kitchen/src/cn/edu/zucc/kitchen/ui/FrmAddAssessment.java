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

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;

public class FrmAddAssessment extends JDialog implements ActionListener {

	public BeanMenu menu = null;
	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelDescription = new JLabel("���ۣ�");
	private JLabel labelCollect = new JLabel("�ղأ�");
	private JLabel labelScore = new JLabel("���֣�");

	private JRadioButton collect = new JRadioButton();
	private JRadioButton s1 = new JRadioButton("���ѳ�");
	private JRadioButton s2 = new JRadioButton("��̫�ó�");
	private JRadioButton s3 = new JRadioButton("һ��");
	private JRadioButton s4 = new JRadioButton("ζ������");
	private JRadioButton s5 = new JRadioButton("��ֱ��ζ����");

	private ButtonGroup bg = new ButtonGroup();

	private JTextArea edtDescription = new JTextArea();

	public FrmAddAssessment(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);

		workPane.setLayout(null);
		labelDescription.setBounds(10, 5, 70, 20);// new
		edtDescription.setBounds(80, 5, 200, 150);
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		labelCollect.setBounds(10, 170, 70, 20);
		collect.setBounds(80, 170, 200, 20);
		labelScore.setBounds(10, 195, 70, 20);
		s5.setBounds(80, 195, 200, 20);
		s4.setBounds(80, 220, 200, 20);
		s3.setBounds(80, 245, 200, 20);
		s2.setBounds(80, 270, 200, 20);
		s1.setBounds(80, 295, 200, 20);
//		fc.setBounds(80, 190, 70, 50);
		workPane.add(labelCollect);
		workPane.add(labelScore);
		workPane.add(labelDescription);
		workPane.add(edtDescription);
		workPane.add(collect);
		workPane.add(s5);
		workPane.add(s4);
		workPane.add(s3);
		workPane.add(s2);
		workPane.add(s1);
		bg.add(s1);
		bg.add(s2);
		bg.add(s3);
		bg.add(s4);
		bg.add(s5);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 400);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			String assessment = this.edtDescription.getText();
			boolean isCollected = false;
			if (this.collect.isSelected()) {
				isCollected = true;
			}
			double menuScore = 0;
			if (this.s5.isSelected()) {
				menuScore = 10;
			} else if (this.s4.isSelected()) {
				menuScore = 8;
			} else if (this.s3.isSelected()) {
				menuScore = 6;
			} else if (this.s2.isSelected()) {
				menuScore = 4;
			} else if (this.s1.isSelected()) {
				menuScore = 2;
			}
			if(menuScore==0) {
				JOptionPane.showMessageDialog(null, "��ѡ������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.assessmentManager.add(BeanUser.currentLoginUser, this.menu, assessment,
						isCollected, menuScore);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
