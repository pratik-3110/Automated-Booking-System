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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.hsbc.system.dao.InsertUsers;
import com.hsbc.system.exceptions.LoggerFile;
import com.hsbc.system.jsonFile.JsonFileReader;
import com.hsbc.system.model.Users;

/**
 * Servlet implementation class UserInsertController
 * Controller to insert users into users table of database from json file
 */
@WebServlet("/add_users")
public class UserInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String fileName = "";
		if (ServletFileUpload.isMultipartContent(request)) {
			try {
				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				for (FileItem item : multiparts) {
					if (!item.isFormField()) {
						String name = new File(item.getName()).getName();
						item.write(new File(name));
						fileName = name;
					}
				}
				
			} catch (Exception ex) {
				System.out.println("File Upload Failed due to " + ex);
			}
		}

		System.out.println(fileName);

		System.out.println("Json file entered " + fileName);
		String filepath = fileName;
		System.out.println(filepath);
		JsonFileReader jReader = new JsonFileReader();
		ArrayList<Users> duplicate = (ArrayList) InsertUsers.insertUsers(jReader.generateUsers(filepath));
		System.out.print(duplicate.size());
		new File(filepath).delete();
		
		LoggerFile.LogHandle("Users added to database. Duplicate entries avoided= "+duplicate.size());
		request.getRequestDispatcher("/adminLogin.jsp").forward(request, response);

	}

}
