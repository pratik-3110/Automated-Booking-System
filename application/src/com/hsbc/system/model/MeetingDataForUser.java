package com.hsbc.system.model;

/**
 * 
 * @author The Xceptionals
 *Bean to return meeting info 
 */
public class MeetingDataForUser {

	private String organiser;
	private String roomName;
	private String meetingTitle;
	private String date;
	private String starttime;
	private String endtime;

	public MeetingDataForUser() {

	}

	public MeetingDataForUser(String organiser, String roomName, String meetingTitle, String date, String starttime,
			String endtime) {
		super();
		this.organiser = organiser;
		this.roomName = roomName;
		this.meetingTitle = meetingTitle;
		this.date = date;
		this.starttime = starttime;
		this.endtime = endtime;
	}

	public String getOrganiser() {
		return organiser;
	}

	public String getRoomName() {
		return roomName;
	}

	public String getMeetingTitle() {
		return meetingTitle;
	}

	public String getDate() {
		return date;
	}

	public String getStarttime() {
		return starttime;
	}

	public String getEndtime() {
		return endtime;
	}

	public String toString() {
		return this.getOrganiser() + " " + this.getRoomName() + " " + this.getMeetingTitle() + " " + this.getDate()
				+ " " + this.getStarttime() + " " + this.getEndtime();
	}

}
