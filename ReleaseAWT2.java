package warehouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReleaseAWT2 {
	private JFrame frame;
	private JTextField codeTextField;
	private JTextField amountTextField;
	private JTextField memberTextField;
	private JTextField otherTextField;
	private int n;
	private JButton releaseButton;
	ZipcodeAWT za;
	ReleasedMgr rsl;	
	ReleasedAWT awt;
	private JTextField addressTextField;

	public ReleaseAWT2(String s,int n, ReleasedAWT awt) {
		initialize();
		this.codeTextField.setText(s);
		this.n=n;
		this.frame.setVisible(true);
		this.awt=awt;
	}
	public void resetCode(String s,int n) {
		this.codeTextField.setText(s);
		this.n=n;
		this.frame.setVisible(true);
		this.frame.requestFocus();
		this.releaseButton.setEnabled(true);
	}
	public void setAddress(String Addr) {
		addressTextField.setText(Addr);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 500);
		frame.setLocationRelativeTo(null);// 창이 가운데 나오게
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		rsl=new ReleasedMgr();
		
		JPanel mainPanel=new JPanel();
		FlowLayout flowLayout = (FlowLayout) mainPanel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		JLabel titleLabel = new JLabel("");
		titleLabel.setIcon(new ImageIcon(ReleaseAWT2.class.getResource("/warehouse/images/releaseTitle2.png")));
		titleLabel.setPreferredSize(new Dimension(60, 90));
		frame.getContentPane().add(titleLabel,BorderLayout.NORTH);
		
		JPanel codePanel=new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) codePanel.getLayout();
		flowLayout_1.setHgap(10);
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		mainPanel.add(codePanel);
		codePanel.setBackground(new Color(255,255,255));
		
		JLabel codeLabel = new JLabel("* 물품코드");
		codePanel.add(codeLabel);
		codeLabel.setSize(300, 50);
		codeLabel.setPreferredSize(new Dimension(80,20));
		
		codeTextField = new JTextField();
		codePanel.add(codeTextField);
		codeTextField.setColumns(10);
		
		JPanel amountPanel=new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) amountPanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		flowLayout_2.setHgap(10);
		mainPanel.add(amountPanel);
		amountPanel.setBackground(new Color(255,255,255));
		
		JLabel amountLabel = new JLabel("* 수  량");
		amountPanel.add(amountLabel);
		amountLabel.setPreferredSize(new Dimension(80,20));
		
		amountTextField = new JTextField();
		amountPanel.add(amountTextField);
		amountTextField.setColumns(10);
		
		JPanel memberPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) memberPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		flowLayout_3.setHgap(10);
		memberPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(memberPanel);
		
		JLabel memberLabel = new JLabel("* 거래처");
		memberPanel.add(memberLabel);
		memberLabel.setPreferredSize(new Dimension(80,20));
		
		memberTextField = new JTextField();
		memberPanel.add(memberTextField);
		memberTextField.setColumns(10);
		
		JPanel addressPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) addressPanel.getLayout();
		flowLayout_4.setHgap(10);
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		addressPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(addressPanel);
		
		JLabel addressLabel = new JLabel("* 주 소 ");
		addressPanel.add(addressLabel);
		addressLabel.setPreferredSize(new Dimension(80,20));
		
		JButton addressButton = new JButton("");
		addressButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				zaOpen();
			}
		});
		
		addressTextField = new JTextField();
		addressPanel.add(addressTextField);
		addressTextField.setPreferredSize(new Dimension(40, 25));
		addressTextField.setColumns(5);
		addressButton.setIcon(new ImageIcon(ReleaseAWT2.class.getResource("/warehouse/images/searchAddressBtn.png")));
		addressPanel.add(addressButton);
		addressButton.setPreferredSize(new Dimension(50, 25));
		addressButton.setBorderPainted(false);
		addressButton.setFocusPainted(false);
		addressButton.setContentAreaFilled(false);
		
		Panel otherPanel = new Panel();
		FlowLayout flowLayout_5 = (FlowLayout) otherPanel.getLayout();
		flowLayout_5.setHgap(10);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		otherPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(otherPanel);
		
		JLabel otherLabel = new JLabel("비고사항");
		otherPanel.add(otherLabel);
		otherLabel.setPreferredSize(new Dimension(80,20));
		
		otherTextField = new JTextField();
		otherPanel.add(otherTextField);
		otherTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("* 표시는 필수 입력 ");
		lblNewLabel.setForeground(Color.RED);
		mainPanel.add(lblNewLabel);
		
		releaseButton = new JButton("");
		releaseButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String prodCode=codeTextField.getText();
				if(prodCode.length()==0)
				{
					JOptionPane.showMessageDialog(null,"물품코드란이 비어 있습니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				String amountSt=amountTextField.getText();
				if(amountSt.length()==0 || amountSt.equals("0"))
				{
					JOptionPane.showMessageDialog(null,"수량이 비어 있습니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				int amount=Integer.parseInt(amountSt);
				if(n<amount)
				{
					JOptionPane.showMessageDialog(null,"재고량이 부족합니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				String member=memberTextField.getText();
				if(member.length()==0)
				{
					JOptionPane.showMessageDialog(null,"회사번호가 비어 있습니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				int memberIdx=Integer.parseInt(member);
				String addr=otherTextField.getText();
				if(addr.length()==0)
				{
					JOptionPane.showMessageDialog(null,"주소가 비어 있습니다.","경고",JOptionPane.WARNING_MESSAGE);
					return;
				}
				if(rsl.releasedStart(prodCode, memberIdx, amount, otherTextField.getText(),addressTextField.getText())==true)
				{
					JOptionPane.showMessageDialog(null,"출고가 완료되었습니다.","알림",JOptionPane.INFORMATION_MESSAGE);
					releaseButton.setEnabled(false);
					awt.reLoad();
					//출고 완료
				}
				else
				{
					JOptionPane.showMessageDialog(null,"출고 작업 중 오류가 발생했습니다.\n물품코드나 회사번호를 확인해 주십시오.","오류",JOptionPane.ERROR_MESSAGE);
					//문제 발생, 보통은 재고 딸려서 이게 나올 것
				}
			}
		});
		releaseButton.setIcon(new ImageIcon(ReleaseAWT2.class.getResource("/warehouse/images/releaseBtn2.png")));
		releaseButton.setPreferredSize(new Dimension(80,48));
		frame.getContentPane().add(releaseButton, BorderLayout.SOUTH);
	}
	public void zaOpen()
	{
		za=new ZipcodeAWT(this);
	}

}
