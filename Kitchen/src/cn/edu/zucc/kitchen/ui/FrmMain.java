package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmMain extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;

	private JPanel toolBar = new JPanel();

	// ��ͨ�û�
	private Button myMenu = new Button("�ҵĲ���");
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("���ײ�ѯ");
	private JMenuBar menubar = new JMenuBar();;
	private JMenu menu_menu = new JMenu("���׹���");
	private JMenu menu_step = new JMenu("�������");
	private JMenu menu_food = new JMenu("���Ϲ���");
	private JMenu menu_order = new JMenu("��������");
	private JMenu menu_more = new JMenu("����");
	private Button menuItem_AllMenu = new Button("���ֲ���");
	private JMenuItem menuItem_ChenckAss = new JMenuItem("�鿴����");
	private JMenuItem menuItem_MyMenu = new JMenuItem("�ҵĲ��׹���");
	private JMenuItem menuItem_MyAssessment = new JMenuItem("�ҵ����۹���");
	private JMenuItem menuItem_Assessment = new JMenuItem("���۲���");
	private JMenuItem menuItem_AddStep = new JMenuItem("��Ӳ���");
	private JMenuItem menuItem_DeleteStep = new JMenuItem("ɾ������");
	private JMenuItem MenuItem_ModifyStep = new JMenuItem("�޸Ĳ���");// new
//	private JMenuItem menuItem_startStep = new JMenuItem("��ʼ����");
//	private JMenuItem menuItem_finishStep = new JMenuItem("��������");
	private JMenuItem menuItem_moveUpStep = new JMenuItem("��������");
	private JMenuItem menuItem_moveDownStep = new JMenuItem("��������");
	private JMenuItem menuItem_searchFood = new JMenuItem("����ʳ��");
	private JMenuItem menuItem_addFood = new JMenuItem("�������");
	private JMenuItem menuItem_modifyFood = new JMenuItem("�޸�����");
	private JMenuItem menuItem_delFood = new JMenuItem("�h������");
	private JMenuItem menuItem_createOrder = new JMenuItem("���ɶ���");
	private JMenuItem menuItem_Order = new JMenuItem("�ҵĶ���");
	private JMenuItem menuItem_modifyUser = new JMenuItem("��Ϣ�޸�");
	private JMenuItem menuItem_modifyPwd = new JMenuItem("�����޸�");
	private JMenuItem menuItem_logoutForever = new JMenuItem("����ע��");

	// ����Ա�û�
	private JMenu menuFood = new JMenu("ʳ����Ϣ");
	private JMenu menuUser = new JMenu("�û���Ϣ");
	private JMenu menuOrder = new JMenu("�ɹ���Ϣ"); // ������
	private JMenuItem menuItem_foodType = new JMenuItem("ʳ��������");
	private JMenuItem menuItem_food = new JMenuItem("ʳ�Ĺ���");
	private JMenuItem menuItem_user = new JMenuItem("��ͨ�û�����");
	private JMenuItem menuItem_CreateAdminUser = new JMenuItem("��������Ա�û�");
	private JMenuItem menuItem_buy = new JMenuItem("ʳ�Ĳɹ�");
	private JMenuItem menuItem_buyMessage = new JMenuItem("�ɹ���Ϣ");
	private Button delmenu = new Button("Υ��ɾ��");
	private JMenuItem menuItem_static1 = new JMenuItem("ͳ��1");

	private JMenuItem menuItem_loadUser = new JMenuItem("�û�����");
	private JMenuItem menuItem_resetPwd = new JMenuItem("��������");
	private JMenuItem menuItem_deleteUser = new JMenuItem("ɾ���û�");

	private FrmLogin dlgLogin = null;
	private JPanel statusBar = new JPanel();

	private Object menuTitle[] = BeanMenu.menuTitle;
	private Object menuData[][];
	DefaultTableModel tabMenuModel = new DefaultTableModel() {// ������˫���༭
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTableMenu = new JTable(tabMenuModel);

//	private Object tblPlanTitle[] = BeanPlan.tableTitles;
	private Object tblPlanData[][];
	DefaultTableModel tabPlanModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTablePlan = new JTable(tabPlanModel);

	private Object stepTitle[] = BeanMenuStep.stepTitle;
	private Object stepData[][];
	DefaultTableModel tabStepModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTableStep = new JTable(tabStepModel);

	private Object foodTitle[] = BeanMenuIngredient.foodTitle;
	private Object foodData[][];
	DefaultTableModel tabFoodModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTableFood = new JTable(tabFoodModel);

	private BeanMenu curMenu = null;
	private BeanMenuStep curStep = null;
	private BeanMenuIngredient food = null;
	List<BeanUser> allUser = null; // new
	List<BeanMenu> allMenu = null;
	List<BeanMenuStep> menuSteps = null;
	List<BeanMenuIngredient> allFood = null;

//	private void reloadUserTable() {// �����û���
//		try {
//			allUser = PersonPlanUtil.userManager.loadAll();
//		} catch (BaseException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		tblUserData = new Object[allUser.size()][BeanUser.tableTitles.length];
//		for (int i = 0; i < allUser.size(); i++) {
//			for (int j = 0; j < BeanUser.tableTitles.length; j++)
//				tblUserData[i][j] = allUser.get(i).getCell(j);
//		}
//		tabUserModel.setDataVector(tblUserData, tblUserTitle);
//		this.dataTableUser.validate();
//		this.dataTableUser.repaint();
//	}

	private void reloadMenuTable() {// ���ǲ������ݣ���Ҫ��ʵ�����滻
		try {
			allMenu = KitchenAssistantUtil.menuManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		menuData = new Object[allMenu.size()][BeanMenu.menuTitle.length];
		for (int i = 0; i < allMenu.size(); i++) {
			for (int j = 0; j < BeanMenu.menuTitle.length; j++)
				menuData[i][j] = allMenu.get(i).getCell(j);
		}
		tabMenuModel.setDataVector(menuData, menuTitle);
		this.dataTableMenu.validate();
		this.dataTableMenu.repaint();
	}

	private void reloadMyMenuTable() {// ���ǲ������ݣ���Ҫ��ʵ�����滻
		try {
			allMenu = KitchenAssistantUtil.menuManager.loadMyMenu(BeanUser.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		menuData = new Object[allMenu.size()][BeanMenu.menuTitle.length];
		for (int i = 0; i < allMenu.size(); i++) {
			for (int j = 0; j < BeanMenu.menuTitle.length; j++)
				menuData[i][j] = allMenu.get(i).getCell(j);
		}
		tabMenuModel.setDataVector(menuData, menuTitle);
		this.dataTableMenu.validate();
		this.dataTableMenu.repaint();

		tabFoodModel.setDataVector(null, foodTitle);
		this.dataTableFood.validate();
		this.dataTableFood.repaint();

		tabStepModel.setDataVector(null, stepTitle);
		this.dataTableStep.validate();
		this.dataTableStep.repaint();

	}

	private void reloadFoodTable(int menuIdx) {// ���ǲ������ݣ���Ҫ��ʵ�����滻
		if (menuIdx < 0)
			return;
		curMenu = allMenu.get(menuIdx);
		if (curMenu.getUser().getUserId() == 0 && BeanUser.currentLoginUser != null) {
			tabStepModel.setDataVector(null, stepTitle);
			this.dataTableStep.validate();
			this.dataTableStep.repaint();
			tabFoodModel.setDataVector(null, foodTitle);
			this.dataTableFood.validate();
			this.dataTableFood.repaint();
			JOptionPane.showMessageDialog(null, "�û���ע�����޷��鿴", "����", JOptionPane.ERROR_MESSAGE);
			this.reloadMenuTable();
			return;
		}
		try {
			allFood = KitchenAssistantUtil.menuIngredient.loadAll(curMenu);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			return;
		}
		foodData = new Object[allFood.size()][BeanMenuIngredient.foodTitle.length];
		for (int i = 0; i < allFood.size(); i++) {
			for (int j = 0; j < BeanMenuIngredient.foodTitle.length; j++)
				foodData[i][j] = allFood.get(i).getCell(j);
		}
		tabFoodModel.setDataVector(foodData, foodTitle);
		this.dataTableFood.validate();
		this.dataTableFood.repaint();
	}

	private void reloadStepTabel(int menuIdx) {
		if (menuIdx < 0)
			return;
		curMenu = allMenu.get(menuIdx);
		if (curMenu.getUser().getUserId() == 0) {
			return;
		}
		try {
			menuSteps = KitchenAssistantUtil.menuStepManager.loadAll(curMenu);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		stepData = new Object[menuSteps.size()][BeanMenuStep.stepTitle.length];
		for (int i = 0; i < menuSteps.size(); i++) {
			for (int j = 0; j < BeanMenuStep.stepTitle.length; j++)
				stepData[i][j] = menuSteps.get(i).getCell(j);
		}

		tabStepModel.setDataVector(stepData, stepTitle);
		this.dataTableStep.validate();
		this.dataTableStep.repaint();

	}

	public FrmMain() {

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		dlgLogin = new FrmLogin(this, "��½", true);
		dlgLogin.setVisible(true);

		JFrame j = this;

		// �˵�
		if (BeanUser.currentLoginUser != null) {
			this.setTitle("����С����");
			this.menu_menu.add(this.menuItem_MyMenu);
			this.menuItem_MyMenu.addActionListener(this);
			this.menu_menu.add(this.menuItem_Assessment);
			this.menuItem_Assessment.addActionListener(this);// new
			this.menu_menu.add(this.menuItem_ChenckAss);
			this.menuItem_ChenckAss.addActionListener(this);
			this.menu_menu.add(this.menuItem_MyAssessment);
			this.menuItem_MyAssessment.addActionListener(this);
			this.menu_step.add(this.menuItem_AddStep);
			this.menuItem_AddStep.addActionListener(this);
			this.menu_step.add(this.menuItem_DeleteStep);
			this.menuItem_DeleteStep.addActionListener(this);
			this.menu_step.add(this.MenuItem_ModifyStep);
			this.MenuItem_ModifyStep.addActionListener(this);// new
//			this.menu_step.add(this.menuItem_startStep);
//			this.menuItem_startStep.addActionListener(this);
//			this.menu_step.add(this.menuItem_finishStep);
//			this.menuItem_finishStep.addActionListener(this);
			this.menu_step.add(this.menuItem_moveUpStep);
			this.menuItem_moveUpStep.addActionListener(this);
			this.menu_step.add(this.menuItem_moveDownStep);
			this.menuItem_moveDownStep.addActionListener(this);
			this.menu_food.add(this.menuItem_searchFood);
			this.menuItem_searchFood.addActionListener(this);
			this.menu_food.add(this.menuItem_addFood);
			this.menuItem_addFood.addActionListener(this);
			this.menu_food.add(this.menuItem_modifyFood);
			this.menuItem_modifyFood.addActionListener(this);
			this.menu_food.add(this.menuItem_delFood);
			this.menuItem_delFood.addActionListener(this);
			this.menu_order.add(this.menuItem_createOrder);
			this.menuItem_createOrder.addActionListener(this);
			this.menu_order.add(this.menuItem_Order);
			this.menuItem_Order.addActionListener(this);
			this.menu_more.add(this.menuItem_modifyUser);
			this.menuItem_modifyUser.addActionListener(this);
			this.menu_more.add(this.menuItem_modifyPwd);
			this.menuItem_modifyPwd.addActionListener(this);
			this.menu_more.add(this.menuItem_logoutForever);
			this.menuItem_logoutForever.addActionListener(this);

//			this.menubar.setLayout(null);
			menubar.add(menu_menu);
			menubar.add(menu_step);
//			menubar.add(menu_static);
			System.out.println("yes");
//			if("admin".equals(BeanUser.currentLoginUser.getUser_type())){
//				menubar.add(menu_user);// new
//				System.out.println("yes");
//		    }
			menubar.add(menu_food);
			menubar.add(menu_order);
			menubar.add(menu_more);
//			menubar.add(myMenu);
			this.myMenu.addActionListener(this);
			this.menuItem_AllMenu.addActionListener(this);
			this.btnSearch.addActionListener(this);

			toolBar.setLayout(new FlowLayout(FlowLayout.TRAILING));
			toolBar.add(myMenu);
			toolBar.add(menuItem_AllMenu);
			toolBar.add(edtKeyword);
			toolBar.add(btnSearch);

			this.getContentPane().add(toolBar, BorderLayout.NORTH);

			this.setJMenuBar(menubar);

//			this.getContentPane().add(new JScrollPane(this.dataTableUser), BorderLayout.WEST);// new
			this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.WEST);

			this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
			this.getContentPane().add(new JScrollPane(this.dataTableFood), BorderLayout.EAST);// new
//			this.reloadUserTable();///////////////////////////////
			this.reloadMenuTable();

			// ״̬��

			statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label = new JLabel("����!" + BeanUser.currentLoginUser.getUserName());// �޸ĳ� ���ã�+��½�û���
			statusBar.add(label);
			this.getContentPane().add(statusBar, BorderLayout.SOUTH);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			this.setVisible(true);

		} else if (BeanAdminUser.currentLoginUser != null) {
			this.setTitle("����С���֡�����̨����");
			this.menuFood.add(this.menuItem_foodType);
			this.menuItem_foodType.addActionListener(this);
			this.menuFood.add(this.menuItem_food);
			this.menuItem_food.addActionListener(this);
			this.menuUser.add(this.menuItem_user);
			this.menuItem_user.addActionListener(this);
			this.menuUser.add(this.menuItem_CreateAdminUser);
			this.menuItem_CreateAdminUser.addActionListener(this);
			this.menuUser.add(this.menuItem_modifyPwd);
			this.menuItem_modifyPwd.addActionListener(this);
			this.menuOrder.add(this.menuItem_buy);
			this.menuItem_buy.addActionListener(this);
			this.menuOrder.add(this.menuItem_buyMessage);
			this.menuItem_buyMessage.addActionListener(this);

			menubar.add(menuFood);
			menubar.add(menuUser);
			menubar.add(menuOrder);
			this.setJMenuBar(menubar);

			this.menuItem_AllMenu.addActionListener(this);
			this.delmenu.addActionListener(this);
			this.btnSearch.addActionListener(this);

			toolBar.setLayout(new FlowLayout(FlowLayout.TRAILING));
//			toolBar.add(myMenu);
			menuItem_AllMenu.setLabel("���в���");
			toolBar.add(menuItem_AllMenu);
			toolBar.add(delmenu);
			toolBar.add(edtKeyword);
			toolBar.add(btnSearch);

			this.getContentPane().add(toolBar, BorderLayout.NORTH);

			// ״̬��
			statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
			JLabel label = new JLabel("����!  " + BeanAdminUser.currentLoginUser.getAdminUserName() + "  ����Ա");// �޸ĳ�

			this.getContentPane().add(new JScrollPane(this.dataTableStep), BorderLayout.CENTER);
			this.getContentPane().add(new JScrollPane(this.dataTableFood), BorderLayout.EAST);
			this.getContentPane().add(new JScrollPane(this.dataTableMenu), BorderLayout.WEST);
			this.reloadMenuTable();
			// ���ã�+��½�û���
			statusBar.add(label);
			this.getContentPane().add(statusBar, BorderLayout.SOUTH);
			this.addWindowListener(new WindowAdapter() {
				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
			});
			this.setVisible(true);
		}
		this.dataTableMenu.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableMenu.getSelectedRow();
				if (i < 0) {
					return;
				}
				if (e.getClickCount() == 1) {
					FrmMain.this.reloadStepTabel(i);
					FrmMain.this.reloadFoodTable(i);
				}

				if (e.getClickCount() == 2) {////////////////////
					curMenu = allMenu.get(i);
					try {
						new FrmMenuMessage(j, curMenu.getMenuName(), true, curMenu).setVisible(true);
						FrmMain.this.reloadMenuTable();
					} catch (BaseException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
		this.dataTableStep.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableStep.getSelectedRow();
				if (i < 0) {
					return;
				}
				if (e.getClickCount() == 2) {
					curStep = menuSteps.get(i);
					try {
						new FrmStepMessage(j, "������������", true, curStep).setVisible(true);
					} catch (BaseException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
		this.dataTableFood.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMain.this.dataTableFood.getSelectedRow();
				if (i < 0) {
					return;
				}
				if (e.getClickCount() == 2) {
					food = allFood.get(i);
					try {
						new FrmFoodMessage(j, "����������Ϣ", true, food).setVisible(true);
					} catch (BaseException e1) {
						e1.printStackTrace();
					}

				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.menuItem_AllMenu) {
			this.reloadMenuTable();
			tabFoodModel.setDataVector(null, foodTitle);
			this.dataTableFood.validate();
			this.dataTableFood.repaint();

			tabStepModel.setDataVector(null, stepTitle);
			this.dataTableStep.validate();
			this.dataTableStep.repaint();
		} else if (e.getSource() == this.menuItem_MyMenu) {
			FrmMenuManager dlg = new FrmMenuManager(this, "�ҵĲ���", true);
			dlg.setVisible(true);
			this.reloadMenuTable();// new
		} else if (e.getSource() == this.menuItem_MyAssessment) {
			FrmMyAssessment dlg = new FrmMyAssessment(this, "�ҵ�����", true);
			dlg.setVisible(true);
			this.reloadMenuTable();// new
		} else if (e.getSource() == this.menuItem_Assessment) {
//			// new
			try {
				int i = FrmMain.this.dataTableMenu.getSelectedRow();
				if (i < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (KitchenAssistantUtil.assessmentManager.search(curMenu) != null) {
					JOptionPane.showMessageDialog(null, "���Ѿ����۹��ò���!\n������_�ҵ����۹���_���޸�����!", "����",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

			} catch (BaseException e1) {
				e1.printStackTrace();
			}

			FrmAddAssessment dlg = new FrmAddAssessment(this, "����", true);
			dlg.menu = curMenu;
			dlg.setVisible(true);
			this.reloadMenuTable();

		} else if (e.getSource() == this.menuItem_AddStep) {
			int i = FrmMain.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			FrmAddStep dlg = new FrmAddStep(this, "��Ӳ���", true);
			dlg.menu = curMenu;
			dlg.setVisible(true);
			this.reloadStepTabel(FrmMain.this.dataTableMenu.getSelectedRow());// new
//			this.reloadMenuTable();
		} else if (e.getSource() == this.menuItem_DeleteStep) {
			int i = FrmMain.this.dataTableStep.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.menuStepManager.delete(this.menuSteps.get(i));
				this.reloadStepTabel(FrmMain.this.dataTableMenu.getSelectedRow());// new
//				this.reloadMenuTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == this.MenuItem_ModifyStep) {
			// new
			int i = FrmMain.this.dataTableStep.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			curStep = this.menuSteps.get(i);
			FrmModifyStep dlg = new FrmModifyStep(this, "�޸Ĳ���", true);
			BeanMenuStep bms = null;
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				bms = (BeanMenuStep) KitchenAssistantUtil.menuStepManager.search(curStep).get(0);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
			dlg.step = curStep;
			dlg.edtDescription.setText(bms.getMenuStepDescription());
			dlg.setVisible(true);
			this.reloadStepTabel(FrmMain.this.dataTableMenu.getSelectedRow());// new
//			this.reloadMenuTable();
		} else if (e.getSource() == this.menuItem_moveUpStep) {
			int i = FrmMain.this.dataTableStep.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.menuStepManager.moveUp(this.menuSteps.get(i));
				this.reloadStepTabel(FrmMain.this.dataTableMenu.getSelectedRow());
//				this.reloadMenuTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}

		} else if (e.getSource() == this.menuItem_moveDownStep) {
			int i = FrmMain.this.dataTableStep.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.menuStepManager.moveDown(this.menuSteps.get(i));
				this.reloadStepTabel(FrmMain.this.dataTableMenu.getSelectedRow());
//				this.reloadMenuTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == this.menuItem_foodType) {
			FrmFoodTypeManager dlg = new FrmFoodTypeManager(this, "ʳ���������", true);
			dlg.setVisible(true);
		} else if (e.getSource() == this.menuItem_food) {
			FrmFoodManager dlg = new FrmFoodManager(this, "ʳ�Ĺ���", true);
			dlg.setVisible(true);
		} else if (e.getSource() == this.menuItem_modifyPwd) {
			FrmModifyPwd dlg = new FrmModifyPwd(this, "�����޸�", true);
			dlg.setVisible(true);
		} else if (e.getSource() == this.menuItem_addFood) {// ���ڴ���ע�����
			int i = FrmMain.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			Map<String, BeanFoodType> ftMap = new LinkedHashMap<>();
			List<BeanFoodType> types = null;
			try {
				types = KitchenAssistantUtil.foodTypeManager.loadAll();
				String[] strTypes = new String[types.size() + 1];
				strTypes[0] = "";
				for (int j = 0; j < types.size(); j++) {
					ftMap.put(types.get(j).getFoodTypeName(), types.get(j));
//					readerTypeMap_id.put(types.get(i).getReaderTypeId(), types.get(i));
					strTypes[j + 1] = types.get(j).getFoodTypeName();
				}
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, "������޷�ȷ������������������\n_��������_����0\n_���ϵ�λ_�����������������������������Ϣ", "��ʾ",
					JOptionPane.INFORMATION_MESSAGE);
			FrmAddMenuIngredient dlg = new FrmAddMenuIngredient(this, "�������", true, ftMap);
			dlg.menu = curMenu;
			dlg.setVisible(true);
			this.reloadFoodTable(i);
			this.reloadStepTabel(i);// new
//			this.reloadMenuTable();
		} else if (e.getSource() == this.menuItem_modifyFood) {
			int i = FrmMain.this.dataTableFood.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ���������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			food = this.allFood.get(i);
			FrmModifyMenuIngredient dlg = new FrmModifyMenuIngredient(this, "�޸�������Ϣ", true);
			dlg.food = food;
			dlg.edtCount.setText((int) food.getIngredientCount() + "");
			dlg.edtUnit.setText(food.getIngredientUnit());
			dlg.setVisible(true);
			this.reloadFoodTable(FrmMain.this.dataTableMenu.getSelectedRow());

		} else if (e.getSource() == this.menuItem_delFood) {
			int i = FrmMain.this.dataTableFood.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ���������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (curMenu.getUser().getUserId() != BeanUser.currentLoginUser.getUserId()) {
				JOptionPane.showMessageDialog(null, "��û��Ȩ���޸������û������Ĳ���!!!", "����", JOptionPane.ERROR_MESSAGE);
				JOptionPane.showMessageDialog(null, "��������Ҳ�_�ҵĲ���_�޸����Ĳ�����Ϣ ", "��ʾ", JOptionPane.WARNING_MESSAGE);
				return;
			}
			try {
				food = this.allFood.get(i);
				KitchenAssistantUtil.menuIngredient.delete(food);
				this.reloadFoodTable(FrmMain.this.dataTableMenu.getSelectedRow());
				this.reloadStepTabel(FrmMain.this.dataTableMenu.getSelectedRow());// new
//				this.reloadMenuTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == this.myMenu) {
			this.reloadMyMenuTable();
		} else if (e.getSource() == this.menuItem_Order) {
			new FrmMyOrder(this, "�ҵĶ���", true).setVisible(true);
		} else if (e.getSource() == this.menuItem_createOrder) {
			int i = FrmMain.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			new FrmCreateOrder(this, "һ����������", true, allMenu.get(i)).setVisible(true);
//			KitchenAssistantUtil.foodOrderManager.add(BeanUser.currentLoginUser, allMenu.get(i), deadline, address, phone);
		} else if (e.getSource() == this.btnSearch) {
			String name = this.edtKeyword.getText();
			try {
				allMenu = KitchenAssistantUtil.menuManager.search(name);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			menuData = new Object[allMenu.size()][BeanMenu.menuTitle.length];
			for (int i = 0; i < allMenu.size(); i++) {
				for (int j = 0; j < BeanMenu.menuTitle.length; j++)
					menuData[i][j] = allMenu.get(i).getCell(j);
			}
			tabMenuModel.setDataVector(menuData, menuTitle);
			this.dataTableMenu.validate();
			this.dataTableMenu.repaint();

			tabFoodModel.setDataVector(null, foodTitle);
			this.dataTableFood.validate();
			this.dataTableFood.repaint();

			tabStepModel.setDataVector(null, stepTitle);
			this.dataTableStep.validate();
			this.dataTableStep.repaint();
		} else if (e.getSource() == this.menuItem_buy) {
			Map<String, BeanFoodType> ftMap = new LinkedHashMap<>();
			List<BeanFoodType> types = null;
			try {
				types = KitchenAssistantUtil.foodTypeManager.loadAll();
				String[] strTypes = new String[types.size() + 1];
				strTypes[0] = "";
				for (int j = 0; j < types.size(); j++) {
					ftMap.put(types.get(j).getFoodTypeName(), types.get(j));
//					readerTypeMap_id.put(types.get(i).getReaderTypeId(), types.get(i));
					strTypes[j + 1] = types.get(j).getFoodTypeName();
				}
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
			FrmPurchaseManager_Add dlg = new FrmPurchaseManager_Add(this, "ʳ�Ĳɹ�", true, ftMap);
			dlg.menu = curMenu;
			dlg.setVisible(true);
		} else if (e.getSource() == this.menuItem_user) {
			FrmUserManager dlg = new FrmUserManager(this, "��ͨ�û�����", true);
			dlg.setVisible(true);
		} else if (e.getSource() == this.menuItem_buyMessage) {
			FrmPurchaseMessage dlg = new FrmPurchaseMessage(this, "�ɹ���Ϣ", true);
			dlg.setVisible(true);
		} else if (e.getSource() == this.delmenu) {
			int i = FrmMain.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.menuManager.delete(allMenu.get(i));
				this.reloadMenuTable();
				tabFoodModel.setDataVector(null, foodTitle);
				this.dataTableFood.validate();
				this.dataTableFood.repaint();

				tabStepModel.setDataVector(null, stepTitle);
				this.dataTableStep.validate();
				this.dataTableStep.repaint();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else if (e.getSource() == this.menuItem_ChenckAss) {
			int i = FrmMain.this.dataTableMenu.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ�����", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (allMenu.get(i).getUser().getUserId() == 0) {
				tabStepModel.setDataVector(null, stepTitle);
				this.dataTableStep.validate();
				this.dataTableStep.repaint();
				tabFoodModel.setDataVector(null, foodTitle);
				this.dataTableFood.validate();
				this.dataTableFood.repaint();
				JOptionPane.showMessageDialog(null, "�û���ע�����޷��鿴", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmCheckAssessment dlg = new FrmCheckAssessment(this, "�������۲鿴", true, allMenu.get(i));
			dlg.setVisible(true);
		} else if (e.getSource() == this.menuItem_searchFood) {
			(new FrmFoodManager(this, "����ʳ��", true)).setVisible(true);
		} else if (e.getSource() == this.menuItem_CreateAdminUser) {
			(new FrmCreateAdminUser(this, "��������Ա�û�", true)).setVisible(true);
		} else if (e.getSource() == this.menuItem_logoutForever) {
			if (JOptionPane.showConfirmDialog(this, "ȷ������ע����", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					KitchenAssistantUtil.userManager.delete(BeanUser.currentLoginUser.getUserName());
					this.setVisible(false);;
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		}else if(e.getSource()==this.menuItem_modifyUser) {
			(new FrmRegister(this, "�û���Ϣ�޸�", true)).setVisible(true);
		}

	}
}
