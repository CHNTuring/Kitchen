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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodManager;
import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodTypeManager;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmFoodManager extends javax.swing.JDialog implements ActionListener {
	private BeanFood food=null;
	private JPanel toolBar = new JPanel();
	private JTextField edtKeyword = new JTextField(10);
	private Button btnSearch = new Button("查询");
	private Button btnAddFood = new Button("新增");
	private Button btnModifyFood = new Button("修改");
	private Button btnDelFood = new Button("删除");
	private Button btnMessage = new Button("详情");
	private Object tblTitle[] = { "名称", "类别", "数量","价格", "规格" };
	private Object tblData[][];
	private Map<String, BeanFoodType> foodTypesMap_name = new HashMap<String, BeanFoodType>();
	private List<BeanFood> foods = null;
	DefaultTableModel tablmod = new DefaultTableModel(){// 不允许双击编辑
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	};
	private JTable foodTable = new JTable(tablmod);

	private JComboBox cmbFoodType = null;


	private void reloadFoodTable() {
		try {
			foods = (new ExampleFoodManager()).loadAll();
			tblData = new Object[foods.size()][5];
			for (int i = 0; i < foods.size(); i++) {
				tblData[i][0] = foods.get(i).getFoodName();
				tblData[i][1] = foods.get(i).getFoodType().getFoodTypeName();
				tblData[i][2] = foods.get(i).getFoodCount();
				tblData[i][3] = foods.get(i).getFoodPrice();
				tblData[i][4] = foods.get(i).getFoodSpecifications();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.foodTable.validate();
			this.foodTable.repaint();
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}
	private void reloadSearchFoodTable(BeanFoodType bft) {
		if(bft==null) {
			tablmod.setDataVector(null, tblTitle);
			this.foodTable.validate();
			this.foodTable.repaint();
		}else {
			foods=new LinkedList<>();
			tblData = new Object[bft.getFoods().size()][5];
			int i=0;
			for (BeanFood bf:bft.getFoods()) {
				foods.add(bf);
				tblData[i][0] = bf.getFoodName();
				tblData[i][1] = bf.getFoodType().getFoodTypeName();
				tblData[i][2] = bf.getFoodCount();
				tblData[i][3] = bf.getFoodPrice();
				tblData[i][4] = bf.getFoodSpecifications();
				i++;
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.foodTable.validate();
			this.foodTable.repaint();
		}
	}

	public FrmFoodManager(Frame f, String s, boolean b) {
		super(f, s, b);
		JDialog j=this;
		List<BeanFoodType> types = null;
		try {
			types = KitchenAssistantUtil.foodTypeManager.loadAll();
			String[] strTypes = new String[types.size() + 1];
			strTypes[0] = "全部食材";
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
		if(BeanAdminUser.currentLoginUser!=null) {
			toolBar.add(btnAddFood);
			toolBar.add(btnModifyFood);
			toolBar.add(this.btnDelFood);
		}
		toolBar.add(cmbFoodType);
		toolBar.add(btnSearch);
		
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// 提取现有数据
		this.reloadFoodTable();
		this.getContentPane().add(new JScrollPane(this.foodTable), BorderLayout.CENTER);

		// 屏幕居中显示
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.reloadFoodTable();

		this.btnAddFood.addActionListener(this);
		this.btnDelFood.addActionListener(this);
		this.btnModifyFood.addActionListener(this);
		this.btnSearch.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
		this.foodTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i = FrmFoodManager.this.foodTable.getSelectedRow();
				if (i < 0) {
					return;
				}

				if (e.getClickCount() == 2) {////////////////////
					food = foods.get(i);
					try {
						new FrmFoodImage(j, "食材详情", true, food).setVisible(true);
					} catch (BaseException e1) {
						e1.printStackTrace();
					}
				}
			}

		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnAddFood) {
			FrmFoodManager_AddFood dlg = new FrmFoodManager_AddFood(this, "添加食材", true, this.foodTypesMap_name);
			dlg.setVisible(true);
			this.reloadFoodTable();
		} else if (e.getSource() == this.btnModifyFood) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择食材", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				BeanFood bf = this.foods.get(i);
				FrmFoodManager_ModifyFood dlg = new FrmFoodManager_ModifyFood(this, "修改食材信息", true,
						this.foodTypesMap_name);
				dlg.food = bf;
				dlg.edtCount.setText(String.valueOf(dlg.food.getFoodCount()));
				dlg.edtDescription.setText(dlg.food.getFoodDescription());
				dlg.edtName.setText(dlg.food.getFoodName());
				dlg.edtSpecification.setText(dlg.food.getFoodSpecifications());
				dlg.edtPrice.setText(String.valueOf(dlg.food.getFoodPrice()));
				dlg.setVisible(true);
				this.reloadFoodTable();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
			}

		} else if (e.getSource() == this.btnDelFood) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "请选择食材", "提示", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "确定删除该食材吗？", "确认",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					BeanFood bf = this.foods.get(i);
					(new ExampleFoodManager()).delete(bf);
					this.reloadFoodTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				}

			}
		} else if(e.getSource()==this.btnSearch) {
			if(this.cmbFoodType.getSelectedIndex()==0) {
				this.reloadFoodTable();
			}else {
				BeanFoodType bft=foodTypesMap_name.get(cmbFoodType.getSelectedItem().toString());
				this.reloadSearchFoodTable(bft);
			}
		}
	}
}
