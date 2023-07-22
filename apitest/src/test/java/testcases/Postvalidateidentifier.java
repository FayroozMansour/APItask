package testcases;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.jayway.jsonpath.JsonPath;

import apitest.endpoints.EndPoints;
import io.restassured.response.Response;
import payload.SearchForHotelRequestData;
import payload.ValidateiIdentifierRequestData;

import org.testng.annotations.Test;

import groovy.transform.ASTTest;
import io.restassured.http.ContentType;
import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import apitest.endpoints.Routes;
;
public class Postvalidateidentifier {
	

	ValidateiIdentifierRequestData payload ;
	@BeforeMethod
	public void setData()
	{
	
	payload =new ValidateiIdentifierRequestData();
	//for identifer
	
	   int randomNumber = new Random().nextInt(10000000);
	   String RandomNumber = String.valueOf(randomNumber);

       //token (this could be handle as parsing from other service if thats possible)
        String token="skdjfh73273$7268u2j89s";
	    payload.setmethod("mokafa");
		payload.setidentifier(RandomNumber);
		payload.setToken("Bearer"+ token);


	}
	
	
@Test
	public  void validateidentifier()
	
	       
		{		
	     Response response =EndPoints.validateidentifier(payload);
	     response.then().log().all()
	     .extract().response();

	// Perform assertions to check the response data
	response.then().assertThat().statusCode(200); // Check if the status code is 200
	response.then().assertThat().body("message", Matchers.equalTo("Invalid Identifier")); // Check the response
	Assert.assertEquals(response.getStatusCode(), 200);}
	





@Test
public  void nullmethod()

{payload.setmethod("");		
 Response response =EndPoints.validateidentifier(payload);
 response.then().log().all()
 .extract().response();
// Check if the status code is 400 
response.then().assertThat().body("detail", Matchers.equalTo("Loyalty method is required")); // Check the response
Assert.assertEquals(response.getStatusCode(), 400);

}

@Test
public  void invalidmethod()

{payload.setmethod("x");		
 Response response =EndPoints.validateidentifier(payload);
 response.then().log().all()
 .extract().response();
// Check if the status code is 500 per the system  
response.then().assertThat().body("title", Matchers.equalTo("Missing Configuration")); // Check the response
Assert.assertEquals(response.getStatusCode(), 500);

}

	
@Test
	public  void nulltoken()
	
	{payload.setToken("Bearer"+ "");		
     Response response =EndPoints.validateidentifier(payload);
     response.then().log().all()
     .extract().response();
 // Check if the status code is 200 while if it was required it should be 401 
     response.then().assertThat().statusCode(200);
}

}