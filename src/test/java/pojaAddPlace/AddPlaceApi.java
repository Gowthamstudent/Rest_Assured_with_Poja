package pojaAddPlace;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import files.Location_Payload;
import files.Payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import pojaclass.Location_post;
import pojaclass.Place_Api;
import pojaclass.Response_payload_post;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AddPlaceApi {
	
	@Test
	public void addPlace() throws IOException {
		
		
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		RequestSpecification reqs = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
									.addHeader("Content-Type","application/json").build();
		
		Place_Api p = new Place_Api();
		Location_post l = new Location_post();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		p.setLocation(l);
		p.setAccuracy(50);
		p.setName("Frontline house");
		p.setPhone_number("(+91) 983 893 3937");
		p.setAddress("29, side layout, cohen 09");
		List<String> at = new ArrayList<String>();
		at.add("shoe park");
		at.add("shop");
		p.setTypes(at);
		p.setWebsite("http://google.com");
		p.setLanguage("French-IN");
		
		
		
		Response_payload_post rew = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").
		//body(Location_Payload.post_Loacation()).
		body(p).
		when().post("maps/api/place/add/json")
		.as(Response_payload_post.class);
		
		
		String PlacceID = rew.getPlace_id();
		System.out.println("Post PlaceId: "+ PlacceID);
		
		
		
		
	
		
		//.then().log().all().assertThat().statusCode(200).extract().response().asString();
		  //JsonPath js = new JsonPath(rew); String PlacceID =
		 // js.getString("place_id"); System.out.println(PlacceID);
		
// Put Method
		
		String UpdateAdd = "70 Summer walk, USA";
		
		given().log().all().spec(reqs).body(Location_Payload.putplace(PlacceID,UpdateAdd)).when().put("maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200);
		
	
//Get Method
		//Place_Api getres =
				given().log().all().queryParam("key","qaclick123").queryParam("place_id",PlacceID).header ("Content-Type","application/json").
		  when().get("maps/api/place/get/json")
		  
		
		//.as(Place_Api.class);
		//String Updated_Address = getres.getAddress();
		//System.out.println(getres.getAddress());
		
		//assert.assertTrue(Updated_Address,UpdateAdd);	  
		  .then().log().all().assertThat().statusCode(200);
		 
		 
		
//Delete Method
		
		 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body(Location_Payload.delPlace(PlacceID))
		  .when().delete("maps/api/place/delete/json").then().log().all().assertThat().
		  statusCode(200).body("status", equalTo("OK"));
		 
		 
		
	}

}
