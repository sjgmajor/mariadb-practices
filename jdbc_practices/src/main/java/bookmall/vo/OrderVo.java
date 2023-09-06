package bookmall.vo;

public class OrderVo {
	private Long orderNo;
	private Long memberNo;
	private String address;
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	@Override
	public String toString() {
		return "OrderVo [orderNo=" + orderNo + ", memberNo=" + memberNo + ", address=" + address + "]";
	}
	
}
