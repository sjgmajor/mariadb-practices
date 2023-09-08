package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMall;
import bookmall.vo.CartVo;

public class CartDao {

	public void insert(CartVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMall.getConnection();
			
			String sql = "insert into cart values(null, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getQuantity());
			pstmt.setLong(2, vo.getBookNo());
			pstmt.setLong(3, vo.getMemberNo());
			
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

	public List<CartVo> findAllbyMemberNo(Long no) {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = 
					" select a.cart_no, a.quantity, a.book_no" +
					"   from cart a" +
					"  where a.member_no=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, no);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long cartNo = rs.getLong(1);
				Long quantity = rs.getLong(2);
				Long bookNo = rs.getLong(3);
				
				CartVo vo = new CartVo();
				vo.setCartNo(cartNo);
				vo.setQuantity(quantity);
				vo.setBookNo(bookNo);
				
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

	public List<CartVo> findAll() {
		List<CartVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = "select a.cart_no, a.quantity, a.book_no, a.member_no from member a;";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long cartNo = rs.getLong(1);
				Long quantity = rs.getLong(2);
				Long bookNo = rs.getLong(3);
				Long memberNo = rs.getLong(4);
				
				CartVo vo = new CartVo();
				vo.setCartNo(cartNo);
				vo.setQuantity(quantity);
				vo.setBookNo(bookNo);
				vo.setMemberNo(memberNo);
				
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
