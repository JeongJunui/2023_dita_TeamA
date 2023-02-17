package warehouse;

import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import member.MemberMgr;
import net.ChatClient3;

import javax.swing.JLabel;
import javax.swing.JComboBox;

// //// 로그인페이지
class LogIn extends JPanel{ 
	
	Image img;
	public LoginAWT win;
	
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
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(238, 197, 74, 23);
		add(btnNewButton);
		
		//// 입력칸
		JTextField textField = new JTextField();
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
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 10,0,this);
	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("SignUp");
		}
	}
}

//2 번째 패널
class SignUp extends JPanel implements ActionListener{
	
	LoginAWT win;
	Image img;
	JTextField textField;
	JComboBox cbx;
	LoginMgr mgr = new LoginMgr();
	
	
	
	public SignUp(LoginAWT win) {
		
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/signUpTitle.png");
		
		this.win = win;
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 100);
		imgBack.setBackground(Color.white);
		
		add(imgBack);
		
		textField = new JTextField();
		textField.setBounds(146, 133, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("전화번호");
		lblNewLabel.setBounds(52, 136, 72, 15);
		add(lblNewLabel);
		
		cbx = new JComboBox();
		cbx.setBounds(146, 183, 130, 21);
		cbx.addItem("LG");
		cbx.addItem("SAMSUNG");
		cbx.addItem("SKT");
		String com = cbx.getSelectedItem().toString();
		add(cbx);
		
		JLabel lblNewLabel2 = new JLabel("회사명");
		lblNewLabel2.setBounds(52, 186, 72, 15);
		add(lblNewLabel2);
		
		
		JButton joinBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/joinBtn.png"));
		joinBtn.setBackground(new Color(255, 255, 255));
		joinBtn.setBounds(240, 285, 97, 23);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		add(joinBtn);
		
		joinBtn.addActionListener(this);
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 56,0,this);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		MemberBean bean = new MemberBean();
		bean.setTel(textField.getText());
		bean.setAddress("123");
		bean.setName(cbx.getSelectedItem().toString());
		mgr.insert(bean);
		win.dispose();//LoginAWT 사라지고
		new MainAWT();
	}
}

public class LoginAWT extends JFrame{
	
	public LogIn jpanel01 = null;
	public SignUp jpanel02 = null;
	
	public LoginAWT() {
		setTitle("");//창의 타이틀
		setSize(400,400);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
	}
	
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
