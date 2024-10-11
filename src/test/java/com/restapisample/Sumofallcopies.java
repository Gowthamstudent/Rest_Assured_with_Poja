package com.restapisample;

import org.testng.Assert;
import org.testng.annotations.Test;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class Sumofallcopies {

	@Test
	public void sumCopies() {
		int sum = 0;
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int Coursesize = js.getInt("courses.size()");
		System.out.println(Coursesize);
		
		for(int i=0;i<Coursesize;i++) {
			String CourseTitle = js.getString("courses["+i+"].title");
			int CousrPrice = js.getInt("courses["+i+"].price");
			int CourseCopies = js.getInt("courses["+i+"].copies");
			int amount = CousrPrice*CourseCopies;
			
			System.out.println(CourseTitle+": "+amount);
			sum = sum+amount;
		}
		System.out.println("Total PuchaseAmount: "+sum);
		
		int ActuaalPurchaseAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println("PurchaseAmount:" +ActuaalPurchaseAmount);
		Assert.assertEquals(ActuaalPurchaseAmount, sum);
	}
	
}
