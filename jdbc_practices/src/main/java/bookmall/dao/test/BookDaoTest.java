package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		BookVo vo = new BookVo();
		vo.setTitle("모순");
		vo.setPrice(11700L);
		vo.setCategoryNo(1L);
		testInsert(vo);
		
		vo.setTitle("초공간");
		vo.setPrice(22500L);
		vo.setCategoryNo(2L);
		testInsert(vo);
		
		vo.setTitle("완전한 연주");
		vo.setPrice(20700L);
		vo.setCategoryNo(3L);
		testInsert(vo);
		
		testFindAll();
		
		testFindNoByTitle("초공간");
		
	}

	private static void testFindNoByTitle(String title) {
		// TODO Auto-generated method stub
		System.out.println(new BookDao().findAllByTitle(title));
	}
	
	private static void testFindAll() {
		List<BookVo> list = new BookDao().findAll();
		for(BookVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(BookVo vo) {
		new BookDao().insert(vo);
	}

}
