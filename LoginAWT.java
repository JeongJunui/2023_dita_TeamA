package warehouse;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.text.NumberFormatter;

import member.MemberMgr;
import net.ChatClient3;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
///////////////////////////////////////////
///////////// 로그인페이지///////////////
/////////////////////////////////////////
class LogIn extends JPanel{ 
	public LoginAWT win;
	JPasswordField textField;
	JLabel msgl;
	Image img;
	LoginMgr mgr = new LoginMgr();
	
	// 로그인 화면 세팅
	public LogIn(LoginAWT win) {
		this.win = win;
		
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/loginTitle.png");
		
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		//// 로고이미지 흰색 뒷배경
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 120);
		imgBack.setBackground(Color.white);
		add(imgBack);
		
		//// 로그인 버튼
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new MyActionListener2());
		btnNewButton.setBounds(238, 197, 74, 23);
		add(btnNewButton);
		
		//// 입력칸
		textField = new JPasswordField();
		textField.setBounds(96, 198, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		//// Password 라벨
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(22, 201, 72, 15);
		add(lblNewLabel);
		
		//// 회원가입 버튼
		JButton signUpBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/signUpBtn.png"));
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setBounds(240, 285, 97, 23);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setContentAreaFilled(false);
		signUpBtn.setFocusPainted(false);
		add(signUpBtn);
		
		signUpBtn.addActionListener(new MyActionListener());
	
		
	}
	///// 로고이미지 그리기
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 10,0,this);
	}
	
	///// 회원가입페이지로 이동
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("SignUp");
		}
	}
	///// 로그인기능
	class MyActionListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().equals("")) { 				///// 빈칸 입력하면
				new MDialog2(win,"알림",true); 				///// 입력하라고 알림
			}else {
				if(mgr.loginChk(textField.getText())) { ///// DB확인
					win.dispose(); 											///// LoginAWT 사라지고
					new MainAWT(); 									///// 메인화면으로 이동
				}else {
					System.out.println("없음");					
					new MDialog(win, "알림", true);			/////  DB확인 후 없을시 알림
				}
			}
		}
	}
	
	///// 로그인 실패 알림
	class MDialog extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog(Frame owner, String title, boolean modal) {
			super(owner, title, modal);
			addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			     dispose();
			    }
		 });
			b = new Button("확인");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("일치하는 번호가 없습니다.", label.CENTER);
			add(label);
			
			layset();
		}
		
		public void layset() {
			int width = 300;
			int height = 150;
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	///// 입력값 없음 알림
	class MDialog2 extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog2(Frame owner, String title, boolean modal) {
			super(owner, title, modal);
			addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			     dispose();
			    }
		 });
			b = new Button("확인");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("비밀번호를 입력해주세요", label.CENTER);
			add(label);
			
			layset();
		}
		
		public void layset() {
			int width = 300;
			int height = 150;
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
}



///////////////////////////////////////////
/////////2 번째 패널 ( 회원가입 )//////////
//////////////////////////////////////////
class SignUp extends JPanel implements ActionListener{
	
	LoginAWT win;
	Image img;
	Container contentPane;
	JFormattedTextField textField;
	JComboBox cbx;
	LoginMgr mgr = new LoginMgr();
	String[] company = { "LG", "SAMSUNG", "SKT" };
	
	///// 회사 로고이미지
	ImageIcon[] images = { 
			 new ImageIcon("C:/Java2/myJava/warehouse/images/lg.png"),  
			 new ImageIcon("C:/Java2/myJava/warehouse/images/samsung.png"),
		       new ImageIcon("C:/Java2/myJava/warehouse/images/skt.png") };
	 JLabel imgLabel = new JLabel(images[0]);
	
	public SignUp(LoginAWT win) {
		
		///// 로고이미지 그리기
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/signUpTitle.png");
		
		this.win = win;
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 100);
		imgBack.setBackground(Color.white);
		
		add(imgBack);	///// 이미지 뒷배경 그리기
		
		///// 전화번호 입력필드 ( 숫자만 입력 )
		textField = new JFormattedTextField(new NumberFormatter());
		textField.setBounds(146, 133, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		///// 전화번호 라벨
		JLabel lblNewLabel = new JLabel("전화번호");
		lblNewLabel.setBounds(52, 136, 72, 15);
		add(lblNewLabel);
		
		///// 회사선택 콤보박스
		cbx = new JComboBox(company); 
		cbx.setBounds(146, 183, 130, 21);
		cbx.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		            JComboBox cb = (JComboBox) e.getSource(); // 콤보박스 알아내기
		             int index = cb.getSelectedIndex();						// 선택된 아이템의 인덱스
		            imgLabel.setIcon(images[index]); 						// 인덱스의 이미지를 이미지 레이블에 출력
		       }
		  });	
		add(cbx);
		imgLabel.setBounds(90,220, 100, 100);
		add(imgLabel);
		
		///// 회사명 라벨
		JLabel lblNewLabel2 = new JLabel("회사명");
		lblNewLabel2.setBounds(52, 186, 72, 15);
		add(lblNewLabel2);
		
		///// 회원가입 버튼
		JButton joinBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/joinBtn.png"));
		joinBtn.setBackground(new Color(255, 255, 255));
		joinBtn.setBounds(240, 285, 97, 23);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		joinBtn.addActionListener(this);
		add(joinBtn);
		
	}
	
	///// 배경이미지 그리기
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 56,0,this);
	}

	///// 회원가입 기능구현
	@Override
	public void actionPerformed(ActionEvent e) {
		
		MemberBean bean = new MemberBean();
		bean.setTel(textField.getText());
		bean.setAddress("123");
		bean.setName(cbx.getSelectedItem().toString());
		
		if(textField.getText().equals("")) { // 필드값에 아무것도 없으면
			new MDialog(win, "알림", true);	// 입력하라고 알림
		}else {
			if(mgr.signUpChk(bean)) {			// DB 중복체크
				mgr.insert(bean);						// DB에 회원정보 저장
				win.dispose();								// LoginAWT 사라지고
				new MainAWT();							// 메인화면으로 넘어감
			}else 
				new MDialog2(win, "알림", true); // 중복확인알림
			}
		}
	}

	///// 입력값 없음 확인알림
	class MDialog extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog(Frame owner, String title, boolean bool) {
			
			super(owner, title, bool);
			
			addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			     dispose();
			    }
		 });
			
			b = new Button("확인");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("비밀번호를 입력해주세요", label.CENTER);
			add(label);
			
			layset();
		}
		
		public void layset() {
			int width = 300;
			int height = 150;
			setSize(width, height);
			setLocationRelativeTo(null);
			setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
}
	///// DB중복 확인알림
	class MDialog2 extends Dialog implements ActionListener{
	
		Button b;
		Label label;

		public MDialog2(Frame owner, String title, boolean bool) {
		
		super(owner, title,bool);
		
		addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		     dispose();
		    }
	 });
		
		b = new Button("확인");
		b.setBounds(120, 110, 50, 20);
		b.addActionListener(this);
		add(b);
		
		label = new Label("이미 등록된 번호입니다.", label.CENTER);
		add(label);
		
		layset();
	}
	
	public void layset() {
		int width = 300;
		int height = 150;
		setSize(width, height);
		setLocationRelativeTo(null);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		dispose();
	}
}
	
////////////////////
////// 프레임///////
////////////////////
public class LoginAWT extends JFrame{
	
	public LogIn jpanel01 = null;  	// 로그인패널
	public SignUp jpanel02 = null;	// 회원가입 패널
	
	public LoginAWT() {
		setTitle("");//창의 타이틀
		setSize(400,400);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
	}
	
	///// 패널 전환
	public void change(String panelName) {
		if(panelName.equals("LogIn")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			revalidate();
			repaint();
		}else {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
		}
	}
	
	public static void main(String[] args){
		LoginAWT win = new LoginAWT();
		
		win.setTitle("");
		win.jpanel01 = new LogIn(win);
		win.jpanel02 = new SignUp(win);
		
		win.getContentPane().add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setVisible(true);
	}
	
}
