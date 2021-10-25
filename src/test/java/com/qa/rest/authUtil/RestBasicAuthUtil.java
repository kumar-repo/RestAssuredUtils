package com.qa.rest.authUtil;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

public class RestBasicAuthUtil {
	
	//basic
	//preemptive
	//digest
	//Oauth1
	//Oauth2.0
	
	public String tokenId;

	@Test
	public void basicAuthRestcall() {	
     given()
     .auth()
     .preemptive()
     .basic("admin", "admin")
     .when()
     .get("https://the-internet.herokuapp.com/basic_auth")
     .then()
     .and()
     .assertThat()
     .statusCode(200);    		
	}
	
	/*
	 * digest will send hashmap the login credentials and send to server 
	 */
	@Test
	public void basicAuthWithDigestcall() {	
     given()
     .auth()
     .digest("admin", "admin")
     .when()
     .get("https://the-internet.herokuapp.com/basic_auth")
     .then()
     .and()
     .assertThat()
     .statusCode(200);    		
	}
	/*
	 * form based authentication 
	 */
	@Test
	public void formBasedAuth() {	
     given()
     .auth()
     .form("admin", "admin", new FormAuthConfig("https://classic.freecrm.com/system/authenticate.cfm", "username", "password"))
     .when()
     .get("https://classic.freecrm.com/system/authenticate.cfm")
     .then()
     .and()
     .assertThat()
     .statusCode(200);    		
	}
	
	@Test
	public void auth2RestCall() {
		
		given()
		.auth()
		.oauth2("4d8f2644d2e66301624ec32a8ad5c4391b6d66d576a8ecc72b2aebcac7804392")
		.when()
		.get("https://gorest.co.in/public/v1/users?name=Sushree&id=3014")
		.then()
		.assertThat()
		.statusCode(200);
			
	}
	
	/*
	 * pass bearer token as header
	 */	
	@Test
	public void auth2WithTokenHeader() {
		
		RestAssured.baseURI="https://gorest.co.in";
		
		given().log().all()
		.contentType("application/json")
		.header("Authorization", "Bearer 4d8f2644d2e66301624ec32a8ad5c4391b6d66d576a8ecc72b2aebcac7804392")
		.queryParam("name", "Tioss")
		.queryParam("id", "2277")
		.when().log().all()
		.get("/public/v1/users")
		.then().log().all()
		.statusCode(200)
		.and()
		.header("Server", "nginx");
	
	}
	
	/*
	 * get auth2 token by call AS API
	 */
	
	@Test
	public void getAuth2TokenFromAPI() {
		
		RequestSpecification request=  RestAssured.given()
		.formParam("client_id", "apiauthtest")
		.formParam("client_secret", "e112b78260c233a44f2ac5175628db1e")
		.formParam("grant_type", "client_credentials");

		//coop.apps login : viniv91789@settags.com /admin123
				
		Response response = request.post("http://coop.apps.symfonycasts.com/token");
		
		//System.out.println(response.getStatusCode());
		//System.out.println(response.prettyPrint());
		
		 tokenId = response.jsonPath().getString("access_token");
		System.out.println("token ID is : " +tokenId);
		
		this.tokenId= tokenId;
	}
	
	@Test
	public void usingAuth2Token() {	
		RequestSpecification request1=  RestAssured.given()
		.auth()
		.oauth2(this.tokenId);
		System.out.println(tokenId);
		Response response1 = request1.post("http://coop.apps.symfonycasts.com/api/2371/chickens-feed");
		
		System.out.println(response1.prettyPrint());
	}
	
	
//	@Test
//	public void twitterAPI_AUth1() {
//		
//		RequestSpecification request2=  RestAssured.given()
//				.auth()
//				.oauth("f8f71457eac3fa270a55eb77accb739d1c4ca45c", "f8f71457eac3fa270a55eb77accb739d1c4ca45ckjdfhgkhdfkk", 
//						"f8f71457eac3fa270a55eb77accb739d1c4ca45c5464646464", "f8f71457eac3fa270a55eb77accb739d1c4ca45cjsgdf4654");
//		
//		Response response2 = request2.post("https://twitter.com/1.1/status/....................");
//		
//		System.out.println(response2.prettyPrint());
//				
//	}
}
