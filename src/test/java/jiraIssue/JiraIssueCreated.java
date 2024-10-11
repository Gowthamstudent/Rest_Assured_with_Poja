package jiraIssue;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.annotations.Test;

import files.jiraPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class JiraIssueCreated {
	
	@Test
	public void jiraissue() {
		
		RestAssured.baseURI = "https://gowthamsvcet2k15.atlassian.net";
		String IssueCreatedResp = given().header("Content-Type", "application/json")
		.header("Authorization","Basic Z293dGhhbXN2Y2V0MmsxNUBnbWFpbC5jb206QVRBVFQzeEZmR0YwUjJaWXFPODd4WTRZVWlCa00ySTRaT1ZKcjNrMUZ5YkduT1dLZDNPNEZFTnVHNU81LWZyeXVZd1NFSmhBd3hTOVVvWkxjbF9fVWlpbHFXWTFxdkJIcnJtVEVDOFB1THFDUjd5R3I3amVleVI0WTVRX0pKY0JTUWdWSU5vUjRWc0dCaTBlYWdWVEtXWW0yYlRmMURjVzFnMUo3ZHdHVXJFRzI5MzFpQTFQTTZvPTk4QTlEMjdB")
		.body(jiraPayload.CreateissuePay()).when().post("rest/api/2/issue").then().log().all().assertThat().statusCode(201)
		.extract().response().asString();
		
		JsonPath js = new JsonPath(IssueCreatedResp);
		int ID = js.getInt("id");
		System.out.println(ID);
		
	//Add Attaachement in Issues
		given().header("X-Atlassian-Token","no-check").pathParam("keyid", ID)
		.header("Authorization","Basic Z293dGhhbXN2Y2V0MmsxNUBnbWFpbC5jb206QVRBVFQzeEZmR0YwUjJaWXFPODd4WTRZVWlCa00ySTRaT1ZKcjNrMUZ5YkduT1dLZDNPNEZFTnVHNU81LWZyeXVZd1NFSmhBd3hTOVVvWkxjbF9fVWlpbHFXWTFxdkJIcnJtVEVDOFB1THFDUjd5R3I3amVleVI0WTVRX0pKY0JTUWdWSU5vUjRWc0dCaTBlYWdWVEtXWW0yYlRmMURjVzFnMUo3ZHdHVXJFRzI5MzFpQTFQTTZvPTk4QTlEMjdB")
		.multiPart("file",new File("D:\\Wallpaper\\20231105_004908.jpg")).log().all().when().post("rest/api/2/issue/{keyid}/attachments")
		.then().log().all().assertThat().statusCode(200);
		
	//Get Attachemnt in Issue
		
		given().header("Content-Type", "application/json").pathParam("keyid", ID)
		.header("Authorization","Basic Z293dGhhbXN2Y2V0MmsxNUBnbWFpbC5jb206QVRBVFQzeEZmR0YwUjJaWXFPODd4WTRZVWlCa00ySTRaT1ZKcjNrMUZ5YkduT1dLZDNPNEZFTnVHNU81LWZyeXVZd1NFSmhBd3hTOVVvWkxjbF9fVWlpbHFXWTFxdkJIcnJtVEVDOFB1THFDUjd5R3I3amVleVI0WTVRX0pKY0JTUWdWSU5vUjRWc0dCaTBlYWdWVEtXWW0yYlRmMURjVzFnMUo3ZHdHVXJFRzI5MzFpQTFQTTZvPTk4QTlEMjdB")
		.when().get("rest/api/2/issue/{keyid}").then().log().all().assertThat().statusCode(200);
	}

}
