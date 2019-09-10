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

public class FrmFoodMessage extends JDialog {

	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JLabel labelName = new JLabel("ʳ��������");
	private JLabel labelPrice = new JLabel("Ŀǰ�۸�");
	private JLabel labelCount = new JLabel("Ŀǰ��棺");

	JTextField edtPrice = new JTextField(260);
	JTextField edtCount = new JTextField(260);
	JTextArea edtDescription = new JTextArea();

	public FrmFoodMessage(JFrame f, String s, boolean b, BeanMenuIngredient mi) throws BaseException {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		Image img = null;
		if (mi.getFood().getFoodImage() != null) {
			try {
				InputStream sbs = new ByteArrayInputStream(
						mi.getFood().getFoodImage().getBytes(1, (int) mi.getFood().getFoodImage().length()));
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

		labelPrice.setBounds(10, 220, 70, 20);
		labelCount.setBounds(10, 245, 70, 20);
		labelName.setBounds(10, 270, 70, 20);// new
		edtPrice.setBounds(80, 220, 260, 20);
		edtCount.setBounds(80, 245, 260, 20);
		edtPrice.setBorder(new LineBorder(null, 1));
		edtPrice.setEditable(false);// �����Ʋ�����༭
		edtPrice.setText(mi.getFood().getFoodPrice()+mi.getFood().getFoodSpecifications());
		edtCount.setBorder(new LineBorder(null, 1));
		edtCount.setEditable(false);// �����Ʋ�����༭
		edtCount.setText(mi.getFood().getFoodCount()+"");
		edtDescription.setBounds(80, 270, 260, 200);// new
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		edtDescription.setEditable(false);// �����Ʋ�����༭
		edtDescription.setText(mi.getFood().getFoodDescription());
		mp.add(labelCount);
		mp.add(labelPrice);
		mp.add(edtCount);
		mp.add(edtPrice);
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
