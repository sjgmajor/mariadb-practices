package bookmall.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bookmall.main.BookMall;
import bookmall.vo.OrderBookVo;
import bookmall.vo.OrderVo;

public class OrderDao {

	public List<OrderVo> findAll() {
		List<OrderVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = "select a.order_no" +
					     ", a.member_no, a.address, a.total_price from orders a;";
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Long orderNo = rs.getLong(1);
				Long memberNo = rs.getLong(2);
				String address = rs.getString(3);
				Long totalPrice = rs.getLong(4);
				
				OrderVo vo = new OrderVo();
				vo.setOrderNo(orderNo);
				vo.setMemberNo(memberNo);
				vo.setAddress(address);
				vo.setTotalPrice(totalPrice);
				
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

	public void insertOrder(OrderVo vo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMall.getConnection();
			
			String sql = "insert into orders values(null, ?, ?, null)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, vo.getMemberNo());
			pstmt.setString(2, vo.getAddress());
			
			pstmt.executeUpdate();;

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
	
	public void insertOrderbook(OrderBookVo obv) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMall.getConnection();
			
			String sql = "insert into order_book values(?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, obv.getBookNo());
			pstmt.setLong(2, obv.getOrderNo());
			pstmt.setLong(3, obv.getPrice());
			pstmt.setLong(4,obv.getQuantity());
			
			pstmt.executeUpdate();

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

	public List<OrderVo> findAllByMemberNo(Long memberNo) {
		List<OrderVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = "select a.order_no" +
					     ", a.address, a.total_price from orders a" +
					     " where a.member_no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, memberNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				
				Long orderNo = rs.getLong(1);
				String address = rs.getString(2);
				Long totalPrice = rs.getLong(3);
				
				OrderVo vo = new OrderVo();
				vo.setOrderNo(orderNo);
				vo.setAddress(address);
				vo.setTotalPrice(totalPrice);
				
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

	public void updateOrder(OrderVo odvo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = BookMall.getConnection();
			
			String sql = " update orders a" +
					     "    set total_price=?" + 
					     "  where a.order_no=?";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setLong(1, odvo.getTotalPrice());
			pstmt.setLong(2, odvo.getOrderNo());
			
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

	public List<OrderBookVo> findOrdeBookByOrderNo(Long orderNo) {
		List<OrderBookVo> result = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = BookMall.getConnection();
			String sql = " select a.book_no, a.price, a.quantity" +
					     "   from order_book a" +
					     "  where a.orders_no=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setLong(1, orderNo);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				Long bookNo = rs.getLong(1);
				Long price = rs.getLong(2);
				Long quantity = rs.getLong(3);
				
				OrderBookVo Obvo = new OrderBookVo();
				Obvo.setBookNo(bookNo);
				Obvo.setPrice(price);
				Obvo.setQuantity(quantity);

				result.add(Obvo);
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

	public OrderVo findLatestByMemberNo(Long memberNo) {
		OrderVo result = null;
	    Connection conn = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    
	    try {
	        conn = BookMall.getConnection();
	        String sql =
	            " select a.order_no" +
	            " , a.address, a.total_price from orders a" +
	            " where a.member_no=?" +
	            " order by order_no desc LIMIT 1"; 
	        
	        pstmt = conn.prepareStatement(sql);

	        pstmt.setLong(1, memberNo);

	        rs = pstmt.executeQuery();

	        while(rs.next()) {
	            Long orderNo = rs.getLong(1);
	            String address = rs.getString(2);
	            Long totalPrice = rs.getLong(3);
	            
	            OrderVo vo = new OrderVo();
	            vo.setOrderNo(orderNo);
	            vo.setAddress(address);
	            vo.setTotalPrice(totalPrice);
	        
	        	result = vo;
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
