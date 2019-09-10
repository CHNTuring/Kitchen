package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingConstants;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmRegister extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ע��");
	private Button btnCancel = new Button("ȡ��");

	private JLabel labelUser = new JLabel("�û���", SwingConstants.LEFT);
	private JLabel labelPwd = new JLabel("���룺", SwingConstants.LEFT);
	private JLabel labelPwd2 = new JLabel("���룺", SwingConstants.LEFT);
	private JLabel labelSex = new JLabel("�Ա�", SwingConstants.LEFT); // �����Ա��ǩ
	private JLabel labelPhone = new JLabel("�绰��", SwingConstants.LEFT);
	private JLabel labelEmail = new JLabel("�������䣺", SwingConstants.LEFT);
	private JLabel labelCity = new JLabel("���ڳ��У�", SwingConstants.LEFT);

	private JRadioButton male = new JRadioButton("��"); // ����������ѡ��
	private JRadioButton female = new JRadioButton("Ů");
	private ButtonGroup bg = new ButtonGroup();
	private JTextField edtUserName = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	private JTextField edtPhone = new JTextField(20);
	private JTextField edtEmail = new JTextField(20);
	private JTextField edtCity = new JTextField(20);

	public FrmRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		bg.add(male);
		bg.add(female);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);// new
		labelUser.setBounds(10, 5, 50, 20);// new
		edtUserName.setBounds(80, 5, 200, 20);
		labelPwd.setBounds(10, 30, 50, 20);
		edtPwd.setBounds(80, 30, 200, 20);
		labelPwd2.setBounds(10, 55, 50, 20);
		edtPwd2.setBounds(80, 55, 200, 20);
		labelSex.setBounds(10, 80, 50, 20);
		male.setBounds(80, 80, 50, 20);
		female.setBounds(130, 80, 50, 20);
		labelPhone.setBounds(10, 105, 50, 20);
		edtPhone.setBounds(80, 105, 200, 20);
		labelEmail.setBounds(10, 130, 70, 20);
		edtEmail.setBounds(80, 130, 200, 20);
		labelCity.setBounds(10, 155, 70, 20);
		edtCity.setBounds(80, 155, 200, 20);
		workPane.add(labelUser);
		workPane.add(edtUserName);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		workPane.add(labelSex);
		workPane.add(male);
		workPane.add(female);
		workPane.add(labelPhone);
		workPane.add(edtPhone);
		workPane.add(labelEmail);
		workPane.add(edtEmail);
		workPane.add(labelCity);
		workPane.add(edtCity);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 280);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	public FrmRegister(JFrame f, String s, boolean b) {
		this.btnOk.setLabel("�޸�");
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		bg.add(male);
		bg.add(female);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);// new
		labelUser.setBounds(10, 5, 50, 20);// new
		edtUserName.setBounds(80, 5, 200, 20);
		labelSex.setBounds(10, 30, 50, 20);
		male.setBounds(80, 30, 50, 20);
		female.setBounds(130, 30, 50, 20);
		labelPhone.setBounds(10, 55, 50, 20);
		edtPhone.setBounds(80, 55, 200, 20);
		labelEmail.setBounds(10, 80, 70, 20);
		edtEmail.setBounds(80, 80, 200, 20);
		labelCity.setBounds(10, 105, 70, 20);
		edtCity.setBounds(80, 105, 200, 20);
		workPane.add(labelUser);
		workPane.add(edtUserName);
		workPane.add(labelSex);
		workPane.add(male);
		workPane.add(female);
		workPane.add(labelPhone);
		workPane.add(edtPhone);
		workPane.add(labelEmail);
		workPane.add(edtEmail);
		workPane.add(labelCity);
		workPane.add(edtCity);
		edtUserName.setText(BeanUser.currentLoginUser.getUserName());
		if (BeanUser.currentLoginUser.getUserSex().equals("��")) {
			male.setSelected(true);
		} else {
			female.setSelected(true);
		}
		edtPhone.setText(BeanUser.currentLoginUser.getUserPhone());
		edtEmail.setText(BeanUser.currentLoginUser.getUserEmail());
		edtCity.setText(BeanUser.currentLoginUser.getUserCity());
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 280);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk) {
			if (BeanUser.currentLoginUser == null) {
				String userName = this.edtUserName.getText();
				String pwd1 = new String(this.edtPwd.getPassword());
				String pwd2 = new String(this.edtPwd2.getPassword());
				String phone = new String(this.edtPhone.getText());
				String city = new String(this.edtCity.getText());
				String email = new String(this.edtEmail.getText());
				String sex = null;
				if (userName == null || "".equals(userName)) {
					JOptionPane.showMessageDialog(null, "����д�����û���!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (pwd1 == null || "".equals(pwd1) || pwd2 == null || "".equals(pwd2)) {
					JOptionPane.showMessageDialog(null, "���벻��Ϊ��!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (!pwd1.equals(pwd2)) {
					JOptionPane.showMessageDialog(null, "���벻ƥ������������!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (male.isSelected()) {
					sex = male.getText();
				}
				if (female.isSelected()) {
					sex = female.getText();
				}
				if (sex == null || "".equals(sex)) {
					JOptionPane.showMessageDialog(null, "��ѡ���Ա�!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (phone == null || "".equals(phone)) {
					JOptionPane.showMessageDialog(null, "����д��ȷ����ϵ�绰!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (phone.length() != 11 && phone.length() != 12) {
					JOptionPane.showMessageDialog(null, "����д11λ�ֻ������12λ�����绰!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if (Long.parseLong(phone) < 0) {
						JOptionPane.showMessageDialog(null, "��ϵ���벻�ܰ�����������������ַ�!", "����", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "��ϵ���벻�ܰ�����������������ַ�!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean status = email.contains("@");  
		        if(!status){  
		        	JOptionPane.showMessageDialog(null, "����д��ȷ�ĵ�������!", "����", JOptionPane.ERROR_MESSAGE);
					return;
		        }
				if (email == null || "".equals(email)) {
					JOptionPane.showMessageDialog(null, "����д��ȷ�ĵ�������!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (city == null || "".equals(city)) {
					JOptionPane.showMessageDialog(null, "����д�����ڵĳ���!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					BeanUser user = KitchenAssistantUtil.userManager.reg(userName, pwd1, pwd2, sex, phone, email, city);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} else {
				String userName = this.edtUserName.getText();
				String phone = new String(this.edtPhone.getText());
				String city = new String(this.edtCity.getText());
				String email = new String(this.edtEmail.getText());
				String sex = null;
				if (userName == null || "".equals(userName)) {
					JOptionPane.showMessageDialog(null, "����д�����û���!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (male.isSelected()) {
					sex = male.getText();
				}
				if (female.isSelected()) {
					sex = female.getText();
				}
				if (sex == null || "".equals(sex)) {
					JOptionPane.showMessageDialog(null, "��ѡ���Ա�!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (phone == null || "".equals(phone)) {
					JOptionPane.showMessageDialog(null, "����д��ȷ����ϵ�绰!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (phone.length() != 11 && phone.length() != 12) {
					JOptionPane.showMessageDialog(null, "����д11λ�ֻ������12λ�����绰!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					if (Long.parseLong(phone) < 0) {
						JOptionPane.showMessageDialog(null, "��ϵ���벻�ܰ�����������������ַ�!", "����", JOptionPane.ERROR_MESSAGE);
						return;
					}
				} catch (NumberFormatException ex) {
					JOptionPane.showMessageDialog(null, "��ϵ���벻�ܰ�����������������ַ�!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (email == null || "".equals(email)) {
					JOptionPane.showMessageDialog(null, "����д��ȷ�ĵ�������!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (city == null || "".equals(city)) {
					JOptionPane.showMessageDialog(null, "����д�����ڵĳ���!", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				try {
					KitchenAssistantUtil.userManager.modify(BeanUser.currentLoginUser, userName, sex, phone, email,
							city);
					BeanUser.currentLoginUser.setUserName(userName);
					BeanUser.currentLoginUser.setUserEmail(email);
					BeanUser.currentLoginUser.setUserPhone(phone);
					BeanUser.currentLoginUser.setUserSex(sex);
					BeanUser.currentLoginUser.setUserCity(city);
					this.setVisible(false);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}

		}

	}

}
