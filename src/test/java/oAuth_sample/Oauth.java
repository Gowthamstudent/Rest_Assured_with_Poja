package oAuth_sample;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class Oauth {
	
	
	@Test
	public void Oauthunt() {
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String accesinn = given().log().all().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials").formParam("scope", "trust")
		.when().post("oauthapi/oauth2/resourceOwner/token").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js = new JsonPath(accesinn);
		String AccessToken = js.getString("access_token");
		System.out.println(AccessToken);
	
	//Get Course Details
		String CourseResp = given().log().all().queryParam("access_token", AccessToken).when().get("oauthapi/getCourseDetails")
		.then().log().all().assertThat().statusCode(401).extract().response().asString();
		
		JsonPath js1 = new JsonPath(CourseResp);
		String expCourse = js1.getString("courses.webAutomation[0].courseTitle");
		System.out.println(expCourse);
		Assert.assertEquals("Selenium Webdriver Java", expCourse);
		
		
		
	}

}
