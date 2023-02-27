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
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
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
	static Vector<String> attachmentFiles; // �ּҰ� ����� ��ġ
	private String clientId = "n03gkha64w";
	private String clientSecret = "rR6Gw5aPo8ek0pe7Uy6OkcVB6e3ANQhGPacfVNKP";
	Vector<String> addressX; // �浵
	Vector<String> addressY; // ����
	String geocodingAddress;
	JComboBox address;
	JPanel titlePanel, mapImagePanel, addressPanel, addressInfoPanel;
	JLabel resAddress, resX, resY, jibunAddress;
	JLabel addressLbl;
	JLabel imageLabel, titleLabel, addressInfoTitle, mapSearch;
	JButton searchBtn;
	JTextField mapSearchTextField;
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
		setSize(900, 550);
		setLayout(null);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // �����
		titlePanel = new JPanel() { // Ÿ��Ʋ �г�
			@Override
			public void paintComponent(Graphics g) {
				super.paintComponent(g);
				ImageIcon imageIcon = new ImageIcon(".\\images\\mapTitlePanel.png");
				Image image = imageIcon.getImage();
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
			}
		};
		titlePanel.setBounds(0, 0, 883, 60); // Ÿ��Ʋ �г�
		titlePanel.setLayout(null);

		titleLabel = new JLabel();
		titleLabel.setIcon(new ImageIcon(".\\images\\mapTitle.png"));
		titleLabel.setBounds(20, 7, 150, 50);
		titlePanel.add(titleLabel);

		mapImagePanel = new JPanel() { // �̹��� ��
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

		imageLabel = new JLabel(); // ���θ� �ּ� ���� �̹��� ��
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

		addressVector = new Vector<String>();
		addressVector.add("�λ걤���� ���з� 233���� 50");
		addressVector.add("�λ걤���� ���з� 233���� 33");

		addressLbl = new JLabel("��� �ּ�"); // �ּ� ��
		addressLbl.setFont(new Font("���� ���", Font.BOLD, 16));
		addressLbl.setForeground(new Color(36, 36, 36));
		addressLbl.setBounds(145, 18, 70, 25);

		address = new JComboBox(addressVector); // ���θ� �ּ� �޺� �ڽ�
		address.setBounds(225, 18, 220, 30);
		address.addActionListener(this);

		addressPanel.add(addressLbl); // �ּ� �̸�
		addressPanel.add(address); // �ּ� �޺��ڽ�

		addressInfoPanel = new JPanel() { // �ּ� ���� �г�
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

		mapSearch = new JLabel(new ImageIcon(".\\images\\mapSearch.png")); // �ּ� �˻� ��
		mapSearch.setBounds(12, 16, 200, 40);
		addressInfoPanel.add(mapSearch);

		searchBtn = new JButton();
		searchBtn.setBounds(24, 21, 28, 28);
		searchBtn.setBorderPainted(false);// ��ư �׵θ� �����
		searchBtn.setContentAreaFilled(false);// ��ư �����
		searchBtn.setFocusable(false);
		searchBtn.addActionListener(this);
		addressInfoPanel.add(searchBtn);

		mapSearchTextField = new JTextField(); // �ּ� �˻� �ʵ�
		mapSearchTextField.setBounds(60, 25, 145, 22);
		mapSearchTextField.setColumns(10);
		mapSearchTextField.setBorder(null);
		mapSearchTextField.addActionListener(this);
		addressInfoPanel.add(mapSearchTextField);

		addressInfoTitle = new JLabel("�ּ� ����"); // �ּ� ���� ����
		addressInfoTitle.setBounds(65, 60, 150, 60);
		addressInfoTitle.setFont(new Font("���� ���", Font.BOLD, 20));
		addressInfoTitle.setForeground(new Color(119, 119, 119));
		addressInfoPanel.add(addressInfoTitle);

		resAddress = new JLabel("���θ�");
		resAddress.setBounds(10, 90, 200, 100);
		addressInfoPanel.add(resAddress);

		resX = new JLabel("�浵");
		resX.setBounds(10, 115, 100, 100);
		addressInfoPanel.add(resX);

		resY = new JLabel("����");
		resY.setBounds(10, 140, 100, 100);
		addressInfoPanel.add(resY);

		add(titlePanel);
		add(mapImagePanel);
		add(addressPanel);
		add(addressInfoPanel);

		setVisible(true);
	}

	// textField �׵θ� ���ִ� �޼ҵ�
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
				JOptionPane.showMessageDialog(null, "�������� �ʴ� �ּ��Դϴ�.", "���", JOptionPane.WARNING_MESSAGE);
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

			// ����ȣ���� ���.
			if (responseCode == 200) {
				InputStream is = con.getInputStream();

				int read = 0;
				byte[] bytes = new byte[1024];

				// ���� ���ϸ����� ���� ����
				String tempName = Long.valueOf(new Date().getTime()).toString();

				File file = new File(".\\mapImages\\" + tempName + ".jpg"); // ���� ����.
				if (!file.getParentFile().exists()) {
					file.getParentFile().mkdirs();
				}

				file.createNewFile();

				OutputStream out = new FileOutputStream(file);

				while ((read = is.read(bytes)) != -1) {
					out.write(bytes, 0, read); // ���� �ۼ�
				}

				is.close();
				ImageIcon img = new ImageIcon(file.getAbsolutePath());
				imageLabel.setIcon(img);
				resAddress.setText(geocodingAddress);
				// jibunAddress.setText();
				resX.setText(x);
				resY.setText(y);

			} else {
				System.out.println(responseCode);
			}

		} catch (Exception e) {
			System.out.println(e);
		}
	}

//	public static void main(String[] args) {
//		NaverMapAPI naverAPI = new NaverMapAPI();
//	}
}
