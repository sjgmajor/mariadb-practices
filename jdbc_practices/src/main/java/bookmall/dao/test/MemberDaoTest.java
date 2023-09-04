package bookmall.dao.test;

public class MemberDaoTest {
	private static void main(String[] args) {
		insertTest();
	}

	private static void insertTest() {
		BookDao dao = new BookDao();
		
		dao.inser(bookVo);
		dao.insert(bookNo);
		
	}

}
