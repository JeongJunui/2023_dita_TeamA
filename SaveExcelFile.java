package warehouse;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SaveExcelFile extends JFrame implements ActionListener {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	private JPanel p1;
	private JTextArea textArea;
	private JLabel title;
	private JScrollPane scrollPane;
	private JButton saveBtn;
	File selectedFile;

	public SaveExcelFile(MainAWT mainAWT) {
		setTitle("현 재고 리스트 저장하기");
		setSize(400, 400);// 프레임의 크기
		setResizable(false);// 창의 크기를 변경하지 못하게
		getContentPane().setBackground(new Color(255, 255, 255));
		setLocationRelativeTo(null);// 창이 가운데 나오게
		getContentPane().setLayout(null);
		Image cursorImage = Toolkit.getDefaultToolkit().getImage(".\\images\\mouseCursor.png");
		Cursor customCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImage, new Point(0, 0), "customCursor");
		setCursor(customCursor);
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		title = new JLabel("");
		title.setIcon(new ImageIcon(".\\images\\preViewTitle.png"));
		title.setBounds(108, 17, 170, 43);

		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0, 70, 384, 291);

		textArea = new JTextArea(10, 20);
		textArea.setEditable(false);

		scrollPane = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(WIDTH + 12, 10, 360, 230);

		saveBtn = new JButton();
		saveBtn.setIcon(new ImageIcon(".\\images\\fileSaveBtn.png"));
		saveBtn.setRolloverIcon(new ImageIcon(".\\images\\fileSaveBtn2.png"));
		saveBtn.setBounds(263, 242, 115, 50);
		saveBtn.setBorderPainted(false);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setFocusable(false);
		saveBtn.addActionListener(this);

		add(title);
		p1.add(scrollPane);
		p1.add(saveBtn);
		add(p1);
		setVisible(true);

		showDataFile();
		
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				mainAWT.setEnabled(true);
			}
		});
	}

	// 저장하기 버튼 이벤트
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		JFileChooser fs = new JFileChooser(new File("c:\\"));

		int result = fs.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			selectedFile = fs.getSelectedFile();
			excelMgr(selectedFile);
			JOptionPane.showMessageDialog(null, "파일 저장 완료!", "Success", JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public void showDataFile() {
		String sql = null;

		try {
			pool = DBConnectionMgr.getInstance();
			sql = "SELECT PROD_CODE, CATEGORY, PROD_NAME, PROD_SIZE, PROD_COLOR, PROD_STOCK\r\n" + "FROM product\r\n"
					+ "ORDER BY PROD_CODE DESC";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			String headerTitle = "제품코드" + " " + "카테고리" + " " + "제품명" + " " + "제품사이즈" + " " + "제품색상" + " " + "재고수량"
					+ "\n";
			String underBar = "==================================================\n";

			textArea.append(headerTitle);
			textArea.append(underBar);
			while (rs.next()) {
				// quary 결과에서 데이터 가져오기
				String prodCode = rs.getString(1); // 쿼리 select문의 첫번째 열
				String category = rs.getString(2); // 쿼리 select문의 두번째 열
				String prodName = rs.getString(3); // 쿼리 select문의 세번째 열
				String prodSize = rs.getString(4); // 쿼리 select문의 네번째 열
				String prodColor = rs.getString(5); // 쿼리 select문의 다섯번째 열
				String prodStock = rs.getString(6); // 쿼리 select문의 여섯번째 열
				textArea.append(prodCode + "\t" + category + "\t" + prodName + "\t" + prodSize + "\t  " + prodColor
						+ "\t  " + prodStock + "\n");
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
	}

	public void excelMgr(File selectedFile) {
		this.selectedFile = selectedFile;

		String sql = null;
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		try {
			pool = DBConnectionMgr.getInstance();
			// 1차로 workbook을 생성
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 2차는 sheet생성
			HSSFSheet sheet = workbook.createSheet("시트명");
			// 엑셀의 행
			HSSFRow xRow = null;
			// 엑셀의 셀
			HSSFCell cell = null;

			sql = "SELECT PROD_CODE, CATEGORY, PROD_NAME, PROD_SIZE, PROD_COLOR, PROD_STOCK\r\n" + "FROM product\r\n"
					+ "ORDER BY PROD_CODE DESC";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			String currentDate = date.format(new Date());
			FileOutputStream fileoutputstream = new FileOutputStream(selectedFile + "(" + currentDate + ").xls");

			int i = 0;
			xRow = sheet.createRow(0);
			cell = xRow.createCell((short) i++);
			cell.setCellValue("제품코드");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("카테고리");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("제품명");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("제품사이즈");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("제품색상");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("재고수량");

			int row = 1; // row번째 행
			int j = 0;
			while (rs.next()) {
				// quary 결과에서 데이터 가져오기
				String prodCode = rs.getString(1); // 쿼리 select문의 첫번째 열
				String category = rs.getString(2); // 쿼리 select문의 두번째 열
				String prodName = rs.getString(3); // 쿼리 select문의 세번째 열
				String prodSize = rs.getString(4); // 쿼리 select문의 네번째 열
				String prodColor = rs.getString(5); // 쿼리 select문의 다섯번째 열
				String prodStock = rs.getString(6); // 쿼리 select문의 여섯번째 열
				// 데이터 입력
				xRow = sheet.createRow(row);
				cell = xRow.createCell((short) j++);
				cell.setCellValue(prodCode);
				cell = xRow.createCell((short) j++);
				cell.setCellValue(category);
				cell = xRow.createCell((short) j++);
				cell.setCellValue(prodName);
				cell = xRow.createCell((short) j++);
				cell.setCellValue(prodSize);
				cell = xRow.createCell((short) j++);
				cell.setCellValue(prodColor);
				cell = xRow.createCell((short) j++);
				cell.setCellValue(prodStock);
				j = 0;
				row++;
			}
			workbook.write(fileoutputstream); // 파일 쓰기
			if (fileoutputstream != null) {
				fileoutputstream.close();
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
	}
}