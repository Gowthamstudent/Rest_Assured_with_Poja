package com.restapisample;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import org.testng.Assert;

import files.Payload;
public class PlaceAllMethods {

	 
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String actualAddress = "70 Summer walk, USA";
		String response = given().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.postPlace())
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().asString();
		System.out.println("Data: "+response);
		JsonPath js = new JsonPath(response);
		String PlacceID = js.getString("place_id");
		System.out.println(PlacceID);
//UpdatePlace
		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.putPlace(PlacceID,actualAddress)).when().put("maps/api/place/update/json").then().log().all()
		.assertThat().body("msg",equalTo("Address successfully updated"));
		
//GetPlace
		
		String GetResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", PlacceID)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();
		JsonPath js1 = new JsonPath(GetResponse);
		String ExpectedAddress = js1.getString("address");
		System.out.println(ExpectedAddress);
		Assert.assertEquals(actualAddress, ExpectedAddress);
		
	//Delete
		
		given().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Payload.delPlace(PlacceID)).when().delete("maps/api/place/delete/json")
		.then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));
		
		
	//GetAfterDelete
		String DelResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id", PlacceID)
				.when().get("maps/api/place/get/json").then().log().all().assertThat().statusCode(404)
		.extract().asString();
		JsonPath js2 = new JsonPath(DelResponse);
		String resmsg = js2.getString("msg");
		System.out.println(resmsg);
		Assert.assertEquals("Get operation failed, looks like place_id  doesn't exists", resmsg);
		
		
	}

}
