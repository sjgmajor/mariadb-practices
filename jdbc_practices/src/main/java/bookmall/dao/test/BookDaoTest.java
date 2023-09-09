package bookmall.dao.test;

import java.util.List;

import bookmall.dao.BookDao;
import bookmall.dao.CategoryDao;
import bookmall.vo.BookVo;

public class BookDaoTest {
	
	public static void main(String[] args) {
		// book
		// 책 이름, 가격, 카테고리 입력
		bookInsert("모순", 11700L, "소설");
		bookInsert("초공간", 22500L, "과학");
		bookInsert("완전한 연주", 20700L, "예술");		
		
		System.out.println("## 상품");
		bookDisplay();
				
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

}
