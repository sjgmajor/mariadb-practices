package bookmall.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.CategoryDao;
import bookmall.dao.MemberDao;
import bookmall.dao.OrderDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;
import bookmall.vo.CategoryVo;
import bookmall.vo.MemberVo;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class BookMall {

	private static String forName = "org.mariadb.jdbc.Driver";
	private static String url = "jdbc:mariadb://192.168.0.183:3307/bookmall?charset=utf8";
	private static String DriverId = "bookmall";
	private static String DriverPassword = "bookmall";
	
	public static void main(String[] args) {
		// member
		//이름, 전화번호, 이메일, 비밀번호 입력
		memberInsert("서정권", "010-1234-5678", "sjg1@gmail.com", "1234");
		memberInsert("권정서", "010-5678-1234", "1sjg@naver.com", "5678");

		// category
		//카테고리 이름 입력
		categoryInsert("소설");
		categoryInsert("과학");
		categoryInsert("예술");

		// book
		// 책 이름, 가격, 카테고리 입력
		bookInsert("모순", 11700L, "소설");
		bookInsert("초공간", 22500L, "과학");
		bookInsert("완전한 연주", 20700L, "예술");
		
		// cart
		// 수량, 책 이름, 회원 이름 입력
		cartInsert("서정권", "모순", 1L);
		cartInsert("서정권", "초공간", 2L);
		cartInsert("권정서", "완전한 연주", 2L);
		
		//order
		//회원 이름, 주소 입력
		orderCart("서정권", "서울특별시 서초구 서초대로74길 33");
		orderCart("권정서", "서울특별시 서초구 서초중앙로28길 16 1F");
		
		// 출력
		System.out.println("## 회원리스트");
		memberDisplay();
		
		System.out.println("## 카테고리");
		categoryDisplay();
		
		System.out.println("## 상품");
		bookDisplay();
		
		System.out.println("## 카트");
		cartDisplay("서정권");
		cartDisplay("권정서");
		
		System.out.println("## 주문");
		orderDisplay("서정권");
		
		System.out.println("## 주문도서");
		// 이름으로 주문한 모든 주문도서 목록 출력
		orderBookDisplay("서정권");
		// 주문번호로 주문도서 목록 출력
//		orderBookListDisplay("20230910-1");
	}

	private static void memberInsert(String name, String tel, String email, String password) {
		MemberDao memberDao = new MemberDao();
		MemberVo memberVo = new MemberVo();

		memberVo.setName(name);
		memberVo.setTel(tel);
		memberVo.setEmail(email);
		memberVo.setPassword(password);
		memberDao.insert(memberVo);
	}

	private static void memberDisplay() {
		List<MemberVo> memberList = new MemberDao().findAll();
		for(MemberVo vo : memberList) {
			System.out.println(vo);
		}
	}
	
	private static void categoryInsert(String name) {
		CategoryDao categoryDao = new CategoryDao();
		CategoryVo categoryVo = new CategoryVo();

		categoryVo.setName(name);
		categoryDao.insert(categoryVo);
	}

	private static void categoryDisplay() {
		List<CategoryVo> categoryList = new CategoryDao().findAll();
		for(CategoryVo vo : categoryList) {
			System.out.println(vo);
		}
	}

	private static void bookInsert(String title, long price, String categoryName) {
		BookDao bookDao = new BookDao();
		BookVo bookVo = new BookVo();

		bookVo.setTitle(title);
		bookVo.setPrice(price);
		bookVo.setCategoryNo(new CategoryDao().findNoByName(categoryName));
		bookDao.insert(bookVo);
	}

	private static void bookDisplay() {
		List<BookVo> bookList = new BookDao().findAll();
		for(BookVo vo : bookList) {
			System.out.println(vo);
		}
	}

	private static void cartInsert(String name, String title, long quantity) {
		CartDao cartDao = new CartDao();
		CartVo cartVo = new CartVo();

		cartVo.setQuantity(quantity);
		cartVo.setBookNo(new BookDao().findbookNoByTitle(title));
		cartVo.setMemberNo(new MemberDao().findNoByName(name));
		cartDao.insert(cartVo);
	}

	private static void cartDisplay(String name) {
		Long memberNo = new MemberDao().findNoByName(name);
		System.out.println("-----[" + name + "]회원의 카트-----");
		List<CartVo> cartList = new CartDao().findAllbyMemberNo(memberNo);
		for(CartVo vo : cartList) {
			List<BookVo> bookList = new BookDao().findAllBybookNo(vo.getBookNo());
			for(BookVo bvo : bookList) {
				System.out.println("도서 제목: " + bvo.getTitle() +
						" ,가격: " + bvo.getPrice() +
						" ,수량: " + vo.getQuantity());
				break;
			}

		}
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
	
	public static Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName(forName);
			conn = DriverManager.getConnection(url, DriverId, DriverPassword);
			} catch (ClassNotFoundException e) {
				System.out.println("드라이버 로딩 실패: " + e);
			}
			return conn;
		}
}
