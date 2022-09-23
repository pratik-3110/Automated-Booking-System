package com.hsbc.system.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
/**
 * This is the function which is used to generate the type of room from the information of amenities during create room. before storing new room in table
 * this generatetype will be called with hashset of amenities as argument. now on basis of store info of type the type will be decided
 * and list of type will be retured to save new room method in dao which called.
 * @author The Xceptionals
 *
 */

public class GenerateRoomType {
	
	public static Map<String, Set<String>> typeDetails = new HashMap<>();
	
	static {
		typeDetails.put("Classroom Training", new HashSet<>());
		typeDetails.put("Online Training", new HashSet<>());
		typeDetails.put("Conference Call", new HashSet<>());
		typeDetails.put("Business", new HashSet<>());
		
		
		typeDetails.get("Classroom Training").add("Whiteboard");
		typeDetails.get("Classroom Training").add("Projector");
		typeDetails.get("Online Training").add("Wifi Connection");
		typeDetails.get("Online Training").add("Projector");
		typeDetails.get("Conference Call").add("Conference Call");
		typeDetails.get("Business").add("Projector");
		
		
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Set<String> roomAmenities = new HashSet<>();
		roomAmenities.add("Coffee Maker");
		roomAmenities.add("Whiteboard");
		roomAmenities.add("Projector");
		
		
		List<String> type = getType(roomAmenities);
		
		System.out.println(type);
		
		
		
	}
	
	public static List<String> getType(Set<String> roomAmenities){
		
		
		
		List<String> type = new ArrayList<>();
		
		Iterator itr = typeDetails.keySet().iterator();
		
		while(itr.hasNext()) {
			
			String key = (String)itr.next();
			Set<String> particularCriteria = typeDetails.get(key);
			
			if(roomAmenities.containsAll(particularCriteria)){
				type.add(key);
			}
		}
		
		if(type.size()==0)
			type.add("Others");
		
		return type;
		
		
		
	}

}
