package files;

public class Location_Payload {
	
	public static String post_Loacation() {
		
		return "{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Frontline house\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://google.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}";	
	}
	
	
	public static String putplace(String placeId, String Address) {
		
		return "{\r\n" + 
				"\"place_id\":\""+placeId+"\",\r\n" + 
				"\"address\":\""+Address+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}";
		
		
	}
	
	
	
	
public static String delPlace(String placeId) {
		
		return "{\r\n" + 
				"    \"place_id\":\""+placeId+"\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
