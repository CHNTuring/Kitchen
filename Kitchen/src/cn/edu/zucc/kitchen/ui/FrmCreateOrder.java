package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Locale;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import com.eltima.components.ui.DatePicker;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmCreateOrder extends javax.swing.JDialog implements ActionListener {
	private BeanMenu menu = null;
	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelTime = new JLabel("Ҫ���ʹ�ʱ�䣺");
	private JLabel labelAddr = new JLabel("�ͻ���ַ��");
	private JLabel labelPhone = new JLabel("��ϵ�绰��");
	private JLabel labelMenu = new JLabel("�������ƣ�");
	private JTextField menuName = new JTextField(20);
	private JTextField edtAddr = new JTextField(20);
	private JTextField edtPhone = new JTextField(20);

	private final DatePicker datepick;

	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		// ��ʽ
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// ��ǰʱ��
		Date date = new Date();
		// ����
		Font font = new Font("Times New Roman", Font.BOLD, 14);

		Dimension dimension = new Dimension(177, 24);

		int[] hilightDays = { 1, 3, 5, 7 };

		int[] disabledDays = { 4, 6, 5, 9 };
		// ���췽������ʼʱ�䣬ʱ����ʾ��ʽ�����壬�ؼ���С��
		datepick = new DatePicker(date, DefaultFormat, font, dimension);

		datepick.setLocation(137, 83);// ������ʼλ��
		/*
		 * //Ҳ����setBounds()ֱ�����ô�С��λ�� datepick.setBounds(137, 83, 177, 24);
		 */
		// ����һ���·�����Ҫ������ʾ������
		datepick.setHightlightdays(hilightDays, Color.red);
		// ����һ���·��в���Ҫ�����ӣ��ʻ�ɫ��ʾ
		datepick.setDisableddays(disabledDays);
		// ���ù���
		datepick.setLocale(Locale.CHINA);
		// ����ʱ�����ɼ�
		datepick.setTimePanleVisible(true);
		return datepick;
	}

	public FrmCreateOrder(JFrame f, String s, boolean b, BeanMenu menu) {
		super(f, s, b);
		this.menu = menu;
		datepick = getDatePicker();

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelMenu.setBounds(10, 10, 100, 20);
		menuName.setBounds(110, 10, 200, 20);
		menuName.setText(menu.getMenuName());
		menuName.setBorder(new LineBorder(null, 1));
		menuName.setEditable(false);
		labelTime.setBounds(10, 35, 100, 20);// new
		datepick.setBounds(110, 35, 200, 20);
		labelAddr.setBounds(10, 60, 70, 20);
		edtAddr.setBounds(110, 60, 200, 20);
		edtAddr.setBorder(new LineBorder(null, 1));
		labelPhone.setBounds(10, 85, 70, 20);
		edtPhone.setBounds(110, 85, 200, 20);
		edtPhone.setBorder(new LineBorder(null, 1));
//		fc.setBounds(80, 190, 70, 50);
		workPane.add(labelAddr);
		workPane.add(edtAddr);
		workPane.add(labelMenu);
		workPane.add(menuName);
		workPane.add(labelPhone);
		workPane.add(edtPhone);
		workPane.add(labelTime);
		workPane.add(datepick);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(335, 200);
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
			String addr = this.edtAddr.getText();
			String phone = this.edtPhone.getText();
			if (addr == null || "".equals(addr)) {
				JOptionPane.showMessageDialog(null, "����д��ȷ���ջ���ַ!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			try {
				Long.parseLong(phone);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "�ֻ����벻�ܰ�����������������ַ�!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (phone == null || "".equals(phone)) {
				JOptionPane.showMessageDialog(null, "����д��ȷ����ϵ�绰!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (phone.length() != 11&&phone.length() != 12) {
				JOptionPane.showMessageDialog(null, "����д11λ�ֻ������12λ�����绰!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			java.sql.Timestamp ts = new Timestamp(((java.util.Date) datepick.getValue()).getTime());
			if(ts.getTime()<System.currentTimeMillis()) {
				JOptionPane.showMessageDialog(null, "Ҫ���ʹ�ʱ�䲻��Ϊ��ȥʱ��!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.foodOrderManager.add(BeanUser.currentLoginUser, this.menu, ts, addr, phone);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
