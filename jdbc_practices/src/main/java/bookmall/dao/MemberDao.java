package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMall;
import bookmall.vo.MemberVo;

public class MemberDao {

	public List<MemberVo> findAll() {
		List<MemberVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = "select a.no, a.name, a.tel, a.email, a.password from member a;";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				long no = rs.getLong(1);
				String name = rs.getString(2);
				String tel = rs.getString(3);
				String email = rs.getString(4);
				String password = rs.getString(5);
				
				MemberVo vo = new MemberVo();
				vo.setNo(no);
				vo.setName(name);
				vo.setTel(tel);
				vo.setEmail(email);
				vo.setPassword(password);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				//6. 자원정리
				if(rs != null) {
					rs.close();
				}
				if( pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
		return result;
	}

	public void insert(MemberVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMall.getConnection();
			
			String sql = "insert into member values(null, ?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getTel());
			pstmt.setString(3, vo.getEmail());
			pstmt.setString(4, vo.getPassword());
			
			//4. SQL 실행
			pstmt.executeQuery();
			

			} catch (SQLException e) {
				System.out.println("error: " + e);
			} finally {
			try {
				//6. 자원정리
				if( pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
			}
	}
	
	public Long findNoByName(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long no = null;
		try {
			conn = BookMall.getConnection();
			String sql = 
					" select a.no" +
					"   from member a" +
					"  where a.name=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1,name);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			no = rs.getLong(1);
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				//6. 자원정리
				if(rs != null) {
					rs.close();
				}
				if( pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
		return no;
	}
	
	public List<MemberVo> findAllByMemberNo(Long memberNo) {
		List<MemberVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = 
					" select a.name, a.email" +
					"   from member a" +
					"  where a.no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,memberNo);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				String name = rs.getString(1);
				String email = rs.getString(2);
				
				MemberVo vo = new MemberVo();
				vo.setName(name);
				vo.setEmail(email);
				
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				//6. 자원정리
				if(rs != null) {
					rs.close();
				}
				if( pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
		return result;
	}
}
