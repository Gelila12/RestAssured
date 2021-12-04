package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.concurrent.TimeUnit;

public class GET_Or_Read_A_Products {
	
	SoftAssert softAssert = new SoftAssert();
	@Test
	public void read_A_Products() {

		// https://techfios.com/api-prod/api/product/read.php

		Response response = given().baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").queryParam("id", "2408").when()
				.get("read_one.php").then()

				.extract().response();
		
//		  int statusCode = response .getStatusCode(); 
//		  
//		  System.out.println("StatusCode: "+ statusCode); 
//		  Assert.assertEquals(statusCode , 200);
//		  
			/*
			 * long responseTime= response.getTimeIn(TimeUnit.MILLISECONDS);
			 * System.out.println("Response time: " + responseTime); if(responseTime<= 2000)
			 * { System.out.println("Response time is within range!"); }else {
			 * System.out.println("Response time is out of range!"); }
			 */
		 
		String responseBody = response.getBody().asString();
		System.out.println("response Body:" + responseBody );
		
		JsonPath js = new JsonPath(responseBody);
		String productId = js.getString("id");
		System.out.println("Product Id:" + productId);    
	 // Assert.assertEquals(productId , "2408");                //hardAssert-if there is a error its just says failed .
		softAssert.assertEquals(productId, "2408","Product id is not matching!");  // softAssert-if there is a mistake  it runs all the codes and tell you where its fails.
		
		String productName = js.getString("name");
		System.out.println("Product Name:" + productName);
	 // Assert.assertEquals(productName , "wow Pillow 3.0");
		softAssert.assertEquals(productName, "wow Pillow 3.0","Product Name is not matching!");
		
		String productPrice = js.getString("price"); 
		System.out.println("Product Price:" + productPrice);
	//	Assert.assertEquals(productPrice , "2000");
		softAssert.assertEquals(productPrice , "2000","Product Price is not matching!");
		
		softAssert.assertAll();

	}
}
