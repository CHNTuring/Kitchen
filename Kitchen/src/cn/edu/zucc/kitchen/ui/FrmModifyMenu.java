package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmModifyMenu extends JDialog implements ActionListener {
	public BeanMenu menu = null;
	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelName = new JLabel("菜谱名称：");
	private JLabel labelDescription = new JLabel("菜谱描述：");
	private JLabel labelImage = new JLabel("菜谱图片：");
	private Button button = new Button("请选择要上传的图片...");

	JTextField edtName = new JTextField(20);
	JTextArea edtDescription = new JTextArea();

	public List<BeanMenu> list = null;

	public FrmModifyMenu(JDialog f, String s, boolean b,BeanMenu menu) throws BaseException {
		super(f, s, b);
		this.menu=menu;
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.setLayout(null);
		labelName.setBounds(10, 5, 70, 20);// new
		edtName.setBounds(80, 5, 200, 20);// new
		edtName.setBorder(new LineBorder(null, 1));
		labelDescription.setBounds(10, 30, 70, 20);
		edtDescription.setBounds(80, 30, 200, 150);
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		labelImage.setBounds(10, 190, 70, 20);
		button.setBounds(80, 190, 200, 20);
//		fc.setBounds(80, 190, 70, 50);
		workPane.add(labelName);
		workPane.add(edtName);
		workPane.add(labelDescription);
		workPane.add(edtDescription);
		workPane.add(labelImage);
		workPane.add(button);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(350, 300);
		edtName.setText(menu.getMenuName());
		edtDescription.setText(menu.getMenuDescription());
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
			String newName = this.edtName.getText();
			String newDescription = this.edtDescription.getText();
			if("".equals(newName)||(newName)==null||"".equals(newDescription)||(newDescription)==null) {
				JOptionPane.showMessageDialog(null, "请填写完整的菜谱信息\n菜谱图片可以在之后上传","错误",JOptionPane.ERROR_MESSAGE); 
				return;
			}
			try {
				KitchenAssistantUtil.menuManager.modify(menu, newName, newDescription, filepath);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

	}

}
