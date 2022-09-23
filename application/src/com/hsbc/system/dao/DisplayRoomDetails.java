package com.hsbc.system.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hsbc.system.model.MeetingRoom;
import com.hsbc.system.util.DatabaseConnection;

/**
 * 
 * @author The Xceptionals
 * Class having methods to display details of room on index page and admin page 
 *
 */
public class DisplayRoomDetails {
	
	public HashMap<String,Integer> roomCount(){
		Connection conn=null;
		PreparedStatement ps=null;
		HashMap<String ,Integer> roomCountDet=new HashMap<>();
		
		try {
			conn=DatabaseConnection.getConnection();
			String query="select roomtype,count(roomname) as room_count from roomtypes group by roomtype";
			
			ps=conn.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				roomCountDet.put(rs.getString(1), rs.getInt(2));
				//System.out.println(rs.getString(1)+ rs.getInt(2));
			}
			
			
		} 
		 catch (SQLException e) {
			 //e.printStackTrace();
			throw new RuntimeException("Could not fetch details");
		}
		
		return roomCountDet;
	}
	
	
	public List<MeetingRoom> roomList(){
		
		Connection conn=null;
		PreparedStatement ps=null;
		List<MeetingRoom> rl=new ArrayList<>();
		
		try {
			conn=DatabaseConnection.getConnection();
			String query="select roomName,seatingcapacity,costperhour from meetingroom";
			
			ps=conn.prepareStatement(query);
			
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				rl.add(new MeetingRoom(rs.getString(1),rs.getInt(2),rs.getInt(3)));
			}
			
			
		} 
		 catch (SQLException e) {
			 e.printStackTrace();
			//throw new RuntimeException("Could not fetch details");
		}
		
		return rl;
		
		
	}
	
	public List<String> getTypes(String roomName) {
		
		Connection conn=null;
		PreparedStatement ps=null;
		List<String> roomTypes=new ArrayList<>();
		
		try {
			conn=DatabaseConnection.getConnection();
			String query="select roomtype from roomtypes where roomname=?";
			
			ps=conn.prepareStatement(query);
			ps.setString(1, roomName);
			ResultSet rs=ps.executeQuery();
			
			while(rs.next()) {
				
				roomTypes.add(rs.getString(1));
			}
			
			
		} 
		 catch (SQLException e) {
			 e.printStackTrace();
			//throw new RuntimeException("Could not fetch details");
		}
		
		return roomTypes;
		
	}
	

	public static void main(String[] args) {
//		ArrayList<String> amenity=(ArrayList)new DisplayRoomDetails().getAmenities("ALT_03");
//	for(String s:amenity)
//		System.out.println(s);
	}
}
