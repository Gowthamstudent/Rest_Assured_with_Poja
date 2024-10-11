package files;

public class jiraPayload {
	
	public static String CreateissuePay()
	{
		return "{\r\n" + 
				"    \"fields\": {\r\n" + 
				"       \"project\":\r\n" + 
				"       {\r\n" + 
				"          \"key\": \"RSJIR\"\r\n" + 
				"       },\r\n" + 
				"       \"summary\": \"IMportantBox can't Work Properlyw\",\r\n" + 
				"       \"description\": \"Creating of an issue using project keys and issue type names using the REST API\",\r\n" + 
				"       \"issuetype\": {\r\n" + 
				"          \"name\": \"Bug\"\r\n" + 
				"       }\r\n" + 
				"   }\r\n" + 
				"}";
		
		
	}
}
