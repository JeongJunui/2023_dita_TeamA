package warehouse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

public class Main4PoiWrite {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private DBConnectionMgr pool;

	public Main4PoiWrite() {
		String sql = null;
		String file_size = null;
		String file_path = null;
		Workbook xworkbook = null; // 엑셀파일 객체 생성
		Sheet xSheet = null; // 시트 객체 생성
		Row xRow = null; // 행 객체 생성
		Cell xCell = null; // 열 객체 생성
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		try {
			xworkbook = new SXSSFWorkbook(); // 엑셀파일 생성
			xSheet = xworkbook.createSheet("sheet1"); // 시트 생성
			con = pool.getConnection();
			sql = "SELECT PROD_CODE, CATEGORY, PROD_NAME, PROD_SIZE, PROD_COLOR, PROD_STOCK\r\n" + "FROM product\r\n"
					+ "ORDER BY PROD_CODE DESC";
			pstmt = con.prepareStatement(sql);

			rs = pstmt.executeQuery();

			File file = new File(file_path + ".xlsx"); // 파일 확장자 .xlsx로 고정
			FileOutputStream fos = new FileOutputStream(file);
			
			xRow = xSheet.createRow(0);
			xCell = xRow.createCell(0);
			xCell.setCellValue("PROD_CODE");
			xCell = xRow.createCell(1);
			xCell.setCellValue("CATEGORY");
			xCell = xRow.createCell(2);
			xCell.setCellValue("PROD_NAME");
			xCell = xRow.createCell(3);
			xCell.setCellValue("PROD_SIZE");
			xCell = xRow.createCell(4);
			xCell.setCellValue("PROD_COLOR");
			xCell = xRow.createCell(5);
			xCell.setCellValue("PROD_STOCK");
			
			int row = 1; // row번째 행

			while (rs.next()) {

				// quary 결과에서 데이터 가져오기
				String prodCode = rs.getString(1); // 쿼리 select문의 첫번째 열
				String category = rs.getString(2); // 쿼리 select문의 첫번째 열
				String prodName = rs.getString(3); // 쿼리 select문의 첫번째 열
				String prodSize = rs.getString(4); // 쿼리 select문의 첫번째 열
				String prodColor = rs.getString(5); // 쿼리 select문의 두번째 열
				String prodStock = rs.getString(6); // 쿼리 select문의 두번째 열
				// String description = rs.getString(description) 처럼 숫자 대신 컬럼명 입력해도 문제 없음

				// 데이터 입력
				xRow = xSheet.createRow(row);
				xCell = xRow.createCell(0);
				xCell.setCellValue(prodCode);
				xCell = xRow.createCell(1);
				xCell.setCellValue(category);
				xCell = xRow.createCell(2);
				xCell.setCellValue(prodName);
				xCell = xRow.createCell(3);
				xCell.setCellValue(prodSize);
				xCell = xRow.createCell(4);
				xCell.setCellValue(prodColor);
				xCell = xRow.createCell(5);
				xCell.setCellValue(prodStock);

				row++;
				// 파일 쓰기
				xworkbook.write(fos);
				if (fos != null) {
					fos.close();
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
	}

	public static void main(String[] args) throws IOException {
		Main4PoiWrite main = new Main4PoiWrite();

	}

}