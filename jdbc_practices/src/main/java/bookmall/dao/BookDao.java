package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.vo.BookVo;

public class BookDao {

	public List<BookVo> findAll() {
		List<BookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = MemberDao.getConnection();
			String sql = "select a.title , a.price from book a";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				String title = rs.getString(1);
				Long price = rs.getLong(2);
				
				BookVo vo = new BookVo();
				vo.setTitle(title);
				vo.setPrice(price);
				result.add(vo);
			}
			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
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

	public void insert(BookVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MemberDao.getConnection();
			
			String sql = "insert into book values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getTitle());
			pstmt.setLong(2, vo.getPrice());
			pstmt.setLong(3, vo.getCategoryNo());
			
			pstmt.executeQuery();
			
			System.out.println("[" + vo.getTitle() + "]도서가 등록되었습니다.");

			} catch (SQLException e) {
				System.out.println("error: " + e);
			} finally {
			try {
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
	
	public Long findbookNoByTitle(String title) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long bookNo = null;
		try {
			conn = MemberDao.getConnection();
			String sql = 
					" select a.book_No" +
					"   from book a" +
					"  where a.title=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,title);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
			bookNo = rs.getLong(1);
			}			
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				//7. 자원정리
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return bookNo;
	} 
	
	public List<BookVo> findAllBybookNo(Long bookNo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BookVo> result = new ArrayList<>();
		try {
			conn = MemberDao.getConnection();
			String sql = 
					" select a.title, a.price, a.category_no" +
					"   from book a" +
					"  where a.book_no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1,bookNo);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {

				String title = rs.getString(1);
				Long price = rs.getLong(2);
				Long categoryNo = rs.getLong(3);
				
				BookVo vo = new BookVo();
				vo.setTitle(title);
				vo.setPrice(price);
				vo.setCategoryNo(categoryNo);
				
				result.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("error: " + e);
		} finally {
			try {
				//7. 자원정리
				if(pstmt != null) {
					pstmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	} 
}
