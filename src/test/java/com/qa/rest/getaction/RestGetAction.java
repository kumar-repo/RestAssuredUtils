package com.qa.rest.getaction;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class RestGetAction {
	
	public long zipCode=90245;
	
	@Test
	public void getData() {		
		RestAssured.baseURI="https://api.zippopotam.us/";
         given().log().all()
         .pathParam("postCode", zipCode)
		.when().log().all()
		.get("us/{postCode}")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.and()
		.contentType(ContentType.JSON)
		.and()
		.header("Server", "cloudflare");	
	}
	
	@Test
	public void getResponse() {
		
		Response respose=
		given()
		.when()
		.get("https://api.zippopotam.us/us/90245");
		//use single quote if json response get-value-of-key-with-spaces-using-jsonpath-library
		String res= respose.getBody().jsonPath().getString("'post code'");
		System.out.println("post code is " + res);
	}

}
