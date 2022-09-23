package com.hsbc.system.model;
import java.util.List;


/**
 * 
 * @author The Xceptionals
 *
 */
public class RoomSuggested {
	
	private String roomName;
	private int credits;
	private float ratings;
	private int seatingCapacity;
	private List<String> amenities;
	private List<Integer> schedule;
	private List<Integer> organizedById;
	private List<String> organizedByName;
	

	

	public RoomSuggested(List<String> amenities, List<Integer> schedule, List<Integer> organizedById, List<String> organizedByName) {
	
		this.amenities = amenities;
		this.schedule = schedule;
		this.organizedById = organizedById;
		this.organizedByName=organizedByName;
	}



	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public float getRatings() {
		return ratings;
	}

	public void setRatings(float ratings) {
		this.ratings = ratings;
	}

	public int getSeatingCapacity() {
		return seatingCapacity;
	}

	public void setSeatingCapacity(int seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}

	public List<String> getAmenities() {
		return amenities;
	}

	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	public List<Integer> getSchedule() {
		return schedule;
	}

	public void setSchedule(List<Integer> schedule) {
		this.schedule = schedule;
	}
	
	public List<Integer> getOrganizedById() {
		return organizedById;
	}



	public void setOrganizedById(List<Integer> organizedById) {
		this.organizedById = organizedById;
	}



	public List<String> getOrganizedByName() {
		return organizedByName;
	}



	public void setOrganizedByName(List<String> organizedByName) {
		this.organizedByName = organizedByName;
	}



	public RoomSuggested() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
	return roomName+" "+credits+" "+ratings+" "+seatingCapacity+" "+amenities+" "+schedule+" "+organizedById+" "+organizedByName; 
	}
	

}
