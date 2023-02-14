package warehouse;

public class ReleasedBean {
	private int takeoutIdx;
	private String takeoutDate;
	private String prodCode;
	private int memberIdx;
	private int takeoutAmount;
	private String other;
	public ReleasedBean() {};
	public ReleasedBean(int takeoutIdx, String prodCode, int memberIdx, String takeoutDate, int takeoutAmount, String other)
	{
		super();
		this.takeoutIdx=takeoutIdx;
		this.takeoutDate=takeoutDate;
		this.prodCode=prodCode;
		this.memberIdx=memberIdx;
		this.takeoutAmount=takeoutAmount;
		this.other=other;
	}
	public int getTakeoutIdx() {
		return takeoutIdx;
	}
	public void setTakeoutIdx(int takeoutIdx) {
		this.takeoutIdx = takeoutIdx;
	}
	public String getTakeoutDate() {
		return takeoutDate;
	}
	public void setTakeoutDate(String takeoutDate) {
		this.takeoutDate = takeoutDate;
	}
	public String getProdCode() {
		return prodCode;
	}
	public void setProdCode(String prodCode) {
		this.prodCode = prodCode;
	}
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public int getTakeoutAmount() {
		return takeoutAmount;
	}
	public void setTakeoutAmount(int takeoutAmount) {
		this.takeoutAmount = takeoutAmount;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
}
