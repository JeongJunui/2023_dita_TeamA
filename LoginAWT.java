package warehouse;

import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

// ---------------------------------로그인 패널------------------------------------------------------------------------

class LogIn extends JPanel{ 
	public LoginAWT win;
	JPanel imgBack;
	JTextField idTextField;
	JPasswordField passTextField;
	JButton btnNewButton, signUpBtn;
	JLabel title;
	JLabel msgl, passLabel, idLabel;
	LoginMgr mgr = new LoginMgr();
	
	// 로그인 화면 세팅
	public LogIn(LoginAWT win) {
		this.win = win;	
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(0, 0, 390, 570);
		
		//// 로고이미지 흰색 뒷배경
		imgBack = new JPanel();
		imgBack.setBounds(0, 0, 440, 130);
		imgBack.setLayout(null);
		imgBack.setBackground(Color.white);
			
		title = new JLabel();
		title.setIcon(new ImageIcon(".\\images\\loginTitle.png"));
		title.setBounds(30, 10, 350, 100);
		imgBack.add(title);
		add(imgBack);
		//// 로그인 버튼
		btnNewButton = new JButton(new ImageIcon(".\\images\\loginBtn.png"));
		btnNewButton.setBackground(Color.white);
		btnNewButton.setBorderPainted(false);
		btnNewButton.setContentAreaFilled(false);
		btnNewButton.setFocusPainted(false);
		btnNewButton.addActionListener(new MyActionListener2());
		btnNewButton.setBounds(265, 240, 75, 75);
		add(btnNewButton);
		
		///// 이름 입력칸
		idTextField = new JTextField();
		idTextField.setBounds(125, 243, 130, 21);
		add(idTextField);
		idTextField.setColumns(10);
		
		//// 비밀번호 입력칸
		passTextField = new JPasswordField();
		passTextField.setBounds(125, 290, 130, 21);
		add(passTextField);
		passTextField.setColumns(40);
		
		//// ID 라벨
		passLabel = new JLabel("이름");
		passLabel.setBounds(60, 247, 72, 15);
		add(passLabel);
		
		//// Password 라벨
		idLabel = new JLabel("전화번호");
		idLabel.setBounds(60, 294, 72, 15);
		add(idLabel);
		
		//// 회원가입 버튼
		signUpBtn = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/signUpBtn.png"));
		signUpBtn = new JButton(new ImageIcon(".\\images\\signUpBtn.png"));
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setBounds(280, 420, 97, 23);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setContentAreaFilled(false);
		signUpBtn.setFocusPainted(false);
		add(signUpBtn);
		
		signUpBtn.addActionListener(new MyActionListener());	
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
			if(idTextField.getText().equals("")) {
				new MDialog3(win,"알림",true);
			}else {
				if(passTextField.getText().equals("")) { 				///// 빈칸 입력하면
					new MDialog2(win,"알림",true); 				///// 입력하라고 알림
				}else {
					if(mgr.loginChk(passTextField.getText(),idTextField.getText() )) { ///// DB확인
						win.dispose(); 											///// LoginAWT 사라지고
						new MainAWT(); 									///// 메인화면으로 이동
					}else {
						System.out.println("없음");					
						new MDialog(win, "알림", true);			/////  DB확인 후 없을시 알림
					}
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
	
	///// 입력값 없음 알림 ( 비밀번호 )
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
			
			label = new Label("전화번호를 입력해주세요", label.CENTER);
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
    ///// 입력값 없음 알림 ( ID )
	class MDialog3 extends Dialog implements ActionListener{
		
		Button b;
		Label label;

		public MDialog3(Frame owner, String title, boolean modal) {
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
			
			label = new Label("이름을 입력해주세요", label.CENTER);
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
////----------------------------------2 번째 패널 ( 회원가입 )-------------------------------------------------------

class SignUp extends JPanel implements ActionListener {

	LoginAWT win;
	Image img;
	Container contentPane;
	JLabel lblNewLabel, lblNewLabel4, lblNewLabel5;
	JLabel title2;
	JTextField pwdTexxtField;
	JTextField textfield, textfield2;
	JComboBox cbx;
	LoginMgr mgr = new LoginMgr();
	String[] company = { "LG", "SAMSUNG", "SKT" };

	///// 회사 로고이미지
	ImageIcon[] images = { new ImageIcon(".\\images\\lg.png"), new ImageIcon(".\\images\\samsung.png"),
			new ImageIcon(".\\images\\skt.png") };
	JLabel imgLabel = new JLabel(images[0]);

	public SignUp(LoginAWT win) {
		this.win = win;
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(0, 0, 383, 514);

		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 383, 100);
		imgBack.setBackground(Color.white);

		title2 = new JLabel();
		title2.setIcon(new ImageIcon(".\\images\\signUpTitle.png"));
		title2.setBounds(30, 10, 350, 100);
		imgBack.add(title2);
		add(imgBack); ///// 이미지 뒷배경 그리기

		///// 전화번호 입력필드 ( 숫자만 입력 )
		pwdTexxtField = new JTextField();
		pwdTexxtField.setBounds(146, 253, 130, 21);
		add(pwdTexxtField);
		pwdTexxtField.setColumns(40);

		///// 전화번호 라벨
		lblNewLabel = new JLabel("전화번호");
		lblNewLabel.setBounds(62, 256, 72, 15);
		add(lblNewLabel);

		///// 이름 라벨
		lblNewLabel4 = new JLabel("이름");
		lblNewLabel4.setBounds(62, 206, 72, 15);
		add(lblNewLabel4);

		///// 이름 입력칸
		textfield = new JTextField();
		textfield.setBounds(146, 203, 130, 21);
		add(textfield);

		///// 주소 라벨
		lblNewLabel5 = new JLabel("주소");
		lblNewLabel5.setBounds(62, 306, 72, 15);
		add(lblNewLabel5);

		///// 주소 입력칸
		textfield2 = new JTextField();
		textfield2.setBounds(146, 303, 130, 21);
		add(textfield2);

		///// 회원가입 버튼
		JButton joinBtn = new JButton(new ImageIcon(".\\images\\joinBtn.png"));
		joinBtn.setBackground(new Color(255, 255, 255));
		joinBtn.setBounds(280, 425, 97, 23);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		joinBtn.addActionListener(this);
		add(joinBtn);

		///// 뒤로가기 버튼
		JButton backBtn = new JButton(new ImageIcon(".\\images\\backBtn.png"));
		backBtn.setBackground(new Color(255, 255, 255));
		backBtn.setBounds(20, 425, 25, 25);
		backBtn.setBorderPainted(false);
		backBtn.setContentAreaFilled(false);
		backBtn.setFocusPainted(false);
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				win.change("LogIn");
			}
		});
		add(backBtn);

	}

	///// 배경이미지 그리기
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 56, 0, this);
	}

	///// 회원가입 기능구현
	@Override
	public void actionPerformed(ActionEvent e) {

		MemberBean bean = new MemberBean();
		bean.setTel(pwdTexxtField.getText());
		bean.setAddress(textfield2.getText());
		bean.setName(textfield.getText());

		if (textfield.getText().equals("")) { // 필드값에 아무것도 없으면
			new MDialog(win, "알림", true); // 입력하라고 알림
		} else {
			if (mgr.signUpChk(bean)) { // DB 중복체크
				mgr.insert(bean); // DB에 회원정보 저장
				JOptionPane.showMessageDialog(null, "회원가입 완료!", "성공", JOptionPane.PLAIN_MESSAGE);
				win.change("LogIn");
			} else
				new MDialog2(win, "알림", true); // 중복확인알림
		}
	}
}

///// 입력값 없음 확인알림
class MDialog extends Dialog implements ActionListener {

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
class MDialog2 extends Dialog implements ActionListener {
	Button b;
	Label label;

	public MDialog2(Frame owner, String title, boolean bool) {

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
public class LoginAWT extends JFrame {
	public LogIn jpanel01 = null; // 로그인패널
	public SignUp jpanel02 = null; // 회원가입 패널

	public LoginAWT() {
		setTitle("");// 창의 타이틀
		setSize(400, 500);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}

	///// 패널 전환
	public void change(String panelName) {
		if (panelName.equals("LogIn")) {
			getContentPane().removeAll();
			getContentPane().add(jpanel01);
			revalidate();
			repaint();
		} else {
			getContentPane().removeAll();
			getContentPane().add(jpanel02);
			revalidate();
			repaint();
		}
	}

	public static void main(String[] args) {
		LoginAWT win = new LoginAWT();
		win.setTitle("");
		win.jpanel01 = new LogIn(win);
		win.jpanel02 = new SignUp(win);
		win.getContentPane().add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setVisible(true);
	}

}
