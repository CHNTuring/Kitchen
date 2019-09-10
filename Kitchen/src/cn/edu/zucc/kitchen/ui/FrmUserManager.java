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
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmUserManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
//	private Button btnAdd = new Button("����û�");
	private Button btnResetPwd = new Button("��������");
	private Button btnDelete = new Button("ע���û�");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("�û���ѯ");
	private Button allUser = new Button("�����û�");
	private Object tblTitle[] = { "�˺�", "����", "���" };
	private Object tblData[][];
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable userTable = new JTable(tablmod);

	private void reloadUserTable() {
		List<BeanUser> users;
		try {
			users = KitchenAssistantUtil.userManager.loadAll();
			tblData = new Object[users.size()][BeanUser.userTitle.length];
			for (int i = 0; i < users.size(); i++) {
				tblData[i][0] = users.get(i).getUserName();
				tblData[i][1] = users.get(i).getUserSex();
				tblData[i][2] = users.get(i).getUserPhone();
				tblData[i][3] = users.get(i).getUserEmail();
				tblData[i][4] = users.get(i).getUserCity();
				tblData[i][5] = users.get(i).getUserRegisterTime();
			}
			tablmod.setDataVector(tblData, BeanUser.userTitle);
			this.userTable.validate();
			this.userTable.repaint();
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

	public FrmUserManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(allUser);
		toolBar.add(btnResetPwd);
		toolBar.add(this.btnDelete);
		toolBar.add(edtKeyword);
		toolBar.add(btnSearch);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadUserTable();
		this.getContentPane().add(new JScrollPane(this.userTable), BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

//		this.btnAdd.addActionListener(this);
		this.btnResetPwd.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.allUser.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnResetPwd) {
			int i = this.userTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ���û�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "ȷ������������", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String userid = this.tblData[i][0].toString();
				try {
					KitchenAssistantUtil.userManager.resetPwd(userid);
					JOptionPane.showMessageDialog(null, "�����������", "��ʾ", JOptionPane.INFORMATION_MESSAGE);
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		} else if (e.getSource() == this.btnDelete) {
			int i = this.userTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ���û�", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "ȷ������ע�����û���", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String userid = this.tblData[i][0].toString();
				try {
					KitchenAssistantUtil.userManager.delete(userid);
					this.reloadUserTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		} else if (e.getSource() == this.btnSearch) {
			String name = this.edtKeyword.getText();
			List<BeanUser> users;
			try {
				users = KitchenAssistantUtil.userManager.search(name);
				tblData = new Object[users.size()][BeanUser.userTitle.length];
				for (int i = 0; i < users.size(); i++) {
					tblData[i][0] = users.get(i).getUserName();
					tblData[i][1] = users.get(i).getUserSex();
					tblData[i][2] = users.get(i).getUserPhone();
					tblData[i][3] = users.get(i).getUserEmail();
					tblData[i][4] = users.get(i).getUserCity();
					tblData[i][5] = users.get(i).getUserRegisterTime();
				}
				tablmod.setDataVector(tblData, BeanUser.userTitle);
				this.userTable.validate();
				this.userTable.repaint();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else {
			this.reloadUserTable();
		}
	}
}
