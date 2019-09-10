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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import org.hibernate.Session;
import org.hibernate.Transaction;

import cn.edu.zucc.kitchen.KitchenAssistantUtil;
import cn.edu.zucc.kitchen.model.BeanMenu;
import cn.edu.zucc.kitchen.util.BaseException;
import cn.edu.zucc.kitchen.util.HibernateUtil;

class MyPanel extends JPanel {
	Icon cnimage = null;
	Image image = null;

	public MyPanel(Image image) {
//		cnimage = new ImageIcon(image);
		this.image = image;
	}

	private static final long serialVersionUID = 1L;

	public void paintComponent(Graphics g) {
		Toolkit tool = this.getToolkit();
		g.drawImage(image, 80, 5, 260, 200, this);
	}

}

public class FrmMenuMessage extends JDialog {

	private String filepath = null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JLabel labelName = new JLabel("≤À∆◊√Ë ˆ£∫");

	JTextArea edtDescription = new JTextArea();

	public FrmMenuMessage(JFrame f, String s, boolean b, BeanMenu menu) throws BaseException {
		super(f, s, b);
		if (menu.getUser().getUserId() != 0) {
			toolBar.setLayout(new FlowLayout(FlowLayout.CENTER));
			this.getContentPane().add(toolBar, BorderLayout.NORTH);
			Image img = null;
			if (menu.getMenuImage() != null) {
				try {
					InputStream sbs = new ByteArrayInputStream(
							menu.getMenuImage().getBytes(1, (int) menu.getMenuImage().length()));
					img = ImageIO.read(sbs);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			MyPanel mp = new MyPanel(img);

			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			BeanMenu m = session.get(BeanMenu.class, menu.getMenuId());
			m.setMenuBrowseCount(menu.getMenuBrowseCount() + 1);
			tx.commit();

			mp.setLayout(null);

			if (img == null) {
				JLabel l = new JLabel("œµÕ≥÷–‘›ŒﬁÕº∆¨£°");
				l.setBounds(80, 5, 200, 50);
				mp.add(l);
			}

			labelName.setBounds(10, 220, 70, 20);// new
			edtDescription.setBounds(80, 220, 260, 200);// new
			edtDescription.setLineWrap(true);
			edtDescription.setWrapStyleWord(true);
			edtDescription.setBorder(new LineBorder(null, 1));
			edtDescription.setEditable(false);// ‘ –Ì∏¥÷∆≤ª‘ –Ì±‡º≠
			edtDescription.setText(menu.getMenuDescription());
			mp.add(labelName);
			mp.add(edtDescription);
			this.getContentPane().add(mp, BorderLayout.CENTER);

			this.setSize(420, 500);
			// ∆¡ƒªæ”÷–œ‘ æ
			double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
			double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
			this.setLocation((int) (width - this.getWidth()) / 2, (int) (height - this.getHeight()) / 3);

			this.validate();
		}

	}

}
