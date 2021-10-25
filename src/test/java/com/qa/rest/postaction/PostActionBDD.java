package com.qa.rest.postaction;

import static io.restassured.RestAssured.given;
import java.io.File;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class PostActionBDD {

	@Test
	public void postActionPassingAsBody() {
		
		/*
		 * URL: https://restful-booker.herokuapp.com/apidoc/
		 * 
		 * Request :
		 * curl -X POST \
  https://restful-booker.herokuapp.com/auth \
  -H 'Content-Type: application/json' \
  -d '{
    "username" : "admin",
    "password" : "password123"
}'
		 * 
		 * 
		 * Response:
		 * HTTP/1.1 200 OK

{
    "token": "abc123"
}
		 */
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		given()
		.contentType(ContentType.JSON)
		.body("{\"username\" : \"admin\",\"password\" : \"password123\"}")
		.when()
		.post("/auth")
		.then()
		.assertThat()
		.statusCode(200);		
	}

	@Test
	public void postActionPassingBodyAsFile() {
		
		RestAssured.baseURI="https://restful-booker.herokuapp.com";
		String token = given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("E:\\Naveen Automation Lab\\API Testing\\QA\\src\\test\\java\\com\\qa\\rest\\testdata\\credentails.json"))
		.when().log().all()
		.post("/auth")
		.then().log().all()
		.extract().path("token");
//assertThat()
//statusCode(200);
		System.out.println("got token value from response :" + token);
		Assert.assertNotNull(token);
	}
}
