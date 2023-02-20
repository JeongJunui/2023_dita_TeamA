package warehouse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
 
public class Main {
	public Main(){
		try {
			excelwrite();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void excelwrite() throws IOException {
		//임의의 VO가 되주는 MAP 객체
		Map<String,Object>map=null;
		//가상 DB조회후 목록을 담을 LIST객체
		ArrayList<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
		ArrayList<String> columnList=new ArrayList<String>();
		//DB조회후 데이터를 담았다는 가상의 데이터
		for(int i=0;i<10;i++){
		    map=new HashMap<String,Object>();
		    map.put("seq", i+1);
		    map.put("title", "제목"+i);
		    map.put("content", "내용"+i);
		    list.add(map);
		}
		//MAP의 KEY값을 담기위함
		if(list !=null &&list.size() >0){
		    //LIST의 첫번째 데이터의 KEY값만 알면 되므로
		    Map<String,Object> m=list.get(0);
		    //MAP의 KEY값을 columnList객체에 ADD
		    for(String k : m.keySet()){
		        columnList.add(k);
		    }
		}
		//1차로 workbook을 생성
		HSSFWorkbook workbook=new HSSFWorkbook();
		//2차는 sheet생성
		HSSFSheet sheet=workbook.createSheet("시트명");
		//엑셀의 행
		HSSFRow row=null;
		//엑셀의 셀
		HSSFCell cell=null;
		//임의의 DB데이터 조회
		if(list !=null &&list.size() >0){
		    int i=0;
		    for(Map<String,Object> mapobject : list){
		        // 시트에 하나의 행을 생성한다(i 값이 0이면 첫번째 줄에 해당)
		        row=sheet.createRow((short)i);
		        i++;
		        if(columnList !=null &&columnList.size() >0){
		            for(int j=0;j<columnList.size();j++){
		                //생성된 row에 컬럼을 생성한다
		                cell=row.createCell((short)j);
		                //map에 담긴 데이터를 가져와 cell에 add한다
		                cell.setCellValue(String.valueOf(mapobject.get(columnList.get(j))));
		            }
		        }
		    }
		}
		FileOutputStream fileoutputstream=new FileOutputStream(".\\CourseList2.xls");
		//파일을 쓴다
		workbook.write(fileoutputstream);
		//필수로 닫아주어야함
		fileoutputstream.close();
		System.out.println("엑셀파일생성성공");
	}
	public static void main(String[] args) {
		Main main = new Main();
	}
}