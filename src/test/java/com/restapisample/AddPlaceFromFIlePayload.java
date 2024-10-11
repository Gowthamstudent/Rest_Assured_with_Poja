package com.restapisample;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import files.Payload;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class AddPlaceFromFIlePayload {

	@Test
	public void addPlace() throws IOException {
		
		String Data = new String(Files.readAllBytes(Paths.get("E:\\Eclipse_Work_Space\\RestAssuredProject\\src\\test\\resources\\jsonFile\\PostPlace.json")));
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String actualAddress = "70 Summer walk, USA";
		String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Data)
				.when().post("maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().asString();
		System.out.println("Data: "+response);
		JsonPath js = new JsonPath(response);
		String PlacceID = js.getString("place_id");
		System.out.println(PlacceID);
		
	//Delete
		
		/*
		 * RequestSpecification addHeader = (RequestSpecification) new
		 * RequestSpecBuilder().setBaseUri(" ").addQueryParam("", "").addHeader("",
		 * "").build(); new ResponseSpecBuilder().
		 */
		given().queryParam("key","qaclick123").header("Content-Type","application/json").body(Payload.delPlace(PlacceID))
		.when().delete("maps/api/place/delete/json").then().log().all().assertThat().statusCode(200).body("status", equalTo("OK"));
		
	}
	
}
