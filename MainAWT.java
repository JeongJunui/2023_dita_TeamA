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
		setSize(700,500);//�������� ũ��
		setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);//â�� ��� ������
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(0,51,102));
		setVisible(true);
		validate();

		//// �ΰ� �̹���
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/mainTitle.png");

		//// �԰�
		JButton mainReceivingBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/mainReceivingBtn.png"));
		mainReceivingBtn.setBounds(100, 170,188,188);
		mainReceivingBtn.setBorderPainted(false);
		mainReceivingBtn.setFocusPainted(false);
		add(mainReceivingBtn);

		//// ���
		JButton mainReleaseBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/mainReleaseBtn.png"));
		mainReleaseBtn.setBounds(400, 170,188,188);
		mainReleaseBtn.setBorderPainted(false);
		mainReleaseBtn.setFocusPainted(false);
		add(mainReleaseBtn);

		//// ���
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