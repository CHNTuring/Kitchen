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
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodOrder;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.model.BeanMenuStep;
import cn.edu.zucc.kitchen.model.BeanOrderDetail;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.BusinessException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

public class FrmMyOrder extends javax.swing.JDialog implements ActionListener {
	private BeanFoodOrder order = null;
	private JPanel toolBar = new JPanel();
	private Button btnAddFood = new Button("����");
	private Button btnModifyFood = new Button("�޸�");
	private Button btnDelFood = new Button("ɾ��");
	private Button btnSure = new Button("ȷ���ջ�");
	private Button btnReturn = new Button("�˻�");
//	private Button btnMessage = new Button("����");
	private Object tblTitle[] = { "�������", "�ջ���ַ", "��ϵ�绰", "����״̬", "Ҫ���ʹ�ʱ��" };
	private Object tblData[][];
	private List<BeanFoodOrder> orders = null;
	private List<BeanOrderDetail> details = null;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable foodTable = new JTable(tablmod);

	private Object tblDetailTitle[] = { "ʳ�����", "ʳ������", "����", "ʳ���ܼ�", "�ۿ�" };
	private Object tblDetailData[][];
	DefaultTableModel tabDetailModel = new DefaultTableModel() {
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable dataTableDetail = new JTable(tabDetailModel);

	private Map<String, BeanFoodType> foodTypesMap_name = new HashMap<String, BeanFoodType>();
	private JComboBox cmbFoodType = null;

	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("��ѯ");

	private void reloadFoodTable() {
		try {
			orders = (new ExampleFoodOrderManager()).loadAll(BeanUser.currentLoginUser);
			tblData = new Object[orders.size()][5];
			for (int i = 0; i < orders.size(); i++) {
				tblData[i][0] = orders.get(i).getFoodOrderId()+"";
				tblData[i][1] = orders.get(i).getAddress()+"";
				tblData[i][2] = orders.get(i).getPhone()+"";
				tblData[i][3] = orders.get(i).getStatus()+"";
				tblData[i][4] = orders.get(i).getDeadline()+"";
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.foodTable.validate();
			this.foodTable.repaint();

			tabDetailModel.setDataVector(null, tblDetailTitle);
			this.dataTableDetail.validate();
			this.dataTableDetail.repaint();
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

	private void reloadDetailTabel(int menuIdx) {
		if (menuIdx < 0)
			return;
		order = orders.get(menuIdx);
		try {
			details = KitchenAssistantUtil.foodOrderManager.load(order);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			return;
		}

		tblDetailData = new Object[details.size()][tblDetailTitle.length];
		for (int i = 0; i < details.size(); i++) {
			tblDetailData[i][0] = details.get(i).getFood().getFoodType().getFoodTypeName();
			tblDetailData[i][1] = details.get(i).getFood().getFoodName();
			tblDetailData[i][2] = details.get(i).getCount()+details.get(i).getFood().getFoodSpecifications().substring(details.get(i).getFood().getFoodSpecifications().length()-1, details.get(i).getFood().getFoodSpecifications().length());
			tblDetailData[i][3] = details.get(i).getPrice()+"Ԫ";
			tblDetailData[i][4] = details.get(i).getDiscount();

		}

		tabDetailModel.setDataVector(tblDetailData, tblDetailTitle);
		this.dataTableDetail.validate();
		this.dataTableDetail.repaint();

	}

	public FrmMyOrder(Frame f, String s, boolean b) {
		super(f, s, b);

		List<BeanFoodType> types = null;
		try {
			types = KitchenAssistantUtil.foodTypeManager.loadAll();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "";
			for (int i = 0; i < types.size(); i++) {
				foodTypesMap_name.put(types.get(i).getFoodTypeName(), types.get(i));
//				readerTypeMap_id.put(types.get(i).getReaderTypeId(), types.get(i));
				strTypes[i + 1] = types.get(i).getFoodTypeName();
			}
			cmbFoodType = new JComboBox(strTypes);
		} catch (BaseException e1) {
			e1.printStackTrace();
		}

		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAddFood);
		toolBar.add(btnModifyFood);
		toolBar.add(this.btnDelFood);
		toolBar.add(btnSure);
		toolBar.add(btnReturn);
//		toolBar.add(this.btnMessage);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadFoodTable();
		this.getContentPane().add(new JScrollPane(this.foodTable), BorderLayout.WEST);
		this.getContentPane().add(new JScrollPane(this.dataTableDetail), BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.reloadFoodTable();

		this.btnAddFood.addActionListener(this);
		this.btnDelFood.addActionListener(this);
		this.btnModifyFood.addActionListener(this);
		this.btnSure.addActionListener(this);
		this.btnReturn.addActionListener(this);
//		this.btnMessage.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
		this.foodTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmMyOrder.this.foodTable.getSelectedRow();
				if (i < 0) {
					return;
				}
				if (e.getClickCount() == 1) {
					FrmMyOrder.this.reloadDetailTabel(i);
//					FrmMyOrder.this.reloadFoodTable(i);
				}

//				if (e.getClickCount() == 2) {////////////////////
//					curMenu = allMenu.get(i);
//					try {
//						new FrmMenuMessage(j, curMenu.getMenuName(), true, curMenu).setVisible(true);
//					} catch (BaseException e1) {
//						e1.printStackTrace();
//					}
//				}
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAddFood) {

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

			FrmMyOrder_AddNewOrder dlg = new FrmMyOrder_AddNewOrder(this, "���ʳ�Ķ���", true, this.foodTypesMap_name);
			dlg.setVisible(true);
			this.reloadFoodTable();
		} else if (e.getSource() == this.btnModifyFood) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ�Ķ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!"�µ�".equals(this.orders.get(i).getStatus())) {
				JOptionPane.showMessageDialog(null, "�̼��ѷ���������Ȩ���޸Ķ�����Ϣ��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				BeanFoodOrder bf = this.orders.get(i);
				FrmMyOrder_Modify dlg = new FrmMyOrder_Modify(this, "�޸�ʳ����Ϣ", true, bf);
				dlg.setVisible(true);
				this.reloadFoodTable();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == this.btnDelFood) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ�Ķ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ("����".equals(this.orders.get(i).getStatus())) {
				JOptionPane.showMessageDialog(null, "���Ķ������������޷�ɾ����", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ���ö�����ɾ���������޷��鿴�ö�����Ϣ", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					BeanFoodOrder bf = this.orders.get(i);
					(new ExampleFoodOrderManager()).delete(this.orders.get(i));
					;
					this.reloadFoodTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		} else if (e.getSource() == this.btnSure) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ�Ķ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ("�ʹ�".equals(this.orders.get(i).getStatus())) {
				JOptionPane.showMessageDialog(null, "���Ķ������ʹ", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if ("�˻�".equals(this.orders.get(i).getStatus())) {
				JOptionPane.showMessageDialog(null, "���Ķ������˻���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (JOptionPane.showConfirmDialog(this, "��ȷ���յ�������ע������Ա��ⲻ��Ҫ����ʧ", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				Session session = null;
				Transaction tx = null;
				try {
					session = HibernateUtil.getSession();
					tx = session.beginTransaction();
					BeanFoodOrder bf = session.get(BeanFoodOrder.class, this.orders.get(i).getFoodOrderId());
					bf.setStatus("�ʹ�");
					session.save(bf);
					tx.commit();
				} catch (Exception e1) {
					e1.printStackTrace();
					if (session != null) {
						try {
							tx.rollback();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} finally {
					try {
						if (session != null) {
							session.close();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			this.reloadFoodTable();

		} else if (e.getSource() == this.btnReturn) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ�Ķ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!"�ʹ�".equals(this.orders.get(i).getStatus())) {
				JOptionPane.showMessageDialog(null, "���Ķ����޷������˻�����鿴����״̬��", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (JOptionPane.showConfirmDialog(this, "���Ķ����������˻���", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				Session session = null;
				Transaction tx = null;
				try {
					session = HibernateUtil.getSession();
					tx = session.beginTransaction();
					BeanFoodOrder bf = session.get(BeanFoodOrder.class, this.orders.get(i).getFoodOrderId());
					bf.setStatus("�˻�");
					session.save(bf);
					tx.commit();
				} catch (Exception e1) {
					e1.printStackTrace();
					if (session != null) {
						try {
							tx.rollback();
						} catch (Exception ex) {
							ex.printStackTrace();
						}
					}
				} finally {
					try {
						if (session != null) {
							session.close();
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
			this.reloadFoodTable();
		}
	}
}
