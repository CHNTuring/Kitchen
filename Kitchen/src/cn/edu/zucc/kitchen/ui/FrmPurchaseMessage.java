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

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanFoodPurchase;
import cn.edu.zucc.kitchen.model.BeanUser;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmPurchaseMessage extends JDialog implements ActionListener {
	private JPanel toolBar = new JPanel();
//	private Button btnAdd = new Button("����û�");
	private Button btnModify = new Button("�޸�");
	private Button btnDelete = new Button("ɾ��");
	private Object tblTitle[] = { "ʳ������", "ʳ������", "ʳ������", "����״̬", "�ɹ���Ա" };
	private Object tblData[][];
	DefaultTableModel tablmod = new DefaultTableModel();
	private JTable purchaseTable = new JTable(tablmod);
	List<BeanFoodPurchase> bfp = null;

	private void reloadpurchaseTable() {
		try {
			bfp = KitchenAssistantUtil.foodPurchaseManager.loadAll();
			tblData = new Object[bfp.size()][tblTitle.length];
			for (int i = 0; i < bfp.size(); i++) {
				tblData[i][0] = bfp.get(i).getFood().getFoodType().getFoodTypeName();
				tblData[i][1] = bfp.get(i).getFood().getFoodName();
				tblData[i][2] = bfp.get(i).getPurchaseCount();
				tblData[i][3] = bfp.get(i).getPurchaseStatus();
				tblData[i][4] = bfp.get(i).getAdminUser().getAdminUserName();
			}
			tablmod.setDataVector(tblData, tblTitle);
			this.purchaseTable.validate();
			this.purchaseTable.repaint();
		} catch (BaseException e) {
			e.printStackTrace();
		}
	}

	public FrmPurchaseMessage(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
//		toolBar.add(btnAdd);
		toolBar.add(btnModify);
		toolBar.add(this.btnDelete);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		// ��ȡ��������
		this.reloadpurchaseTable();
		this.getContentPane().add(new JScrollPane(this.purchaseTable), BorderLayout.CENTER);

		// ��Ļ������ʾ
		this.setSize(800, 600);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();

//		this.btnAdd.addActionListener(this);
		this.btnModify.addActionListener(this);
		this.btnDelete.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				// System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == this.btnModify) {
			int i = this.purchaseTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ɹ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			FrmPurchaseManager_Modify dlg=new FrmPurchaseManager_Modify(this, "�ɹ����޸�", true, this.bfp.get(i));
			dlg.setVisible(true);
			this.reloadpurchaseTable();
		} else if (e.getSource() == this.btnDelete) {
			int i = this.purchaseTable.getSelectedRow();
			if (i < 0) {
				JOptionPane.showMessageDialog(null, "��ѡ��ɹ���", "��ʾ", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if("��;".equals(this.bfp.get(i).getPurchaseStatus())) {
				JOptionPane.showMessageDialog(null, "���Ĳɹ���״̬Ϊ_��;_�޷�ɾ��\n�������޸���Ӧ�Ĳɹ���״̬", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (JOptionPane.showConfirmDialog(this, "ȷ��ɾ���òɹ�����", "ȷ��",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				String userid = this.tblData[i][0].toString();
				try {
					KitchenAssistantUtil.foodPurchaseManager.delete(this.bfp.get(i));
					this.reloadpurchaseTable();
				} catch (BaseException e1) {
					JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				}

			}
		}
	}
}
