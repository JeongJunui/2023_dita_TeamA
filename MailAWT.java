package warehouse;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MailAWT extends JFrame{
	private JPanel p1;
	private JLabel mailTitle, receiver, sendTitle, mailAttach, sendAttach, fileName;
	private JLabel mailBar, mailBar2;
	private JButton myPCBtn;
	public MailAWT() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("메일");
		setSize(450, 550);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);
			
		MailPanel();
		setVisible(true);
	}
	
	public void MailPanel() {
		p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.setLayout(null);
		p1.setBounds(0, 0, 434, 511);
		
		mailTitle = new JLabel();
		mailTitle.setIcon(new ImageIcon(".\\images\\mailTitle.png"));
		mailTitle.setBounds(20, 10, 94, 45);
		
		receiver = new JLabel("받는사람");
		receiver.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		receiver.setForeground(new Color(118,118,120));
		receiver.setBounds(20, 65, 60, 28);
		
		mailBar = new JLabel();
		mailBar.setIcon(new ImageIcon(".\\images\\mailBar.png"));
		mailBar.setBounds(90, 78, 311, 34);
		
		sendTitle = new JLabel("제목");
		sendTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		sendTitle.setForeground(new Color(118,118,120));
		sendTitle.setBounds(20, 110, 60, 28);
		
		mailBar2 = new JLabel();
		mailBar2.setIcon(new ImageIcon(".\\images\\mailBar.png"));
		mailBar2.setBounds(90, 120, 311, 34);
		
		mailAttach = new JLabel("파일첨부");
		mailAttach.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mailAttach.setForeground(new Color(118,118,120));
		mailAttach.setBounds(20, 155, 60, 28);
		
		myPCBtn = new RoundedButton("내 PC");
		myPCBtn.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		myPCBtn.setBounds(90, 157, 50, 25);
		
		sendAttach = new JLabel();
		sendAttach.setIcon(new ImageIcon(".\\images\\mailAttach.png"));
		sendAttach.setBounds(90, 180, 340, 70);
		
		fileName = new JLabel("내 PC");
		myPCBtn.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		myPCBtn.setBounds(90, 157, 50, 25);
		
		p1.add(mailTitle);	
		p1.add(receiver);
		p1.add(mailBar);	
		p1.add(sendTitle);
		
		
		p1.add(mailBar2);
		p1.add(mailAttach);
		p1.add(myPCBtn);
		p1.add(sendAttach);
		add(p1);
	}
	
	public static void main(String[] args) {
		MailAWT mailAWT = new MailAWT(); 
	}
}

class RoundedButton extends JButton {
    public RoundedButton() { super(); decorate(); } 
    public RoundedButton(String text) { super(text); decorate(); } 
    public RoundedButton(Action action) { super(); decorate(); } 
    public RoundedButton(Icon icon) { super(icon); decorate(); } 
    public RoundedButton(String text, Icon icon) { super(text, icon); decorate(); } 
    protected void decorate() { setBorderPainted(false); setOpaque(false); }
    
    @Override 
    protected void paintComponent(Graphics g) {
       Color c=new Color(234,234,234); //배경색 결정
       Color o=new Color(0,0,0); //글자색 결정
       int width = getWidth(); 
       int height = getHeight(); 
       Graphics2D graphics = (Graphics2D) g; 
       graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON); 
       if (getModel().isArmed()) { graphics.setColor(c.darker()); } 
       else if (getModel().isRollover()) { graphics.setColor(c.LIGHT_GRAY); } 
       else { graphics.setColor(c); } 
       graphics.fillRoundRect(0, 0, width, height, 10, 10); 
       FontMetrics fontMetrics = graphics.getFontMetrics(); 
       Rectangle stringBounds = fontMetrics.getStringBounds(this.getText(), graphics).getBounds(); 
       int textX = (width - stringBounds.width) / 2; 
       int textY = (height - stringBounds.height) / 2 + fontMetrics.getAscent(); 
       graphics.setColor(o); 
       graphics.setFont(getFont()); 
       graphics.drawString(getText(), textX, textY); 
       graphics.dispose(); 
       super.paintComponent(g); 
       }
    }