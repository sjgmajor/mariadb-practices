package bookmall.vo;

public class CartVo {
	private Long quantity;
	private Long bookNo;
	private Long memberNo;
	
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(Long memberNo) {
		this.memberNo = memberNo;
	}
	@Override
	public String toString() {
		return "CartVo [quantity=" + quantity + ", bookNo=" + bookNo + ", memberNo=" + memberNo + "]";
	}
}
