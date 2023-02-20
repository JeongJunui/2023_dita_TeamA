package warehouse;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class SaveExcelFile extends JFrame implements ActionListener{
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;
	private JTextArea textArea;
	private JLabel title;
	private JScrollPane scrollPane;
	private JButton saveBtn;
	public SaveExcelFile() {
		setTitle("�� ��� ����Ʈ �����ϱ�");
		setSize(400, 400);// �������� ũ��
		setResizable(false);// â�� ũ�⸦ �������� ���ϰ�
		getContentPane().setBackground(new Color(0, 32, 96));
		setLocationRelativeTo(null);// â�� ��� ������
		getContentPane().setLayout(null);

		textArea = new JTextArea(10,20);
		textArea.setEditable(false);
		
		scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS); 
		scrollPane.setBounds(WIDTH+12, 53, 360, 247);
		
		title = new JLabel("�� ��� ����Ʈ ");
		title.setForeground(new Color(137, 191, 220));
		title.setFont(new Font("���� ���", Font.BOLD, 22));
		title.setBounds(120, 10, 156, 33);
		
		saveBtn = new JButton();
		saveBtn.setIcon(new ImageIcon(".\\images\\fileSaveBtn.png"));
		saveBtn.setRolloverIcon(new ImageIcon(".\\images\\fileSaveBtn2.png"));
		saveBtn.setBounds(263, 308, 115, 50);
		saveBtn.setBorderPainted(false);
		saveBtn.setContentAreaFilled(false);
		saveBtn.setFocusable(false);
		saveBtn.addActionListener(this);
		
		add(scrollPane);
		add(scrollPane);
		add(title);
		add(saveBtn);
		
		setVisible(true);
		showDataFile();
		// excelMgr();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj == saveBtn) {
			excelMgr();
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
			String headerTitle = "��ǰ�ڵ�"+"\t"+ "ī�װ�"+"\t"+"��ǰ��"+"\t"+"��ǰ������"+"\t"+"��ǰ����"+"\t"+"������"+"\n";
			String underBar = "=======================================================================\n";
			
			textArea.append(headerTitle);
			textArea.append(underBar);
			while (rs.next()) {
				// quary ������� ������ ��������
				String prodCode = rs.getString(1); // ���� select���� ù��° ��
				String category = rs.getString(2); // ���� select���� ù��° ��
				String prodName = rs.getString(3); // ���� select���� ù��° ��
				String prodSize = rs.getString(4); // ���� select���� ù��° ��
				String prodColor = rs.getString(5); // ���� select���� �ι�° ��
				String prodStock = rs.getString(6); // ���� select���� �ι�° ��
				textArea.append(prodCode+"\t"+category+"\t"+prodName+"\t"+prodSize+"\t"+prodColor+"\t"+prodStock+"\n");
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

	public void excelMgr() {
		String sql = null;
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

		try {
			pool = DBConnectionMgr.getInstance();
			// 1���� workbook�� ����
			HSSFWorkbook workbook = new HSSFWorkbook();
			// 2���� sheet����
			HSSFSheet sheet = workbook.createSheet("��Ʈ��");
			// ������ ��
			HSSFRow xRow = null;
			// ������ ��
			HSSFCell cell = null;

			sql = "SELECT PROD_CODE, CATEGORY, PROD_NAME, PROD_SIZE, PROD_COLOR, PROD_STOCK\r\n" + "FROM product\r\n"
					+ "ORDER BY PROD_CODE DESC";
			con = pool.getConnection();
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();
			String currentDate = date.format(new Date());
			FileOutputStream fileoutputstream = new FileOutputStream(".\\�� ��� ����Ʈ(" + currentDate + ").xls");

			int i = 0;
			xRow = sheet.createRow(0);
			cell = xRow.createCell((short) i++);
			cell.setCellValue("��ǰ�ڵ�");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("ī�װ�");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("��ǰ��");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("��ǰ������");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("��ǰ����");
			cell = xRow.createCell((short) i++);
			cell.setCellValue("������");

			int row = 1; // row��° ��
			int j = 0;
			while (rs.next()) {
				// quary ������� ������ ��������
				String prodCode = rs.getString(1); // ���� select���� ù��° ��
				String category = rs.getString(2); // ���� select���� ù��° ��
				String prodName = rs.getString(3); // ���� select���� ù��° ��
				String prodSize = rs.getString(4); // ���� select���� ù��° ��
				String prodColor = rs.getString(5); // ���� select���� �ι�° ��
				String prodStock = rs.getString(6); // ���� select���� �ι�° ��
				// ������ �Է�
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
			workbook.write(fileoutputstream); // ���� ����
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

	public static void main(String[] args) throws IOException {
		SaveExcelFile main = new SaveExcelFile();
	}
}