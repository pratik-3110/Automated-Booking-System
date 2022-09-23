package com.hsbc.system.service;

import java.util.ArrayList;
import java.util.List;

import com.hsbc.system.dao.UserDao;
import com.hsbc.system.model.MeetingDataForUser;

/**
 * 
 * @author The Xceptionals
 * Service class for Member functions
 *
 */
public class MemberService {
	
	private UserDao userDao=new UserDao();
	
	public List<MeetingDataForUser> meetings(int userid){
		
		try {
			return userDao.getMeetings(userid);
		}
		catch(RuntimeException re) {
			throw re;
		}
		
	}
	
	

}
