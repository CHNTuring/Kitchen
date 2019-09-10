package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuStep;

public class FrmModifyStep extends JDialog implements ActionListener {
	private String filepath = null;
	public BeanMenuStep step = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelStepDescription = new JLabel("����������");
	private JLabel labelImage = new JLabel("����ͼƬ��");
	private Button button = new Button("��ѡ��Ҫ�ϴ���ͼƬ...");

	JTextArea edtDescription = new JTextArea();

	public FrmModifyStep(JFrame f, String s, boolean b) {
		super(f, s, b);

		String[] order = new String[10];
		for (int i = 0; i < 10; i++) {
			order[i] = i + 1 + "";
		}

		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
//		edtName.setBounds(80, 5, 200, 20);// new
//		edtName.setBorder(new LineBorder(null, 1));
		labelStepDescription.setBounds(10, 5, 70, 20);
		edtDescription.setBounds(80, 5, 200, 150);
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		labelImage.setBounds(10, 170, 70, 20);
		button.setBounds(80, 170, 200, 20);
		workPane.add(labelImage);
		workPane.add(labelStepDescription);
		workPane.add(edtDescription);
		workPane.add(button);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 280);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		this.button.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int result = 0;
		if (e.getSource() == this.button) {
			JFileChooser jfc = new JFileChooser();
			GifFileFilter gifFilter = new GifFileFilter(); // gif������
			JpgFileFilter jpgFilter = new JpgFileFilter(); // jpg������
			jfc.addChoosableFileFilter(jpgFilter); // ����jpg�ļ�������
			jfc.addChoosableFileFilter(gifFilter); // ����gif�ļ�������
			jfc.setApproveButtonText("ȷ��"); // ���塰ȷ������ť��
			jfc.setDialogTitle("���ļ�"); // �����ļ�ѡ������
			result = jfc.showOpenDialog(this); // ��ʾ�򿪶Ի���
			if (result == JFileChooser.APPROVE_OPTION) {
				filepath = jfc.getSelectedFile().getPath();
			}
			button.setLabel(filepath);
		}

		if (e.getSource() == this.btnCancel) {
			this.setVisible(false);
			return;
		} else if (e.getSource() == this.btnOk) {
			String menuStepDescription = this.edtDescription.getText();
			if("".equals(menuStepDescription)||(menuStepDescription)==null) {
				JOptionPane.showMessageDialog(null, "���Ĳ���ȱ����Ӧ�Ĳ�������", "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.menuStepManager.modify(step, menuStepDescription, filepath);
				System.out.println("aaaa");
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

}
