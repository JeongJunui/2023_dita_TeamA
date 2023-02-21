package warehouse;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class MailAWT extends JFrame implements ActionListener {
	private JPanel p1;
	private JLabel mailTitle, receiver, sendTitle, mailAttach, statisticsFile, sendAttach;
	private JLabel statistics1, statistics2, statistics3;
	private JCheckBox checkBox1, checkBox2, checkBox3;
	private JLabel mailBar, mailBar2;
	private JButton myPCBtn, closeBtn, sendBtn;
	private JTextField recieveTextField, titleTextField;
	private JTextArea textArea, attachTextArea;
	private JScrollPane scrollPane;
	String fileName1_1 = "";

	public MailAWT() {
		setTitle("메일");
		setSize(450, 650);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);

		MailPanel();
		setVisible(true);
	}

	public void MailPanel() {
		p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.setLayout(null);
		p1.setBounds(0, 0, 434, 611);
		// 타이틀 라벨
		mailTitle = new JLabel();
		mailTitle.setIcon(new ImageIcon(".\\images\\mailTitle.png"));
		mailTitle.setBounds(20, 10, 94, 45);
		// 받는사람 라벨
		receiver = new JLabel("받는사람");
		receiver.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		receiver.setForeground(new Color(118, 118, 120));
		receiver.setBounds(20, 65, 60, 28);
		// 받는사람 입력 필드
		recieveTextField = new JTextField();
		recieveTextField.setBounds(95, 70, 300, 22);
		recieveTextField.setColumns(10);
		recieveTextField.setBorder(null);
		recieveTextField.setFocusable(true);
		mailBar = new JLabel();
		mailBar.setIcon(new ImageIcon(".\\images\\mailBar.png"));
		mailBar.setBounds(90, 78, 311, 34);
		// 제목 라벨
		sendTitle = new JLabel("제목");
		sendTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		sendTitle.setForeground(new Color(118, 118, 120));
		sendTitle.setBounds(20, 110, 60, 28);
		// 제목 입력 필드
		titleTextField = new JTextField();
		titleTextField.setBounds(95, 112, 300, 22);
		titleTextField.setColumns(10);
		titleTextField.setBorder(null);
		mailBar2 = new JLabel();
		mailBar2.setIcon(new ImageIcon(".\\images\\mailBar.png"));
		mailBar2.setBounds(90, 120, 311, 34);
		// 파일첨부 라벨
		mailAttach = new JLabel("파일첨부");
		mailAttach.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mailAttach.setForeground(new Color(118, 118, 120));
		mailAttach.setBounds(20, 155, 60, 28);
		// 내 pc 파일첨부 버튼
		myPCBtn = new RoundedButton("내 PC");
		myPCBtn.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		myPCBtn.setBounds(90, 157, 50, 25);
		myPCBtn.addActionListener(this);
		// 파일첨부 TextArea
		attachTextArea = new JTextArea();
		attachTextArea.setBounds(95, 254, 300, 25);
		attachTextArea.setColumns(10);
		attachTextArea.setEditable(false);
		// 파일첨부 박스 이미지 라벨
		sendAttach = new JLabel();
		sendAttach.setIcon(new ImageIcon(".\\images\\mailAttach.png"));
		sendAttach.setBounds(90, 220, 340, 70);
		// 통계 파일 라벨
		statisticsFile = new JLabel("통계파일");
		statisticsFile.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		statisticsFile.setForeground(new Color(118, 118, 120));
		statisticsFile.setBounds(20, 188, 60, 28);

		statistics1 = new JLabel("입고내역");
		statistics1.setFont(new Font("", Font.PLAIN, 12));
		statistics1.setForeground(new Color(118, 118, 120));
		statistics1.setBounds(90, 190, 60, 28);

		checkBox1 = new JCheckBox();
		checkBox1.setBounds(150, 193, 20, 20);

		statistics2 = new JLabel("출고내역");
		statistics2.setFont(new Font("", Font.PLAIN, 12));
		statistics2.setForeground(new Color(118, 118, 120));
		statistics2.setBounds(195, 190, 60, 28);

		checkBox2 = new JCheckBox();
		checkBox2.setBounds(255, 193, 20, 20);

		statistics3 = new JLabel("재고내역");
		statistics3.setFont(new Font("", Font.PLAIN, 12));
		statistics3.setForeground(new Color(118, 118, 120));
		statistics3.setBounds(300, 190, 60, 28);

		checkBox3 = new JCheckBox();
		checkBox3.setBounds(360, 193, 20, 20);
		// 메일 입력 textArea
		textArea = new JTextArea(10, 20);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(WIDTH + -2, 290, 452, 260);
		// 파일첨부 삭제 버튼
		closeBtn = new JButton("x");
		closeBtn.setBounds(54, 189, 100, 100);
		closeBtn.setForeground(new Color(118, 118, 120));
		closeBtn.setFocusPainted(false);
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.addActionListener(this);
		// 메일 보내기 버튼
		sendBtn = new JButton(new ImageIcon(".\\images\\sendBtn.png"));
		sendBtn.setRolloverIcon(new ImageIcon(".\\images\\sendBtn2.png"));
		sendBtn.setBounds(340, 557, 83, 50);
		sendBtn.setFocusPainted(false);
		sendBtn.setBorderPainted(false);
		sendBtn.setContentAreaFilled(false);
		sendBtn.addActionListener(this);

		p1.add(mailTitle);
		p1.add(receiver);
		p1.add(recieveTextField);
		p1.add(mailBar);
		p1.add(sendTitle);
		p1.add(titleTextField);
		p1.add(mailBar2);
		p1.add(mailAttach);
		p1.add(myPCBtn);
		p1.add(statisticsFile);
		p1.add(statistics1);
		p1.add(checkBox1);
		p1.add(statistics2);
		p1.add(checkBox2);
		p1.add(statistics3);
		p1.add(checkBox3);
		p1.add(attachTextArea);
		p1.add(sendAttach);
		p1.add(scrollPane);
		p1.add(sendBtn);
		add(closeBtn);
		add(p1);
	}

	public void setBorder(Border border) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JFileChooser chooser = new JFileChooser(new File("c:\\")); // 파일 다이얼로그 생성
		if (obj == myPCBtn) {
			// FileNameExtensionFilter filter = new FileNameExtensionFilter("JPG & GIF
			// Images", // 파일 이름에 창에 출력될 문자열
			// "jpg", "gif"); // 파일 필터로 사용되는 확장자. *.jpg. *.gif만 나열됨
			// chooser.setFileFilter(filter); // 파일 다이얼로그에 파일 필터 설정

			// 파일 다이얼로그 출력
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소 버튼을 누른 경우
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
			String fileName1 = chooser.getSelectedFile().getName();
			fileName1_1 = chooser.getSelectedFile().getAbsolutePath();
			System.out.println(fileName1_1);
			attachTextArea.setText(fileName1 + " ");
		} else if (obj == sendBtn) {	
			if (!attachTextArea.getText().equals("") || !textArea.getText().equals("")) {
				String toEmail = recieveTextField.getText();
				String toTitle = titleTextField.getText();
				String setMessage = "<html><head><meta charset='ms949'/></head><body><font color = 'blue'><h2>창고프로그램<h2><br><br></font><br /><hr><img src='https://ssl.pstatic.net/tveta/libs/1313/1313466/5f5d81fa7f2d6adb4704_20201211101154645.jpg'></body></html>"
						+ textArea.getText();

				new SendMailSMTP(toEmail, toTitle, fileName1_1, setMessage);
				fileName1_1 = "";
				attachTextArea.setText("");
				textArea.setText("");
				JOptionPane.showMessageDialog(null, "메일 보내기 성공!", "성공", JOptionPane.INFORMATION_MESSAGE);
			}else {
				JOptionPane.showMessageDialog(null, "메일 보내기 실패!", "실패", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}

class RoundedButton extends JButton {
	public RoundedButton() {
		super();
		decorate();
	}

	public RoundedButton(String text) {
		super(text);
		decorate();
	}

	public RoundedButton(Action action) {
		super();
		decorate();
	}

	public RoundedButton(Icon icon) {
		super(icon);
		decorate();
	}

	public RoundedButton(String text, Icon icon) {
		super(text, icon);
		decorate();
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color c = new Color(234, 234, 234); // 배경색 결정
		Color o = new Color(0, 0, 0); // 글자색 결정
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(c.darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(c.LIGHT_GRAY);
		} else {
			graphics.setColor(c);
		}
		graphics.fillRoundRect(0, 0, width, height, 10, 10);
		FontMetrics fontMetrics = graphics.getFontMetrics();
		Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds();
		int textX = (width - stringBounds.width) / 2;
		int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent();
		graphics.setColor(o);
		graphics.setFont(getFont());
		graphics.drawString(getText(), textX, textY);
		graphics.dispose();
		super.paintComponent(g);
	}
}