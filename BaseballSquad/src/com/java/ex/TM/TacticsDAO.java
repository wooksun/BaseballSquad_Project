package com.java.ex.TM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TacticsDAO {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/baseballsquad";
	static String dbId = "root";
	static String dbPw = "1234"; 
	
	Connection conn = null;
	
	public ArrayList<String> getPlayerName(String pos) {
		this.dbConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> name = new ArrayList<String>();
		name.add("선수를 선택하세요.");
	    try {
	      String sql = "select player_name from player where player_position = ? order by player_backnumber asc";
	      pst = conn.prepareStatement(sql);
	      pst.setString(1, pos);
	      rs = pst.executeQuery();
	 
	      while (rs.next()) {
			name.add(rs.getString("player_name"));
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
			this.closeRs(rs);
			this.closePst(pst);
			this.closeDB(conn);
		}
	    return name;
	}
	
	public ArrayList<String> getTacticsName(String category) {
		this.dbConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList<String> name = new ArrayList<String>();
		if(category.equals("del")) {
			name.add("삭제할 전술을 선택하세요.");

		} else if(category.equals("intro")) {
			name.add("소개할 전술을 선택하세요.");
		} else if(category.equals("load")) {
			name.add("불러올 전술을 선택하세요.");
		} else {
			name.add("");
		}
	    try {
	      String sql = "select tactics_name from tactics order by tactics_name asc";
	      pst = conn.prepareStatement(sql);
	      rs = pst.executeQuery(sql);
	 
	      while (rs.next()) {
			name.add(rs.getString("tactics_name"));
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
			this.closeRs(rs);
			this.closePst(pst);
			this.closeDB(conn);
		}
	    return name;
	}
	
	public boolean duplicateCheck(String name) {
		this.dbConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			String sql = "select count(*) cnt from tactics where tactics_name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			if (rs.next()) {
				int cnt = rs.getInt("cnt");
				if(cnt > 0) result = true;
				else result = false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeRs(rs);
			this.closePst(pst);
			this.closeDB(conn);
		}
		return result;
	}
	
	public boolean addTactics(TacticsDTO tacticsDTO) {
		this.dbConn();
		boolean result = true;
		PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into tactics values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, tacticsDTO.getTactics_name());
			pst.setString(2, tacticsDTO.getDh());
			pst.setString(3, tacticsDTO.getC());
			pst.setString(4, tacticsDTO.getB1());
			pst.setString(5, tacticsDTO.getB2());
			pst.setString(6, tacticsDTO.getB3());
			pst.setString(7, tacticsDTO.getSs());
			pst.setString(8, tacticsDTO.getLf());
			pst.setString(9, tacticsDTO.getCf());
			pst.setString(10, tacticsDTO.getRf());
			pst.setString(11, tacticsDTO.getCh1());
			pst.setString(12, tacticsDTO.getCh2());
			pst.setString(13, tacticsDTO.getCh3());
			pst.setString(14, tacticsDTO.getSp1());
			pst.setString(15, tacticsDTO.getSp2());
			pst.setString(16, tacticsDTO.getSp3());
			pst.setString(17, tacticsDTO.getSp4());
			pst.setString(18, tacticsDTO.getSp5());
			pst.setString(19, tacticsDTO.getRp1());
			pst.setString(20, tacticsDTO.getRp2());
			pst.setString(21, tacticsDTO.getCp1());
			pst.setString(22, tacticsDTO.getCp2());
			
			if(1 == pst.executeUpdate()) {
				result = true;
			} else {
				result = false;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.closePst(pst);
			this.closeDB(conn);
		}
		return result;
	}
		
	public boolean updateTactics(TacticsDTO tacticsDTO) {
		this.dbConn();
		PreparedStatement pst = null;
		boolean result = true;
		try {
			conn.setAutoCommit(false);
			String sql = "update tactics set dh=?, c=?, b1=?, b2=?, b3=?, ss=?, lf=?, cf=?, rf=?, ch1=?, ch2=?,"
									+ " ch3=?, sp1=?, sp2=?, sp3=?, sp4=?, sp5=?, rp1=?, rp2=?, cp1=?, cp2=?"
					                + "where tactics_name=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, tacticsDTO.getDh());
			pst.setString(2, tacticsDTO.getC());
			pst.setString(3, tacticsDTO.getB1());
			pst.setString(4, tacticsDTO.getB2());
			pst.setString(5, tacticsDTO.getB3());
			pst.setString(6, tacticsDTO.getSs());
			pst.setString(7, tacticsDTO.getLf());
			pst.setString(8, tacticsDTO.getCf());
			pst.setString(9, tacticsDTO.getRf());
			pst.setString(10, tacticsDTO.getCh1());
			pst.setString(11, tacticsDTO.getCh2());
			pst.setString(12, tacticsDTO.getCh3());
			pst.setString(13, tacticsDTO.getSp1());
			pst.setString(14, tacticsDTO.getSp2());
			pst.setString(15, tacticsDTO.getSp3());
			pst.setString(16, tacticsDTO.getSp4());
			pst.setString(17, tacticsDTO.getSp5());
			pst.setString(18, tacticsDTO.getRp1());
			pst.setString(19, tacticsDTO.getRp2());
			pst.setString(20, tacticsDTO.getCp1());
			pst.setString(21, tacticsDTO.getCp2());
			pst.setString(22, tacticsDTO.getTactics_name());
//			pst.setString(1, tacticsDTO.getFormation());
//			pst.setString(2, tacticsDTO.getP1());
//			pst.setString(3, tacticsDTO.getP1_t());
//			pst.setString(4, tacticsDTO.getP2());
//			pst.setString(5, tacticsDTO.getP2_t());
//			pst.setString(6, tacticsDTO.getP3());
//			pst.setString(7, tacticsDTO.getP3_t());
//			pst.setString(8, tacticsDTO.getP4());
//			pst.setString(9, tacticsDTO.getP4_t());
//			pst.setString(10, tacticsDTO.getP5());
//			pst.setString(11, tacticsDTO.getP5_t());
//			pst.setString(12, tacticsDTO.getP6());
//			pst.setString(13, tacticsDTO.getP6_t());
//			pst.setString(14, tacticsDTO.getP7());
//			pst.setString(15, tacticsDTO.getP7_t());
//			pst.setString(16, tacticsDTO.getP8());
//			pst.setString(17, tacticsDTO.getP8_t());
//			pst.setString(18, tacticsDTO.getP9());
//			pst.setString(19, tacticsDTO.getP9_t());
//			pst.setString(20, tacticsDTO.getP10());
//			pst.setString(21, tacticsDTO.getP10_t());
//			pst.setString(22, tacticsDTO.getP11());
//			pst.setString(23, tacticsDTO.getP11_t());
//			pst.setString(24, tacticsDTO.getComment());
//			pst.setString(25, tacticsDTO.getName());
			if(1 == pst.executeUpdate()) {
				result = true;
			} else {
				result = false;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.closePst(pst);
			this.closeDB(conn);
		}
		return result;
	}
	
	public boolean delTactics(String name) {
		this.dbConn();
		boolean result = true;
		try {
			conn.setAutoCommit(false);
			String sql = "delete from tactics where tactics_name = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			if(1 == pst.executeUpdate()) {
				result = true;
			} else {
				result = false;
			}
			conn.commit();
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			this.closeDB(conn);
		}
		return result;
	}
	
	public TacticsDTO loadTactics(String name) {
		this.dbConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		TacticsDTO tactics = null;
		try {
			String sql = "select * from tactics where tactics_name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				tactics = new TacticsDTO();
				tactics.setTactics_name(rs.getString("tactics_name"));
				tactics.setDh(rs.getString("dh"));
				tactics.setC(rs.getString("c"));
				tactics.setB1(rs.getString("b1"));
				tactics.setB2(rs.getString("b2"));
				tactics.setB3(rs.getString("b3"));
				tactics.setSs(rs.getString("ss"));
				tactics.setLf(rs.getString("lf"));
				tactics.setCf(rs.getString("cf"));
				tactics.setRf(rs.getString("rf"));
				tactics.setCh1(rs.getString("ch1"));
				tactics.setCh2(rs.getString("ch2"));
				tactics.setCh3(rs.getString("ch3"));
				tactics.setSp1(rs.getString("sp1"));
				tactics.setSp2(rs.getString("sp2"));
				tactics.setSp3(rs.getString("sp3"));
				tactics.setSp4(rs.getString("sp4"));
				tactics.setSp5(rs.getString("sp5"));
				tactics.setRp1(rs.getString("rp1"));
				tactics.setRp2(rs.getString("rp2"));
				tactics.setCp1(rs.getString("cp1"));
				tactics.setCp2(rs.getString("cp2"));
//				tactics.setName(rs.getString("name"));
//				tactics.setFormation(rs.getString("formation"));
//				tactics.setP1(rs.getString("p1")); tactics.setP1_t(rs.getString("p1_t")); 
//				tactics.setP2(rs.getString("p2")); tactics.setP2_t(rs.getString("p2_t")); 
//				tactics.setP3(rs.getString("p3")); tactics.setP3_t(rs.getString("p3_t")); 
//				tactics.setP4(rs.getString("p4")); tactics.setP4_t(rs.getString("p4_t")); 
//				tactics.setP5(rs.getString("p5")); tactics.setP5_t(rs.getString("p5_t")); 
//				tactics.setP6(rs.getString("p6")); tactics.setP6_t(rs.getString("p6_t")); 
//				tactics.setP7(rs.getString("p7")); tactics.setP7_t(rs.getString("p7_t")); 
//				tactics.setP8(rs.getString("p8")); tactics.setP8_t(rs.getString("p8_t")); 
//				tactics.setP9(rs.getString("p9")); tactics.setP9_t(rs.getString("p9_t")); 
//				tactics.setP10(rs.getString("p10")); tactics.setP10_t(rs.getString("p10_t")); 
//				tactics.setP11(rs.getString("p11")); tactics.setP11_t(rs.getString("p11_t"));
//				tactics.setComment(rs.getString("comment"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closePst(pst);
			this.closeRs(rs);
			this.closeDB(conn);
		}
		try {
			return tactics;
		} catch (Exception e) {
			return null;
		}
	}
	
	private void dbConn() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbId, dbPw);
		} catch (Exception e) {
		}
	}
	
	private void closeRs(ResultSet rs) {
		try {
			if(rs != null) rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void closePst(PreparedStatement pst) {
		try {
			if(pst != null) pst.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void closeDB(Connection conn) {
		try {
			if(conn != null) conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
