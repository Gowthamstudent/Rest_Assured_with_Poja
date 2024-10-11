package libraryAPI;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import files.LibraryPayload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class LibrariesAllMethods {
	
	@Test (dataProvider="dataset")
	public void libraryApi(String isbn, String aisle) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String postResponse = given().log().all().header("Content-Type","application/json").body(LibraryPayload.postpayload(isbn,aisle))
		.when().post("Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().asString();
		
		JsonPath js = new JsonPath(postResponse);
		String LibID = js.getString("ID");
		System.out.println("Library ID: "+LibID);
		
		
//Delete Library
		
		String DelLib = given().body(LibraryPayload.DelPayload(LibID)).when().delete("Library/DeleteBook.php").then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("book is successfully deleted")).extract().asString();
		
	}
	
	@DataProvider
	public Object[][] dataset(){
		
		Object[][] dat = {{"leelavathy","8989"},{"Gowtham","9898"},{"Abirami","8767"}};
		
		return dat;
			
	}
}
