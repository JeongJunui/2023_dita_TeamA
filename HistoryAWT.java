package warehouse;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class HistoryAWT extends JFrame implements ActionListener {
	ImageIcon image;
	JPanel p1, p2, p3,p4;
	JLabel statisticsTitle, history;
	JButton p1_btn1, p1_btn2, p1_btn3, p1_btn4;
	JButton p2_btn1, p2_btn2;
	JComboBox comboBox;
	JTextField textField;
	JTable table;
	JScrollPane scrollpane;
	InventoryStatusAWT inventoryStatusAWT;
	int num = 0;
	public HistoryAWT() {
		getContentPane().setLayout(null);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuPanel();
		
	}

	// 왼쪽 패널
	public void menuPanel() {
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(44, 112, 147));
		p1.setBounds(0, 0, 133, 461);
		// 통계 타이틀
		image = new ImageIcon(".\\images\\statisticsTitle.png");
		statisticsTitle = new JLabel(image);
		statisticsTitle.setBounds(10, 30, 119, 48);
		// p1패널 버튼
		p1_btn1 = new JButton();
		p1_btn1.setIcon(new ImageIcon(".\\images\\statusBtn1_1.png"));
		p1_btn1.setRolloverIcon(new ImageIcon(".\\images\\statusBtn1_2.png"));
		p1_btn1.setBounds(20, 150, 110, 30);
		p1_btn1.setBorderPainted(false);// 버튼 테두리 투명색
		p1_btn1.setContentAreaFilled(false);// 버튼 투명색
		p1_btn1.setFocusable(false);
		p1_btn1.addActionListener(this);

		p1_btn2 = new JButton();
		p1_btn2.setIcon(new ImageIcon(".\\images\\statusBtn2_1.png"));
		p1_btn2.setRolloverIcon(new ImageIcon(".\\images\\statusBtn2_2.png"));
		p1_btn2.setBounds(18, 200, 110, 30);
		p1_btn2.setBorderPainted(false);
		p1_btn2.setContentAreaFilled(false);
		p1_btn2.setFocusable(false);
		p1_btn2.addActionListener(this);

		p1_btn3 = new JButton();
		p1_btn3.setIcon(new ImageIcon(".\\images\\statusBtn3_1.png"));
		p1_btn3.setRolloverIcon(new ImageIcon(".\\images\\statusBtn3_2.png"));
		p1_btn3.setBounds(18, 250, 110, 30);
		p1_btn3.setBorderPainted(false);
		p1_btn3.setContentAreaFilled(false);
		p1_btn3.setFocusable(false);
		p1_btn3.addActionListener(this);

		p1_btn4 = new JButton();
		p1_btn4.setIcon(new ImageIcon(".\\images\\homeBtn.png"));
		p1_btn4.setRolloverIcon(new ImageIcon(".\\images\\homeBtn2.png"));
		p1_btn4.setBounds(6, 340, 125, 125);
		p1_btn4.setBorderPainted(false);
		p1_btn4.setContentAreaFilled(false);
		p1_btn4.setFocusable(false);
		p1_btn4.addActionListener(this);

		p1.add(statisticsTitle);
		p1.add(p1_btn1);
		p1.add(p1_btn2);
		p1.add(p1_btn3);
		p1.add(p1_btn4);
		add(p1);

		historyPanel();
	}

	// 오른쪽 패널
	public void historyPanel() {
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(0, 32, 96));
		p2.setBounds(132, 0, 552, 461);
		// 입출고 내역 타이틀
		image = new ImageIcon(".\\images\\history.png");
		history = new JLabel(image);
		history.setBounds(10, 30, 290, 48);

		comboBox = new JComboBox();
		comboBox.setBounds(25, 95, 90, 32);

		textField = new JTextField();
		textField.setBounds(119, 95, 180, 32);
		textField.setColumns(10);

		p2_btn1 = new JButton();
		p2_btn1.setIcon(new ImageIcon(".\\images\\check.png"));
		p2_btn1.setRolloverIcon(new ImageIcon(".\\images\\check_1.png"));
		p2_btn1.setBounds(305, 94, 115, 38);
		p2_btn1.setBorderPainted(false);
		p2_btn1.setContentAreaFilled(false);
		p2_btn1.setFocusable(false);
		p2_btn1.addActionListener(this);

		p2_btn2 = new JButton();
		p2_btn2.setIcon(new ImageIcon(".\\images\\checkAll.png"));
		p2_btn2.setRolloverIcon(new ImageIcon(".\\images\\checkAll_1.png"));
		p2_btn2.setBounds(420, 94, 115, 38);
		p2_btn2.setBorderPainted(false);
		p2_btn2.setContentAreaFilled(false);
		p2_btn2.setFocusable(false);
		p2_btn2.addActionListener(this);

		p2.add(history);
		p2.add(comboBox);
		p2.add(textField);
		p2.add(p2_btn1);
		p2.add(p2_btn2);

		tablePanel();
	}

	// 테이블 패널
	public void tablePanel() {
		p3 = new JPanel();
		p3.setLayout(new BoxLayout(p3, BoxLayout.X_AXIS));
		p3.setBounds(25, 140, 505, 300);

		String header[] = { "카테고리", "제품코드", "제품명", "재고수량", "고객번호", "비고" };
		String contents[][] = { { "옷", "A001", "좋은옷", "5", "123", "" } };
		table = new JTable(contents, header);
		scrollpane = new JScrollPane(table);
		p3.add(scrollpane);
		p2.add(p3);
		add(p2);

		setVisible(true);
	}

	
	// 버튼 이벤트
	@Override

	public void actionPerformed(ActionEvent e) {
	
		Object obj = e.getSource();
		
		if (obj == p1_btn1) { // 입출고 내역 버튼
			//new HistoryAWT();
			//dispose();
			p2.setVisible(false);
			p2.setVisible(true);
		

		} else if (obj == p1_btn2) { // 재고 현황 버튼
			
			p2.setVisible(false);
			new InventoryStatusAWT(this);
		} else if (obj == p1_btn3) { // 기간별 차트 버튼

		} else if (obj == p1_btn4) { // Home 버튼

		}

	}

	public static void main(String[] args) {
		HistoryAWT h = new HistoryAWT();
	}
}
