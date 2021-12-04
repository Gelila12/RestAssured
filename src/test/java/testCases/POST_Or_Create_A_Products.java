package testCases;

import static io.restassured.RestAssured.given;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class POST_Or_Create_A_Products {
	
	
	SoftAssert softAssert = new SoftAssert();
	@Test
	public void Create_A_Products() {

		// https://techfios.com/api-prod/api/product/read.php
		HashMap payload = new HashMap();
		payload.put("id", "2408");
		payload.put( "name", "wow Pillow 3.0");
		payload.put("description", "The best pillow for amazing programmers.");
		payload.put( "price", "2000");
		payload.put ("category_id", "2");
		payload.put("category_name", "Electronics");

		Response response =
			given()
				.baseUri("https://techfios.com/api-prod/api/product")
				.header("Content-Type", "application/json; charset=UTF-8").queryParam("id", "2408")
				.body(payload).
			when()
			    .post("/create.php").
			then()
			    .extract().response();
			
		 
		String responseBody = response.getBody().asString();
		System.out.println("response Body:" + responseBody );
		
	 //Parsing responseBody to Json
		JsonPath js = new JsonPath(responseBody);
		
		String message = js.getString("message");
		
		//System.out.println("Product Id:" + productId);    
	 // Assert.assertEquals(productId , "2408");                //hardAssert-if there is a error its just says failed .
		softAssert.assertEquals(message, "Product was created."," not matching the assertions!!!");  // softAssert-if there is a mistake  it runs all the codes and tell you where its fails.
		
		//String productName = js.getString("name");
		//System.out.println("Product Name:" + productName);
	 // Assert.assertEquals(productName , "wow Pillow 3.0");
		//softAssert.assertEquals(productName, "wow Pillow 3.0","Product Name is not matching!");
		
		//String productPrice = js.getString("price"); 
		//System.out.println("Product Price:" + productPrice);
	//	Assert.assertEquals(productPrice , "2000");
		//softAssert.assertEquals(productPrice , "2000","Product Price is not matching!");
		
		softAssert.assertAll();

	}
}
		
	
	
	
	
	


