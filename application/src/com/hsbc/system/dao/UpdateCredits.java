package com.hsbc.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import com.hsbc.system.util.DatabaseConnection;

public class UpdateCredits {

	public static String update() throws Exception {
		Connection conn=null;
		PreparedStatement ps=null;
		try {
			conn=DatabaseConnection.getConnection();
			String loginQuery="update users set credit=? where role=?";			
			ps=conn.prepareStatement(loginQuery);
			ps.setInt(1,2000);
			ps.setString(2,"Manager");			
			int rowCount=ps.executeUpdate();			
			return "updated";
			
		}catch(Exception e) {
			throw new Exception("Not updated");
		}
		
	}
}
