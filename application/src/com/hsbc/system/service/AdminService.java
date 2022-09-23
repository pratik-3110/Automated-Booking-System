package com.hsbc.system.service;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import com.hsbc.system.dao.AdminDao;
import com.hsbc.system.model.MeetingRoom;
import com.hsbc.system.model.Users;

/**
 * 
 * @author The Xceptionals
 *Service class for admin operations
 */
public class AdminService {
	
	AdminDao adminDao= new AdminDao();
	
	public boolean roomExists(String roomName) {
		return adminDao.checkRoom(roomName);
	}
	
	public int calculateCredit(String[] amenities,int capacity) {
		int credit=0;
		HashMap<String,Integer> creditValue=new HashMap<>();
		creditValue.put("Projector", 5);
		creditValue.put("Wifi Connection", 10);
		creditValue.put("Conference Call", 15);
		creditValue.put("Whiteboard", 5);
		creditValue.put("Water Dispenser", 5);
		creditValue.put("TV", 10);
		creditValue.put("Coffee Machine", 10);
		
		for(int i=0;i<amenities.length;i++) {
			//System.out.println(creditValue.get(amenities[i]));
			credit+=((Integer)creditValue.get(amenities[i])).intValue();
		}
		
		if(capacity>5 && capacity<=10)
			credit+=10;
		else if(capacity>10)
			credit+=20;
		
		return credit;
	}
	
	public String createRoom(String roomName, int cost, int capacity, String[] amenities) {
		MeetingRoom mRoom=new MeetingRoom(roomName, capacity, 0, 0, cost, amenities);
		try {
		 return adminDao.addRoom(mRoom);
		}
		catch(SQLException se) {
			throw new RuntimeException("Sorry!! Something went wrong. Please try again.");
		}
	}
	
	public List<Users> userList(){
		return adminDao.getUserList();
	}
	
	

}
