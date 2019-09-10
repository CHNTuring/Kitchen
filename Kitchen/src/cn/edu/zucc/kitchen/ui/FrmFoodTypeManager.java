package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodTypeManager;
import cn.edu.zucc.kitchen.model.BeanAdminUser;
import cn.edu.zucc.kitchen.model.BeanFoodType;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmFoodTypeManager extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
	private Button btnAddType = new Button("����ʳ�����");
	private Button btnModifyType = new Button("�޸�ʳ�����");
	private Button btnDelType = new Button("ɾ��ʳ�����");
	private Object tblTitle[] = { "ʳ��������", "ʳ���������", "ʳ���������" };
	private Object tblData[][];
	private List<BeanFoodType> types = null;
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable foodTable = new JTable(tablmod);

	private void reloadFoodTable() {
		try {
			types = (new ExampleFoodTypeManager()).loadAll();
			tblData = new Object[types.size()][3];
			for (int i = 0; i < types.size(); i++) {
				tblData[i][0] = i+1+"";
				tblData[i][1] = types.get(i).getFoodTypeName();
				tblData[i][2] = types.get(i).getFoodTypeDescription();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.foodTable.validate();
			this.foodTable.repaint();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FrmFoodTypeManager(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
		toolBar.add(btnAddType);
		toolBar.add(btnModifyType);
		toolBar.add(this.btnDelType);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadFoodTable();
		this.getContentPane().add(new JScrollPane(this.foodTable), BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

		this.reloadFoodTable();

		this.btnAddType.addActionListener(this);
		this.btnModifyType.addActionListener(this);
		this.btnDelType.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == this.btnAddType) {
			FrmFoodTypeManager_AddFoodType dlg = new FrmFoodTypeManager_AddFoodType(this, "���ʳ�����", true);
			dlg.setVisible(true);
			this.reloadFoodTable();
		} else if (e.getSource() == this.btnDelType) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ������", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ����ʳ��������", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				try {
					BeanFoodType bft = this.types.get(i);
					(new ExampleFoodTypeManager()).delete(bft);
					this.reloadFoodTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		}else if(e.getSource()==this.btnModifyType) {
			int i = this.foodTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ʳ������", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			(new FrmFoodTypeManager_ModifyFoodType(this,"ʳ������޸�",true,this.types.get(i))).setVisible(true);
			this.reloadFoodTable();
		}
	}
}
