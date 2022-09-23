package com.hsbc.system.model;

import java.sql.Time;

/**
 * 
 * @author The Xceptionals
 *
 */
public class BookingInfo {

	private int bookingId;
	
	private String title;
	private String roomName;
	private String date;
	private String startTime;
	private int duration;
	private Time endTime;
	private String israted;
	
	
	
	
	public BookingInfo() {
		super();
	}

	
	
	public BookingInfo(int bookingId, String title, String roomName, String date, String startTime, int duration,
			Time endTime, String israted) {
		super();
		this.bookingId = bookingId;
		this.title = title;
		this.roomName = roomName;
		this.date = date;
		this.startTime = startTime;
		this.duration = duration;
		this.endTime = endTime;
		this.israted = israted;
	}



	public int getBookingId() {
		return bookingId;
	}


	public void setBookingId(int bookingId) {
		this.bookingId = bookingId;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getStartTime() {
		return startTime;
	}


	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}


	

	public int getDuration() {
		return duration;
	}




	public void setDuration(int duration) {
		this.duration = duration;
	}




	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}


   
	
	
	public Time getEndTime() {
		return endTime;
	}



	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}



	public String getIsrated() {
		return israted;
	}



	public void setIsrated(String israted) {
		this.israted = israted;
	}



	
	
	
}
