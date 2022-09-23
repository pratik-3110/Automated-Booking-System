package com.hsbc.system.service;

import com.hsbc.system.dao.UserDao;
import com.hsbc.system.exceptions.UserNotExistsException;
import com.hsbc.system.model.Users;

/**
 * 
 * @author The Xceptionals
 *Service class for login action
 */
public class LoginService {
	
	 private UserDao uLogin=new UserDao();
	
	public Users login(int id, String email) {	
	
		try {
		 return uLogin.login(id,email);
		}
		catch(UserNotExistsException ue) {
			throw new RuntimeException(ue);
		}
		
		
	}
	
	public void updateLoginTime(int id, String loginTime) {
		try {
			uLogin.updateLastLoginTime(id, loginTime);
		}catch(RuntimeException re) {
			throw re;
		}
	}
	

}
