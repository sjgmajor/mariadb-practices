package bookmall.vo;

public class CartVo {
	private Long cartNo;
	private Long quantity;
	private Long bookNo;
	private Long memberNo;
	
	public Long getCartNo() {
		return cartNo;
	}
	public void setCartNo(Long cartNo) {
		this.cartNo = cartNo;
	}
	
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
