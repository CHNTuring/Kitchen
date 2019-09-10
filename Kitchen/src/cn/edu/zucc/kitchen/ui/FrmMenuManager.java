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
import javax.swing.JDialog;
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
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmMenuManager extends javax.swing.JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAddMenu = new Button("����");
	private Button btnModifyMenu = new Button("�޸�");
	private Button btnDelMenu = new Button("ɾ��");
	private Button btnMessage = new Button("����");
	private Object tblTitle[] = BeanMenu.menuTitle;
	private Object tblData[][];
//	private Map<String, BeanFoodType> foodTypesMap_name = new HashMap<String, BeanFoodType>();
	private List<BeanFood> foods = null;
	DefaultTableModel tablmod = new DefaultTableModel();

	private JComboBox cmbFoodType = null;

	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");

	private BeanMenu curMenu = null;
	private BeanMenuStep curStep = null;
	private BeanMenuIngredient food = null;
	List<BeanUser> allUser = null; // new
	List<BeanMenu> myMenus = null;
	List<BeanMenuStep> menuSteps = null;
	List<BeanMenuIngredient> allFood = null;
	private Object menuTitle[] = BeanMenu.menuTitle;
	private Object menuData[][];
	DefaultTableModel tabMenuModel = new DefaultTableModel() {// ������˫���༭
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTableMenu = new JTable(tabMenuModel);

	public FrmMenuManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAddMenu);
		toolBar.add(btnModifyMenu);
		toolBar.add(this.btnDelMenu);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadMenuTable();
		this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.CENTER);
		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.btnAddMenu.addActionListener(this);
		this.btnDelMenu.addActionListener(this);
		this.btnModifyMenu.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});

	}

	private void reloadMenuTable() {// ���ǲ������ݣ���Ҫ��ʵ�����滻
		try {
			myMenus = KitchenAssistantUtil.menuManager.loadMyMenu(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		menuData = new Object[myMenus.size()][BeanMenu.menuTitle.length];
		for (int i = 0; i < myMenus.size(); i++) {
			for (int j = 0; j < BeanMenu.menuTitle.length; j++)
				menuData[i][j] = myMenus.get(i).getCell(j);
		}
		tabMenuModel.setDataVector(menuData, menuTitle);
		this.dataTableMenu.validate();
		this.dataTableMenu.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAddMenu) {
			FrmAddMenu dlg = new FrmAddMenu(this, "��Ӳ���", true);
			dlg.setVisible(true);
			this.reloadMenuTable();// new
		} else if (e.getSource() == this.btnModifyMenu) {
			int i = FrmMenuManager.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmModifyMenu dlg;
			try {
				dlg = new FrmModifyMenu(this, "�޸Ĳ���", true, myMenus.get(i));
				dlg.setVisible(true);
				this.reloadMenuTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}

		} else if (e.getSource() == this.btnDelMenu) {
			int i = FrmMenuManager.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ���ò�����", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					KitchenAssistantUtil.menuManager.delete(myMenus.get(i));
					this.reloadMenuTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
					return;
				}

			}
		}
	}
}
