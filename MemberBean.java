package warehouse;

import java.sql.Date;

public class MemberBean {
	private int memberIdx;
	private String JoinedDate;
	private String name;
	private String tel;
	private String address;
	
	public int getMemberIdx() {
		return memberIdx;
	}
	public void setMemberIdx(int memberIdx) {
		this.memberIdx = memberIdx;
	}
	public String getJoinedDate() {
		return JoinedDate;
	}
	public void setJoinedDate(String joinedDate) {
		JoinedDate = joinedDate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
