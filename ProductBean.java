package warehouse;

public class ProductBean {
	private String prodCode;
	private String category;
	private String prodName;
	private String prodSize;
	private String prodColor;
	private int prodStock;
	public ProductBean() {
	}
	public ProductBean(String prodCode, String category, String prodName, String prodSize, String prodColor, int prodStock) {
		super();
		this.prodCode=prodCode;
		this.category=category;
		this.prodName=prodName;
		this.prodSize=prodSize;
		this.prodColor=prodColor;
		this.prodStock=prodStock;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getProdName() {
		return prodName;
	}
	public void setProdName(String prodName) {
		this.prodName = prodName;
	}
	public String getProdSize() {
		return prodSize;
	}
	public void setProdSize(String prodSize) {
		this.prodSize = prodSize;
	}
	public String getProdColor() {
		return prodColor;
	}
	public void setProdColor(String prodColor) {
		this.prodColor = prodColor;
	}
	public int getProdStock() {
		return prodStock;
	}
	public void setProdStock(int prodStock) {
		this.prodStock = prodStock;
	}
}
