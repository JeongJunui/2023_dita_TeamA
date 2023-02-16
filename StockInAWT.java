package warehouse;

import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.PublicKey;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class StockInAWT extends JFrame implements ActionListener{

	JPanel p1,p2,p3;
	JButton b1,b2,b3,regBtn, search;
	JLabel label, label2, l3,label3;
	JLabel pl1, pl2, pl3, pl4, pl5, pl6, pl7;
	JTextField pf1, pf2, pf3, pf4, pf5, pf6, pf7;
	JTextField searchField;
	JLabel bar1, bar2, bar3, bar4, bar5, bar6, bar7;
	Font myFont1 = new Font("맑은 고딕", Font.BOLD, 15);
	JTable stockinTable;
	int menuCheck = 0;
	
	class imgPanel extends JPanel{ //입고하기 안에 있는 패널
		Image background=new ImageIcon(StockInAWT.class.getResource("/warehouse/images/releaseBox.png")).getImage();
		public void paint(Graphics g) {//그리는 함수
				g.drawImage(background, 0, 0, null);//background를 그려줌		
		}
	};
	
	public StockInAWT() {
		setBounds(500, 300, 700, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		setTitle("StockInAWT");
		
		menuPanel();
		setVisible(true);
//		setVisible(true);
//		validate();
	}
	
	public void menuPanel(){
		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBackground(new Color(44,112,147));
		p1.setBounds(0, 0, 133, 461);
		
		
		label = new JLabel();
		label.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiveTitle.png")));
		label.setBounds(28, 34, 102, 66);
		p1.add(label);
		
		b1 = new JButton("");
		b1.setBounds(20, 130, 100, 50);
		b1.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiveBtn1_1.png")));
		b1.setRolloverIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiveBtn1_2.png")));
		b1.setBorderPainted(false);
		b1.setContentAreaFilled(false);
		b1.setFocusable(false);
		b1.addActionListener(this);
		p1.add(b1);
		
		b2 = new JButton("");
		b2.setBounds(20, 190, 100, 50);
		b2.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiveBtn2_1.png")));
		b2.setRolloverIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiveBtn2_2.png")));
		b2.setBorderPainted(false);
		b2.setContentAreaFilled(false);
		b2.setFocusable(false);
		b2.addActionListener(this);
		p1.add(b2);
		
		b3 = new JButton("");
		b3.setBounds(20, 350, 100, 100);
		b3.setIcon(new ImageIcon(test.class.getResource("/warehouse/images/homeBtn.png")));
		b3.setRolloverIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/homeBtn2.png")));
		b3.setBorderPainted(false);
		b3.setContentAreaFilled(false);
		b3.setFocusable(false);
		b3.addActionListener(this);
		p1.add(b3);
		add(p1);
		
		rightPanel();
	}

	public void rightPanel(){ //입고하기
		p2 = new JPanel();
		p2.setLayout(null); 
		p2.setBackground(new Color(0,32,96));
		p2.setBounds(132, 0, 552, 461);
		add(p2);
		
		label2 = new JLabel();
		label2.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiving.png")));
		label2.setBounds(28, 34, 150, 50);
		p2.add(label2);

		//imgPanel startP = new imgPanel();
		Panel startP = new Panel();
		startP.setBackground(Color.white);
		//startP.getRootPane();
		startP.setLayout(null);
		startP.setBounds(50, 130, 450, 300);
		p2.add(startP);
		
		pl1 = new JLabel("물품코드 : ");
		pl1.setBounds(20,20,80,30);
		pl1.setFont(myFont1);
		startP.add(pl1);
		
		pl2 = new JLabel("카테고리 : ");
		pl2.setBounds(220,20,80,30);
		pl2.setFont(myFont1);
		startP.add(pl2);
		
		pl3 = new JLabel("물품명 : ");
		pl3.setBounds(20,80,80,30);
		pl3.setFont(myFont1);
		startP.add(pl3);
		
		pl4 = new JLabel("사이즈 : ");
		pl4.setBounds(220,80,70,30);
		pl4.setFont(myFont1);
		startP.add(pl4);
		
		pl5 = new JLabel("색상 : ");
		pl5.setBounds(20,140,70,30);
		pl5.setFont(myFont1);
		startP.add(pl5);
		
		pl6 = new JLabel("입고수량 : ");
		pl6.setBounds(220,140,80,30);
		pl6.setFont(myFont1);
		startP.add(pl6);
		
		pl7 = new JLabel("고객번호 : ");
		pl7.setBounds(20,200,80,30);
		pl7.setFont(myFont1);
		startP.add(pl7);
		
		pf1 = new JTextField("물품코드");
		pf1.setBounds(100,20,100,30);
		startP.add(pf1);
		
		pf2 = new JTextField("카테고리");
		pf2.setBounds(300,20,100,30);
		startP.add(pf2);
		
		pf3 = new JTextField("물품명");
		pf3.setBounds(100,80,100,30);
		startP.add(pf3);
		
		pf4 = new JTextField("사이즈");
		pf4.setBounds(300,80,100,30);
		startP.add(pf4);
		
		pf5 = new JTextField("색상");
		pf5.setBounds(100,140,100,30);
		startP.add(pf5);
		
		pf6 = new JTextField("입고수량");
		pf6.setBounds(300,140,100,30);
		startP.add(pf6);
		
		pf7 = new JTextField("고객번호");
		pf7.setBounds(100,200,100,30);
		startP.add(pf7);
		
		regBtn = new JButton();
		regBtn.setBounds(310, 250, 130, 40);
		regBtn.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/regist.png")));
		startP.add(regBtn);
		
		bar1 = new JLabel();
		bar1.setBounds(20,20,200,70);
		bar1.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar1);
		
		bar2 = new JLabel();
		bar2.setBounds(220,20,200,70);
		bar2.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar2);
		
		bar3 = new JLabel();
		bar3.setBounds(20,80,200,70);
		bar3.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar3);
		
		bar4 = new JLabel();
		bar4.setBounds(220,80,200,70);
		bar4.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar4);
		
		bar5 = new JLabel();
		bar5.setBounds(20,140,200,70);
		bar5.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar5);
		
		bar6 = new JLabel();
		bar6.setBounds(220,140,200,70);
		bar6.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar6);
		
		bar7 = new JLabel();
		bar7.setBounds(20,200,200,70);
		bar7.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
		startP.add(bar7);
	}
	
	public void rightPanel2() { //입고현황
		p3 = new JPanel();
		p3.setLayout(null); 
		p3.setBackground(new Color(0,32,96));
		p3.setBounds(132, 0, 552, 461);
		add(p3);
		
		label3 = new JLabel();
		label3.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/recieptStatus.png")));
		label3.setBounds(28, 34, 150, 50);
		p3.add(label3);
		
		JButton search = new JButton();
		search.setBounds(120, 100, 50, 25);
		search.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/search.png")));
		search.setFocusable(false);
		p3.add(search);
		
		searchField = new JTextField("검색창");
		searchField.setBounds(200, 100, 200, 25);
		p3.add(searchField);
		
		JButton searchBtn = new JButton();
		searchBtn.setBounds(400, 100, 30, 25);
		searchBtn.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/searchBtn.png")));
		searchBtn.setFocusable(false);
		p3.add(searchBtn);
		
		JButton correct = new JButton();
		correct.setBounds(350, 400, 50, 25);
		correct.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/modifyBtn.png")));
		correct.setFocusable(false);
		p3.add(correct);
		
		JButton delete = new JButton();
		delete.setBounds(420, 400, 50, 25);
		delete.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/deleteBtn.png")));
		delete.setFocusable(false);
		p3.add(delete);
		
		JPanel p4 = new JPanel();
		p4.setBounds(25, 160, 505, 230);
		p3.add(p4);
		String colNames[] = {"입고순서","물품코드","카테고리", "물품이름", "사이즈", "색상", "입고수량"};
		Object data[][] = {{1, "A01", "옷", "바지", "34", "black", "50"}};
		stockinTable = new JTable(data, colNames);
		JScrollPane scrollPane = new JScrollPane(stockinTable);
		p4.add(scrollPane);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		
		
		if(obj == b1) {
			if(menuCheck == 0) {
//				p2.setVisible(false);
//				rightPanel();
//				revalidate();
//				repaint();
			} else if(menuCheck == 1) {
				p3.setVisible(false);
				rightPanel();
				revalidate();
				repaint();
				menuCheck = 0;
			}
			System.out.println("1번 버튼!!!!!!!!!" + menuCheck);
		}else if(obj==b2){
			if(menuCheck == 0) {
				p2.setVisible(false);
				rightPanel2();
				revalidate();
				repaint();
				menuCheck = 1;
			} else if(menuCheck == 1) {
//				p3.setVisible(false);
//				rightPanel2();
//				revalidate();
//				repaint();
			}
			System.out.println("2번 버튼!!!!!!!!!" + menuCheck);
		}else if(obj==b3) {
			System.out.println("3번 버튼!!!!!!!!!");
		}
		
	}
	
	public static void main(String[] args) {
		new StockInAWT();
	}

}
