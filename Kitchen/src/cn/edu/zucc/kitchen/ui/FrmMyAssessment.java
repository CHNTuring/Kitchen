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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodOrderManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodTypeManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleMenuAssessmentManager;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodOrder;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanOrderDetail;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class FrmMyAssessment extends javax.swing.JDialog implements ActionListener {

	private Button btnModify = new Button("修改");
	private Button btnDel = new Button("删除");

	private Object tblTitle[] = { "菜谱名称", "我的评分", "是否收藏" };
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
			allAsse = KitchenAssistantUtil.assessmentManager.loadMyMenu(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblData = new Object[allAsse.size()][tblTitle.length];
		for (int i = 0; i < allAsse.size(); i++) {
			tblData[i][0] = allAsse.get(i).getMenu().getMenuName();
			tblData[i][1] = allAsse.get(i).getMenuScore();
			tblData[i][2] = allAsse.get(i).getIsCollected();
		}
		tablmod.setDataVector(tblData, tblTitle);
		this.dataTableAsse.validate();
		this.dataTableAsse.repaint();
	}

	private JPanel toolBar = new JPanel();

	public FrmMyAssessment(Frame f, String s, boolean b) {
		super(f, s, b);

		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnModify);
		toolBar.add(this.btnDel);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);

		FrmMyAssessment j = this;
		this.menu = menu;
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

		this.btnDel.addActionListener(this);
		this.btnModify.addActionListener(this);

		this.dataTableAsse.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMyAssessment.this.dataTableAsse.getSelectedRow();
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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnModify) {
			int i = this.dataTableAsse.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择评价", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				BeanMenuAssessment assess = this.allAsse.get(i);
				FrmModifyAssessment dlg = new FrmModifyAssessment(this, "修改食材信息", true, assess);
				dlg.setVisible(true);
				this.reloadAsseTable();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}
		} else if (e.getSource() == this.btnDel) {
			int i = this.dataTableAsse.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择评价", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定删除该评价吗？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					BeanMenuAssessment assess = this.allAsse.get(i);
					(new ExampleMenuAssessmentManager()).delete(assess);
					;
					this.reloadAsseTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}
}
