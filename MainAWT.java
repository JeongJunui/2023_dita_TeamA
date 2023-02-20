package warehouse;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class MainAWT extends JFrame implements ActionListener {
	ImageIcon image;
	JLabel mainTitle;
	JPanel mainPanel;
	JButton mainReceivingBtn, mainReleaseBtn, mainStatisticsBtn;

	public MainAWT() {
		setTitle("창고 관리 프로그램");
		setSize(700, 500);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);
		mainPanel();
	}
// ----------------------------------------메인 패널----------------------------------------
	public void mainPanel() {
		mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(0, 32, 96));
		mainPanel.setBounds(0,0,684,461);
		// 로고 이미지
		image = new ImageIcon(".\\images\\mainTitle.png");
		mainTitle = new JLabel(image);
		mainTitle.setBounds(0, 15, 310, 90);
		// 입고
		mainReceivingBtn = new JButton(new ImageIcon(".\\images\\mainReceivingBtn.png"));
		mainReceivingBtn.setRolloverIcon(new ImageIcon(".\\images\\mainReceivingBtn2.png"));
		mainReceivingBtn.setBounds(100, 170, 188, 188);
		mainReceivingBtn.setFocusPainted(false);
		mainReceivingBtn.setBorderPainted(false);
		mainReceivingBtn.setContentAreaFilled(false);
		mainReceivingBtn.addActionListener(this);
		// 출고
		mainReleaseBtn = new JButton(new ImageIcon(".\\images\\mainReleaseBtn.png"));
		mainReleaseBtn.setRolloverIcon(new ImageIcon(".\\images\\mainReleaseBtn2.png"));
		mainReleaseBtn.setBounds(400, 170, 188, 188);
		mainReleaseBtn.setBorderPainted(false);
		mainReleaseBtn.setFocusPainted(false);
		mainReleaseBtn.setContentAreaFilled(false);
		mainReleaseBtn.addActionListener(this);
		// 통계
		mainStatisticsBtn = new JButton(new ImageIcon(".\\images\\mainStatisticsBtn.png"));
		mainStatisticsBtn.setRolloverIcon(new ImageIcon(".\\images\\mainStatisticsBtn2.png"));
		mainStatisticsBtn.setBounds(620, 390, 50, 50);
		mainStatisticsBtn.setBorderPainted(false);
		mainStatisticsBtn.setContentAreaFilled(false);
		mainStatisticsBtn.setFocusable(false);
		mainStatisticsBtn.setContentAreaFilled(false);
		mainStatisticsBtn.addActionListener(this);
		

		mainPanel.add(mainReceivingBtn);
		mainPanel.add(mainReleaseBtn);
		mainPanel.add(mainStatisticsBtn);
		mainPanel.add(mainTitle);
		add(mainPanel);
		setVisible(true);
	}
// ----------------------------------------메인 패널 버튼 이벤트----------------------------------------
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == mainReceivingBtn) { // 입고 버튼
			//TODO
		} else if (obj == mainReleaseBtn) { // 출고 버튼
			//TODO
		} else if (obj == mainStatisticsBtn) { // 통계 버튼
			//ainPanel.setVisible(false);
			mainPanel.removeAll();
			mainPanel.revalidate();
			mainPanel.repaint();
			new StatisticsAWT(this);
		}
	}
	public static void main(String[] args) {
		MainAWT main = new MainAWT();
	}
}