package warehouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

// 패널에 이미지 넣는 클래스
class CompleteMailPanel extends JPanel {
	private Image img;

	public CompleteMailPanel(Image img) {
		this.img = img;
		setSize(434, 661);
		setLayout(null);
	}

	public void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}
}
// 메일 전송 완료 클래스
public class CompleteMail extends Thread implements ActionListener {
	private JButton writeMailBtn;
	private JLabel textLabel1, textLabel2, textLabel3, textLabel4, textLabel5;
	private JLabel threadLabel;
	private JTextArea mailAddressTA;
	boolean check = false;
	String toEmail;
	MailAWT mailAWT;
	CompleteMailPanel panel;

	public CompleteMail(MailAWT mailAWT, String toEmail) {
		this.mailAWT = mailAWT;
		this.toEmail = toEmail;
		CompleteMailPanel();
	}

	// 메일 전송 완료 패널
	public void CompleteMailPanel() {
		panel = new CompleteMailPanel(new ImageIcon(".\\images\\completeMessageFooter.png").getImage());
		// 메일 보내는 gif 아이콘 라벨
		textLabel1 = new JLabel();
		textLabel1.setIcon(new ImageIcon(".\\images\\mailSend.gif"));
		textLabel1.setBounds(120, 85, 160, 160);

		textLabel2 = new JLabel("메일을 성공적으로 보냈습니다.");
		textLabel2.setFont(new Font("맑은 고딕", Font.PLAIN, 18));
		textLabel2.setForeground(new Color(48, 48, 56));
		textLabel2.setBounds(96, 270, 270, 20);
		// 보내기 버튼
		writeMailBtn = new JButton(new ImageIcon(".\\images\\writeMailBtn.png"));
		writeMailBtn.setRolloverIcon(new ImageIcon(".\\images\\writeMailBtn2.png"));
		writeMailBtn.setBounds(171, 320, 110, 40);
		writeMailBtn.setFocusPainted(false);
		writeMailBtn.setBorderPainted(false);
		writeMailBtn.setContentAreaFilled(false);
		writeMailBtn.addActionListener(this);

		textLabel3 = new JLabel();
		textLabel3.setIcon(new ImageIcon(".\\images\\panelBarLong.png"));
		textLabel3.setBounds(10, 410, 410, 10);
		// 5초후 꺼지는 쓰레드 라벨
		threadLabel = new JLabel();
		threadLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 15));
		threadLabel.setForeground(Color.DARK_GRAY);
		threadLabel.setBounds(153, 374, 300, 20);

		textLabel4 = new JLabel("메일쓰기를 누를시 다시 메일을 쓸 수 있습니다.");
		textLabel4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		textLabel4.setForeground(Color.DARK_GRAY);
		textLabel4.setBounds(80, 460, 300, 20);
		// 받는 사람 이메일 텍스트필드
		mailAddressTA = new JTextArea(toEmail);
		mailAddressTA.setBounds(180, 544, 220, 25);
		mailAddressTA.setFont(new Font("", Font.PLAIN, 14));
		mailAddressTA.setForeground(Color.LIGHT_GRAY);
		mailAddressTA.setColumns(10);
		mailAddressTA.setEditable(false);

		textLabel5 = new JLabel("©copy자바A조 Korea Corporation All Rights Reserved.");
		textLabel5.setFont(new Font("맑은 고딕", Font.PLAIN, 11));
		textLabel5.setForeground(Color.DARK_GRAY);
		textLabel5.setBounds(80, 630, 300, 20);

		panel.add(textLabel1);
		panel.add(textLabel2);
		panel.add(textLabel3);
		panel.add(threadLabel);
		panel.add(textLabel4);
		panel.add(mailAddressTA);
		panel.add(textLabel5);
		panel.add(writeMailBtn);
		mailAWT.add(panel);

		start();
	}

	// 5초 후 프레임 꺼지는 쓰레드 메소드
	@Override
	public void run() {
		for (int i = 5; i > 0; i--) {
			try {
				threadLabel.setText(i + "초후 창이 꺼집니다");
				Thread.sleep(250);// 0.25초
				threadLabel.setText(i + "초후 창이 꺼집니다 .");
				Thread.sleep(250);// 0.25초
				threadLabel.setText(i + "초후 창이 꺼집니다 . .");
				Thread.sleep(250);// 0.25초
				threadLabel.setText(i + "초후 창이 꺼집니다 . . .");
				Thread.sleep(250);// 0.25초
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (check == false) {
			mailAWT.setVisible(false);
		}
	}

	// 메일쓰기 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		check = true;
		if (obj == writeMailBtn) {
			panel.setVisible(false);
			mailAWT.mailPanel();
			// new MailAWT();
		}
	}
}