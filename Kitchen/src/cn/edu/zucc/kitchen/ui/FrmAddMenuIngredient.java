package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmAddMenuIngredient extends JDialog implements ActionListener {
	public BeanMenu menu = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelType = new JLabel("食材类别：");
	private JLabel labelFood = new JLabel("用料食材：");
	private JLabel labelCount = new JLabel("用料数量：");
	private JLabel labelUnit = new JLabel("用料单位：");

	private JTextField edtCount = new JTextField(20);
	private JTextField edtUnit = new JTextField(20);

	private Map<String, BeanFoodType> foodTypeMap_name = null;
	private Map<BeanFoodType, List<BeanFood>> foodMap_name = new LinkedHashMap<BeanFoodType, List<BeanFood>>();
	private JComboBox cmbFoodtype = null;
	private JComboBox cmbFood = null;

//	private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
	public FrmAddMenuIngredient(Frame f, String s, boolean b, Map<String, BeanFoodType> ftMap) {
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
//		try {
//			for (int j = 0; j < this.foodTypeMap_name.size(); j++) {
//				this.foodMap_name.put(this.foodTypeMap_name.get(this.cmbFoodtype.getSelectedItem().toString()),
//						KitchenAssistantUtil.foodManager
//								.load(this.foodTypeMap_name.get(this.cmbFoodtype.getSelectedItem().toString())));
//			}
//		} catch (BaseException e1) {
//			e1.printStackTrace();
//		}

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelType.setBounds(25, 5, 70, 20);
		cmbFoodtype.setBounds(95, 5, 150, 20);
		labelFood.setBounds(25, 30, 70, 20);
		cmbFood.setBounds(95, 30, 150, 20);
		labelCount.setBounds(25, 55, 150, 20);
		edtCount.setBounds(95, 55, 150, 20);
		labelUnit.setBounds(25, 80, 150, 20);
		edtUnit.setBounds(95, 80, 150, 20);
		edtUnit.setBorder(new LineBorder(null, 1));
		edtCount.setBorder(new LineBorder(null, 1));

		workPane.add(labelType);
		workPane.add(cmbFoodtype);
		workPane.add(labelFood);
		workPane.add(cmbFood);
		workPane.add(labelCount);
		workPane.add(edtCount);
		workPane.add(labelUnit);
		workPane.add(edtUnit);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(285, 190);
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
		String unit = null;
		String count = null;
		String ftName = null;
		BeanFoodType rt = null;
		List<BeanFood> bfList = null;
		String[] bfString = null;
		BeanFood food = null;

//		if (e.getSource() == this.cmbFoodtype) {
//		if (this.cmbFoodtype.getSelectedIndex() < 0) {
//			JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		unit = this.edtUnit.getText();
//		count = this.edtCount.getText();
//		ftName = this.cmbFoodtype.getSelectedItem().toString();
//		rt = this.foodTypeMap_name.get(ftName);
//		if (rt == null) {
//			JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//
//		bfList = this.foodMap_name.get(rt);
//		bfString = new String[bfList.size() + 1];
//		bfString[0] = "";
//		for (int k = 1; k <= bfList.size(); k++) {
//			bfString[k] = bfList.get(k - 1).getFoodName();
//		}
//			this.cmbFood = new JComboBox(bfString);

//		ComboBoxModel aModel1 = new DefaultComboBoxModel(bfString);
//		this.cmbFoodtype.addItemListener(new ItemListener() {
//
//			@Override
//			public void itemStateChanged(ItemEvent e) {
//				if (e.getStateChange() == ItemEvent.SELECTED) {
//					cmbFood.setModel(aModel1);
//				}
//			}
//		});
//
//			if (this.cmbFood.getSelectedIndex() < 0) {
//				JOptionPane.showMessageDialog(null, "请选择用料食材", "错误", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			String bf = this.cmbFood.getSelectedItem().toString();
//			if (bf == null) {
//				JOptionPane.showMessageDialog(null, "请选择用料食材", "错误", JOptionPane.ERROR_MESSAGE);
//				return;
//			}
//			food = bfList.get(this.cmbFood.getSelectedIndex());
//		}

		if (e.getSource() == this.btnCancel)

		{
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			if (this.cmbFoodtype.getSelectedIndex() < 0) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			unit = this.edtUnit.getText();
			count = this.edtCount.getText();
			ftName = this.cmbFoodtype.getSelectedItem().toString();
			rt = this.foodTypeMap_name.get(ftName);
			if (rt == null) {
				JOptionPane.showMessageDialog(null, "请选择食材类别", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}

			bfList = this.foodMap_name.get(rt);
//			String[] bfString = new String[bfList.size() + 1];
//			bfString[0] = "";
//			for (int k = 1; k <= bfList.size(); k++) {
//				bfString[k] = bfList.get(k - 1).getFoodName();
//			}
//			this.cmbFood = new JComboBox(bfString);
			try {
				if (this.cmbFood.getSelectedIndex() < 0) {
					JOptionPane.showMessageDialog(null, "请选择用料食材", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String bf = this.cmbFood.getSelectedItem().toString();
				if ("".equals(bf)) {
					JOptionPane.showMessageDialog(null, "请选择用料食材", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
//			bfList=this.foodMap_name.get(key)
				food = bfList.get(this.cmbFood.getSelectedIndex() - 1);
				if ("".equals(count)) {
					JOptionPane.showMessageDialog(null, "请输入用料数量", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ("".equals(unit)) {
					JOptionPane.showMessageDialog(null, "请输入用料单位", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if(Double.parseDouble(count)<0) {
					JOptionPane.showMessageDialog(null, "请输入用料数量必须大于0", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}

				KitchenAssistantUtil.menuIngredient.add(menu, food, Double.parseDouble(count), unit);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "食材数量或价格不能包含除数字和小数点外的其他字符!", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}
}
