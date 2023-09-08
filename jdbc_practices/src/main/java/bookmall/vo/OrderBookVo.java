package bookmall.vo;

public class OrderBookVo {
	private Long bookNo;
	private Long orderNo;
	private Long price;
	private Long quantity;
	public Long getBookNo() {
		return bookNo;
	}
	public void setBookNo(Long bookNo) {
		this.bookNo = bookNo;
	}
	public Long getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(Long orderNo) {
		this.orderNo = orderNo;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Long getQuantity() {
		return quantity;
	}
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}
	@Override
	public String toString() {
		return "OrderBookVo [book_no=" + bookNo + ", order_no=" + orderNo + ", price=" + price + ", quantity="
				+ quantity + "]";
	}
}

