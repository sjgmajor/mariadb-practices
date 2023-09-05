package bookmall.main;

import bookmall.dao.MemberDao;

public class BookMall {

	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		MemberDao.insert(memberVo1);
		MemberDao.insert(memberVo2);
		
		System.out.println("## 회원리스트");
		//2 member
		MemberDao.displaymember();
		
		System.out.println("## 카테고리");
		//3 category
		
		System.out.println("## 상품");
		//3 book
		
		System.out.println("## 카트");
		//2 -> cart 도서제목, 수량, 가격
		
		System.out.println("## 주문");
		// 1 -> orders
	}

}
