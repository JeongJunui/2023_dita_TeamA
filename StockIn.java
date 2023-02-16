package warehouse;

public class StockIn {

	private DBConnectionMgr pool;
	
		public StockIn() {
//	public StockIn(int ia, int ib, int ic,
//	String sa, String sb, String sc, String sd) {
//		int PROD_CODE = ia, PROD_STOCK = ib, MEMBER_IDX = ic;
//		String CATEGORY = sa, PROD_NAME = sb, PROD_SIZE = sc, PROD_COLOR = sd;
		
		pool = DBConnectionMgr.getInstance();
		
	}

}
