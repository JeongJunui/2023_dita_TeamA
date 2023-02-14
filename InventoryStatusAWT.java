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

public class InventoryStatusAWT implements ActionListener {
	ImageIcon image;
	JPanel p1, p2;
	JLabel invenStatus, categoryName;
	JButton check2Btn, checkAll2Btn;
	JComboBox comboBox;
	JTextField textField;
	JTable table;
	JScrollPane scrollpane;
	HistoryAWT historyAWT;

	public InventoryStatusAWT(HistoryAWT historyAWT) {
		this.historyAWT = historyAWT;
		InventoryStatusPanel();
	}

	public void InventoryStatusPanel() {
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(0, 32, 96));
		p1.setBounds(132, 0, 552, 461);
		// 현 재고 현황 타이틀
		image = new ImageIcon(".\\images\\invenStatus.png");
		invenStatus = new JLabel(image);
		invenStatus.setBounds(10, 30, 290, 48);
		// 카테고리 제품명 라벨
		image = new ImageIcon(".\\images\\category&name.png");
		categoryName = new JLabel(image);
		categoryName.setBounds(130, 85, 210, 48);

		comboBox = new JComboBox();
		comboBox.setBounds(141, 123, 65, 27);

		textField = new JTextField();
		textField.setBounds(206, 123, 130, 27);
		textField.setColumns(10);
		// 조회하기 버튼
		check2Btn = new JButton();
		check2Btn.setIcon(new ImageIcon(".\\images\\check2.png"));
		check2Btn.setRolloverIcon(new ImageIcon(".\\images\\check2_1.png"));
		check2Btn.setBounds(325, 94, 115, 62);
		check2Btn.setBorderPainted(false);
		check2Btn.setContentAreaFilled(false);
		check2Btn.setFocusable(false);
		check2Btn.addActionListener(this);
		// 전체조회 버튼
		checkAll2Btn = new JButton();
		checkAll2Btn.setIcon(new ImageIcon(".\\images\\checkAll2.png"));
		checkAll2Btn.setRolloverIcon(new ImageIcon(".\\images\\checkAll2_1.png"));
		checkAll2Btn.setBounds(405, 94, 115, 62);
		checkAll2Btn.setBorderPainted(false);
		checkAll2Btn.setContentAreaFilled(false);
		checkAll2Btn.setFocusable(false);
		checkAll2Btn.addActionListener(this);

		p1.add(invenStatus);
		p1.add(categoryName);
		p1.add(comboBox);
		p1.add(textField);
		p1.add(check2Btn);
		p1.add(checkAll2Btn);
		tablePanel();
		// historyAWT.add(p1);

	}

	// 테이블 패널
	public void tablePanel() {
		p2 = new JPanel();
		p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
		p2.setBounds(25, 160, 505, 280);

		String header[] = { "카테고리", "제품코드", "제품명", "재고수량", "고객번호", "비고" };
		String contents[][] = { { "옷", "A001", "좋은옷", "5", "123", "" } };
		table = new JTable(contents, header);
		scrollpane = new JScrollPane(table);
		p2.add(scrollpane);
		p1.add(p2);

		historyAWT.add(p1);
		//historyAWT.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
