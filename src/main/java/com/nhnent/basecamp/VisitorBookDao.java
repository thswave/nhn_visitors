package com.nhnent.basecamp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VisitorBookDao {

	public static VisitorBook get(int id) {
		VisitorBook visitorBook = new VisitorBook();
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			Class.forName("cubrid.jdbc.driver.CUBRIDDriver");
			String url = "jdbc:cubrid:192.168.0.106:30000:nhnbasecamp:::";
			String userid = "nhnbasecamp";
			String password = "nhnbasecamp";

			conn = DriverManager.getConnection(url, userid, password);
			String sql = "select id, name, password, content from guest_book";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				visitorBook.setId(rs.getInt("id"));
				visitorBook.setName( rs.getString("name"));
				visitorBook.setPassword( rs.getString("password"));
				visitorBook.setContent(rs.getString("content"));
				
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			if (conn != null)
				try{
					conn.close();
				} catch(Exception e){}
		}
		return visitorBook;
	}

}
