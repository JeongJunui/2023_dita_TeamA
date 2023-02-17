package warehouse;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class MainAWT extends JFrame{
	Image img;

	public MainAWT() {
		setTitle("");
		setSize(700,500);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0,51,102));
		setVisible(true);
		validate();

		//// 로고 이미지
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/mainTitle.png");

		//// 입고
		JButton mainReceivingBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/mainReceivingBtn.png"));
		mainReceivingBtn.setBounds(100, 170,188,188);
		mainReceivingBtn.setBorderPainted(false);
		mainReceivingBtn.setFocusPainted(false);
		add(mainReceivingBtn);

		//// 출고
		JButton mainReleaseBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/mainReleaseBtn.png"));
		mainReleaseBtn.setBounds(400, 170,188,188);
		mainReleaseBtn.setBorderPainted(false);
		mainReleaseBtn.setFocusPainted(false);
		add(mainReleaseBtn);

		//// 통계
		JButton mainStatisticsBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/mainStatisticsBtn.png"));
		mainStatisticsBtn.setBounds(620, 400,50,50);
		mainStatisticsBtn.setBorderPainted(false);
		mainStatisticsBtn.setContentAreaFilled(false);
		mainStatisticsBtn.setFocusPainted(false);
		add(mainStatisticsBtn);
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 9,50,this);
	}
}