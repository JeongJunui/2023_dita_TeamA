package warehouse;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Panel;
import javax.swing.BoxLayout;
import java.awt.GridLayout;

public class ReleaseAWT2 {

	private JFrame frame;
	private JTextField codeTextField;
	private JTextField amountTextField;
	private JTextField memberTextField;
	private JTextField roadAddressTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReleaseAWT2 window = new ReleaseAWT2();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ReleaseAWT2() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel mainPanel=new JPanel();
		FlowLayout flowLayout = (FlowLayout) mainPanel.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(20);
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.setBackground(new Color(255, 255, 255));
		frame.getContentPane().add(mainPanel,BorderLayout.CENTER);
		
		JLabel titleLabel = new JLabel("");
		titleLabel.setIcon(new ImageIcon(ReleaseAWT2.class.getResource("/warehouse/images/releaseTitle2.png")));
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
		
		JButton addressButton = new JButton("주소찾기");
		addressPanel.add(addressButton);
		addressButton.setPreferredSize(new Dimension(110,20));
		
		Panel roadAddressPanel = new Panel();
		FlowLayout flowLayout_5 = (FlowLayout) roadAddressPanel.getLayout();
		flowLayout_5.setHgap(10);
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		roadAddressPanel.setBackground(new Color(255, 255, 255));
		mainPanel.add(roadAddressPanel);
		
		JLabel roadAddressLabel = new JLabel("도로명주소");
		roadAddressPanel.add(roadAddressLabel);
		roadAddressLabel.setPreferredSize(new Dimension(80,20));
		
		roadAddressTextField = new JTextField();
		roadAddressPanel.add(roadAddressTextField);
		roadAddressTextField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("* 표시는 필수 입력 ");
		mainPanel.add(lblNewLabel);
		
		JButton releaseButton = new JButton("");
		releaseButton.setIcon(new ImageIcon(ReleaseAWT2.class.getResource("/warehouse/images/releaseBtn2.png")));
		frame.getContentPane().add(releaseButton, BorderLayout.SOUTH);
	}

}
