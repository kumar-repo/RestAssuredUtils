package com.qa.rest.schema;

import static io.restassured.RestAssured.given;
import java.io.File;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;



public class ValidateSchemaTest {

	/*
	 * JSON schema converter : https://www.liquid-technologies.com/online-json-to-schema-converter
	 */
	@Test
	public void booking_schema_Test() {
		
		RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		
		 given().log().all()
		.contentType(ContentType.JSON)
		.body(new File("E:\\Naveen Automation Lab\\API Testing\\QA\\src\\test\\resource\\booking.json"))
		.when().log().all()
		.post("/booking")
		.then().log().all()
		.assertThat()
		.statusCode(200)
		.body(matchesJsonSchemaInClasspath("BookingSchema.json"));
	}	
	
}
