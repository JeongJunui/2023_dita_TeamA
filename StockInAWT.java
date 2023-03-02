package warehouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class StockInAWT extends JPanel implements ActionListener{

	JPanel p1,p2,p3 , pp;
	static JPanel pp1;
	static JPanel p4;
	JButton b1,b2,homeBtn,regBtn, registBtn, search, correct, delete, searchBtn, proddelete, prodcorrect, backButton, b4;
	JButton searchAllBtn;
	JLabel label, label2, l3,label3;
	static JTextField pf[] = new JTextField[7];
	static JLabel pl[] = new JLabel[7];
	JTextField searchField;
	Font myFont1 = new Font("맑은 고딕", Font.BOLD, 15);
	int menuCheck = 0;
	List<String> list;
	String str[];
	
	JComboBox<?> comboBox;
	
	int num[] = new int[7];
	DefaultTableModel model;
	LoadStockin loadStockin;
	LoadProduct loadProduct;
	CheckMemberIdx checkMemberIdx;
	MainAWT mainAWT;
	
	public StockInAWT(MainAWT mainAWT) {
		this.mainAWT = mainAWT;
		//setLayout(null);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		menuPanel();
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
		
		homeBtn = new JButton("");
		homeBtn.setBounds(20, 350, 100, 100);
		homeBtn.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/homeBtn.png")));
		homeBtn.setRolloverIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/homeBtn2.png")));
		homeBtn.setBorderPainted(false);
		homeBtn.setContentAreaFilled(false);
		homeBtn.setFocusable(false);
		homeBtn.addActionListener(this);
		p1.add(homeBtn);
		add(p1);
		
		mainAWT.mainPanel.add(p1);
		//rightPanel();	//입고하기 페이지 실행
		productPanel();
	}

	public void rightPanel(){ //입고하기
		
		JLabel bar[] = new JLabel[7];
		
		p2 = new JPanel();
		p2.setLayout(null); 
		p2.setBackground(new Color(0,32,96));
		p2.setBounds(132, 0, 552, 461);
		
		label2 = new JLabel();
		label2.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiving.png")));
		label2.setBounds(28, 34, 150, 50);
		p2.add(label2);

		Panel startP = new Panel();
		startP.setBackground(Color.white);
		startP.setLayout(null);
		startP.setBounds(40, 120, 475, 310);
		p2.add(startP);
		
		pl[0] = new JLabel("물품코드 : ");
		pl[1] = new JLabel("카테고리 : ");
		pl[2] = new JLabel("물품명 : ");
		pl[3] = new JLabel("사이즈 : ");
		pl[4] = new JLabel("색상 : ");
		pl[5] = new JLabel("입고수량 : ");
		pl[6] = new JLabel("고객번호 : ");
		
		for (int i = 0; i < 7; i++) {
			if(i%2 == 1) {
				pl[i].setBounds(240, 20+(60*(i/2)), 80, 30);
			} else {
				pl[i].setBounds(20, 20+(60*(i/2)), 80, 30);
			}
			pl[i].setFont(myFont1);
			startP.add(pl[i]);
		}
		
		for (int i = 0; i < 7; i++) {
			pf[i] = new JTextField("");
			if(i%2 == 1) {
				pf[i].setBounds(320, 20+(60*(i/2)), 100, 30);
			} else {
				pf[i].setBounds(100, 20+(60*(i/2)), 100, 30);
			}
			pf[i].setBorder(null);
			startP.add(pf[i]);
		}
		
		backButton = new JButton();
		backButton.setBounds(0, 275, 40, 40);
		backButton.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/backBtn.png")));
		backButton.setFocusable(false);
		backButton.setBorderPainted(false);
		backButton.setContentAreaFilled(false);
		backButton.addActionListener(this);
		startP.add(backButton);
		
		regBtn = new JButton();
		regBtn.setBounds(345, 272, 130, 40);
		regBtn.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/regist.png")));
		regBtn.setRolloverIcon(new ImageIcon(".\\images\\regist2.png"));
		regBtn.setFocusable(false);
		regBtn.setBorderPainted(false);
		regBtn.setContentAreaFilled(false);
		regBtn.addActionListener(this);
		startP.add(regBtn);
		
		for (int i = 0; i < 7; i++) {
			bar[i] = new JLabel();
			if(i%2 == 1) {
				bar[i].setBounds(240,20+(60*(i/2)),200,70);
			} else {
				bar[i].setBounds(20,20+(60*(i/2)),200,70);
			}
			bar[i].setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/panelBar.png")));
			startP.add(bar[i]);
		}
		mainAWT.mainPanel.add(p2);
	}
	
	public void setBorder(Border border) {
		
	}
	
	public void rightPanel2() { //입고현황
		p3 = new JPanel();
		p3.setLayout(null); 
		p3.setBackground(new Color(0,32,96));
		p3.setBounds(180, 0, 452, 461);
		mainAWT.mainPanel.add(p3);
		
		label3 = new JLabel();
		label3.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/recieptStatus.png")));
		label3.setBounds(0, 34, 150, 50);
		p3.add(label3);
		
		searchField = new JTextField("");
		searchField.setBounds(100, 95, 200, 30);
		searchField.addActionListener(this);
		p3.add(searchField);
		
		searchBtn = new JButton();
		searchBtn.setBounds(292, 88, 45, 45);
		searchBtn.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/searchBtn.png")));
		searchBtn.setBorderPainted(false);
		searchBtn.setContentAreaFilled(false);
		searchBtn.setFocusable(false);
		searchBtn.addActionListener(this);
		p3.add(searchBtn);
		
		
		searchAllBtn = new JButton("전체 조회");
		searchAllBtn.setBounds(345, 92, 130, 40);
		searchAllBtn.setIcon(new ImageIcon(".\\images\\stockinCheckAll.png"));
		searchAllBtn.setRolloverIcon(new ImageIcon(".\\images\\stockinCheckAll2.png"));
		searchAllBtn.setFocusable(false);
		searchAllBtn.setBorderPainted(false);
		searchAllBtn.setContentAreaFilled(false);
		searchAllBtn.addActionListener(this);
		p3.add(searchAllBtn);
		
		
		correct = new JButton();
		correct.setBounds(327, 420, 60, 30);
		correct.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/modifyBtn.png")));
		correct.setRolloverIcon(new ImageIcon(".\\images\\modifyBtn2.png"));
		correct.addActionListener(this);
		correct.setFocusable(false);
		correct.setBorderPainted(false);
		correct.setContentAreaFilled(false);
		p3.add(correct);
		
		delete = new JButton();
		delete.setBounds(392, 420, 60, 30);
		delete.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/deleteBtn.png")));
		delete.setRolloverIcon(new ImageIcon(".\\images\\deleteBtn2.png"));
		delete.addActionListener(this);
		delete.setFocusable(false);
		delete.setBorderPainted(false);
		delete.setContentAreaFilled(false);
		p3.add(delete);
		
		p4 = new JPanel();
		p4.setBounds(-49, 140, 550, 270);
		p3.add(p4);
		
		loadStockin = new LoadStockin(this);
	}
	
	public void productPanel() {
		pp = new JPanel();
		pp.setLayout(null); 
		pp.setBackground(new Color(0,32,96));
		pp.setBounds(180, 0, 452, 460);
		
		label2 = new JLabel();
		label2.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/receiving.png")));
		label2.setBounds(0, 34, 150, 50);
		pp.add(label2);
		
		prodcorrect = new JButton();
		prodcorrect.setBounds(321, 412, 65, 40);
		prodcorrect.setIcon(new ImageIcon(".\\images\\modifyBtn.png"));
		prodcorrect.setRolloverIcon(new ImageIcon(".\\images\\modifyBtn2.png"));
		prodcorrect.addActionListener(this);
		prodcorrect.setBorderPainted(false);
		prodcorrect.setContentAreaFilled(false);
		prodcorrect.setFocusable(false);
		pp.add(prodcorrect);
		
		proddelete = new JButton();
		proddelete.setBounds(389, 412, 65, 40);
		proddelete.setIcon(new ImageIcon(".\\images\\deleteBtn.png"));
		proddelete.setRolloverIcon(new ImageIcon(".\\images\\deleteBtn2.png"));
		proddelete.setBorderPainted(false);
		proddelete.setContentAreaFilled(false);
		proddelete.setFocusable(false);
		proddelete.addActionListener(this);
		pp.add(proddelete);
		
		b4 = new JButton();
		b4.setBounds(-10, 411, 120, 40);
		b4.setIcon(new ImageIcon(StockInAWT.class.getResource(".//images//productRegistBtn.png")));
		b4.setRolloverIcon(new ImageIcon(StockInAWT.class.getResource(".//images//productRegistBtn2.png")));
		b4.setBorderPainted(false);
		b4.setContentAreaFilled(false);
		b4.addActionListener(this);
		b4.setFocusable(false);
		pp.add(b4);
		
		registBtn = new JButton();
		registBtn.setBounds(253, 411, 65, 40);
		registBtn.setIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/registBtn2.png")));
		registBtn.setRolloverIcon(new ImageIcon(StockInAWT.class.getResource("/warehouse/images/registBtn.png")));
		registBtn.setBorderPainted(false);
		registBtn.setContentAreaFilled(false);
		registBtn.addActionListener(this);
		registBtn.setFocusable(false);
		pp.add(registBtn);
		
		pp1 = new JPanel();
		pp1.setBounds(-101, 100, 655, 290);
		loadProduct = new LoadProduct(this);
		pp.add(pp1);
		mainAWT.mainPanel.add(pp);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		str = new String[7];
		
		int check = 0;
		if(obj == b1) {
			if(menuCheck == 0) {
			} else if(menuCheck == 1) {
				p2.setVisible(false);
				productPanel();
				revalidate();
				repaint();
				menuCheck = 0;
			} else if(menuCheck == 2) {
				p3.setVisible(false);
				productPanel();
				revalidate();
				repaint();
				menuCheck = 0;
			}
		}else if(obj==b2){ //입고현황
			if(menuCheck == 0) {
				pp.setVisible(false);
				rightPanel2();
				revalidate();
				repaint();
			} else if(menuCheck == 1) {
				p2.setVisible(false);
				rightPanel2();
				revalidate();
				repaint();
			} else if(menuCheck == 2) {
			}
			menuCheck = 2;
		}else if(obj==b4){ //제품 등록
			pp.setVisible(false);
			rightPanel();
			revalidate();
			repaint();
			menuCheck = 1;
			
		}else if(obj == backButton) {
			p2.setVisible(false);
			productPanel();
			revalidate();
			repaint();
			menuCheck = 0;
			
		}else if(obj==homeBtn) {
			mainAWT.mainPanel.setVisible(false);
			mainAWT.mainPanel();
			
		}else if(obj == regBtn){	//등록하기 버튼
			checkMemberIdx = new CheckMemberIdx(); 
			for (int i = 0; i < 7; i++) {
				str[i] = pf[i].getText();
				if(str[i].isEmpty()) { //빈값 체크
					System.out.println("빈값");
					JOptionPane.showMessageDialog(null, pl[i].getText() + "에 빈값이 있습니다");
					check++;
				}
			}
			
			if(check == 0) { //빈값 없으면 테이블 추가
				if(checkMemberIdx.check(str[6]))
					new StartStockIn(model,str,loadProduct.checkRegist(str));
				else {
					JOptionPane.showMessageDialog(null, "잘못된 고객번호 입니다.");
				}
			}
			for (int i = 0; i < 7; i++) {
				pf[i].setText("");
			} //값비우기
			check = 0;
		}else if(obj==correct) {	//수정 버튼
			int row = loadStockin.row;
			int col = loadStockin.col;
			System.out.println(row + "row-----------------------");
			System.out.println(col + "col-----------------------");
			loadStockin.correct(row,col);
			System.out.println(row + "행 수정 완료");
			
		}else if(obj==delete) {		//삭제 버튼
			int row = loadStockin.mrow;
			loadStockin.delete(row);
			p3.setVisible(false);
			rightPanel2();
			revalidate();
			repaint();
			System.out.println(row + "행 삭제 완료");
			
		}else if(obj==searchBtn || obj == searchField) {		//검색 버튼
	        String word = searchField.getText();
	        loadStockin.search(word);
		
		}else if(obj==searchAllBtn) {		//검색 버튼
	        loadStockin.search("");
		
		}else if(obj==proddelete) {		//삭제 버튼
			int row = loadProduct.mrow;
			loadProduct.delete(row);
			pp.setVisible(false);
			productPanel();
			revalidate();
			repaint();
			System.out.println(row + "행 삭제 완료");
			
		}else if(obj==prodcorrect) {	//수정 버튼
			int row = loadProduct.row;
			int col = loadProduct.col;
			loadProduct.correct(row,col);
			System.out.println(row + "행 수정 완료");
			
		}else if(obj==registBtn) {	//등록 버튼
			int row = loadProduct.mrow;
			String[] reStrings = new String[6];
			reStrings = loadProduct.regist(row);
			loadProduct.regist(row);
			
			pp.setVisible(false);
			rightPanel();
			revalidate();
			repaint();
			menuCheck = 1;
			
			for (int i = 0; i < reStrings.length; i++) {
				pf[i].setText(reStrings[i]);
				System.out.println(reStrings[i]);
			}
		}
	}
}
