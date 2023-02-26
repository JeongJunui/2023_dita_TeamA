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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.text.NumberFormatter;

import member.MemberMgr;
import net.ChatClient3;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
///////////////////////////////////////////
///////////// �α���������///////////////
/////////////////////////////////////////
class LogIn extends JPanel{ 
	public LoginAWT win;
	JPasswordField textField;
	JLabel msgl;
	Image img;
	LoginMgr mgr = new LoginMgr();
	
	// �α��� ȭ�� ����
	public LogIn(LoginAWT win) {
		this.win = win;
		
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/loginTitle.png");
		
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		//// �ΰ��̹��� ��� �޹��
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 120);
		imgBack.setBackground(Color.white);
		add(imgBack);
		
		//// �α��� ��ư
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new MyActionListener2());
		btnNewButton.setBounds(238, 197, 74, 23);
		add(btnNewButton);
		
		//// �Է�ĭ
		textField = new JPasswordField();
		textField.setBounds(96, 198, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		//// Password ��
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(22, 201, 72, 15);
		add(lblNewLabel);
		
		//// ȸ������ ��ư
		JButton signUpBtn = new JButton(new ImageIcon(".\\images\\signUpBtn.png"));
		signUpBtn.setBackground(new Color(255, 255, 255));
		signUpBtn.setBounds(240, 285, 97, 23);
		signUpBtn.setBorderPainted(false);
		signUpBtn.setContentAreaFilled(false);
		signUpBtn.setFocusPainted(false);
		add(signUpBtn);
		
		signUpBtn.addActionListener(new MyActionListener());
	
		
	}
	///// �ΰ��̹��� �׸���
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 10,0,this);
	}
	
	///// ȸ�������������� �̵�
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("SignUp");
		}
	}
	///// �α��α��
	class MyActionListener2 implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			if(textField.getText().equals("")) { 				///// ��ĭ �Է��ϸ�
				new MDialog2(win,"�˸�",true); 				///// �Է��϶�� �˸�
			}else {
				if(mgr.loginChk(textField.getText())) { ///// DBȮ��
					win.dispose(); 											///// LoginAWT �������
					new MainAWT(); 									///// ����ȭ������ �̵�
				}else {
					System.out.println("����");					
					new MDialog(win, "�˸�", true);			/////  DBȮ�� �� ������ �˸�
				}
			}
		}
	}
	
	///// �α��� ���� �˸�
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
			b = new Button("Ȯ��");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("��ġ�ϴ� ��ȣ�� �����ϴ�.", label.CENTER);
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
	
	///// �Է°� ���� �˸�
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
			b = new Button("Ȯ��");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("��й�ȣ�� �Է����ּ���", label.CENTER);
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
/////////2 ��° �г� ( ȸ������ )//////////
//////////////////////////////////////////
class SignUp extends JPanel implements ActionListener{
	
	LoginAWT win;
	Image img;
	Container contentPane;
	JFormattedTextField textField;
	JComboBox cbx;
	LoginMgr mgr = new LoginMgr();
	String[] company = { "LG", "SAMSUNG", "SKT" };
	
	///// ȸ�� �ΰ��̹���
	ImageIcon[] images = { 
			 new ImageIcon(".\\images\\lg.png"),  
			 new ImageIcon(".\\images\\samsung.png"),
		       new ImageIcon(".\\images\\skt.png") };
	 JLabel imgLabel = new JLabel(images[0]);
	
	public SignUp(LoginAWT win) {
		
		///// �ΰ��̹��� �׸���
		img = Toolkit.getDefaultToolkit().getImage(".\\images\\signUpTitle.png");
		
		this.win = win;
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 100);
		imgBack.setBackground(Color.white);
		
		add(imgBack);	///// �̹��� �޹�� �׸���
		
		///// ��ȭ��ȣ �Է��ʵ� ( ���ڸ� �Է� )
		textField = new JFormattedTextField(new NumberFormatter());
		textField.setBounds(146, 133, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		///// ��ȭ��ȣ ��
		JLabel lblNewLabel = new JLabel("��ȭ��ȣ");
		lblNewLabel.setBounds(52, 136, 72, 15);
		add(lblNewLabel);
		
		///// ȸ�缱�� �޺��ڽ�
		cbx = new JComboBox(company); 
		cbx.setBounds(146, 183, 130, 21);
		cbx.addActionListener(new ActionListener() {
		       public void actionPerformed(ActionEvent e) {
		            JComboBox cb = (JComboBox) e.getSource(); // �޺��ڽ� �˾Ƴ���
		             int index = cb.getSelectedIndex();						// ���õ� �������� �ε���
		            imgLabel.setIcon(images[index]); 						// �ε����� �̹����� �̹��� ���̺� ���
		       }
		  });	
		add(cbx);
		imgLabel.setBounds(90,220, 100, 100);
		add(imgLabel);
		
		///// ȸ��� ��
		JLabel lblNewLabel2 = new JLabel("ȸ���");
		lblNewLabel2.setBounds(52, 186, 72, 15);
		add(lblNewLabel2);
		
		///// ȸ������ ��ư
		JButton joinBtn = new JButton(new ImageIcon(".\\images\\joinBtn.png"));
		joinBtn.setBackground(new Color(255, 255, 255));
		joinBtn.setBounds(240, 285, 97, 23);
		joinBtn.setBorderPainted(false);
		joinBtn.setContentAreaFilled(false);
		joinBtn.setFocusPainted(false);
		joinBtn.addActionListener(this);
		add(joinBtn);
		
	}
	
	///// ����̹��� �׸���
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 56,0,this);
	}

	///// ȸ������ ��ɱ���
	@Override
	public void actionPerformed(ActionEvent e) {
		
		MemberBean bean = new MemberBean();
		bean.setTel(textField.getText());
		bean.setAddress("123");
		bean.setName(cbx.getSelectedItem().toString());
		
		if(textField.getText().equals("")) { // �ʵ尪�� �ƹ��͵� ������
			new MDialog(win, "�˸�", true);	// �Է��϶�� �˸�
		}else {
			if(mgr.signUpChk(bean)) {			// DB �ߺ�üũ
				mgr.insert(bean);						// DB�� ȸ������ ����
				win.dispose();								// LoginAWT �������
				new MainAWT();							// ����ȭ������ �Ѿ
			}else 
				new MDialog2(win, "�˸�", true); // �ߺ�Ȯ�ξ˸�
			}
		}
	}

	///// �Է°� ���� Ȯ�ξ˸�
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
			
			b = new Button("Ȯ��");
			b.setBounds(120, 110, 50, 20);
			b.addActionListener(this);
			add(b);
			
			label = new Label("��й�ȣ�� �Է����ּ���", label.CENTER);
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
	///// DB�ߺ� Ȯ�ξ˸�
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
		
		b = new Button("Ȯ��");
		b.setBounds(120, 110, 50, 20);
		b.addActionListener(this);
		add(b);
		
		label = new Label("�̹� ��ϵ� ��ȣ�Դϴ�.", label.CENTER);
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
////// ������///////
////////////////////
public class LoginAWT extends JFrame{
	
	public LogIn jpanel01 = null;  	// �α����г�
	public SignUp jpanel02 = null;	// ȸ������ �г�
	
	public LoginAWT() {
		setTitle("");//â�� Ÿ��Ʋ
		setSize(400,400);//�������� ũ��
		setResizable(false);//â�� ũ�⸦ �������� ���ϰ�
		setLocationRelativeTo(null);//â�� ��� ������
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

	}
	
	///// �г� ��ȯ
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
