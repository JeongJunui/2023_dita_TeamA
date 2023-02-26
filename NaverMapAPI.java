package warehouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
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
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.json.JSONArray;
import org.json.JSONObject;

public class NaverMapAPI extends JFrame implements ActionListener {
	static Vector<String> attachmentFiles; // 주소가 저장될 위
	Vector<String> addressX; // 경도
	Vector<String> addressY; // 위도
	private String clientId = "n03gkha64w";
	private String clientSecret = "rR6Gw5aPo8ek0pe7Uy6OkcVB6e3ANQhGPacfVNKP";
	String geocodingAddress;
	JComboBox address;
	JLabel resAddress, resX, resY, jibunAddress;
	JLabel imageLabel;
	Vector<String> addressVector;
	String x;
	String y;
	public NaverMapAPI() {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		setTitle("출고 지도");
		setLocationRelativeTo(null);// 창이 가운데 나오게
		setResizable(false);// 창의 크기를 변경하지 못하게
		Container c = getContentPane(); // 지도 이미지 컨테이너
		imageLabel = new JLabel(); // JFrame 안쪽 영역 상단에 들어갈 지도보기
		
		JPanel pan = new JPanel();
		pan.setBounds(0, 0, 850, 100);
		pan.setBackground(Color.WHITE); // 위쪽 패널
	

		addressVector = new Vector<String>();
		addressVector.add("부산광역시 승학로 233번길 50");
		addressVector.add("부산광역시 승학로 233번길 33");
		
		JLabel addressLbl = new JLabel("출고 주소"); // JFrame 안쪽 영역 상단에 들어갈 주소입력
		address = new JComboBox(addressVector);

		pan.add(addressLbl);
		pan.add(address);
		address.addActionListener(this);

		JPanel pan1 = new JPanel();
		
		pan1.setLayout(new GridLayout(4, 1)); // 지도 하단 그리드 4행 1열로 생성.
		resAddress = new JLabel("도로명"); // 그리드 1행에 들어갈 도로명
		resX = new JLabel("경도"); // 그리드 3행에 들어갈 경도
		resY = new JLabel("위도"); // 그리드 4행에 들어갈 위도
		
		pan1.add(resAddress);
		pan1.add(resX);
		pan1.add(resY);
		pan1.setBackground(Color.WHITE);
		c.add(BorderLayout.NORTH, pan); // 상단 pan 세팅
		c.add(BorderLayout.EAST, imageLabel); // 센터 imageLabel 세팅
		c.add(BorderLayout.WEST, pan1); // 하단 pan1 세팅

		setSize(900, 550);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		geocodingAddress = address.getSelectedItem().toString();
		try {
			getGeocoding(geocodingAddress);
		} catch (Exception e1) {
			e1.printStackTrace();
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

		//addressX.add(x); // 벡터에 경도값 저장
		//addressY.add(y); // 백터에 위도값 저장
		
		mapService();
	}
	
	public void mapService() {
	String URL_STATICMAP = "https://naveropenapi.apigw.ntruss.com/map-static/v2/raster?";
		
		try {
			String pos = URLEncoder.encode(x + " " + y, "UTF-8");
			URL_STATICMAP += "center=" + x + "," + y;
			URL_STATICMAP += "&level=16&w=650&h=350";
			URL_STATICMAP += "&markers=type:t|size:mid|pos:" + pos + "|label:" + URLEncoder.encode(geocodingAddress, "UTF-8");
			
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
				File file = new File(tempName + ".jpg");	// 파일 생성.
				
				file.createNewFile();
				
				OutputStream out = new FileOutputStream(file);
				
				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read);	// 파일 작성
				}
				
				is.close();
				ImageIcon img = new ImageIcon(file.getName());
				imageLabel.setIcon(img);
				resAddress.setText(geocodingAddress);
				//jibunAddress.setText();
				resX.setText(x);
				resY.setText(y);
				
			} else {
				System.out.println(responseCode);
			}
			
		} catch(Exception e) {
			System.out.println(e);
		}
	

	}

	public static void main(String[] args) {
		NaverMapAPI naverAPI = new NaverMapAPI();
	}
}
