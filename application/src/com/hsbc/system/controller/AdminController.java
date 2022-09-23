package com.hsbc.system.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hsbc.system.dao.InsertUsers;
import com.hsbc.system.exceptions.LoggerFile;
import com.hsbc.system.jsonFile.JsonFileReader;
import com.hsbc.system.model.Users;
import com.hsbc.system.service.AdminService;

/**
 * 
 * @author The Xceptionals
 * Controller file to handle adminLogin.jsp operations
 *
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType("text/html");
		 PrintWriter out = response.getWriter();
        String action=request.getParameter("submit").toLowerCase();
        
        AdminService aService=new AdminService();
		
		switch(action)
		{
		case "create room":String roomName=request.getParameter("name");
										int capacity=Integer.parseInt(request.getParameter("seats"));
										String amenities[]=request.getParameterValues("amenities");
										int  perHourCost=aService.calculateCredit(amenities,capacity);
			
										
										boolean isValidRoom=aService.roomExists(roomName);
			
										if(isValidRoom){
											try {
												
												String resp=aService.createRoom(roomName, perHourCost, capacity, amenities);
												if(resp.equalsIgnoreCase("success")) {
													request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
												}
												
												
												
											} catch (RuntimeException re) {
												
												LoggerFile.LogHandle("Admin tried to create a rooms.But  Operation failed!");
												request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
											}
				
									}
									else
									{
										LoggerFile.LogHandle("Admin tried to create duplicate rooms. Operation failed!");
										response.getWriter().println("<div class=\" w3-text-red w3-xlarge\" style=\"margin-left:440px; margin-top:10px;\">Meeting room with same name already exists!!\n Give  a different name.</div>");
										request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);
										
									}
			
									break;
		
		
								}
	}

}
