package files;

public class PojaClass_LibraryPayload {
	
	
	public static String postpayload(String isb, String i) {
		
		return "{\r\n" + 
				"\r\n" + 
				"\"name\":\"Learn Appium Automation with Java\",\r\n" + 
				"\"isbn\":\""+isb+"\",\r\n" + 
				"\"aisle\":\""+i+"\",\r\n" + 
				"\"author\":\"John foe\"\r\n" + 
				"}\r\n" + 
				"";
	}
	
	
	public static String DelPayload(String Libid) {
		
		return "{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+Libid+"\"\r\n" + 
				" \r\n" + 
				"} \r\n" + 
				"";
	}

}
