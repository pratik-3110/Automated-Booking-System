package com.hsbc.system.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

/**
 * This is the function which is called to provide unique id to the insertUsers
 * function.
 * 
 * @author The Xceptionals
 *
 */

public class UserIdGenerator {

	public static int generateId() {

		Connection conn = null;
		PreparedStatement ps = null;
		String userid = "1000000";
		try {
			conn = DatabaseConnection.getConnection();
			String query = "Select count,currentyear from util";

			ps = conn.prepareStatement(query);

			ResultSet rs = ps.executeQuery();

			rs.next();
			int count = rs.getInt(1);
			int cYear = rs.getInt(2);

			LocalDate date = LocalDate.now();
			if (cYear != date.getYear()) {
				cYear = date.getYear();
				count = 100;

				ps = conn.prepareStatement("Update util set count=100, currentyear=" + cYear);
				ps.executeUpdate();
			}

			count += 1;

			userid = cYear + Integer.toString(count);
			//System.out.println(userid);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return Integer.parseInt(userid);
	}

	public static void updateCount(int i) {
		Connection conn = null;
		PreparedStatement ps = null;

		try {

			conn = DatabaseConnection.getConnection();
			ps = conn.prepareStatement("update util set count=count+" + i);
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
