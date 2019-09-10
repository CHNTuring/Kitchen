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
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmModifyMenuIngredient extends JDialog implements ActionListener {
	public BeanMenuIngredient food = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelCount = new JLabel("用料数量：");
	private JLabel labelUnit = new JLabel("用料单位：");

	JTextField edtCount = new JTextField(20);
	JTextField edtUnit = new JTextField(20);

	public FrmModifyMenuIngredient(Frame f, String s, boolean b) {
		super(f, s, b);

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelCount.setBounds(25, 5, 70, 20);
		edtCount.setBounds(95, 5, 200, 20);
		labelUnit.setBounds(25, 30, 70, 20);
		edtUnit.setBounds(95, 30, 200, 20);
		edtUnit.setBorder(new LineBorder(null, 1));
		edtCount.setBorder(new LineBorder(null, 1));

		workPane.add(labelCount);
		workPane.add(edtCount);
		workPane.add(labelUnit);
		workPane.add(edtUnit);

		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 150);
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
		String unit = null;
		String count = null;

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			try {
				unit = this.edtUnit.getText();
				count = this.edtCount.getText();
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
				KitchenAssistantUtil.menuIngredient.modify(food, Double.parseDouble(count), unit);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}catch(NumberFormatException ex) {
				JOptionPane.showMessageDialog(null, "输入不能包含除阿拉伯数字外的其他字符！", "错误", JOptionPane.ERROR_MESSAGE);
			}
		}

	}
}
