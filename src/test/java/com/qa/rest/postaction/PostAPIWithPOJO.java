package com.qa.rest.postaction;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.annotations.Test;
import com.qa.rest.postaction.RequestBodyPOJO;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class PostAPIWithPOJO {
	
	@Test
	public void createUser_With_POJO_Test() {
		
		RequestBodyPOJO requestBody = new RequestBodyPOJO("ash","male","ashcity@gmail.com","active");
				
		// RequestBodyPOJO();	
		//convert java POJO to JSON -- serialization( converting object to stream of bytes (over the network json will be sent as stream of bytes)) -- using JACKSON databind API liberary
		
		ObjectMapper mapper = new ObjectMapper();
		
		String UserJsonBody=null;
		
		try{
			 UserJsonBody = mapper.writeValueAsString(requestBody);
		}
		catch(JsonProcessingException e){
			e.printStackTrace();		
		}
				
		System.out.println(UserJsonBody);
		
		
		RestAssured.baseURI="https://gorest.co.in";
		 given().log().all()
		.contentType(ContentType.JSON)
		.header("Authorization", "Bearer ef670a8f857504b9c1ad8c048bc0d560d5c99b81021cafae35cb831e6a9c9e33")
		.body(UserJsonBody)
		.when().log().all()
		.post("/public/v1/users")
		.then().log().all()
		.assertThat()
		.contentType(ContentType.JSON);
		//.and()
		//.body("data.name",equal("uyutuyt"));
		//("data.name",equalTo(requestBody.getName()));
		
	}

	
	
	
}
