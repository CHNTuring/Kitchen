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
import cn.edu.zucc.kitchen.model.BeanFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmPurchaseManager_Modify extends JDialog implements ActionListener {
	private BeanFoodPurchase purchase = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelType = new JLabel("食材类别：");
	private JLabel labelFood = new JLabel("用料食材：");
	private JLabel labelCount = new JLabel("采购数量：");
	private JLabel labelUnit = new JLabel("订单状态：");

	private JTextField edtCount = new JTextField(20);
	private JTextField type = new JTextField(20);
	private JTextField Count = new JTextField(20);
//	private JTextField edtUnit = new JTextField(20);

	private JComboBox cmbFoodtype = null;
	private JComboBox cmbFood = null;
	private JComboBox cmbStatus = null;

//	private JComboBox cmbUsertype= new JComboBox(new String[] { "管理员", "借阅员"});
	public FrmPurchaseManager_Modify(JDialog f, String s, boolean b,
			BeanFoodPurchase purchase) {
		super(f, s, b);
		this.purchase = purchase;
		cmbStatus = new JComboBox(new String[] { "", "下单", "在途", "入库" });
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
		type.setBounds(95, 5, 150, 20);
		type.setEditable(false);
		labelFood.setBounds(25, 30, 70, 20);
		Count.setBounds(95, 30, 150, 20);
		Count.setEditable(false);
		labelCount.setBounds(25, 55, 150, 20);
		edtCount.setBounds(95, 55, 150, 20);
		labelUnit.setBounds(25, 80, 150, 20);
		cmbStatus.setBounds(95, 80, 150, 20);
//		edtUnit.setBounds(95, 80, 150, 20);
//		edtUnit.setBorder(new LineBorder(null, 1));
		edtCount.setBorder(new LineBorder(null, 1));
		edtCount.setText(purchase.getPurchaseCount()+"");
		
		workPane.add(labelType);
		workPane.add(type);
		workPane.add(labelFood);
		workPane.add(Count);
		workPane.add(labelCount);
		workPane.add(edtCount);
		workPane.add(labelUnit);
		workPane.add(cmbStatus);

		type.setText(purchase.getFood().getFoodType().getFoodTypeName());
		Count.setText(purchase.getFood().getFoodName());
		type.setBorder(new LineBorder(null, 1));
		Count.setBorder(new LineBorder(null, 1));
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(285, 190);
		// 屏幕居中显示
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

			String count = this.edtCount.getText();
			if ("".equals(count)) {
				JOptionPane.showMessageDialog(null, "请输入采购数量", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				if(Integer.parseInt(count)<=0) {
					JOptionPane.showMessageDialog(null, "采购数量必须大于为大于0的整数!", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if ("".equals(this.cmbStatus.getSelectedItem())) {
					JOptionPane.showMessageDialog(null, "请选择订单状态!", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				KitchenAssistantUtil.foodPurchaseManager.modify(this.purchase, this.purchase.getFood(), Double.parseDouble(count),
						this.cmbStatus.getSelectedItem().toString());
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "采购数量必须大于为大于0的整数!请输入阿拉伯数字!", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
