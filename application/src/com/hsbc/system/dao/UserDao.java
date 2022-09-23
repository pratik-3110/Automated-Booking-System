package com.hsbc.system.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.hsbc.system.exceptions.UserNotExistsException;
import com.hsbc.system.model.MeetingDataForUser;
import com.hsbc.system.model.Users;
import com.hsbc.system.util.DatabaseConnection;

/**
 * 
 * @author The Xceptionals
 * DAO for checking login credentials and update last login time of user
 *Also fetch details of meetings schedules for member and manager taking their userid
 */
public class UserDao {

	 public Users login(int userId,String email) throws UserNotExistsException
	 {
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=DatabaseConnection.getConnection();
			String loginQuery="select * from users where userid=? and email=?";
			
			ps=conn.prepareStatement(loginQuery);
			ps.setInt(1,userId);
			ps.setString(2,email);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				Users u=new Users(rs.getInt("userid"),rs.getString("username"),rs.getString("email"),rs.getString("phone"),rs.getString("role"),rs.getInt("credit"),rs.getString("lastloggedin"));
				System.out.println("success");
				return u;
				
			}
			else
			{
				System.out.println("fail");
				throw new UserNotExistsException();
			}
			
			
		} 
		 catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		 
	 }
	 
	 public  void updateLastLoginTime(int userId,String newLoginTime) 
	 {
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=DatabaseConnection.getConnection();
			String updateQuery="update users set lastLoggedIn=? where userid=?";
			
			ps=conn.prepareStatement(updateQuery);
			ps.setString(1,newLoginTime);
			ps.setInt(2,userId);
			
			ps.executeUpdate();
			
			
		} 
		 catch (SQLException e) {
			throw new RuntimeException("Oops!! Something went wrong.");
		}
		finally {
			try {
				if(conn!=null)
				{
					conn.close();
				}
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
		
		 
	 }
	 
	 public List<MeetingDataForUser> getMeetings(int userid) {
			Connection conn = null;
			PreparedStatement ps = null;
			List<MeetingDataForUser> uData = null;
			LocalDate sdate=LocalDate.now();
			try {
				conn = DatabaseConnection.getConnection();

				String query = "select title, username, roomname,date,starttime,endtime from bookinginfo join meeting on bookinginfo.bookingid=meeting.bookingid join users on bookinginfo.userid=users.userid where date>=(?) and bookinginfo.bookingid IN (select bookingid from attend where userid=(?)) ";

				
				ps = conn.prepareStatement(query);
				
				ps.setInt(2, userid);
				ps.setDate(1, Date.valueOf(sdate));

				ResultSet rs = ps.executeQuery();

				//System.out.println(" data");
				uData = new ArrayList<>();
				
				while (rs.next()) {
						uData.add(new MeetingDataForUser(rs.getString("username"), rs.getString("roomname"),
						rs.getString("title"), rs.getString("date"), rs.getString("starttime"),
						rs.getString("endtime")));
				}
				return uData;

			} catch (SQLException e) {
				e.printStackTrace();
				throw new RuntimeException("Data could not be fetched.");

			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (SQLException e3) {
					e3.printStackTrace();
				}
			}
	 }

//		public static void main(String[] args) {
//			ArrayList<MeetingDataForUser> ml=(ArrayList)new UserDao().getMeetings(2020103);
//			System.out.println(ml.size());
//			for (MeetingDataForUser m :ml ) {
//				System.out.println(m.getOrganiser());
//			}
//		}

}
