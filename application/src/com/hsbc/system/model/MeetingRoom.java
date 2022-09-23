package com.hsbc.system.model;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author The Xceptionals
 * Bean to fetch fields of meeting rooms
 *
 */
public class MeetingRoom {

	private String roomName;
	private int capacity;
	private float rating;
	private int noOfRating;
	private int perHourCost;
	private String[] amenities;
	private List<String> roomTypes;
	
	public MeetingRoom() {
		super();
	}
	
	public MeetingRoom(String roomName, int capacity, int perHourCost) {
		this.roomName=roomName;
		this.capacity=capacity;
		this.perHourCost=perHourCost;
	}
	
	public MeetingRoom(String roomName, int capacity, float rating, int noOfRating, int perHourCost,
			String[] amenities) {
		this(roomName,capacity,perHourCost);
		this.rating = rating;
		this.noOfRating = noOfRating;
		this.amenities = amenities;
	}

	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public int getCapacity() {
		return capacity;
	}
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getNoOfRating() {
		return noOfRating;
	}
	public void setNoOfRating(int noOfRating) {
		this.noOfRating = noOfRating;
	}
	public int getPerHourCost() {
		return perHourCost;
	}
	public void setPerHourCost(int perHourCost) {
		this.perHourCost = perHourCost;
	}
	public String[] getAmenities() {
		return amenities;
	}
	public void setAmenities(String[] amenities) {
		this.amenities = amenities;
	}

	public List<String> getRoomTypes() {
		return roomTypes;
	}

	public void setRoomTypes(List<String> roomTypes) {
		this.roomTypes = roomTypes;
	}

	@Override
	public String toString() {
		return "MeetingRoom [roomName=" + roomName + ", capacity=" + capacity + ", rating=" + rating + ", noOfRating="
				+ noOfRating + ", perHourCost=" + perHourCost + ", amenities=" + Arrays.toString(amenities)
				+ ", roomTypes=" + roomTypes + "]";
	}

	
	
}
