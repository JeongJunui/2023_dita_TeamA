package warehouse;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.PixelInterleavedSampleModel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class EventTest extends WindowAdapter implements ActionListener {
	JFrame frm = new JFrame("메모장");
	JPanel p = new JPanel();
	JToggleButton boldBtn = new JToggleButton("가");
	JToggleButton italicBtn = new JToggleButton("가");
	JToggleButton plainBtn = new JToggleButton("가");
	JButton foreColor = new JButton("글자색");
	JTextArea ta = new JTextArea();

	public EventTest() {
		p.add(boldBtn);
		p.add(italicBtn);
		p.add(plainBtn);
		p.add(foreColor);

		frm.add(p, "North");
		frm.add(ta, "Center");
		frm.setSize(500, 500);
		frm.setVisible(true);
		// frm.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// JFrame 자동 종료 해제

		frm.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		MyEventProdess event = new MyEventProdess(ta, boldBtn, italicBtn, plainBtn);
		boldBtn.addActionListener(event);
		italicBtn.addActionListener(event);
		plainBtn.addActionListener(event);
		foreColor.addActionListener(this);
		frm.addWindowListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		String str = ae.getActionCommand();
		if (str.equals("글자색")) {
			// 컬러선택탐색시
			JColorChooser cc = new JColorChooser();
			// 색상선택기 실행 (부모객체, 제목, 초기색상)
			Color color = cc.showDialog(frm, "글자색", Color.red);
			ta.setForeground(color);
		}
	}

	// 윈도우 이벤트 오버라이딩

	// WindowAdapter를 상속하여 필요한 메소드만 재 오버라이딩하여 사용한다.

	public void windowClosing(WindowEvent we) {
		int showType = JOptionPane.showConfirmDialog(frm, "종료하시겠습니까?", "확인", JOptionPane.OK_CANCEL_OPTION);
		if (showType == JOptionPane.OK_OPTION) {
			frm.dispose();
			System.exit(0);
		} else if (showType == JOptionPane.CANCEL_OPTION) {
		}
	}

	public static void main(String[] args) {
		new EventTest();
	}
}

class MyEventProdess implements ActionListener {
	JTextArea ta;
	JToggleButton boldBtn, italicBtn, plainBtn;

	int bold = 0, italic = 0; // Plain값은 생략

	Font fnt = new Font("Serif", Font.PLAIN, 20);

	public MyEventProdess() {

	}

	public MyEventProdess(JTextArea ta, JToggleButton boldBtn, JToggleButton italicBtn, JToggleButton plainBtn) {
		this.ta = ta;
		this.boldBtn = boldBtn;
		this.italicBtn = italicBtn;
		this.plainBtn = plainBtn;
	}

	// 오버라이딩 = public class에서 implements 하였다면 이곳에 오버라이딩을 해야 한다.

	public void actionPerformed(ActionEvent ae) {
		// 이벤트

		// Font("Serif", Font.BOLD, 20);

		// FontBOLD=1, Font.ITALIC=2, Font.PLAIN=0

		Object obj = ae.getSource();
		if (obj == boldBtn) { // 진하게
			if (boldBtn.isSelected()) { // 선택
				bold = 1;
			} else { // 비선택
				bold = 0;
			}
		} else if (obj == italicBtn) { // 이텔릭
			if (italicBtn.isSelected()) {
				italic = 2;
			} else {
				italic = 0;
			}
		} else if (obj == plainBtn) { // 보통
			bold = 0;
			italic = 0;
			boldBtn.setSelected(false);
			italicBtn.setSelected(false);
		}

		////////

		fnt = new Font("Serif", bold + italic, 20);
		ta.setFont(fnt);
	}
}