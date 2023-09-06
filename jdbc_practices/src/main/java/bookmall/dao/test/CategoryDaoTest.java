package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		CategoryVo vo = new CategoryVo();
		vo.setName("소설");		
		testInsert(vo);
		
		vo.setName("과학");
		testInsert(vo);
		
		vo.setName("예술");
		testInsert(vo);
		
		testFindAll();
	}

	private static void testFindAll() {
		List<CategoryVo> list = new CategoryDao().findAll();
		for(CategoryVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(CategoryVo vo) {
		new CategoryDao().insert(vo);
	}

}
