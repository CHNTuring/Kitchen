package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodTypeManager;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmCheckAssessment extends javax.swing.JDialog {
	private Object tblTitle[] = { "评价用户", "用户评分", "是否收藏" };
	private Object tblData[][];
	DefaultTableModel tablmod = new DefaultTableModel() {// 不允许双击编辑
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTableAsse = new JTable(tablmod);

	BeanMenu menu = null;
	private BeanMenuAssessment assessment = null;
	List<BeanMenuAssessment> allAsse = null;

	private void reloadAsseTable() {// 这是测试数据，需要用实际数替换
		try {
			allAsse = KitchenAssistantUtil.assessmentManager.loadAll(menu);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData = new Object[allAsse.size()][tblTitle.length];
		for (int i = 0; i < allAsse.size(); i++) {
				tblData[i][0] = allAsse.get(i).getUser().getUserName();
				tblData[i][1] = allAsse.get(i).getMenuScore();
				tblData[i][2] = allAsse.get(i).getIsCollected();
		}
		tablmod.setDataVector(tblData, tblTitle);
		this.dataTableAsse.validate();
		this.dataTableAsse.repaint();
	}

	public FrmCheckAssessment(Frame f, String s, boolean b,BeanMenu menu) {
		super(f, s, b);
		FrmCheckAssessment j = this;
		this.menu=menu;
		// 提取现有数据
		this.reloadAsseTable();
		this.getContentPane().add(new JScrollPane(this.dataTableAsse), BorderLayout.CENTER);
		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});

		this.dataTableAsse.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmCheckAssessment.this.dataTableAsse.getSelectedRow();
				if (i < 0) {
					return;
				}
				if (e.getClickCount() == 2) {
					assessment = allAsse.get(i);
					try {
						new FrmAssessmentDetail(j, "评价详情", true, assessment).setVisible(true);
					} catch (BaseException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
	}
	
}
