package warehouse;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverMapAPI extends JFrame implements ActionListener {
	private String clientId = "n03gkha64w";
	private String clientSecret = "rR6Gw5aPo8ek0pe7Uy6OkcVB6e3ANQhGPacfVNKP";
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	Vector<String> addressX; // 경도
	Vector<String> addressY; // 위도
	String geocodingAddress;
	JComboBox address;
	JPanel titlePanel, mapImagePanel, addressPanel, addressInfoPanel;
	JLabel resAddresLabel, resXYLabel, addressInfoLabel, productCodeLabel, releaseCountLabel;
	JLabel productCodeLabel2, releaseCountLabel2;
	JLabel resAddress, resX, resY;
	JLabel addressLbl;
	JLabel imageLabel, titleLabel, addressInfoTitle, mapSearch;
	JButton searchBtn;
	JTextField mapSearchTextField;
	String x;
	String y;
	String code;

	public NaverMapAPI() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("출고 지도");
		setSize(900, 550);
		setResizable(false);// 창의 크기를 변경하지 못하게
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setLayout(null);
		titlePanel = new JPanel() { // 타이틀 패널
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(".\\images\\mapTitlePanel.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		titlePanel.setBounds(0, 0, 883, 60); // 타이틀 패널
		titlePanel.setLayout(null);

		titleLabel = new JLabel();
		titleLabel.setIcon(new ImageIcon(".\\images\\mapTitle.png"));
		titleLabel.setBounds(20, 7, 150, 50);
		titlePanel.add(titleLabel);

		mapImagePanel = new JPanel() { // 이미지 라벨
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(".\\images\\mapImagePanel.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		mapImagePanel.setBounds(223, 122, 658, 386);
		mapImagePanel.setLayout(null);

		imageLabel = new JLabel(); // 도로명 주소 지도 이미지 라벨
		imageLabel.setBounds(4, 0, 660, 385);
		mapImagePanel.add(imageLabel);

		addressPanel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(".\\images\\mapAddressPanel.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};

		addressPanel.setBounds(220, 59, 664, 66);
		addressPanel.setLayout(null);

		addressLbl = new JLabel("출고 주소"); // 주소 라벨
		addressLbl.setFont(new Font("맑은 고딕", Font.BOLD, 16));
		addressLbl.setForeground(new Color(36, 36, 36));
		addressLbl.setBounds(145, 18, 70, 25);

		address = new JComboBox(getTakeoutAddress()); // 도로명 주소 콤보 박스
		address.setBounds(225, 18, 220, 30);
		address.addActionListener(this);

		addressPanel.add(addressLbl); // 주소 이름
		addressPanel.add(address); // 주소 콤보박스

		addressInfoPanel = new JPanel() { // 주소 정보 패널
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(".\\images\\mapLeftPanel.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		addressInfoPanel.setBounds(0, 58, 225, 452);
		addressInfoPanel.setLayout(null);

		mapSearch = new JLabel(new ImageIcon(".\\images\\mapSearch.png")); // 주소 검색 라벨
		mapSearch.setBounds(12, 16, 200, 40);
		addressInfoPanel.add(mapSearch);

		searchBtn = new JButton();
		searchBtn.setBounds(24, 21, 28, 28);
		searchBtn.setBorderPainted(false);// 버튼 테두리 투명색
		searchBtn.setContentAreaFilled(false);// 버튼 투명색
		searchBtn.setFocusable(false);
		searchBtn.addActionListener(this);
		addressInfoPanel.add(searchBtn);

		mapSearchTextField = new JTextField(); // 주소 검색 필드
		mapSearchTextField.setBounds(60, 25, 145, 22);
		mapSearchTextField.setColumns(10);
		mapSearchTextField.setBorder(null);
		mapSearchTextField.addActionListener(this);
		addressInfoPanel.add(mapSearchTextField);

		addressInfoTitle = new JLabel("주소 정보"); // 주소 정보 제목
		addressInfoTitle.setBounds(65, 60, 150, 60);
		addressInfoTitle.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		addressInfoTitle.setForeground(new Color(119, 119, 119));
		addressInfoPanel.add(addressInfoTitle);

		resAddresLabel = new JLabel(new ImageIcon(".\\images\\resAddress.png")); // 도로명 주소 라벨
		resAddresLabel.setBounds(10, 120, 130, 50);
		addressInfoPanel.add(resAddresLabel);

		resAddress = new JLabel("도로명");
		resAddress.setBounds(25, 135, 200, 100);
		addressInfoPanel.add(resAddress);

		resXYLabel = new JLabel(new ImageIcon(".\\images\\resXY.png")); // 위도 경도 라벨
		resXYLabel.setBounds(13, 200, 115, 50);
		addressInfoPanel.add(resXYLabel);

		resX = new JLabel("경도");
		resX.setBounds(25, 213, 100, 100);
		addressInfoPanel.add(resX);

		resY = new JLabel("위도");
		resY.setBounds(25, 243, 100, 100);
		addressInfoPanel.add(resY);

		addressInfoLabel = new JLabel(new ImageIcon(".\\images\\addressInfo.png")); // 제품코드, 출고수량 라벨
		addressInfoLabel.setBounds(25, 310, 155, 50);
		addressInfoPanel.add(addressInfoLabel);

		productCodeLabel = new JLabel("제품코드");
		productCodeLabel.setBounds(25, 350, 155, 50);
		addressInfoPanel.add(productCodeLabel);

		productCodeLabel2 = new JLabel();
		productCodeLabel2.setBounds(80, 350, 200, 50);
		addressInfoPanel.add(productCodeLabel2);

		releaseCountLabel = new JLabel("출고수량");
		releaseCountLabel.setBounds(25, 380, 155, 50);
		addressInfoPanel.add(releaseCountLabel);

		releaseCountLabel2 = new JLabel();
		releaseCountLabel2.setBounds(80, 380, 200, 50);
		addressInfoPanel.add(releaseCountLabel2);

		add(titlePanel);
		add(mapImagePanel);
		add(addressPanel);
		add(addressInfoPanel);

		setVisible(true);
	}

	// 주소 콤보박스 내용 반환
	public Vector<String> getTakeoutAddress() {
		Vector<String> takeoutAddress = new Vector<String>();
		String sql = null;
		try {
			pool = DBConnectionMgr.getInstance();
			sql = "SELECT distinct TAKEOUT_ADDRESS\r\n" + "FROM takeout_log";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				takeoutAddress.add(rs.getString("TAKEOUT_ADDRESS"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
		return takeoutAddress;
	}

	// 제품코드, 출고수량 반환
	public String getTakeoutAddress2(boolean check) {
		String sql = null;
		code = "";

		try {
			pool = DBConnectionMgr.getInstance();
			sql = "SELECT p.PROD_CODE, t.TAKEOUT_AMOUNT\r\n" + "FROM takeout_log t, product p, member m\r\n"
					+ "where t.PROD_CODE = p.PROD_CODE\r\n"
					+ "	and t.MEMBER_IDX = m.MEMBER_IDX and t.TAKEOUT_ADDRESS like '";

			con = pool.getConnection();

			pstmt = con.prepareStatement(sql + geocodingAddress + "'" + "ORDER BY t.TAKEOUT_DATE DESC");
			rs = pstmt.executeQuery();

			if (check == true) {
				while (rs.next()) {
					code += rs.getString("p.PROD_CODE") + " ";

				}
			} else {
				while (rs.next()) {
					code += rs.getString("t.TAKEOUT_AMOUNT") + " ";
				}
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				rs.close();
				pstmt.close();
				con.close();
			} catch (Exception e2) {

			}
		}
		return code;
	}

	// textField 테두리 없애는 메소드
	public void setBorder(Border border) {

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == searchBtn || obj == mapSearchTextField) {
			geocodingAddress = mapSearchTextField.getText();
			try {
				getGeocoding(geocodingAddress);
			} catch (Exception e1) {
				e1.printStackTrace();
				JOptionPane.showMessageDialog(null, "존재하지 않는 주소입니다.", "경고", JOptionPane.WARNING_MESSAGE);
			}
		} else if (obj == address) {
			geocodingAddress = address.getSelectedItem().toString();
			try {
				getGeocoding(geocodingAddress);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
	}

	public void getGeocoding(String address) throws Exception {
		// 주소 입력 -> 위도, 경도 좌표 추출.
		address = URLEncoder.encode(address, "UTF-8");

		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address; // json
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
		con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
		int responseCode = con.getResponseCode();
		BufferedReader br;

		if (responseCode == 200) { // 정상 호출
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // 에러 발생
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();

		// 받아온 JSON 데이터 파싱하기
		// JSON 라이브러리(org.json)를 이용하여 파싱
		JSONObject jsonObj = new JSONObject(response.toString());
		JSONArray arr = jsonObj.getJSONArray("addresses");
		JSONObject obj = arr.getJSONObject(0);
		x = obj.getString("x"); // 경도
		y = obj.getString("y"); // 위도

		System.out.println("위도: " + y + ", 경도: " + x);
		mapService();
	}

	public void mapService() {
		String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";

		try {
			String pos = URLEncoder.encode(x + " " + y, "UTF-8");
			URL_STATICMAP += "center=" + x + "," + y;
			URL_STATICMAP += "&level=16&w=650&h=370";
			URL_STATICMAP += "&markers=type:t|size:mid|pos:" + pos + "|label:"
					+ URLEncoder.encode(geocodingAddress, "UTF-8");

			URL url = new URL(URL_STATICMAP);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
			con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);

			int responseCode = con.getResponseCode();
			BufferedReader br;

			// 정상호출인 경우.
			if (responseCode == 200) {
				InputStream is = con.getInputStream();

				int read = 0;
				byte[] bytes = new byte[1024];

				// 랜덤 파일명으로 파일 생성
				String tempName = Long.valueOf(new Date().getTime()).toString();

				File file = new File(".\\mapImages\\" + tempName + ".jpg"); // 파일 생성.
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				file.createNewFile();

				OutputStream out = new FileOutputStream(file);

				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read); // 파일 작성
				}

				is.close();
				ImageIcon img = new ImageIcon(file.getAbsolutePath());
				imageLabel.setIcon(img);
				resAddress.setText(geocodingAddress);
				resX.setText("X: " + x);
				resY.setText("Y: " + y);
				productCodeLabel.setText("제품코드");
				productCodeLabel2.setText("");
				releaseCountLabel.setText("출고수량");
				releaseCountLabel2.setText("");

				for (int i = 0; i < address.getItemCount(); i++) {
					if (geocodingAddress.equals(address.getItemAt(i).toString())) {
						productCodeLabel.setText("제품코드:");
						productCodeLabel2.setText(getTakeoutAddress2(true));
						releaseCountLabel.setText("출고수량:");
						releaseCountLabel2.setText(getTakeoutAddress2(false));
					}
				}
				code = "";

			} else {
				System.out.println(responseCode);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
