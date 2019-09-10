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
import cn.edu.zucc.kitchen.model.BeanMenuAssessment;
import cn.edu.zucc.kitchen.model.BeanMenuIngredient;
import cn.edu.zucc.kitchen.util.BaseException;

public class FrmAssessmentDetail extends JDialog {

	private JPanel toolBar = new JPanel();
	JTextArea edtDescription = new JTextArea();

	public FrmAssessmentDetail(JDialog f, String s, boolean b, BeanMenuAssessment assessment) throws BaseException {
		super(f, s, b);

//		toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.getContentPane().add(toolBar, BorderLayout.CENTER);
		toolBar.setLayout(null);
		edtDescription.setBounds(5, 5, 185, 160);// new
		edtDescription.setLineWrap(true);
		edtDescription.setWrapStyleWord(true);
		edtDescription.setBorder(new LineBorder(null, 1));
		edtDescription.setEditable(false);// 允许复制不允许编辑
		if(assessment.getMenuAssessmentContent().equals("")) {
			edtDescription.setText("此用户没有填写评价。");
		}
		else{
			edtDescription.setText(assessment.getMenuAssessmentContent());
		}

		toolBar.add(edtDescription);
		this.setSize(210, 210);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 3);

		this.validate();

	}

}
