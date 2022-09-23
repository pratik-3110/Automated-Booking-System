package com.hsbc.system.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hsbc.system.dao.FetchSlots;
import com.hsbc.system.dao.ManagerDao;
import com.hsbc.system.exceptions.LoggerFile;
import com.hsbc.system.model.MeetingSubmit;
import com.hsbc.system.model.RoomSuggested;
import com.hsbc.system.model.Users;

/**
 * 
 * @author The Xceptionals
 * Servlet controller for manager operation in creating, deleting meeting and give ratings to the meeting
 *
 */

@WebServlet("/ManagerController")
public class ManagerController extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession(false);
		Users u=(Users)session.getAttribute("userOb");
		
		//HttpSession session1=request.getSession(true);
		
		//this fuctionality is for saving meeting data into table but this is remaining...........
		
		//First 
		if(request.getParameter("submit").equals("findRooms"))
		{
			String date=request.getParameter("date");
			String roomType=request.getParameter("roomType");
			
			String meetingTitle =request.getParameter("meetingTitle");
			String meetingInfo=request.getParameter("meetingInfo");
			
			
			
			
			
			session.setAttribute("date",date);
			session.setAttribute("roomType",roomType);
			
			System.out.println(roomType);
			session.setAttribute("meetingTitle",meetingTitle);
			session.setAttribute("meetingInfo",meetingInfo);
			
			
			
			try {
				HashMap<String, RoomSuggested> hm=FetchSlots.fetchRooms(roomType, date);
				session.setAttribute("HashMap",hm);
				request.getRequestDispatcher("/organizeMeeting.jsp").forward(request, response);
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		//satrangi second
		else if(request.getParameter("submit").equals("insert"))
		{
			
			
			String roomName=request.getParameter("roomName");
			String startTime=request.getParameter("startTime");
			String endTime=request.getParameter("duration");
			//String meetingTitle =request.getParameter("meetingTitle");
			//String meetingInfo=request.getParameter("meetingInfo");
			
			
		
			String par[] = startTime.split(":");
			Time t = new Time(Integer.parseInt(par[0]),Integer.parseInt(par[1]),Integer.parseInt(par[2]));
			
			
			String par1[] = endTime.split(":");
			Time t1 = new Time(Integer.parseInt(par1[0]),Integer.parseInt(par1[1]),Integer.parseInt(par1[2]));
			
			
		   int duration=(int) ((t1.getTime()-t.getTime())/(1000*60));
		   System.out.println(duration);
		   
		   
			int costPerHour=ManagerDao.getCostByRoomName(roomName);
			int totalCost=(duration*costPerHour)/60;
			
			 int managerId=u.getUserId();
			 int credit=ManagerDao.getCreditById(managerId);
			 
			 if(credit<totalCost)
			 {
				 request.getRequestDispatcher("/organizeMeeting.jsp").forward(request, response);
			 }
			 else
			 {
				int finalCredit=credit-totalCost;
				
				
				session.setAttribute("finalCredit",finalCredit);
				session.setAttribute("roomName",roomName);
				session.setAttribute("startTime",startTime);
				session.setAttribute("endTime",endTime);
			//	session1.setAttribute("meetingTitle",meetingTitle);
				//session1.setAttribute("meetingInfo",meetingInfo);
				
				
				boolean valid=ManagerDao.varifyTime(startTime, endTime,(String)session.getAttribute("date") , roomName);
				
				if(valid)
				{
					request.getRequestDispatcher("/selectUsers.jsp").forward(request, response);
					
				}else
				{
					response.setContentType("text/html");
					response.getWriter().println("<div class=\" w3-text-red w3-xlarge\" style=\"margin-left:400px; margin-top:20px;\">*Time Overlaping With Other Meeting</div>");
					request.getRequestDispatcher("/organizeMeeting.jsp").include(request, response); 
				}
			 }
		
		}
		else if(request.getParameter("submit").equals("finish"))
		{
		  String NameId[]=request.getParameterValues("UserId");
		  int size=NameId.length;
		  String roomName=(String) session.getAttribute("roomName");
		  
		  HashMap<String, RoomSuggested> hm=(HashMap<String, RoomSuggested>) session.getAttribute("HashMap");
		  
		 int capacity=hm.get(roomName).getSeatingCapacity();
		  
		  if(size>capacity)
		  {
			  response.setContentType("text/html");
				response.getWriter().println("<div class=\" w3-text-red w3-xlarge\" style=\"margin-left:400px; margin-top:20px;\">Number of selected members exceeds room capacity!!</div>");
				response.getWriter().println("<h3>RoomCapcity: "+capacity+"</h3>");
				request.getRequestDispatcher("/selectUsers.jsp").include(request, response);
				
		  }
		  else
		  {
			  
		 
				  List<Integer> l=new ArrayList<>();
				  
				  for(String s:NameId)
				  {
					  //String ans[]=s.split("-");
					  int id=Integer.parseInt(s);
					  System.out.println(id);
					  l.add(id);
				  }
				  
				 
				   
					
					HttpSession session2=request.getSession(false);
					
					String startTime=(String)session2.getAttribute("startTime");
					String par[] = startTime.split(":");
					Time t = new Time(Integer.parseInt(par[0]),Integer.parseInt(par[1]),Integer.parseInt(par[2]));
					
					String duration=(String)session2.getAttribute("endTime");
					String par1[] = duration.split(":");
					Time t1 = new Time(Integer.parseInt(par1[0]),Integer.parseInt(par1[1]),Integer.parseInt(par1[2]));
					
					session2.getAttribute("duration");
					session2.getAttribute("totalCost");
					
					
					
					
					MeetingSubmit ob=new MeetingSubmit((String)session2.getAttribute("roomName"),
							u.getUserId(),
							(String)session2.getAttribute("date"),
							t,
							t1,
							(int)session2.getAttribute("finalCredit"),
							(String)session2.getAttribute("meetingTitle"),
							(String)session2.getAttribute("roomType"),
							(String)session2.getAttribute("meetingInfo"),l);
					
					 try {
						boolean result=ManagerDao.addMeeting(ob);
						u.setCredit((int)session2.getAttribute("finalCredit"));
						session.setAttribute("userOb", u);
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					 
					 request.getRequestDispatcher("/managerLogin.jsp").include(request, response);
			
					 
		  }			 
		}
		// Deleting scheduled meeting by bookingId
		else if(request.getParameter("submit").equals("Delete Meeting"))
		{
			int bookingId=Integer.parseInt(request.getParameter("bookingId"));
			String roomName=request.getParameter("roomName");
			String duration=request.getParameter("duration");
		   
			int costPerHour=ManagerDao.getCostByRoomName(roomName);
			int totalCost=(Integer.parseInt(duration)*costPerHour)/60;
			
			int managerId=u.getUserId();
			int credit=u.getCredit();
			int finalCredit=credit+totalCost;
			
			ManagerDao.deleteMeetingByBookoingId(bookingId);
			ManagerDao.updateCreditById(managerId, finalCredit);
			
			u.setCredit(finalCredit);
			session.setAttribute("userOb", u);
			
			LoggerFile.LogHandle("Manager "+u.getUserName()+" deleted a meeting supposed to be held in room "+ roomName);
			response.sendRedirect("managerLogin.jsp");
			
		}
		else if(request.getParameter("submit").equalsIgnoreCase("Give Rating"))
		{
			int bookingId=Integer.parseInt(request.getParameter("bookingId"));
			String roomName=request.getParameter("roomName");
			float rating=Float.parseFloat(request.getParameter("ratings"));
			
			ManagerDao.updateRating(roomName, rating,bookingId);
			request.getRequestDispatcher("/managerLogin.jsp").include(request, response);
			
		}
		
	}

}
