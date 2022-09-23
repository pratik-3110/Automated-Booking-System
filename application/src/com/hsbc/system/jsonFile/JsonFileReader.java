package com.hsbc.system.jsonFile;

import java.io.File;
/**
 * @author The Xceptionals
 *
 */
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.hsbc.system.exceptions.InvalidEmail;
import com.hsbc.system.exceptions.InvalidPhoneNumber;
import com.hsbc.system.exceptions.InvalidRole;
import com.hsbc.system.exceptions.InvalidUser;
import com.hsbc.system.model.Users;

/**
 * 
 * @author The Xceptionals Read a json file whose path is given as parameter to
 *         method. It parse the json objects, fetch values and check for valid
 *         values. If valid , put the fields into users object and ultimately
 *         return a list of users objects.
 *
 */
public class JsonFileReader {

	private int credits;

	public void setCredits(int credit) {
		this.credits = credit;
	}

	public static boolean VerifyEmail(String mail) {
		final Pattern REGEX = Pattern.compile(
				"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
				Pattern.CASE_INSENSITIVE);
		return REGEX.matcher(mail).matches();
	}

	public static boolean VerifyName(String name) {

		  String regx = "^[a-zA-Z\\s]+$";
		    Pattern pattern = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
		    Matcher matcher = pattern.matcher(name);
		    return matcher.matches();
	}

	public static boolean VerifyPhone(String phone) {

		for (int i = 0; i < phone.length(); i++) {
			char s = phone.charAt(i);
			if (!(s >= 48 && s <= 57)) {
				return false;
			}
		}
		return true;
	}

	public static boolean VerifyRole(String role) {

		role = role.toLowerCase();
		switch (role) {
		case "admin":
			return true;
		case "manager":
			return true;
		case "member":
			return true;
		}
		return false;
	}

	public static List<Users> generateUsers(String filePath) {

		List<Users> userList = new ArrayList<>();
		JSONParser parser = new JSONParser();

		JsonFileReader objCredit = new JsonFileReader();

		Logger logger = Logger.getLogger("JsonLog");
		FileHandler f = null;

		try {

			f = new FileHandler("JsonLogFile.log", true);
			logger.addHandler(f);
			SimpleFormatter formatter = new SimpleFormatter();
			f.setFormatter(formatter);
			FileReader fr=new FileReader(filePath);
			Object obj = parser.parse(fr);

			fr.close();
			JSONObject jsonObject = (JSONObject) obj;

			logger.info("Json File parsed successfully");

			JSONArray arr = (JSONArray) jsonObject.get("Users");

			logger.info("Object Array created successfully");

			int i = 0;
			try {
				while (true) {
					JSONObject o1 = (JSONObject) arr.get(i++);
					String name = (String) o1.get("name");
					String email = (String) o1.get("email");
					String phone = (String) o1.get("phone");
					String role = (String) o1.get("role");

					if (name == null || name == "" || !VerifyName(name)) {
						logger.info("Name of user :  " + name + "\nReason: ");
						logger.info("Invalid username in the Json Field");
						throw new InvalidUser();
					}
					if (email == null || email == "" || !VerifyEmail(email)) {
						logger.info("Name of user :  " + name + "\nReason: ");
						logger.info("Invalid Email in the Json Field");
						throw new InvalidEmail();
					}
					if (phone == null || phone.length() != 10 || !VerifyPhone(phone)) {
						logger.info("Name of user :  " + name + "\nReason: ");
						logger.info("Invalid phone in the Json Field");
						throw new InvalidPhoneNumber();
					}
					if (role == null || role == "" || !VerifyRole(role)) {
						logger.info("Name of user :  " + name + "\nReason: ");
						logger.info("Invalid role in the Json Field");
						throw new InvalidRole();
					}

					if (role.equalsIgnoreCase("manager")) {
						objCredit.credits = 2000;
					} else {
						objCredit.credits = 0;
					}

					userList.add(new Users(name, email, phone, role, objCredit.credits));

				}
			} catch (Exception e) {
				logger.info("End of parsing Json file");

			}

			f.close();

		} catch (NullPointerException e) {
			logger.info("Caught Unwanted Null Pointer Exception");
		} catch (IOException ie) {
			System.out.println(ie);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			System.out.println(e1);
		}

		return userList;
	}

	public static void main(String[] args) {

	}
}