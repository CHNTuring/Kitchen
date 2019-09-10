package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
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

import com.eltima.components.ui.DatePicker;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmMyOrder_AddNewOrder extends JDialog implements ActionListener {
	public BeanMenu menu = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelType = new JLabel("食材类别：");
	private JLabel labelFood = new JLabel("订单食材：");
	private JLabel labelCount = new JLabel("购买数量：");
//	private JLabel labelPrice = new JLabel("食材单价：");
//	private JLabel labelSumPrice =new JLabel("订单总价：");
	private JLabel labelDeadline = new JLabel("要求送达时间：");
	private JLabel labelAddr = new JLabel("收货地址：");
	private JLabel labelPhone = new JLabel("联系电话：");

	private JTextField edtCount = new JTextField(20);
//	private JTextField edtPrice = new JTextField(20);
//	private JTextField edtDeadline = new JTextField(20);
	private JTextField edtAddr = new JTextField(20);
	private JTextField edtPhone = new JTextField(20);

	private Map<String, BeanFoodType> foodTypeMap_name = null;
	private Map<BeanFoodType, List<BeanFood>> foodMap_name = new LinkedHashMap<BeanFoodType, List<BeanFood>>();
	private JComboBox cmbFoodtype = null;
	private JComboBox cmbFood = null;

	private final DatePicker datepick;

	private static DatePicker getDatePicker() {
		final DatePicker datepick;
		// 格式
		String DefaultFormat = "yyyy-MM-dd HH:mm:ss";
		// 当前时间
		Date date = new Date();
		// 字体
		Font font = new Font("Times New Roman", Font.BOLD, 14);

		Dimension dimension = new Dimension(177, 24);

		int[] hilightDays = { 1, 3, 5, 7 };

		int[] disabledDays = { 4, 6, 5, 9 };
		// 构造方法（初始时间，时间显示格式，字体，控件大小）
		datepick = new DatePicker(date, DefaultFormat, font, dimension);

		datepick.setLocation(137, 83);// 设置起始位置
		/*
		 * //也可用setBounds()直接设置大小与位置 datepick.setBounds(137, 83, 177, 24);
		 */
		// 设置一个月份中需要高亮显示的日子
		datepick.setHightlightdays(hilightDays, Color.red);
		// 设置一个月份中不需要的日子，呈灰色显示
		datepick.setDisableddays(disabledDays);
		// 设置国家
		datepick.setLocale(Locale.CHINA);
		// 设置时钟面板可见
		datepick.setTimePanleVisible(true);
		return datepick;
	}

	public FrmMyOrder_AddNewOrder(JDialog f, String s, boolean b, Map<String, BeanFoodType> ftMap) {
		super(f, s, b);
		this.foodTypeMap_name = ftMap;
		String[] strTypes = new String[this.foodTypeMap_name.size() + 1];
		strTypes[0] = "";
		java.util.Iterator<BeanFoodType> itFt = this.foodTypeMap_name.values().iterator();
		int i = 1;
		while (itFt.hasNext()) {
			BeanFoodType ft = itFt.next();
			try {
				this.foodMap_name.put(ft, KitchenAssistantUtil.foodManager.load(ft));
			} catch (BaseException e) {
				e.printStackTrace();
			}
			strTypes[i] = ft.getFoodTypeName();
			i++;
		}
		cmbFoodtype = new JComboBox(strTypes);
		cmbFood = new JComboBox();

		datepick = getDatePicker();

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelType.setBounds(25, 5, 70, 20);
		cmbFoodtype.setBounds(110, 5, 220, 20);
		labelFood.setBounds(25, 30, 70, 20);
		cmbFood.setBounds(110, 30, 220, 20);
		labelCount.setBounds(25, 55, 220, 20);
		edtCount.setBounds(110, 55, 220, 20);
//		labelPrice.setBounds(25, 80, 220, 20);
//		edtPrice.setBounds(110, 80, 220, 20);
		labelDeadline.setBounds(25, 80, 220, 20);
		datepick.setBounds(110, 80, 220, 20);
		labelAddr.setBounds(25, 105, 220, 20);
		edtAddr.setBounds(110, 105, 220, 20);
		labelPhone.setBounds(25, 130, 220, 20);
		edtPhone.setBounds(110, 130, 220, 20);
		edtPhone.setBorder(new LineBorder(null, 1));
//		edtPrice.setBorder(new LineBorder(null, 1));
		edtAddr.setBorder(new LineBorder(null, 1));
//		edtPrice.setBorder(new LineBorder(null, 1));
		edtCount.setBorder(new LineBorder(null, 1));

		workPane.add(labelType);
		workPane.add(cmbFoodtype);
		workPane.add(labelFood);
		workPane.add(cmbFood);
		workPane.add(labelCount);
		workPane.add(edtCount);
//		workPane.add(labelPrice);
		workPane.add(labelPhone);
		workPane.add(edtPhone);
		workPane.add(datepick);
		workPane.add(edtAddr);
//		workPane.add(edtPrice);
		workPane.add(labelDeadline);
		workPane.add(labelAddr);

//		edtPrice.setEditable(false);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(380, 250);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);

		String ftName = this.cmbFoodtype.getSelectedItem().toString();
		BeanFoodType rt = this.foodTypeMap_name.get(ftName);

		this.cmbFoodtype.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if (e.getStateChange() == ItemEvent.SELECTED) {
					String ftName = cmbFoodtype.getSelectedItem().toString();
					cmbFood.setModel(selectCMB(ftName));
				}
			}
		});
	}

	public ComboBoxModel selectCMB(String ftName) {

		BeanFoodType rt = this.foodTypeMap_name.get(ftName);
		List<BeanFood> bfList = this.foodMap_name.get(rt);
		String[] bfString = new String[bfList.size() + 1];
		bfString[0] = "";
		for (int k = 1; k <= bfList.size(); k++) {
			bfString[k] = bfList.get(k - 1).getFoodName();
		}
//			this.cmbFood = new JComboBox(bfString);

		ComboBoxModel aModel1 = new DefaultComboBoxModel(bfString);
		return aModel1;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {

			String count = null;
			String ftName = null;
			BeanFoodType rt = null;
			double price = 0;
			java.sql.Timestamp deadline = null;
			String addr = null;
			String phone = null;
			List<BeanFood> bfList = null;
			String[] bfString = null;
			BeanFood food = null;

			if (this.cmbFoodtype.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			ftName = this.cmbFoodtype.getSelectedItem().toString();
			rt = this.foodTypeMap_name.get(ftName);
			if (rt == null) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

			bfList = this.foodMap_name.get(rt);

			if (this.cmbFood.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "请选择购买食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			String bf = this.cmbFood.getSelectedItem().toString();
			if ("".equals(bf)) {
				JOptionPane.showMessageDialog(null, "请选择购买食材", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
//			bfList=this.foodMap_name.get(key)
			food = bfList.get(this.cmbFood.getSelectedIndex()-1);
			count = edtCount.getText();
//			edtPrice.setText(food.getFoodPrice() + "");
			price = food.getFoodPrice();
			addr = edtAddr.getText();
			phone = edtPhone.getText();
			if ("".equals(count) || count == null) {
				JOptionPane.showMessageDialog(null, "请输入购买数量", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			deadline = new Timestamp(((java.util.Date) datepick.getValue()).getTime());
			if(deadline.getTime()<System.currentTimeMillis()) {
				JOptionPane.showMessageDialog(null, "要求送达时间不能为过去时间!", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(addr==null||"".equals(addr)) {
				JOptionPane.showMessageDialog(null, "请填写收货地址", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (phone == null || "".equals(phone)) {
				JOptionPane.showMessageDialog(null, "请填写正确的联系电话!", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (phone.length() != 11&&phone.length() != 12) {
				JOptionPane.showMessageDialog(null, "请填写11位联系电话或12位座机电话!", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				Long.parseLong(phone);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "手机号码不能包含除数字外的其他字符!", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				if (Integer.parseInt(count) <= 0) {
					JOptionPane.showMessageDialog(null, "购买数量必须大于0", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
			} catch (NumberFormatException e1) {
				JOptionPane.showMessageDialog(null, "购买数量必须为整数", "错误", JOptionPane.ERROR_MESSAGE);
			}
			try {
				KitchenAssistantUtil.foodOrderManager.addFoodOrder(BeanUser.currentLoginUser, food,
						Double.parseDouble(count), deadline, addr, phone);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
