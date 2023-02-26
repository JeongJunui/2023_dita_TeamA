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
	static Vector<String> attachmentFiles; // �ּҰ� ����� ��
	Vector<String> addressX; // �浵
	Vector<String> addressY; // ����
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
		setTitle("��� ����");
		setLocationRelativeTo(null);// â�� ��� ������
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		Container c = getContentPane(); // ���� �̹��� �����̳�
		imageLabel = new JLabel(); // JFrame ���� ���� ��ܿ� �� ��������
		
		JPanel pan = new JPanel();
		pan.setBounds(0, 0, 850, 100);
		pan.setBackground(Color.WHITE); // ���� �г�
	

		addressVector = new Vector<String>();
		addressVector.add("�λ걤���� ���з� 233���� 50");
		addressVector.add("�λ걤���� ���з� 233���� 33");
		
		JLabel addressLbl = new JLabel("��� �ּ�"); // JFrame ���� ���� ��ܿ� �� �ּ��Է�
		address = new JComboBox(addressVector);

		pan.add(addressLbl);
		pan.add(address);
		address.addActionListener(this);

		JPanel pan1 = new JPanel();
		
		pan1.setLayout(new GridLayout(4, 1)); // ���� �ϴ� �׸��� 4�� 1���� ����.
		resAddress = new JLabel("���θ�"); // �׸��� 1�࿡ �� ���θ�
		resX = new JLabel("�浵"); // �׸��� 3�࿡ �� �浵
		resY = new JLabel("����"); // �׸��� 4�࿡ �� ����
		
		pan1.add(resAddress);
		pan1.add(resX);
		pan1.add(resY);
		pan1.setBackground(Color.WHITE);
		c.add(BorderLayout.NORTH, pan); // ��� pan ����
		c.add(BorderLayout.EAST, imageLabel); // ���� imageLabel ����
		c.add(BorderLayout.WEST, pan1); // �ϴ� pan1 ����

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
		// �ּ� �Է� -> ����, �浵 ��ǥ ����.

		address = URLEncoder.encode(address, "UTF-8");

		String apiURL = "https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode?query=" + address; // json
		URL url = new URL(apiURL);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("X-NCP-APIGW-API-KEY-ID", clientId);
		con.setRequestProperty("X-NCP-APIGW-API-KEY", clientSecret);
		int responseCode = con.getResponseCode();
		BufferedReader br;

		if (responseCode == 200) { // ���� ȣ��
			br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		} else { // ���� �߻�
			br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		}
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			response.append(inputLine);
		}
		br.close();

		// �޾ƿ� JSON ������ �Ľ��ϱ�
		// JSON ���̺귯��(org.json)�� �̿��Ͽ� �Ľ�
		JSONObject jsonObj = new JSONObject(response.toString());
		JSONArray arr = jsonObj.getJSONArray("addresses");
		JSONObject obj = arr.getJSONObject(0);
		x = obj.getString("x"); // �浵
		y = obj.getString("y"); // ����

		System.out.println("����: " + y + ", �浵: " + x);

		//addressX.add(x); // ���Ϳ� �浵�� ����
		//addressY.add(y); // ���Ϳ� ������ ����
		
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
			
			// ����ȣ���� ���.
			if (responseCode == 200) {
				InputStream is = con.getInputStream();
				
				int read = 0;
				byte[] bytes = new byte[1024];
				
				// ���� ���ϸ����� ���� ����
				String tempName = Long.valueOf(new Date().getTime()).toString();
				File file = new File(tempName + ".jpg");	// ���� ����.
				
				file.createNewFile();
				
				OutputStream out = new FileOutputStream(file);
				
				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read);	// ���� �ۼ�
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
