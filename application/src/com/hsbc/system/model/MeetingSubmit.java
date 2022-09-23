package com.hsbc.system.model;

import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author The Xceptionals
 *
 */
public class MeetingSubmit {

	private  String roomName;
	private  int userId;
	private String date;
	private Time startTime;
	private Time endTime;
	private int credit;
	private String title;
	private String meetingType;
	private String meetingInfo;
	private List<Integer> userList;
	
	public MeetingSubmit() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public MeetingSubmit(String roomName, int userId, String date, Time startTime, Time endTime, int credit,
			String title, String meetingType, String meetingInfo, List<Integer> userList) {
		super();
		this.roomName = roomName;
		this.userId = userId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.credit = credit;
		this.title = title;
		this.meetingType = meetingType;
		this.meetingInfo = meetingInfo;
		this.userList = userList;
	}




	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMeetingType() {
		return meetingType;
	}

	public void setMeetingType(String meetingType) {
		this.meetingType = meetingType;
	}

	public String getMeetingInfo() {
		return meetingInfo;
	}

	public void setMeetingInfo(String meetingInfo) {
		this.meetingInfo = meetingInfo;
	}

	public List<Integer> getUserList() {
		return userList;
	}

	public void setUserList(List<Integer> userList) {
		this.userList = userList;
	}
	
	
	
	
}
