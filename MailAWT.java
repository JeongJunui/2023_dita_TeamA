package warehouse;

import java.awt.Color;
import java.awt.Desktop.Action;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Vector;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

import org.apache.commons.validator.routines.EmailValidator;

public class MailAWT extends JFrame implements ActionListener {
	private JPanel p1;
	private JPanel letterEventPanel;
	private JLabel mailTitle, receiver, sendTitle, mailAttach, statisticsFile, sendAttach;
	private JLabel statistics1, statistics2, statistics3;
	private JLabel fontSize, fontSizePX;
	private Font font;
	private JCheckBox checkBox1, checkBox2, checkBox3;
	private JLabel mailBar, mailBar2;
	private JButton myPCBtn, closeBtn, sendBtn;
	private JButton fontSizeUpBtn, fontSizeDownBtn;
	private JButton boldBtn, boldBtn2, italicBtn, italicBtn2, textColorBtn ;
	private JTextField recieveTextField, titleTextField;
	private JComboBox fontBox;
	private JTextArea textArea, attachTextArea;
	private JScrollPane scrollPane;
	Vector<String> attachmentFiles; // 첨부 파일 경로
	Vector<String> attachmentFiles_1; // 첨부 파일 경로 textArea에 출력
	String fileName1_1 = "";
	String fontFamily = "굴림" ;
	String [] fontColor = {"0","0","0"} ;
	String [] fontSizeArr = {"14", "18", "24", "36", "48"};
	String r, g, b;
	String CssResult = "";
	String CssColor = "color: black;" , CssFontWeight = "", CssFontStyle = "", CssFontFamily = "" , CssFontSize = "";	
	Color color = Color.BLACK;
	int[] rgb;
	int j = 0;
	String checkFiles = "";
	boolean isValid ;
	int bold = 0, italic = 0;

	public MailAWT() {
		setTitle("메일");
		setSize(450, 700);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);

		mailPanel();
		setVisible(true);
	}

	public void mailPanel() {
		p1 = new JPanel();
		p1.setBackground(Color.white);
		p1.setLayout(null);
		p1.setBounds(0, 0, 434, 661);
		// 타이틀 라벨
		mailTitle = new JLabel();
		mailTitle.setIcon(new ImageIcon(".\\images\\mailTitle.png"));
		mailTitle.setBounds(20, 10, 94, 45);
		// 받는사람 라벨
		receiver = new JLabel("받는사람");
		receiver.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		receiver.setForeground(new Color(118, 118, 120));
		receiver.setBounds(20, 65, 60, 28);
		// 받는사람 입력 필드
		recieveTextField = new JTextField();
		recieveTextField.setBounds(95, 70, 300, 22);
		recieveTextField.setColumns(10);
		recieveTextField.setBorder(null);
		recieveTextField.setFocusable(true);
		mailBar = new JLabel();
		mailBar.setIcon(new ImageIcon(".\\images\\mailBar.png"));
		mailBar.setBounds(90, 78, 311, 34);
		// 제목 라벨
		sendTitle = new JLabel("제목");
		sendTitle.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		sendTitle.setForeground(new Color(118, 118, 120));
		sendTitle.setBounds(20, 110, 60, 28);
		// 제목 입력 필드
		titleTextField = new JTextField();
		titleTextField.setBounds(95, 112, 300, 22);
		titleTextField.setColumns(10);
		titleTextField.setBorder(null);
		mailBar2 = new JLabel();
		mailBar2.setIcon(new ImageIcon(".\\images\\mailBar.png"));
		mailBar2.setBounds(90, 120, 311, 34);
		// 파일첨부 라벨
		mailAttach = new JLabel("파일첨부");
		mailAttach.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		mailAttach.setForeground(new Color(118, 118, 120));
		mailAttach.setBounds(20, 155, 60, 28);
		// 내 pc 파일첨부 버튼
		myPCBtn = new RoundedButton("내 PC");
		myPCBtn.setFont(new Font("맑은 고딕", Font.ITALIC, 13));
		myPCBtn.setBounds(90, 157, 50, 25);
		myPCBtn.addActionListener(this);
		// 파일첨부 TextArea
		attachTextArea = new JTextArea();
		attachTextArea.setBounds(95, 254, 300, 25);
		attachTextArea.setColumns(10);
		attachTextArea.setEditable(false);
		// 파일첨부 박스 이미지 라벨
		sendAttach = new JLabel();
		sendAttach.setIcon(new ImageIcon(".\\images\\mailAttach.png"));
		sendAttach.setBounds(90, 220, 340, 70);
		// 통계 파일 라벨
		statisticsFile = new JLabel("통계파일");
		statisticsFile.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		statisticsFile.setForeground(new Color(118, 118, 120));
		statisticsFile.setBounds(20, 188, 60, 28);
		// 입고내역 라벨
		statistics1 = new JLabel("입고내역");
		statistics1.setFont(new Font("", Font.PLAIN, 12));
		statistics1.setForeground(new Color(118, 118, 120));
		statistics1.setBounds(90, 190, 60, 28);
		// 입고내역 체크박스
		checkBox1 = new JCheckBox();
		checkBox1.setBounds(150, 193, 20, 20);
		// 출고내역 라벨
		statistics2 = new JLabel("출고내역");
		statistics2.setFont(new Font("", Font.PLAIN, 12));
		statistics2.setForeground(new Color(118, 118, 120));
		statistics2.setBounds(195, 190, 60, 28);
		// 출고내역 체크박스
		checkBox2 = new JCheckBox();
		checkBox2.setBounds(255, 193, 20, 20);
		// 재고내역 라벨
		statistics3 = new JLabel("재고내역");
		statistics3.setFont(new Font("", Font.PLAIN, 12));
		statistics3.setForeground(new Color(118, 118, 120));
		statistics3.setBounds(300, 190, 60, 28);
		// 재고내역 체크박스
		checkBox3 = new JCheckBox();
		checkBox3.setBounds(360, 193, 20, 20);
		// 메일 입력 textArea
		textArea = new JTextArea(10, 20);
		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(WIDTH + -2, 340, 452, 260);
		// 파일첨부 삭제 버튼
		closeBtn = new JButton("x");
		closeBtn.setBounds(54, 189, 100, 100);
		closeBtn.setForeground(new Color(118, 118, 120));
		closeBtn.setFocusPainted(false);
		closeBtn.setBorderPainted(false);
		closeBtn.setContentAreaFilled(false);
		closeBtn.addActionListener(this);
		// 메일 보내기 버튼
		sendBtn = new JButton(new ImageIcon(".\\images\\sendBtn.png"));
		sendBtn.setRolloverIcon(new ImageIcon(".\\images\\sendBtn2.png"));
		sendBtn.setBounds(340, 607, 83, 50);
		sendBtn.setFocusPainted(false);
		sendBtn.setBorderPainted(false);
		sendBtn.setContentAreaFilled(false);
		sendBtn.addActionListener(this);

		attachmentFiles = new Vector<String>();
		attachmentFiles_1 = new Vector<String>();
		
		p1.add(mailTitle);
		p1.add(receiver);
		p1.add(recieveTextField);
		p1.add(mailBar);
		p1.add(sendTitle);
		p1.add(titleTextField);
		p1.add(mailBar2);
		p1.add(mailAttach);
		p1.add(myPCBtn);
		p1.add(statisticsFile);
		p1.add(statistics1);
		p1.add(checkBox1);
		p1.add(statistics2);
		p1.add(checkBox2);
		p1.add(statistics3);
		p1.add(checkBox3);
		p1.add(attachTextArea);
		p1.add(sendAttach);
		p1.add(scrollPane);
		p1.add(sendBtn);
		
		letterEventPanel();
		
		add(closeBtn);
		add(p1);	
	}
	// 글자 글꼴 색상 변경 패널
		public void letterEventPanel() {	
			letterEventPanel = new JPanel();
			letterEventPanel.setBounds(0,300, 434, 50);
			letterEventPanel.setLayout(null);
			letterEventPanel.setBackground(new Color(246, 246, 246));
			// textArea 기본 폰트 지정
			font = new Font("굴림", Font.PLAIN, Integer.parseInt(fontSizeArr[j]));
			textArea.setFont(font);
			//textArea.setForeground(color.BLACK);
			
			// 폰트 콤보박스
			fontBox = new JComboBox(); 
			fontBox.setFont(font);
			
		    Font font1 = new Font("맑은 고딕", Font.PLAIN, 14);
		    Font font2 = new Font("굴림", Font.PLAIN, 14);
		    Font font3 = new Font("굴림체", Font.PLAIN, 14);
		    Font font4 = new Font("돋움", Font.PLAIN, 14);
		    Font font5 = new Font("바탕", Font.PLAIN, 14);
		    Font font6 = new Font("바탕체", Font.PLAIN, 14);
		    Font font7 = new Font("궁서", Font.PLAIN, 14);
		    Font font8 = new Font("궁서체", Font.PLAIN, 14);
		    Font font9 = new Font("Arial", Font.PLAIN, 14);
		    
		    fontBox.addItem(new ComboItem("맑은 고딕", font1));
		    fontBox.addItem(new ComboItem("굴림", font2));
		    fontBox.addItem(new ComboItem("굴림체", font3));
		    fontBox.addItem(new ComboItem("돋움", font4));
		    fontBox.addItem(new ComboItem("바탕", font5));
		    fontBox.addItem(new ComboItem("바탕체", font6));
		    fontBox.addItem(new ComboItem("궁서", font7));
		    fontBox.addItem(new ComboItem("궁서체", font8));
		    fontBox.addItem(new ComboItem("Arial", font9));
		    fontBox.setRenderer(new ComboRenderer());
		    fontBox.setSelectedIndex(1); // 콤보박스 두 번째 아이템을 먼저 보여줌	
			fontBox.setBounds(0, 1, 95, 39);
			fontBox.addActionListener(this);
			// 폰트 사이즈
			fontSize = new JLabel(fontSizeArr[0]);
			fontSize.setFont(new Font("돋음", Font.PLAIN, 15));
			fontSize.setBounds(117, 1, 17, 38);
			// 폰트 사이즈 px 
			fontSizePX = new JLabel("px");
			fontSizePX.setFont(new Font("돋음", Font.PLAIN, 15));
			fontSizePX.setBounds(134, 1, 17, 38);
			// 폰트 사이즈 업 버튼
			fontSizeUpBtn = new JButton(new ImageIcon(".\\images\\fontSIzeUpBtn.png"));
			fontSizeUpBtn.setBounds(166, 4, 16, 16);
			fontSizeUpBtn.setFocusPainted(false);
			fontSizeUpBtn.setBorderPainted(false);
			fontSizeUpBtn.setContentAreaFilled(false);
			fontSizeUpBtn.addActionListener(this);
			// 폰트 사이즈 다운 버튼
			fontSizeDownBtn = new JButton(new ImageIcon(".\\images\\fontSIzeDownBtn.png"));
			fontSizeDownBtn.setBounds(166, 22, 16, 16);
			fontSizeDownBtn.setFocusPainted(false);
			fontSizeDownBtn.setBorderPainted(false);
			fontSizeDownBtn.setContentAreaFilled(false);
			fontSizeDownBtn.addActionListener(this);	
			// 글씨체 Bold 버튼
			boldBtn = new JButton(new ImageIcon(".\\images\\bold.png"));
			boldBtn.setBounds(195, 8, 25, 27);
			boldBtn.setFocusPainted(false);
			boldBtn.setBorderPainted(false);
			boldBtn.setContentAreaFilled(false);
			boldBtn.addActionListener(this);
			
			boldBtn2 = new JButton(new ImageIcon(".\\images\\bold2.png"));
			boldBtn2.setBounds(195, 8, 25, 27);
			boldBtn2.setFocusPainted(false);
			boldBtn2.setBorderPainted(false);
			boldBtn2.setContentAreaFilled(false);	
			boldBtn2.addActionListener(this);
			// 글씨체 기울임(italic) 버튼
			italicBtn = new JButton(new ImageIcon(".\\images\\italic.png"));
			italicBtn.setBounds(225, 8, 25, 27);
			italicBtn.setFocusPainted(false);
			italicBtn.setBorderPainted(false);
			italicBtn.setContentAreaFilled(false);
			italicBtn.addActionListener(this);
			
			italicBtn2 = new JButton(new ImageIcon(".\\images\\italic2.png"));
			italicBtn2.setBounds(225, 8, 25, 27);
			italicBtn2.setFocusPainted(false);
			italicBtn2.setBorderPainted(false);
			italicBtn2.setContentAreaFilled(false);
			italicBtn2.addActionListener(this);	
			// 글자 색상 변경 버튼
			textColorBtn = new JButton(new ImageIcon(".\\images\\textColor.png"));
			textColorBtn.setRolloverIcon(new ImageIcon(".\\images\\textColor2.png"));
			textColorBtn.setBounds(260, 7, 25, 27);
			textColorBtn.setFocusPainted(false);
			textColorBtn.setBorderPainted(false);
			textColorBtn.setContentAreaFilled(false);
			textColorBtn.addActionListener(this);
					
			letterEventPanel.add(fontBox);
			letterEventPanel.add(fontSize);
			letterEventPanel.add(fontSizePX);
			letterEventPanel.add(fontSizeUpBtn);
			letterEventPanel.add(fontSizeDownBtn);
			letterEventPanel.add(boldBtn);
			letterEventPanel.add(boldBtn2);
			
			boldBtn2.setVisible(false);
			letterEventPanel.add(italicBtn);
			letterEventPanel.add(italicBtn2);
			italicBtn2.setVisible(false);
			letterEventPanel.add(textColorBtn);
			p1.add(letterEventPanel);		
		}
	// textField 테두리 없애는 메소드
	public void setBorder(Border border) {

	}
	// 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JFileChooser chooser = new JFileChooser(new File("c:\\")); // 파일 다이얼로그 생성
		if (obj == myPCBtn) {
			// 파일 다이얼로그 출력
			int ret = chooser.showOpenDialog(null);
			if (ret != JFileChooser.APPROVE_OPTION) { // 사용자가 창을 강제로 닫았거나 취소 버튼을 누른 경우
				JOptionPane.showMessageDialog(null, "파일을 선택하지 않았습니다", "경고", JOptionPane.WARNING_MESSAGE);
				return;
			}
		
			attachmentFiles.add(chooser.getSelectedFile().getName());
			attachmentFiles_1.add(chooser.getSelectedFile().getAbsolutePath());
			for (int i = 0; i < attachmentFiles_1.size(); i++) {
				System.out.println(attachmentFiles_1.get(i));
			}
			for (int i = 0; i < attachmentFiles.size(); i++) {
				checkFiles +=  attachmentFiles.get(i)+ "  ";	
			}
			attachTextArea.setText(checkFiles);
			checkFiles = "";
		} else if (obj == fontBox) { // 폰트 변경 콤보박스 
			fontFamily = fontBox.getSelectedItem().toString();
		} else if (obj == fontSizeUpBtn) { // 폰트 사이즈 업 버튼	
			if(j>=0 && j<4) {
				fontSize.setText(fontSizeArr[++j]);
			}
		} else if (obj == fontSizeDownBtn) { // 폰트 사이즈 다운 버튼
			if(j>=1 && j<5) {
				fontSize.setText(fontSizeArr[--j]);
			}
		} else if (obj == boldBtn) { // 텍스트 Bold 버튼
			boldBtn.setVisible(false);
			boldBtn2.setVisible(true);
			bold = 1;
		} else if (obj == boldBtn2) {
			boldBtn2.setVisible(false);
			boldBtn.setVisible(true);
			bold = 0;
		} else if (obj == italicBtn) { // 텍스트 italic 버튼
			italicBtn.setVisible(false);
			italicBtn2.setVisible(true);
			italic = 2;
		} else if (obj == italicBtn2) {
			italicBtn2.setVisible(false);
			italicBtn.setVisible(true);		
			italic = 0;
		} else if (obj == textColorBtn) { // 텍스트 색상변경 버튼
			// 컬러선택탐색시
			JColorChooser cc = new JColorChooser();
			// 색상선택기 실행 (부모객체, 제목, 초기색상)
			color = cc.showDialog(this, "글자색", Color.RED);		
			if (color == null) // 사용자가 창을 강제로 닫았거나 취소 버튼을 누른 경우
				return;
			textArea.setForeground(color);	
		} else if (obj == sendBtn) {	
			if (!attachTextArea.getText().equals("") || !textArea.getText().equals("")) {
				// 폰트 지정(컬러 10진수 -> 16진수 변환)
				String str3 = color.toString();
				str3 = str3.replaceAll("java.awt.Color","");
				str3 = str3.replaceAll("r=","");
				str3 = str3.replaceAll("g=","");
				str3 = str3.replaceAll("b=","");
				
				StringBuilder myString2 = new StringBuilder(str3);
				myString2.deleteCharAt(0);
				myString2.deleteCharAt(myString2.length()-1);
				
				fontColor = myString2.toString().split(","); // rgb 색 배열에 저장
				rgb = Arrays.stream(fontColor).mapToInt(Integer::parseInt).toArray(); // rgb 색 int 형 배열에 저장	
				r = Integer.toHexString(rgb[0]);
				g = Integer.toHexString(rgb[1]);
				b = Integer.toHexString(rgb[2]);
				if(r.equals("0")) {
					r=r+"0";
				} else if(g.equals("0")) {
					g=g+"0";
				} else if(b.equals("0")) {
					b=b+"0";		
				}
							
				// html 문서에 저장할 css style 저장
				CssColor = "color: #"+r+g+b+";";
				CssFontFamily = "font-family: "+"\""+fontFamily+"\";";
				CssFontSize = "font-size: "+fontSizeArr[j]+"px;";	
				if(bold == 1) {
					CssFontWeight = "font-weight: bold;";	
				}
				if(italic == 2) {
					CssFontStyle = "font-style: italic;";
				}
				CssResult = CssColor+CssFontSize+CssFontStyle+CssFontWeight;
				
				String toEmail = recieveTextField.getText();
				isValid = isValidEmailAddress(toEmail);
				String toTitle = titleTextField.getText();
				String setMessage = "<html><head><meta charset='ms949'/></head><body><table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">\r\n"
						+ "        <tr>\r\n"
						+ "            <td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0;\">\r\n"
						+ "                <img src=\"https://velog.velcdn.com/images/thalsghks/post/ea3f376f-f223-4e0f-92af-a96985ef3ddd/image.png\" alt=\"Creating Email Magic\" width=\"360\" height=\"230\" style=\"display: block;\" />\r\n"
						+ "                A조 \r\n"
						+ "               </td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <td bgcolor=\"white\" style=\"padding: 40px 30px 40px 30px; \">\r\n"
						+ "                <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
						+ "                    <tr>\r\n"
						+ "                     <td>\r\n"
						+ "                      Java Mail Api 를 통해 보낸 내용\r\n"
						+ "                     </td>\r\n"
						+ "                    </tr>\r\n"
						+ "                    <tr>\r\n"
						+ "                     <td style=\"padding: 20px 0 30px 0;  text-shadow: black 0.01em 0.01em 0.01em;" + CssResult + "\">"
						+ 				          textArea.getText()
						+ "                     </td>\r\n"
						+ "                    </tr>\r\n"
						+ "                    <tr>\r\n"
						+ "                     <td>\r\n"
						+ "                      자바 프로젝트 샘플\r\n"
						+ "                     </td>\r\n"
						+ "                    </tr>\r\n"
						+ "                   </table>\r\n"
						+ "                   <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
						+ "                    <tr>\r\n"
						+ "                     <td width=\"260\" valign=\"top\">\r\n"
						+ "                      <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
						+ "                       <tr>\r\n"
						+ "                        <td>\r\n"
						+ "                         <img src=\"https://velog.velcdn.com/images/thalsghks/post/f8a10790-c65d-4018-adeb-edc3647381ad/image.png\" alt=\"\" width=\"100%\" height=\"140\" style=\"display: block;\" />\r\n"
						+ "                        </td>\r\n"
						+ "                       </tr>\r\n"
						+ "                       <tr>\r\n"
						+ "                        <td style=\"padding: 25px 0 0 0;\">\r\n"
						+ "                            자바 프로젝트 메인 화면입니다. 입고, 출고, 통계 버튼을 눌러 각각의 기능을 이용하실 수 있습니다. 기존에 존재하는 기능을 최대한 활용하였습니다. \r\n"
						+ "                            더 많은 내용을 보고 싶다면 아래의 깃허브 주소를 참고하세요.\r\n"
						+ "                        </td>\r\n"
						+ "                       </tr>\r\n"
						+ "                      </table>\r\n"
						+ "                     </td>\r\n"
						+ "                     <td style=\"font-size: 0; line-height: 0;\" width=\"20\">\r\n"
						+ "                      &nbsp;\r\n"
						+ "                     </td>\r\n"
						+ "                     <td width=\"260\" valign=\"top\">\r\n"
						+ "                      <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
						+ "                       <tr>\r\n"
						+ "                        <td>\r\n"
						+ "                         <img src=\"https://velog.velcdn.com/images/thalsghks/post/5a93c921-9eb3-4284-acf3-b75592c3fdeb/image.png\" alt=\"\" width=\"100%\" height=\"140\" style=\"display: block;\" />\r\n"
						+ "                        </td>\r\n"
						+ "                       </tr>\r\n"
						+ "                       <tr>\r\n"
						+ "                        <td style=\"padding: 25px 0 0 0;\">\r\n"
						+ "                         입출고 내역 화면입니다. db에 저장된 입출고 내역을 각각 확인이 가능하고 추가적으로 검색 기능을 퉁해 \r\n"
						+ "                         원하는 제품을 손쉽게 검색하실 수 있습니다. 그 이외의 기능은 아래의 깃허브 주소를 참고하세요. 이상입니다.\r\n"
						+ "                        </td>\r\n"
						+ "                       </tr>\r\n"
						+ "                      </table>\r\n"
						+ "                     </td>\r\n"
						+ "                    </tr>\r\n"
						+ "                   </table>\r\n"
						+ "               </td>\r\n"
						+ "        </tr>\r\n"
						+ "        <tr>\r\n"
						+ "            <td bgcolor=\"#ee4c50\" style=\"padding: 30px 30px 30px 30px;\">\r\n"
						+ "                <table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\r\n"
						+ "                    <tr>\r\n"
						+ "                        <td width=\"75%\">\r\n"
						+ "                            &nbsp;©copySMH Korea Corporation All Rights Reserved.\r\n"
						+ "                           </td>\r\n"
						+ "                        <td align=\"right\">\r\n"
						+ "                            <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n"
						+ "                             <tr>\r\n"
						+ "                              <td>\r\n"
						+ "                               <a href=\"https://github.com/Sominhwan\">\r\n"
						+ "                                <img src=\"https://sominhwan.github.io/myPage_html/images/github.png\" alt=\"github\" width=\"35\" height=\"35\" style=\"display: block;\" border=\"0\" />\r\n"
						+ "                               </a>\r\n"
						+ "                              </td>\r\n"
						+ "                              <td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>\r\n"
						+ "                              <td>\r\n"
						+ "                               <a href=\"https://twitter.com/smh7527\">\r\n"
						+ "                                <img src=\"https://sominhwan.github.io/myPage_html/images/twitter.png\" alt=\"twitter\" width=\"35\" height=\"35\" style=\"display: block;\" border=\"0\" />\r\n"
						+ "                               </a>\r\n"
						+ "                              </td>\r\n"
						+ "                              <td>\r\n"
						+ "                                <a href=\"https://discord.gg/GERb7eD5\">\r\n"
						+ "                                 <img src=\"https://sominhwan.github.io/myPage_html/images/discord.png\" alt=\"discord\" width=\"38\" height=\"38\" style=\"display: block;\" border=\"0\" />\r\n"
						+ "                                </a>\r\n"
						+ "                               </td>\r\n"
						+ "                             </tr>\r\n"
						+ "                            </table>\r\n"
						+ "                           </td>             \r\n"
						+ "                    </tr>\r\n"
						+ "                   </table>\r\n"
						+ "               </td>        \r\n"
						+ "        </tr></body></html>";
				// 이메일 검증 검사
				if(isValid) {
					new SendMailSMTP(toEmail, toTitle, attachmentFiles_1, setMessage);
					fileName1_1 = "";
					attachTextArea.setText("");
					textArea.setText("");
					closeBtn.setVisible(false);
					p1.setVisible(false);
					new CompleteMail(this, toEmail);
				} else {
					JOptionPane.showMessageDialog(null, "올바르지 않는 이메일 형식입니다.", "실패", JOptionPane.ERROR_MESSAGE);		
				}
			}else {
				JOptionPane.showMessageDialog(null, "메일 보내기 실패!", "실패", JOptionPane.ERROR_MESSAGE);
			}
		} else if(obj == closeBtn) {
			attachTextArea.setText("");
			attachmentFiles.clear();
			attachmentFiles_1.clear();
		}
	
		System.out.println(CssColor+" "+CssFontFamily+" "+CssFontSize+" "+CssFontStyle+" "+CssFontWeight);
		font = new Font(fontFamily, bold + italic, Integer.parseInt(fontSizeArr[j]));
		textArea.setFont(font);
	}
	// 이메일 검증
	  public static boolean isValidEmailAddress(String toEmail) {
	        boolean isValid = false;
	        EmailValidator emailValidator = EmailValidator.getInstance();
	        isValid = emailValidator.isValid(toEmail);
	        if (!isValid) {
	            return false;
	        }

	        try {
	            InternetAddress internetAddress = new InternetAddress(toEmail);
	            internetAddress.validate();
	        } catch (AddressException e) {
	            e.printStackTrace();
	            return false;
	        }
	        return true;
	    }
}

// 둥근 버튼 생성 클래스
class RoundedButton extends JButton {
	public RoundedButton() {
		super();
		decorate();
	}

	public RoundedButton(String text) {
		super(text);
		decorate();
	}

	public RoundedButton(Action action) {
		super();
		decorate();
	}

	public RoundedButton(Icon icon) {
		super(icon);
		decorate();
	}

	public RoundedButton(String text, Icon icon) {
		super(text, icon);
		decorate();
	}

	protected void decorate() {
		setBorderPainted(false);
		setOpaque(false);
	}

	@Override
	protected void paintComponent(Graphics g) {
		Color c = new Color(234, 234, 234); // 배경색 결정
		Color o = new Color(0, 0, 0); // 글자색 결정
		int width = getWidth();
		int height = getHeight();
		Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		if (getModel().isArmed()) {
			graphics.setColor(c.darker());
		} else if (getModel().isRollover()) {
			graphics.setColor(c.LIGHT_GRAY);
		} else {
			graphics.setColor(c);
		}
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