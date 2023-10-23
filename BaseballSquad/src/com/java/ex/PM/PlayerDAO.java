package com.java.ex.PM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class PlayerDAO {
	static String driver = "com.mysql.jdbc.Driver";
	static String url = "jdbc:mysql://127.0.0.1:3306/baseballsquad";
	static String dbId = "root";
	static String dbPw = "1234"; 
	
	Connection conn = null;
	
	//등 번호 중복 체크
	public boolean duplicateCheck(int backNum) {
		this.dbConn();
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean result = false;
		
		try {
			String sql = "select count(*) cnt from player where player_backnumber = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, backNum);
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
	
	//선수 추가
	public boolean addPlayer(PlayerDTO playerDTO) {
		this.dbConn();
		boolean result = true;
		PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			String sql = "insert into player values (?,?,?,?,?,?,?)";
			pst = conn.prepareStatement(sql);
			pst.setString(1, playerDTO.getPlayer_name());
			pst.setInt(2, playerDTO.getPlayer_age());
			pst.setInt(3, playerDTO.getPlayer_height());
			pst.setInt(4, playerDTO.getPlayer_weight());
			pst.setInt(5, playerDTO.getPlayer_backnumber());
			pst.setString(6, playerDTO.getPlayer_pitchingandhitting());
			pst.setString(7, playerDTO.getPlayer_position());
			
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
	
	//선수 확인 후 데이터 추출 (등번호로 찾기)
	public PlayerDTO confirmPlayer(int backNum) {
		this.dbConn();
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from player where player_backnumber = ?";
			pst = conn.prepareStatement(sql);
			pst.setInt(1, backNum);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlayerDTO playerDTO = new PlayerDTO();
				playerDTO.setPlayer_name(rs.getString("player_name"));
				playerDTO.setPlayer_age(rs.getInt("player_age"));
				playerDTO.setPlayer_height(rs.getInt("player_height"));
				playerDTO.setPlayer_weight(rs.getInt("player_weight"));
				playerDTO.setPlayer_backnumber(rs.getInt("player_backnumber"));
				playerDTO.setPlayer_pitchingandhitting(rs.getString("player_pitchingandhitting"));
				playerDTO.setPlayer_position(rs.getString("player_position"));
				list.add(playerDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.closeRs(rs);
			this.closePst(pst);
			this.closeDB(conn);
		}
		return list.get(0);
	}
	public PlayerDTO confirmPlayer(String name) {
		this.dbConn();
		List<PlayerDTO> list = new ArrayList<PlayerDTO>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			String sql = "select * from player where player_name = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, name);
			rs = pst.executeQuery();
			while (rs.next()) {
				PlayerDTO playerDTO = new PlayerDTO();
				playerDTO.setPlayer_name(rs.getString("player_name"));
				playerDTO.setPlayer_age(rs.getInt("player_age"));
				playerDTO.setPlayer_height(rs.getInt("player_height"));
				playerDTO.setPlayer_weight(rs.getInt("player_weight"));
				playerDTO.setPlayer_backnumber(rs.getInt("player_backnumber"));
				playerDTO.setPlayer_pitchingandhitting(rs.getString("player_pitchingandhitting"));
				playerDTO.setPlayer_position(rs.getString("player_position"));
				list.add(playerDTO);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			this.closeRs(rs);
			this.closePst(pst);
			this.closeDB(conn);
		}
		return list.get(0);
	}
	
	public boolean updatePlayer(PlayerDTO playerDTO) {
		this.dbConn();
		PreparedStatement pst = null;
		boolean result = true;
		try {
			conn.setAutoCommit(false);
			String sql = "update player set player_name=?, player_age=?, player_height=?, player_weight=?, player_pitchingandhitting=?, player_position=? where player_backnumber=?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, playerDTO.getPlayer_name());
			pst.setInt(2, playerDTO.getPlayer_age());
			pst.setInt(3, playerDTO.getPlayer_height());
			pst.setInt(4, playerDTO.getPlayer_weight());
			pst.setString(5, playerDTO.getPlayer_pitchingandhitting());
			pst.setString(6, playerDTO.getPlayer_position());
			pst.setInt(7, playerDTO.getPlayer_backnumber());
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
		}
		return result;
	}
	
	public boolean deletePlayer(int backNum) {
		this.dbConn();
		boolean result = true;
		try {
			conn.setAutoCommit(false);
			String sql = "delete from player where player_backnumber = ?";
			PreparedStatement pst = conn.prepareStatement(sql);
			pst.setInt(1, backNum);
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
		} 
		return result;
	}
	
	public Vector getPlayer() {
		this.dbConn();
		Vector data = new Vector();
		PreparedStatement pst = null;
		ResultSet rs = null;
	    try {
	      String sql = "select * from player order by player_backnumber asc";
	      pst = conn.prepareStatement(sql);
	      rs = pst.executeQuery(sql);
	 
	      while (rs.next()) {
			String player_name = rs.getString("player_name");
			String player_age = rs.getString("player_age");
			String player_height = rs.getString("player_height");
			String player_weight = rs.getString("player_weight");
			String player_backnumber = rs.getString("player_backnumber");
			String player_pitchingandhitting = rs.getString("player_pitchingandhitting");
			String player_position = rs.getString("player_position");
			
			Vector row = new Vector();
			row.add(player_backnumber);
			row.add(player_pitchingandhitting);
			row.add(player_name);
			row.add(player_age);
			row.add(player_height);
			row.add(player_weight);
			row.add(player_position);
			
			data.add(row);
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    } finally {
			this.closeRs(rs);
			this.closePst(pst);
			this.closeDB(conn);
		}
	    return data;
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
