package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.dao.MemberDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		// cart
		// 수량, 책 이름, 회원 이름 입력
		cartInsert("서정권", "모순", 1L);
		cartInsert("서정권", "초공간", 2L);
		cartInsert("권정서", "완전한 연주", 2L);
		
		System.out.println("## 카트");
		cartDisplay("서정권");
		cartDisplay("권정서");
		
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
	
}
