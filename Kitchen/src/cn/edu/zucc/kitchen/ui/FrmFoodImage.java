package cn.edu.zucc.kitchen.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import cn.edu.zucc.kitchen.model.BeanFood;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmFoodImage extends JDialog {

	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JLabel labelName = new JLabel("ʳ��������");

	JTextArea edtDescription = new JTextArea();

	public FrmFoodImage(JDialog f, String s, boolean b, BeanFood food) throws BaseException {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		Image img = null;
		if (food.getFoodImage() != null) {
			try {
				InputStream sbs = new ByteArrayInputStream(
						food.getFoodImage().getBytes(1, (int) food.getFoodImage().length()));
				img = ImageIO.read(sbs);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		MyPanel mp = new MyPanel(img);

		mp.setLayout(null);

		if (img == null) {
			JLabel l = new JLabel("ϵͳ������ͼƬ��");
			l.setBounds(80, 5, 200, 50);
			mp.add(l);
		}
		labelName.setBounds(10, 2350, 70, 20);// new
		edtDescription.setBounds(80, 230, 260, 200);// new
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		edtDescription.setEditable(false);// �������Ʋ������༭
		edtDescription.setText(food.getFoodDescription());
		mp.add(labelName);
		mp.add(edtDescription);
		this.getContentPane().add(mp, BorderLayout.CENTER);

		this.setSize(420, 530);
		// ��Ļ������ʾ
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 3);

		this.validate();

	}

}