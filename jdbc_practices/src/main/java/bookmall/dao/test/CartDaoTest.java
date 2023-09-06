package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CartDao;
import bookmall.vo.BookVo;
import bookmall.vo.CartVo;

public class CartDaoTest {

	public static void main(String[] args) {
		CartVo vo = new CartVo();
		vo.setQuantity(2L);
		vo.setBookNo(2L);
		vo.setMemberNo(1L);
		testInsert(vo);
		
		List<BookVo> list = new BookDao().findAllByTitle("모순");
		System.out.println(list);
		
		vo.setQuantity(1L);
		vo.setBookNo(3L);
		vo.setMemberNo(2L);
		testInsert(vo);
		
		vo.setQuantity(1L);
		vo.setBookNo(1L);
		vo.setMemberNo(2L);
		testInsert(vo);
		
		testFindAll();
	}

	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(CartVo vo) {
		new CartDao().insert(vo);
	}
	
}
