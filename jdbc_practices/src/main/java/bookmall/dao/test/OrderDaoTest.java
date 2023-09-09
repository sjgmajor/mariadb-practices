package bookmall.dao.test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		
		//order
		//회원 이름, 주소 입력
//		orderCart("서정권", "서울특별시 서초구 서초대로74길 33");
//		orderCart("권정서", "서울특별시 서초구 서초중앙로28길 16 1F");
		
		System.out.println("## 주문");
		orderDisplay("서정권");
		orderDisplay("권정서");
		
		System.out.println("## 주문도서");
		orderBookDisplay("서정권");
		orderBookDisplay("권정서");
 		orderBookListDisplay("20230909-1");
 		orderBookListDisplay("20230909-2");
	}

	private static void orderCart(String name, String address) {
		OrderDao orderDao = new OrderDao();
		OrderVo orderVo = new OrderVo();

		// 이름으로부터 회원번호 얻기
		Long memberNo = new MemberDao().findNoByName(name);

		// 신규주 문 생성 및 삽입, 그리고 그 결과 받기
		orderVo.setMemberNo(memberNo);
		orderVo.setAddress(address);
		orderDao.insertOrder(orderVo);

		Long totalPrice = 0L;

		OrderVo latestOrder = orderDao.findLatestByMemberNo(memberNo);
		List<CartVo> cartList = new CartDao().findAllbyMemberNo(memberNo);

		for(CartVo cart : cartList) {
			List<BookVo> bookList = new BookDao().findAllBybookNo(cart.getBookNo());

			for(BookVo book : bookList) {
				OrderBookVo obv = new OrderBookVo();
				obv.setPrice(book.getPrice());
				obv.setQuantity(cart.getQuantity());
				obv.setOrderNo(latestOrder.getOrderNo());
				obv.setBookNo(cart.getBookNo());
				totalPrice += obv.getPrice() * obv.getQuantity();
				orderVo.setOrderNo(obv.getOrderNo());
				orderDao.insertOrderbook(obv);

			}
		}
		orderVo.setTotalPrice(totalPrice);
		orderDao.updateOrder(orderVo);
	}

	private static void orderDisplay(String name) {
		Long memberNo = new MemberDao().findNoByName(name);
		List<MemberVo> mvList = new MemberDao().findAllByMemberNo(memberNo);

		for(MemberVo mv : mvList) {
			System.out.println("-----[" + mv.getName() + "]회원의 주문서-----");
			List<OrderVo> orderList = new OrderDao().findAllByMemberNo(memberNo);
			for(OrderVo vo : orderList) {
				System.out.println("주문번호: " +
						new SimpleDateFormat("yyyyMMdd").format(new Date())
								.concat("-").concat(vo.getOrderNo().toString()) +
						", 이름: " + mv.getName() +
						", 이메일: " + mv.getEmail() +
						", 주소: " + vo.getAddress() +
						", 총 가격: " + vo.getTotalPrice());
			}
		}
	}
	
	private static void orderBookDisplay(String name) {
		Long memberNo = new MemberDao().findNoByName(name);

		List<OrderVo> ovList = new OrderDao().findAllByMemberNo(memberNo);

		for(OrderVo ov : ovList){
			List<OrderBookVo> orderbookList = new OrderDao().findOrdeBookByOrderNo(ov.getOrderNo());
			System.out.println("-----[" + name + "]회원 [주문번호]" +
					new SimpleDateFormat("yyyyMMdd").format(new Date())
							.concat("-").concat(ov.getOrderNo().toString()) +
					"의 주문도서 목록-----");
			for(OrderBookVo obov : orderbookList) {
				List<BookVo> bookList = new BookDao().findAllBybookNo(obov.getBookNo());
				for(BookVo bvo : bookList) {
					System.out.println("도서 번호: " + obov.getBookNo() +
							", 도서 제목: " + bvo.getTitle() +
							", 수량: " + obov.getQuantity());
				}
			}
		}
	}
	
	private static void orderBookListDisplay(String orderNumber) {
		
		Long orderNo = Long.parseLong(orderNumber.split("-")[1]);
		System.out.println("-----[주문번호]" + orderNumber + "의 주문도서 목록-----" );
		List<OrderBookVo> orderbookList = new OrderDao().findOrdeBookByOrderNo(orderNo);
			for(OrderBookVo obov : orderbookList) {
				List<BookVo> bookList = new BookDao().findAllBybookNo(obov.getBookNo());
				for(BookVo bvo : bookList) {
					System.out.println("도서 번호: " + obov.getBookNo() +
							", 도서 제목: " + bvo.getTitle() +
							", 수량: " + obov.getQuantity());
				}
			}
	}

}
