package com.hsbc.system.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;

import com.hsbc.system.exceptions.UserNotExistsException;
import com.hsbc.system.model.BookingInfo;
import com.hsbc.system.model.MeetingSubmit;
import com.hsbc.system.model.Users;
import com.hsbc.system.util.DatabaseConnection;

/**
 * DAO implementation of role-manager operations basically revolving around meetings.
 * @author The Xceptionals
 *
 */
public class ManagerDao {

	/**
	 * this Functionality is for getting costPerHour of perticuler Roomname
	 * @param roomName
	 * @return
	 */
	public static int getCostByRoomName(String roomName)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		int cost=0;
		
		try {
			conn=DatabaseConnection.getConnection();
			String getCostPerHour="select * from MEETINGROOM where roomname=?";
			
			ps=conn.prepareStatement(getCostPerHour);
			ps.setString(1,roomName);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				cost=rs.getInt("costPerHour");
			}
			
			return cost;
		} 
		 catch (SQLException e) {
			throw new RuntimeException(e);
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
	
	/**
	 * This functionality is for getting credits by userid
	 * @param userId
	 * @return
	 */
	public static int getCreditById(int userId)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		int credit=0;
		
		try {
			conn=DatabaseConnection.getConnection();
			String getCostPerHour="select * from Users where userid=?";
			
			ps=conn.prepareStatement(getCostPerHour);
			ps.setInt(1,userId);
			
			ResultSet rs=ps.executeQuery();
			
			if(rs.next())
			{
				credit=rs.getInt("credit");
			}
			else
			{
				
				System.out.println("fail");
				throw new UserNotExistsException();
				
			}
			return credit;
			
		} 
		  catch (UserNotExistsException e) {
			  throw new RuntimeException(e);
		}
		catch (SQLException e) {
			throw new RuntimeException(e);
			
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

	
	/**
	 * get Scheduled Meetings By managerId
	 * @param userId
	 * @return
	 */
	public static ArrayList<BookingInfo> getScheduledMeetingById(int userId)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		int credit=0;
		ArrayList<BookingInfo> as=new ArrayList<>();
		
		try {
			conn=DatabaseConnection.getConnection();
			String getScheduledMeeting="select b.bookingid,b.roomname,b.date,b.starttime,b.endtime,b.israted,m.title from BookingInfo b inner join meeting m on b.userid=? and b.bookingid=m.bookingid";
			
			ps=conn.prepareStatement(getScheduledMeeting);
			ps.setInt(1,userId);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next())
			{
				
				Time st=rs.getTime("starttime");
				Time et=rs.getTime("endtime");
				
				int duration=(int) ((et.getTime()-st.getTime())/(60*1000));
				
				as.add(new BookingInfo(rs.getInt("bookingid"),rs.getString("title"),rs.getString("roomname"),rs.getString("date"),st.toString(), duration,et,rs.getString("israted")));
				
			}
			return as;
			
		} 
		 
		catch (SQLException e) {
			throw new RuntimeException(e);
			
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
	
	
	/**
	 * Delete a meeting using booking id of the meeting
	 * @param bookingId
	 */
	public static void deleteMeetingByBookoingId(int bookingId)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		int credit=0;
		
		try {
			conn=DatabaseConnection.getConnection();
			String delete="delete from bookinginfo where bookingId=?";
			
			ps=conn.prepareStatement(delete);
			ps.setInt(1,bookingId);
			
			ps.executeUpdate();
			
		} 
		 
		catch (SQLException e) {
			throw new RuntimeException(e);
			
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
	
	/**
	 * Update ratings of a room on the basis of meetings conducted
	 * @param roomName
	 * @param rating
	 * @param bookingId
	 */
	public static void updateRating(String roomName,float rating,int bookingId)
	{
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=DatabaseConnection.getConnection();
			String getRatings="select * from MEETINGROOM where roomname=?";
			
			ps=conn.prepareStatement(getRatings);
			ps.setString(1,roomName);
			
			ResultSet rs=ps.executeQuery();
			
			float ratings=0;
			int noOfRatings=0;
			if(rs.next())
			{
				ratings=rs.getFloat("ratings");
				noOfRatings=rs.getInt("noOfRatings");
				
			}
			
			noOfRatings=noOfRatings+1;
			float avgRating= ((ratings*noOfRatings)+rating)/noOfRatings ;
			
			String updateRatings="update meetingRoom set ratings=?,noOfRatings=? where roomname=?";
			ps=conn.prepareStatement(updateRatings);
	
			ps.setFloat(1,avgRating);
			ps.setInt(2,noOfRatings);
			ps.setString(3,roomName);
			int i=ps.executeUpdate();
			
			String updateisRated="update bookingInfo set isRated=? where bookingId=?";
			ps=conn.prepareStatement(updateisRated);
			
			ps.setString(1,"Yes");
			ps.setInt(2, bookingId);
			
			ps.executeUpdate();
			
			
		} 
		 catch (SQLException e) {
			throw new RuntimeException(e);
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
	
	/**
	 * Update credits of manager on the basis of deleting or organising meetings.
	 * @param userId
	 * @param credit
	 */
	
	public static void updateCreditById(int userId,int credit)
	{
		
		Connection conn=null;
		PreparedStatement ps=null;
		
		try {
			conn=DatabaseConnection.getConnection();
			
			String updateCredits="update users set credit=? where userId=?";
			ps=conn.prepareStatement(updateCredits);
	
			ps.setInt(1,credit);
			ps.setInt(2, userId);
			
			ps.executeUpdate();
			
			
			
		} 
		 catch (SQLException e) {
			throw new RuntimeException(e);
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
	
public synchronized static boolean varifyTime(String starttime,String endtime,String date,String roomName)
{
	
	Connection conn = DatabaseConnection.getConnection();
	PreparedStatement pst = null;
	Statement smt= null;
	
	String querycheckslot = "select * from bookinginfo where ((?<=starttime and ?<endtime and ?>starttime)"
			+ "or (?>starttime and ?<endtime) or (?<starttime and ?>endtime) or (?>=starttime and ?<endtime and ?>endtime)"
			+ "or (?=starttime and ?=endtime)) and date=? and roomname=?";
	
	try {
		pst = conn.prepareStatement(querycheckslot);
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		pst.setString(3, endtime);
		pst.setString(4, starttime);
		pst.setString(5, endtime);
		pst.setString(6, starttime);
		pst.setString(7, endtime);
		pst.setString(8, starttime);
		pst.setString(9, starttime);
		pst.setString(10, endtime);
		pst.setString(11, starttime);
		pst.setString(12, endtime);
		
		pst.setString(13, date);
		pst.setString(14, roomName);
		

		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getInt("bookingid"));
			return false;
		}else {
			
	     return true;
			
		}
		
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		throw new RuntimeException(e);
		
	}
	
	
	
}
	

/**
 * database batch operation to insert data of meeting to 3 different tables
 * @param meetingsubmit
 * @return
 * @throws SQLException
 */
	
public synchronized static boolean addMeeting(MeetingSubmit meetingsubmit) throws SQLException {
		
		Connection conn = DatabaseConnection.getConnection();
		PreparedStatement pst = null;
		Statement smt= null;
		
		String  starttime = meetingsubmit.getStartTime().toString();
		String endtime = meetingsubmit.getEndTime().toString();
		System.out.println(endtime);
		System.out.println(starttime);
		
		
		

	//	System.out.println(datestring);
		
		
	//	String querycheckslot = "select * from bookinginfo where ((?<starttime and ?<endtime and ?>starttime)"
	//			+ " or (?<endtime and ?>starttime)"
	//			+ " or (?<starttime and ?>endtime) or (?=starttime and ?=endtime))"
	//			+ " and date=? and roomname=?";
		
		
		String querycheckslot = "select * from bookinginfo where ((?<=starttime and ?<endtime and ?>starttime)"
				+ "or (?>starttime and ?<endtime) or (?<starttime and ?>endtime) or (?>=starttime and ?<endtime and ?>endtime)"
				+ "or (?=starttime and ?=endtime)) and date=? and roomname=?";
		
		pst = conn.prepareStatement(querycheckslot);
		
		System.out.println(querycheckslot);

		
		
		pst.setString(1, starttime);
		pst.setString(2, endtime);
		pst.setString(3, endtime);
		pst.setString(4, starttime);
		pst.setString(5, endtime);
		pst.setString(6, starttime);
		pst.setString(7, endtime);
		pst.setString(8, starttime);
		pst.setString(9, starttime);
		pst.setString(10, endtime);
		pst.setString(11, starttime);
		pst.setString(12, endtime);
		pst.setString(13, meetingsubmit.getDate());
		pst.setString(14, meetingsubmit.getRoomName());
		
	
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			System.out.println(rs.getInt("bookingid"));
			return false;
		}else {
			
		conn.setAutoCommit(false);
			
			
		pst = conn.prepareStatement("insert into bookinginfo (roomname,userid,date,starttime,endtime,israted) values (?,?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		//pst.setInt(1, i);
		pst.setString(1, meetingsubmit.getRoomName());
		pst.setInt(2, meetingsubmit.getUserId());
		pst.setString(3, meetingsubmit.getDate());
		pst.setString(4, starttime);
		pst.setString(5, endtime);
		pst.setString(6, "No");
		pst.execute();
		
		int i=0;
		
		ResultSet rs4= pst.getGeneratedKeys();
		while(rs4.next()) {
			i=rs4.getInt(1);
		}
		
		

			
			
			pst = conn.prepareStatement("insert into meeting values (?,?,?,?)");
			pst.setInt(1, i);
			pst.setString(2,meetingsubmit.getTitle());
			pst.setString(3,meetingsubmit.getMeetingType());
			pst.setString(4, meetingsubmit.getMeetingInfo());
			
			pst.execute();
			
			
			pst= conn.prepareStatement("insert into attend values (?,?)");
			for(Integer userid:meetingsubmit.getUserList()) {
				pst.setInt(1, i);
				pst.setInt(2, userid);
				pst.execute();
			}
			
			
			pst = conn.prepareStatement("update users set credit=? where userid=?");
			pst.setInt(1, meetingsubmit.getCredit());
			pst.setInt(2, meetingsubmit.getUserId());
			
			
			pst.execute();
			
			conn.commit();
				
			
			//conn.rollback();
			
			return true;
		
		}
}
}	