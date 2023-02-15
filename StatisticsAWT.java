package warehouse;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class StatisticsAWT extends JFrame implements ActionListener {
	ImageIcon image;
	JPanel p1, p2, p3, p4, p5, p6, p7;
	JLabel statisticsTitle, history, invenStatus, categoryName, chart;
	JButton p1_btn1, p1_btn2, p1_btn3, p1_btn4;
	JButton p2_btn1, p2_btn2, p2_btn3, p2_btn4;
	JButton p4_btn1, p4_btn2;
	JButton p6_btn1;
	JComboBox comboBox;
	JTextField textField;
	JTable table;
	JScrollPane scrollpane;
	Vector<Object> list;
	int menuCheck = 0; // 상황에 따른 메뉴 카테고리 버튼 패널 변환 확인
	int reciept_releaseCheck = 0; // 상항에 따른 입고 출고 버튼 패널 변환 확인

	// 통계 프레임
	public StatisticsAWT() {
		getContentPane().setLayout(null);
		setSize(700, 500);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		menuPanel();
	}

	// 왼쪽 메뉴 패널
	public void menuPanel() {
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(44, 112, 147));
		p1.setBounds(0, 0, 133, 461);
		// 통계 타이틀
		image = new ImageIcon(".\\images\\statisticsTitle.png");
		statisticsTitle = new JLabel(image);
		statisticsTitle.setBounds(10, 30, 119, 48);
		// 입출고 내역 버튼
		p1_btn1 = new JButton();
		p1_btn1.setIcon(new ImageIcon(".\\images\\statusBtn1_1.png"));
		p1_btn1.setRolloverIcon(new ImageIcon(".\\images\\statusBtn1_2.png"));
		p1_btn1.setBounds(20, 150, 110, 30);
		p1_btn1.setBorderPainted(false);// 버튼 테두리 투명색
		p1_btn1.setContentAreaFilled(false);// 버튼 투명색
		p1_btn1.setFocusable(false);
		p1_btn1.addActionListener(this);
		// 재고 현황 버튼
		p1_btn2 = new JButton();
		p1_btn2.setIcon(new ImageIcon(".\\images\\statusBtn2_1.png"));
		p1_btn2.setRolloverIcon(new ImageIcon(".\\images\\statusBtn2_2.png"));
		p1_btn2.setBounds(18, 200, 110, 30);
		p1_btn2.setBorderPainted(false);
		p1_btn2.setContentAreaFilled(false);
		p1_btn2.setFocusable(false);
		p1_btn2.addActionListener(this);
		// 기간별 차트 버튼
		p1_btn3 = new JButton();
		p1_btn3.setIcon(new ImageIcon(".\\images\\statusBtn3_1.png"));
		p1_btn3.setRolloverIcon(new ImageIcon(".\\images\\statusBtn3_2.png"));
		p1_btn3.setBounds(18, 250, 110, 30);
		p1_btn3.setBorderPainted(false);
		p1_btn3.setContentAreaFilled(false);
		p1_btn3.setFocusable(false);
		p1_btn3.addActionListener(this);
		// Home 버튼
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

	// 입출고 내역 패널
	public void historyPanel() {
		p2 = new JPanel();
		p2.setLayout(null);
		p2.setBackground(new Color(0, 32, 96));
		p2.setBounds(132, 0, 552, 461);
		// 입출고 내역 타이틀
		image = new ImageIcon(".\\images\\history.png");
		history = new JLabel(image);
		history.setBounds(10, 30, 290, 48);

		list = new Vector<>();
		list.add("제품코드");
		list.add("고객코드");
		comboBox = new JComboBox(list);
		comboBox.setBounds(25, 95, 90, 32);
		comboBox.addActionListener(this);
		
		textField = new JTextField();
		textField.setBounds(119, 95, 180, 32);
		textField.setColumns(10);
		// 조회하기 버튼
		p2_btn1 = new JButton();
		p2_btn1.setIcon(new ImageIcon(".\\images\\check.png"));
		p2_btn1.setRolloverIcon(new ImageIcon(".\\images\\check_1.png"));
		p2_btn1.setBounds(305, 94, 115, 38);
		p2_btn1.setBorderPainted(false);
		p2_btn1.setContentAreaFilled(false);
		p2_btn1.setFocusable(false);
		p2_btn1.addActionListener(this);
		// 전체조회 버튼
		p2_btn2 = new JButton();
		p2_btn2.setIcon(new ImageIcon(".\\images\\checkAll.png"));
		p2_btn2.setRolloverIcon(new ImageIcon(".\\images\\checkAll_1.png"));
		p2_btn2.setBounds(420, 94, 115, 38);
		p2_btn2.setBorderPainted(false);
		p2_btn2.setContentAreaFilled(false);
		p2_btn2.setFocusable(false);
		p2_btn2.addActionListener(this);
		// 입고내역 버튼
		p2_btn3 = new JButton();
		p2_btn3.setIcon(new ImageIcon(".\\images\\receiptHistoryBtn1.png"));
		p2_btn3.setRolloverIcon(new ImageIcon(".\\images\\receiptHistoryBtn2.png"));
		p2_btn3.setBounds(300, 415, 115, 38);
		p2_btn3.setBorderPainted(false);
		p2_btn3.setContentAreaFilled(false);
		p2_btn3.setFocusable(false);
		p2_btn3.addActionListener(this);
		// 출고내역 버튼
		p2_btn4 = new JButton();
		p2_btn4.setIcon(new ImageIcon(".\\images\\releaseHistoryBtn1.png"));
		p2_btn4.setRolloverIcon(new ImageIcon(".\\images\\releaseHistoryBtn2.png"));
		p2_btn4.setBounds(420, 415, 115, 38);
		p2_btn4.setBorderPainted(false);
		p2_btn4.setContentAreaFilled(false);
		p2_btn4.setFocusable(false);
		p2_btn4.addActionListener(this);

		p2.add(history);
		p2.add(comboBox);
		p2.add(textField);
		p2.add(p2_btn1);
		p2.add(p2_btn2);
		p2.add(p2_btn3);
		p2.add(p2_btn4);
		// 입출고내역 테이블 클래스 호출
		new HistoryMgr(this, reciept_releaseCheck);

		add(p2);
		setVisible(true);
	}

	// 재고 현황 패널
	public void inventoryStatusPanel() {
		p4 = new JPanel();
		p4.setLayout(null);
		p4.setBackground(new Color(0, 32, 96));
		p4.setBounds(132, 0, 552, 461);
		// 현 재고 현황 타이틀
		image = new ImageIcon(".\\images\\invenStatus.png");
		invenStatus = new JLabel(image);
		invenStatus.setBounds(10, 30, 290, 48);
		// 카테고리 제품명 라벨
		image = new ImageIcon(".\\images\\category&name.png");
		categoryName = new JLabel(image);
		categoryName.setBounds(184, 85, 210, 48);

		comboBox = new JComboBox();
		comboBox.setBounds(195, 123, 65, 27);

		textField = new JTextField();
		textField.setBounds(260, 123, 130, 27);
		textField.setColumns(10);
		// 조회하기 버튼
		p4_btn1 = new JButton();
		p4_btn1.setIcon(new ImageIcon(".\\images\\check2.png"));
		p4_btn1.setRolloverIcon(new ImageIcon(".\\images\\check2_1.png"));
		p4_btn1.setBounds(370, 94, 115, 62);
		p4_btn1.setBorderPainted(false);
		p4_btn1.setContentAreaFilled(false);
		p4_btn1.setFocusable(false);
		p4_btn1.addActionListener(this);
		// 전체조회 버튼
		p4_btn2 = new JButton();
		p4_btn2.setIcon(new ImageIcon(".\\images\\checkAll2.png"));
		p4_btn2.setRolloverIcon(new ImageIcon(".\\images\\checkAll2_1.png"));
		p4_btn2.setBounds(440, 94, 115, 62);
		p4_btn2.setBorderPainted(false);
		p4_btn2.setContentAreaFilled(false);
		p4_btn2.setFocusable(false);
		p4_btn2.addActionListener(this);

		p4.add(invenStatus);
		p4.add(categoryName);
		p4.add(comboBox);
		p4.add(textField);
		p4.add(p4_btn1);
		p4.add(p4_btn2);

		// 재고현황 테이블 클래스 호출
		new InventoryStatusMgr(this);
		add(p4);
	}

	// 기간별 차트 패널
	public void chartPanel() {
		p5 = new JPanel();
		p5.setLayout(null);
		p5.setBackground(new Color(0, 32, 96));
		p5.setBounds(132, 0, 552, 461);
		// 현 재고 현황 타이틀
		image = new ImageIcon(".\\images\\chart.png");
		chart = new JLabel(image);
		chart.setBounds(10, 30, 290, 48);

		comboBox = new JComboBox();
		comboBox.setBounds(327, 95, 90, 32);

		// 조회하기 버튼
		p6_btn1 = new JButton();
		p6_btn1.setIcon(new ImageIcon(".\\images\\check.png"));
		p6_btn1.setRolloverIcon(new ImageIcon(".\\images\\check_1.png"));
		p6_btn1.setBounds(420, 94, 115, 38);
		p6_btn1.setBorderPainted(false);
		p6_btn1.setContentAreaFilled(false);
		p6_btn1.setFocusable(false);
		p6_btn1.addActionListener(this);

		p6 = new JPanel();
		p6.setLayout(null);
		p6.setBounds(25, 140, 505, 300);
		p6.setBackground(Color.white);

		p5.add(chart);
		p5.add(comboBox);
		p5.add(p6_btn1);
		p5.add(p6);
		add(p5);
	}

	// 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == p1_btn1) { // 입출고 내역 버튼
			if (menuCheck == 0) {
				menuCheck = 0;
				p2.setVisible(false);
				historyPanel();
				revalidate();
				repaint();
			} else if (menuCheck == 1) {
				menuCheck = 0;
				p4.setVisible(false);
				historyPanel();
				revalidate();
				repaint();
			} else if (menuCheck == 2) {
				menuCheck = 0;
				p5.setVisible(false);
				historyPanel();
				revalidate();
				repaint();
			}
		} else if (obj == p1_btn2) { // 재고 현황 버튼
			if (menuCheck == 0) {
				menuCheck = 1;
				p2.setVisible(false);
				inventoryStatusPanel();
				revalidate();
				repaint();
			} else if (menuCheck == 1) {
				menuCheck = 1;
				p4.setVisible(false);
				inventoryStatusPanel();
				revalidate();
				repaint();
			} else if (menuCheck == 2) {
				menuCheck = 1;
				p5.setVisible(false);
				inventoryStatusPanel();
				revalidate();
				repaint();
			}

		} else if (obj == p1_btn3) { // 기간별 차트 버튼
			if (menuCheck == 0) {
				menuCheck = 2;
				p2.setVisible(false);
				chartPanel();
				revalidate();
				repaint();
			} else if (menuCheck == 1) {
				menuCheck = 2;
				p4.setVisible(false);
				chartPanel();
				revalidate();
				repaint();
			} else if (menuCheck == 2) {
				menuCheck = 2;
				p5.setVisible(false);
				chartPanel();
				revalidate();
				repaint();
			}

		} else if (obj == p1_btn4) { // Home 버튼

		} else if (obj == p2_btn3) { // 입고내역 버튼
			reciept_releaseCheck = 0;
			p2.setVisible(false);
			historyPanel();
			revalidate();
			repaint();
		} else if (obj == p2_btn4) { // 출고내역 버튼
			reciept_releaseCheck = 1;
			p2.setVisible(false);
			historyPanel();
			revalidate();
			repaint();
		}
	}

	public static void main(String[] args) {
		StatisticsAWT h = new StatisticsAWT();
	}
}
