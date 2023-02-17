package warehouse;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MainAWT extends JFrame implements ActionListener {
	ImageIcon image;
	JLabel mainTitle;
	JButton mainReceivingBtn, mainReleaseBtn, mainStatisticsBtn;

	public MainAWT() {
	
		setTitle("â�� ���� ���α׷�");
		setSize(700, 500);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);// â�� ��� ������
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0, 32, 96));

		//// �ΰ� �̹���
		image = new ImageIcon(".\\images\\mainTitle.png");
		mainTitle = new JLabel(image);
		mainTitle.setBounds(0, 15, 310, 90);
		//// �԰�
		mainReceivingBtn = new JButton(new ImageIcon(".\\images\\mainReceivingBtn.png"));
		mainReceivingBtn.setRolloverIcon(new ImageIcon(".\\images\\mainReceivingBtn2.png"));
		mainReceivingBtn.setBounds(100, 170, 188, 188);
		mainReceivingBtn.setFocusPainted(false);
		mainReceivingBtn.setBorderPainted(false);
		mainReceivingBtn.addActionListener(this);
		//// ���
		mainReleaseBtn = new JButton(new ImageIcon(".\\images\\mainReleaseBtn.png"));
		mainReleaseBtn.setRolloverIcon(new ImageIcon(".\\images\\mainReleaseBtn2.png"));
		mainReleaseBtn.setBounds(400, 170, 188, 188);
		mainReleaseBtn.setBorderPainted(false);
		mainReleaseBtn.setFocusPainted(false);
		mainReleaseBtn.addActionListener(this);
		//// ���
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

		if (obj == mainReceivingBtn) { // �԰� ��ư
		} else if (obj == mainReleaseBtn) { // ��� ��ư

		} else if (obj == mainStatisticsBtn) { // ��� ��ư
			new StatisticsAWT();
			setVisible(false);
		}
	}

	public static void main(String[] args) {
		MainAWT main = new MainAWT();
	}
}