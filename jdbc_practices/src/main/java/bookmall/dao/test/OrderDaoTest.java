package bookmall.dao.test;

import java.util.List;

import bookmall.dao.OrderDao;
import bookmall.vo.OrderVo;

public class OrderDaoTest {

	public static void main(String[] args) {
		OrderVo vo = new OrderVo();
		vo.setMemberNo(1L);
		vo.setAddress("서울특별시 서초구 서초대로74길 33");
		testInsert(vo);
		
		vo.setMemberNo(2L);
		vo.setAddress("서울특별시 서초구 서초중앙로28길 16 1F");
		testInsert(vo);
		
		testFindAll();
	}

	private static void testFindAll() {
		List<OrderVo> list = new OrderDao().findAll();
		for(OrderVo vo : list) {
			System.out.println(vo);
		}
	}

	private static void testInsert(OrderVo vo) {
		new OrderDao().insert(vo);
	}

}
