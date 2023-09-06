package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import bookmall.vo.OrderVo;

public class OrderDao {

	public List<OrderVo> findAll() {
		return null;
		
	}

	public void insert(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = MemberDao.getConnection();
			
			String sql = "insert into orders values(null, ?, ?, null)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setString(2, vo.getAddress());
			
			pstmt.executeQuery();

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
}
