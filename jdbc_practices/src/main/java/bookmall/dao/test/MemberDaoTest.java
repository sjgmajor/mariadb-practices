package bookmall.dao.test;

import java.util.List;

import bookmall.dao.MemberDao;
import bookmall.vo.MemberVo;

public class MemberDaoTest {
	
	public static void main(String[] args) {
		// member
		//이름, 전화번호, 이메일, 비밀번호 입력
		memberInsert("서정권", "010-1234-5678", "sjg1@gmail.com", "1234");
		memberInsert("권정서", "010-5678-1234", "1sjg@naver.com", "5678");
		
		System.out.println("## 회원리스트");
		memberDisplay();
	}

	private static void memberInsert(String name, String tel, String email, String password) {
		MemberDao memberDao = new MemberDao();
		MemberVo memberVo = new MemberVo();

		memberVo.setName(name);
		memberVo.setTel(tel);
		memberVo.setEmail(email);
		memberVo.setPassword(password);
		memberDao.insert(memberVo);
	}

	private static void memberDisplay() {
		List<MemberVo> memberList = new MemberDao().findAll();
		for(MemberVo vo : memberList) {
			System.out.println(vo);
		}
	}
}
