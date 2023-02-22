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
	JFrame frm = new JFrame("�޸���");
	JPanel p = new JPanel();
	JToggleButton boldBtn = new JToggleButton("��");
	JToggleButton italicBtn = new JToggleButton("��");
	JToggleButton plainBtn = new JToggleButton("��");
	JButton foreColor = new JButton("���ڻ�");
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

		// JFrame �ڵ� ���� ����

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
		if (str.equals("���ڻ�")) {
			// �÷�����Ž����
			JColorChooser cc = new JColorChooser();
			// �����ñ� ���� (�θ�ü, ����, �ʱ����)
			Color color = cc.showDialog(frm, "���ڻ�", Color.red);
			ta.setForeground(color);
		}
	}

	// ������ �̺�Ʈ �������̵�

	// WindowAdapter�� ����Ͽ� �ʿ��� �޼ҵ常 �� �������̵��Ͽ� ����Ѵ�.

	public void windowClosing(WindowEvent we) {
		int showType = JOptionPane.showConfirmDialog(frm, "�����Ͻðڽ��ϱ�?", "Ȯ��", JOptionPane.OK_CANCEL_OPTION);
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

	int bold = 0, italic = 0; // Plain���� ����

	Font fnt = new Font("Serif", Font.PLAIN, 20);

	public MyEventProdess() {

	}

	public MyEventProdess(JTextArea ta, JToggleButton boldBtn, JToggleButton italicBtn, JToggleButton plainBtn) {
		this.ta = ta;
		this.boldBtn = boldBtn;
		this.italicBtn = italicBtn;
		this.plainBtn = plainBtn;
	}

	// �������̵� = public class���� implements �Ͽ��ٸ� �̰��� �������̵��� �ؾ� �Ѵ�.

	public void actionPerformed(ActionEvent ae) {
		// �̺�Ʈ

		// Font("Serif", Font.BOLD, 20);

		// FontBOLD=1, Font.ITALIC=2, Font.PLAIN=0

		Object obj = ae.getSource();
		if (obj == boldBtn) { // ���ϰ�
			if (boldBtn.isSelected()) { // ����
				bold = 1;
			} else { // ����
				bold = 0;
			}
		} else if (obj == italicBtn) { // ���ڸ�
			if (italicBtn.isSelected()) {
				italic = 2;
			} else {
				italic = 0;
			}
		} else if (obj == plainBtn) { // ����
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