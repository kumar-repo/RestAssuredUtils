package com.qa.rest.postaction;

import static io.restassured.RestAssured.given;
import org.testng.annotations.Test;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

/*
 * we can use response spec builder to validate common responses like "status code","content type" and other common attributes in response header
 *  for all test cases instead of asserting in each test case individually 
 *  simply call that respspecbuilder to assert all common header response
 * Basically any common assertion in response related to all test cases, check expectmethod
 */
public class ResponseSpecBuilderUtil {

	
	ResponseSpecBuilder res = new ResponseSpecBuilder();
	ResponseSpecification spec = res.expectContentType(ContentType.JSON).expectStatusCode(200).build();
			
	
	@Test
	public void ResponseSpecTest() {		
		given()
		.auth()
		.oauth2("4d8f2644d2e66301624ec32a8ad5c4391b6d66d576a8ecc72b2aebcac7804392")
		.when()
		.get("https://gorest.co.in/public/v1/users?name=Sushree&id=3014")
		.then()
		.assertThat()
		.spec(spec);
	}
	
	
	
}
