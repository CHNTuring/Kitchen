package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmLogin extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("��½");
	private JButton btnCancel = new JButton("�˳�");
	private JButton btnRegister = new JButton("ע��");
	private JLabel labelUser = new JLabel("�û���");
	private JLabel labelPwd = new JLabel("���룺");
	private JLabel labelUserType = new JLabel("��֤��");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);

	private JRadioButton ordinary = new JRadioButton("��ͨ�û�");
	private JRadioButton admin = new JRadioButton("����Ա");
	private ButtonGroup bg = new ButtonGroup();

	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		bg.add(admin);
		bg.add(ordinary);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);

		workPane.setLayout(null);// new
		labelUser.setBounds(30, 5, 50, 20);// new
		edtUserId.setBounds(80, 5, 200, 20);
		labelPwd.setBounds(30, 30, 50, 20);
		edtPwd.setBounds(80, 30, 200, 20);
		labelUserType.setBounds(30, 55, 50, 20);
		ordinary.setBounds(80, 55, 80, 20);
		admin.setBounds(180, 55, 80, 20);

		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelUserType);
		workPane.add(ordinary);
		workPane.add(admin);
		ordinary.setSelected(true);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 160);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin) {
			String userid = this.edtUserId.getText();
			String pwd = new String(this.edtPwd.getPassword());

			try {
				if (ordinary.isSelected()) {
					BeanUser.currentLoginUser = KitchenAssistantUtil.userManager.login(userid, pwd);
				}
				if (admin.isSelected()) {
					if ("123456".equals(pwd)) {
						JOptionPane.showMessageDialog(null, "���޸ĳ�ʼ����", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
						FrmModifyPwd flg = new FrmModifyPwd(this, "�����޸�", true);
						flg.userName = userid;
						flg.setVisible(true);
						return;
						
					}else {
						BeanAdminUser.currentLoginUser = KitchenAssistantUtil.adminUserManager.login(userid, pwd);
					}
				}
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);

		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if (e.getSource() == this.btnRegister) {
			FrmRegister dlg = new FrmRegister(this, "ע��", true);
			dlg.setVisible(true);
		}
	}

}
