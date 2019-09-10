package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.kitchen.comtrol.example.ExampleFoodTypeManager;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmFoodTypeManager_AddFoodType extends JDialog implements ActionListener {

	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelType = new JLabel("������ƣ�");
	private JLabel labelDescription = new JLabel("���������");
//	private JLabel labelUsertype = new JLabel("���");

	private JTextField edtType = new JTextField(20);
	private JTextField edtDescription = new JTextField(20);

//	private JComboBox cmbUsertype= new JComboBox(new String[] { "����Ա", "����Ա"});
	public FrmFoodTypeManager_AddFoodType(JDialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelType);
		workPane.add(edtType);
		workPane.add(labelDescription);
		workPane.add(edtDescription);
//		workPane.add(labelUsertype);
//		workPane.add(cmbUsertype);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 180);
		// ��Ļ������ʾ
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
			String type = this.edtType.getText();
			String description = this.edtDescription.getText();
			if(type==null||"".equals(type)) {
				JOptionPane.showMessageDialog(null, "����дʳ�����!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if(description==null||"".equals(description)) {
				JOptionPane.showMessageDialog(null, "����дʳ���������!", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				(new ExampleFoodTypeManager()).add(type, description);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

//	public BeanSystemUser getUser() {
//		return user;
//	}

}
