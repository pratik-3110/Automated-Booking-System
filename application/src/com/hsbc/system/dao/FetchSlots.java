package com.hsbc.system.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hsbc.system.model.RoomSuggested;
import com.hsbc.system.util.DatabaseConnection;

import java.util.Date;
/**
 * This is to produce the rooms which satisfy the roomtype and date give nby manager in organize meeting
 * @author The Xceptionals
 *
 */

public class FetchSlots {

	public static void main(String[] args) throws SQLException {
		
		
		HashMap<String, RoomSuggested> result = fetchRooms("Business", "2020-11-14");
		
		System.out.println(result.keySet());
		
		
	

	}
	
	public static HashMap<String, RoomSuggested> fetchRooms(String roomType,String date) throws SQLException {
		
		
		
		

		Connection conn= null;
		PreparedStatement pst = null;
		Statement smt= null;
		conn = DatabaseConnection.getConnection();
		

		HashMap<String, List<Time>> startMap = new HashMap<>();
		HashMap<String, List<Time>> endMap = new HashMap<>();
		HashMap<String,RoomSuggested> resp = new HashMap<>();
		
		
		try {

	 	conn.prepareStatement("drop view roomscapture").execute();
	 	}catch(Exception e) {
	 		
	 	}
		try {
		conn.prepareStatement("drop view resp2").execute();
		}catch(Exception e) {
			
		}
		try {
		conn.prepareStatement("drop view roomtypematch2").execute();
		}catch(Exception e) {
			
		}
		
		
		/**
		 * This is the query to find the room with type given and dategiven.
		 */
		
		String query= "create view roomtypematch2 as select roomname from roomtypes where roomtype= '"+roomType+"'";
		pst = conn.prepareStatement(query);
		pst.executeUpdate();
		pst = conn.prepareStatement("create view resp2 as select roomname, starttime, endtime, userid from bookinginfo where roomname in"
				+ " (select roomname from roomtypematch2)"
				+ "and date= '"+date+ "'order by roomname asc, starttime asc");
		pst.executeUpdate();
		
		
		
		
		
		
		ResultSet rs = conn.prepareStatement("select * from resp2").executeQuery();
		
		
		
		/**
		 * This is to store the start and end time of room's all meeting in list and that list in hashmap.
		 */
		while(rs.next()) {
		
			//System.out.println(rs.getString("roomname")+" "+rs.getTime("starttime")+" "+rs.getTime("endtime"));
		
			System.out.println("here in this page");
		
			if(resp.containsKey(rs.getString("roomname"))==false)
					resp.put(rs.getString("roomname"), new RoomSuggested(new ArrayList<String>(), 
							new ArrayList<Integer>(), new ArrayList<>(),new ArrayList<>()));
			
			resp.get(rs.getString("roomname")).getOrganizedById().add(rs.getInt("userid"));
			
			if(startMap.containsKey(rs.getString("roomname"))==false) {
				startMap.put(rs.getString("roomname"), new ArrayList<Time>());
				endMap.put(rs.getString("roomname"), new ArrayList<Time>());
				
				List<Time> startList = startMap.get(rs.getString("roomname"));
				List<Time> endList = endMap.get(rs.getString("roomname"));
				startList.add(rs.getTime("starttime"));
				endList.add(rs.getTime("endtime"));
				startMap.put(rs.getString("roomname"), startList);
				endMap.put(rs.getString("roomname"), endList);
			}else {
				List<Time> startList = startMap.get(rs.getString("roomname"));
				List<Time> endList = endMap.get(rs.getString("roomname"));
				startList.add(rs.getTime("starttime"));
				endList.add(rs.getTime("endtime"));
				startMap.put(rs.getString("roomname"), startList);
				endMap.put(rs.getString("roomname"), endList);
			}
		}
		
		pst = conn.prepareStatement(" create view roomscapture as select roomname from resp2");
		pst.executeUpdate();
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		ResultSet rs1 = conn.prepareStatement("select * from meetingroom where roomname in (select roomname from roomscapture)").executeQuery();
	
		
		while(rs1.next()) {

			
			RoomSuggested roomSuggested= resp.get(rs1.getString("roomname"));
			roomSuggested.setRoomName(rs1.getString("roomname"));
			roomSuggested.setCredits(rs1.getInt("costperhour"));
			roomSuggested.setRatings(rs1.getFloat("ratings"));
			roomSuggested.setSeatingCapacity(rs1.getInt("seatingcapacity"));
			
			resp.put(rs1.getString("roomname"), roomSuggested);
				
		}
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		ResultSet rs2 = conn.prepareStatement("select * from roomamenities where roomname in (select roomname from roomscapture) order by roomname asc").executeQuery();

		
		while(rs2.next()) {
			
		//	System.out.println(rs2.getString("roomname")+" "+rs2.getString("roomamenity"));
			RoomSuggested roomSuggested = resp.get(rs2.getString("roomname"));
			roomSuggested.getAmenities().add(rs2.getString("roomamenity"));
			
			resp.put(rs2.getString("roomname"), roomSuggested);
			
		}
		
		
		
		
		
		
		
		/**
		 * This part is used to fetch the name of manager who booked the room.
		 */
		ResultSet rs3= conn.prepareStatement("select userid, username from users where userid in (select userid from resp2)").executeQuery();
		 
		 HashMap<Integer, String> idName = new HashMap<Integer, String>();
		 
		 while(rs3.next()) {
			 System.out.println(rs3.getInt("userid")+" "+rs3.getString("username"));
			 idName.put(rs3.getInt("userid"),rs3.getString("username"));
		 }
		 
		 for(Map.Entry<String, RoomSuggested> entry: resp.entrySet()) {
			 
			 List<Integer> IdList = entry.getValue().getOrganizedById();
			 List<String> NameList = entry.getValue().getOrganizedByName();
			 for(int i=0;i<IdList.size();i++) {
				 NameList.add(idName.get(IdList.get(i)));
			 }
		 }
		 
		 
		 ResultSet rsempty = conn.prepareStatement("select * from (roomamenities natural join meetingroom natural join "
					+ "(select roomname from roomtypematch2 where roomname not in (select roomname from resp2)) as a)").executeQuery();
			
			while(rsempty.next()) {
				
				if(resp.containsKey(rsempty.getString("roomname"))==false) {
					RoomSuggested room = new RoomSuggested(new ArrayList<String>(), new ArrayList<Integer>(),new ArrayList<Integer>(), new ArrayList<String>());
					room.setRoomName(rsempty.getString("roomname"));
					room.setCredits(rsempty.getInt("costperhour"));
					room.setRatings(rsempty.getFloat("ratings"));
					room.setSeatingCapacity(rsempty.getInt("seatingcapacity"));
					room.getAmenities().add(rsempty.getString("roomamenity"));
					room.getSchedule().add(480);
					resp.put(rsempty.getString("roomname"),room );
					
				}else {
					resp.get(rsempty.getString("roomname")).getAmenities().add(rsempty.getString("roomamenity"));
				}
			}
		 
		 
		 
		
		
		 for (Map.Entry<String,List<Time>> entry : startMap.entrySet())  {
			 
			 	String roomname= entry.getKey();
			 	List<Time> startTime = entry.getValue();
			 	List<Time> endTime = endMap.get(roomname);
			 	List<Integer> schedule = new ArrayList<>();
			 	
				Time t = new Time(9,0,0);
				long store = t.getTime();
			 	
			 	for(int i=0;i<startTime.size();i++) {
			 		
			 		long diff= startTime.get(i).getTime()-store;
		//			System.out.println("empty: "+diff/(1000*60));
					schedule.add((int)(diff/(1000*60)));
					
					long book = endTime.get(i).getTime()-startTime.get(i).getTime();
			//		System.out.println("booked: "+book/(1000*60));
					schedule.add((int)(book/(1000*60)));
					
					store=endTime.get(i).getTime();
			 	}
			 	
			// 	System.out.println("empty: "+(new Time(17,0,0).getTime()-endTime.get(endTime.size()-1).getTime())/(1000*60));
			 	schedule.add((int)(new Time(17,0,0).getTime()-endTime.get(endTime.size()-1).getTime())/(1000*60));
			 	
			 	RoomSuggested roomSuggested = resp.get(roomname);
			 	roomSuggested.setSchedule(schedule);
			 	resp.put(roomname, roomSuggested);
			 	
			 	System.out.println(roomSuggested);
			 	
		 }
	
		 return resp;
	}
	


}