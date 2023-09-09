package bookmall.dao.test;

import java.util.List;

import bookmall.dao.CategoryDao;
import bookmall.vo.CategoryVo;

public class CategoryDaoTest {

	public static void main(String[] args) {
		// category
		//카테고리 이름 입력
		categoryInsert("소설");
		categoryInsert("과학");
		categoryInsert("예술");
		
		System.out.println("## 카테고리");
		categoryDisplay();
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


}
