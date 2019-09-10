package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.comtrol.example.ExampleAdminUserManager;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmCreateAdminUser extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");

	private JLabel labelName = new JLabel("新管理员用户名：");
	private JTextField edtName = new JTextField(20);

	public FrmCreateAdminUser(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtName);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 150);
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnCancel)
			this.setVisible(false);
		else if (e.getSource() == this.btnOk) {
			try {
				String name=null;
				name=edtName.getText();
				if("".equals(name)||name==null) {
					JOptionPane.showMessageDialog(null, "请输入新增管理员的用户名", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				(new ExampleAdminUserManager()).reg(name);
				JOptionPane.showMessageDialog(null, "新增成功！初始密码为123456！请及时登录修改","提示",JOptionPane.INFORMATION_MESSAGE); 
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

}
