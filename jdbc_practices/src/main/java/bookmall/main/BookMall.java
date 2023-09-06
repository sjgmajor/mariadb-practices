package bookmall.main;

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
import bookmall.vo.OrderVo;

public class BookMall {

	public static void main(String[] args) {
		// member
		MemberDao memberDao = new MemberDao();
		MemberVo memberVo = new MemberVo();
		
//		memberSet(memberDao, memberVo, "서정권", "010-1234-5678", "sjg1@gmail.com", "1234");
//		memberSet(memberDao, memberVo, "권정서", "010-5678-1234", "1sjg@naver.com", "5678");

		// category
		CategoryDao categoryDao = new CategoryDao();
		CategoryVo categoryVo = new CategoryVo();
	
//		categorySet(categoryDao, categoryVo, "소설");
//		categorySet(categoryDao, categoryVo, "과학");
//		categorySet(categoryDao, categoryVo, "예술");

		// book
		BookDao bookDao = new BookDao();
		BookVo bookVo = new BookVo();

//		bookSet(bookDao, bookVo, "모순", 11700L, 1L);
//		bookSet(bookDao, bookVo, "초공간", 22500L, 2L);
//		bookSet(bookDao, bookVo, "완전한 연주", 20700L, 3L);
		
		// cart
		CartDao cartDao = new CartDao();
		CartVo cartVo = new CartVo();
		
//		cartSet(cartDao, cartVo, 1L, "모순", "서정권");
//		cartSet(cartDao, cartVo, 2L, "초공간", "권정서");
//		cartSet(cartDao, cartVo, 2L, "완전한 연주", "권정서");
		
		//order
		OrderDao orderDao = new OrderDao();
		OrderVo orderVo = new OrderVo();
		
//		orderSet(orderDao, orderVo, "서정권", "서울특별시 서초구 서초대로74길 33");
//		orderUpdate();
//		orderDispaly();
		// 출력
		System.out.println("## 회원리스트");
		memberDisplay();
		
		System.out.println("## 카테고리");
		categoryDisplay();
		
		System.out.println("## 상품");
		bookDisplay();
		
		System.out.println("## 카트");
		cartDisplay();
		System.out.println("## 주문");
		orderDispaly();
	}
	
	private static void orderSet(OrderDao orderDao, OrderVo orderVo, String name, String email) {
		
	}

	private static void orderDispaly() {
		
	}

	private static void cartSet(CartDao cartDao, CartVo cartVo, long quantity, String title, String name) {
		new MemberDao().findNoByName(name);
		cartVo.setQuantity(quantity);
		cartVo.setBookNo(new BookDao().findbookNoByTitle(title));
		cartVo.setMemberNo(new MemberDao().findNoByName(name));
		cartDao.insert(cartVo);
		System.out.println("[" + name + "]회원 카트에 [" + title + "]도서가 등록되었습니다.");
	}
	
	private static void cartDisplay() {
		for(MemberVo mv : new MemberDao().findAll()) {
			System.out.println("-----[" + mv.getName() + "]회원의 카트-----");
			for(CartVo vo : new CartDao().findAllbyMemberNo(mv.getNo())) {
				for(BookVo book : new BookDao().findAllBybookNo(vo.getBookNo())) {
				System.out.println("도서 제목: " + book.getTitle() +
								   " ,가격: " + book.getPrice() +	       
								   " ,수량: " + vo.getQuantity());
				}
			}
		}
	}
	
	private static void bookSet(BookDao bookDao, BookVo bookVo, String title, long price, long categoryNo) {
		bookVo.setTitle(title);
		bookVo.setPrice(price);
		bookVo.setCategoryNo(categoryNo);
		bookDao.insert(bookVo);
		System.out.println("도서 목록이 출력되었습니다.");
	}

	private static void bookDisplay() {
		List<BookVo> bookList = new BookDao().findAll();
		for(BookVo vo : bookList) {
			System.out.println(vo);
		}
	}
	
	private static void categorySet(CategoryDao categoryDao, CategoryVo categoryVo, String name) {
		categoryVo.setName(name);
		categoryDao.insert(categoryVo);
		System.out.println("카테고리 목록이 출력되었습니다.");
	}

	private static void categoryDisplay() {
		List<CategoryVo> categoryList = new CategoryDao().findAll();
		for(CategoryVo vo : categoryList) {
			System.out.println(vo);
		}
	}
	
	private static void memberSet(MemberDao memberDao, MemberVo memberVo, String name, String tel, String email, String password) {
		memberVo.setName(name);
		memberVo.setTel(tel);
		memberVo.setEmail(email);
		memberVo.setPassword(password);
		memberDao.insert(memberVo);
		System.out.println("회원 목록이 출력되었습니다.");
	}
	
	private static void memberDisplay() {
		List<MemberVo> memberList = new MemberDao().findAll();
		for(MemberVo vo : memberList) {
			System.out.println(vo);
		}
	}
}
