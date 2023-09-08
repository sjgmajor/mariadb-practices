package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMall;
import bookmall.vo.CategoryVo;

public class CategoryDao {

	public List<CategoryVo> findAll() {
		List<CategoryVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = "select a.category_No, a.name from category a";
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			
			while(rs.next()) {

				Long categoryNo = rs.getLong(1);
				String name = rs.getString(2);
				
				CategoryVo vo = new CategoryVo();
				vo.setCategoryNo(categoryNo);
				vo.setName(name);
				
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

	public void insert(CategoryVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMall.getConnection();
			
			String sql = "insert into category values(null, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, vo.getName());
			
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

	public Long findNoByName(String categoryName) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Long categoryNo = null;
		try {
			conn = BookMall.getConnection();
			String sql = 
					" select a.category_no" +
					"   from category a" +
					"  where a.name=?";
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1,categoryName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				categoryNo = rs.getLong(1);
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
		return categoryNo;
	}
}
