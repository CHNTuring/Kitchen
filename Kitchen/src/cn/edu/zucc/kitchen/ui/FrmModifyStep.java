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
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelStepDescription = new JLabel("步骤描述：");
	private JLabel labelImage = new JLabel("步骤图片：");
	private Button button = new Button("请选择要上传的图片...");

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
		// 屏幕居中显示
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
			GifFileFilter gifFilter = new GifFileFilter(); // gif过滤器
			JpgFileFilter jpgFilter = new JpgFileFilter(); // jpg过滤器
			jfc.addChoosableFileFilter(jpgFilter); // 加载jpg文件过滤器
			jfc.addChoosableFileFilter(gifFilter); // 加载gif文件过滤器
			jfc.setApproveButtonText("确定"); // 定义“确定“按钮”
			jfc.setDialogTitle("打开文件"); // 定义文件选择框标题
			result = jfc.showOpenDialog(this); // 显示打开对话框
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
				JOptionPane.showMessageDialog(null, "您的步骤缺乏相应的步骤描述", "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				KitchenAssistantUtil.menuStepManager.modify(step, menuStepDescription, filepath);
				System.out.println("aaaa");
				this.setVisible(false);
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

}
