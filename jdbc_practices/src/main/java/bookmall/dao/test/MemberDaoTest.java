package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	
	public static void main(String[] args) {
		MemberVo vo = new MemberVo();
		vo.setName("서정권");
		vo.setTel("010-1234-5678");
		vo.setEmail("sjg1@gmail.com");
		vo.setPassword("1234");
		testInsert(vo);
		
		vo.setName("권정서");
		vo.setTel("010-5678-1234");
		vo.setEmail("1sjg@naver.com");
		vo.setPassword("5678");
		testInsert(vo);
		
		testFindAll();
	}

	private static void testFindAll() {
		List<MemberVo> list = new MemberDao().findAll();
		for(MemberVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(MemberVo vo) {
		new MemberDao().insert(vo);
	}
}
