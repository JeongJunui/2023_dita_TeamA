package warehouse;

import java.awt.Color;
<<<<<<< HEAD
=======
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
>>>>>>> 9b35a8641dac3ba1549629e039cfcdaea9308454
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
<<<<<<< HEAD

public class MainAWT extends JFrame implements ActionListener {
	ImageIcon image;
	JLabel mainTitle;
	JButton mainReceivingBtn, mainReleaseBtn, mainStatisticsBtn;

	public MainAWT() {
	
		setTitle("창고 관리 프로그램");
		setSize(700, 500);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0, 32, 96));

		//// 로고 이미지
		image = new ImageIcon(".\\images\\mainTitle.png");
		mainTitle = new JLabel(image);
		mainTitle.setBounds(0, 15, 310, 90);
		//// 입고
		mainReceivingBtn = new JButton(new ImageIcon(".\\images\\mainReceivingBtn.png"));
		mainReceivingBtn.setRolloverIcon(new ImageIcon(".\\images\\mainReceivingBtn2.png"));
		mainReceivingBtn.setBounds(100, 170, 188, 188);
		mainReceivingBtn.setFocusPainted(false);
		mainReceivingBtn.setBorderPainted(false);
		mainReceivingBtn.addActionListener(this);
		//// 출고
		mainReleaseBtn = new JButton(new ImageIcon(".\\images\\mainReleaseBtn.png"));
		mainReleaseBtn.setRolloverIcon(new ImageIcon(".\\images\\mainReleaseBtn2.png"));
		mainReleaseBtn.setBounds(400, 170, 188, 188);
		mainReleaseBtn.setBorderPainted(false);
		mainReleaseBtn.setFocusPainted(false);
		mainReleaseBtn.addActionListener(this);
		//// 통계
		mainStatisticsBtn = new JButton(new ImageIcon(".\\images\\mainStatisticsBtn.png"));
		mainStatisticsBtn.setRolloverIcon(new ImageIcon(".\\images\\mainStatisticsBtn2.png"));
		mainStatisticsBtn.setBounds(620, 390, 50, 50);
		mainStatisticsBtn.setBorderPainted(false);
		mainStatisticsBtn.setContentAreaFilled(false);
		mainStatisticsBtn.setFocusable(false);
		mainStatisticsBtn.addActionListener(this);

		add(mainReceivingBtn);
		add(mainReleaseBtn);
		add(mainStatisticsBtn);
		add(mainTitle);
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == mainReceivingBtn) { // 입고 버튼
		} else if (obj == mainReleaseBtn) { // 출고 버튼

		} else if (obj == mainStatisticsBtn) { // 통계 버튼
			new StatisticsAWT();
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		MainAWT main = new MainAWT();
=======
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
>>>>>>> 9b35a8641dac3ba1549629e039cfcdaea9308454
	}
}