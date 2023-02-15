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
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

// 1번째 패널
class JPanel01 extends JPanel{ 
	
	Image img;
	private LoginAWT win;
	
	public JPanel01(LoginAWT win) {
		this.win = win;
		
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/loginTitle.png");
		
		
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 120);
		imgBack.setBackground(Color.white);
		
		add(imgBack);
		
		JButton btnNewButton = new JButton("login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton.setBounds(238, 197, 74, 23);
		add(btnNewButton);
		
		JTextField textField = new JTextField();
		textField.setBounds(96, 198, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(22, 201, 72, 15);
		add(lblNewLabel);
		
		JButton signUp = new JButton(new ImageIcon("C:/Java2/myJava/warehouse/images/signUpBtn.png"));
		signUp.setBackground(new Color(255, 255, 255));
		signUp.setBounds(240, 285, 97, 23);
		signUp.setBorderPainted(false);
		signUp.setContentAreaFilled(false);
		signUp.setFocusPainted(false);
		add(signUp);
		
		signUp.addActionListener(new MyActionListener());
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 10,0,this);
	}
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel02");
		}
	}
}

//2 번째 패널
class JPanel02 extends JPanel{
	
	private LoginAWT win;
	Image img;
	public JPanel02(LoginAWT win) {
		
		img = Toolkit.getDefaultToolkit().getImage("C:/Java2/myJava/warehouse/images/signUpTitle.png");
		
		this.win = win;
		setBackground(new Color(239, 239, 239));
		setLayout(null);
		setBounds(23, 23, 338, 314);
		
		JPanel imgBack = new JPanel();
		imgBack.setBounds(0, 0, 338, 100);
		imgBack.setBackground(Color.white);
		
		add(imgBack);
		
		JTextField textField = new JTextField();
		textField.setBounds(146, 133, 130, 21);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("전화번호");
		lblNewLabel.setBounds(52, 136, 72, 15);
		add(lblNewLabel);
		
		JComboBox cbx = new JComboBox();
		cbx.setBounds(146, 183, 130, 21);
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
		
		joinBtn.addActionListener(new MyActionListener());
	}
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(img, 56,0,this);
	}
	
	class MyActionListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			win.change("panel01");
		}
	}
}

public class LoginAWT extends JFrame{
	
	public JPanel01 jpanel01 = null;
	public JPanel02 jpanel02 = null;
	
	public LoginAWT() {
		setTitle("");//창의 타이틀
		setSize(400,400);//프레임의 크기
		setResizable(false);//창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);//창이 가운데 나오게
		getContentPane().setLayout(null);
		getContentPane().setBackground(Color.white);
	}
	
	public void change(String panelName) {
		if(panelName.equals("panel01")) {
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
		win.jpanel01 = new JPanel01(win);
		win.jpanel02 = new JPanel02(win);
		
		win.getContentPane().add(win.jpanel01);
		win.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		win.setVisible(true);
	}
}
