package com.restapisample;

import files.Payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		JsonPath js = new JsonPath(Payload.CoursePrice());
		int Coursesize = js.getInt("courses.size()");
		System.out.println(Coursesize);
		
		int Purchaseamount = js.getInt("dashboard.purchaseAmount");
		System.out.println(Purchaseamount);
		
		String FirstCourse = js.getString("courses[0].title");
		System.out.println(FirstCourse);
		
//4. Print All course titles and their respective Prices
		
		for(int i=0;i<Coursesize;i++) {
		String allCourseTitle = js.getString("courses["+i+"].title");
		System.out.println("Course Title: "+allCourseTitle);
			System.out.println(js.getString("courses["+i+"].price"));
		}
		
//Print no of copies sold by RPA Course
		System.out.println("Print Number of Copies");
		for(int i=0;i<Coursesize;i++) {
		String allCourseTi = js.getString("courses["+i+"].title");
		if(allCourseTi.equalsIgnoreCase("RPA")) {
			
			int RPA1 = js.getInt("courses["+i+"].copies");
			System.out.println(RPA1);
			break;
		}
		
		}
		
		
	}

}
