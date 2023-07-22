package apitest.endpoints;

import org.testng.annotations.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

import java.sql.Date;
import java.time.LocalDate;

import payload.ValidateiIdentifierRequestData;
import com.google.gson.Gson;


public class EndPoints {
	
@Test
	public static  Response validateidentifier(ValidateiIdentifierRequestData payload)
	{	
	
		 Response response =(Response) given()
	             .contentType("application/json")
	             .header("x-authorization", payload.getToken() )
	         	     .body(payload).log().all()

		 .when()
		.post(Routes.postvalidate);
		 
		 
		 return response; 
	}
	@Test
	public static  Response gethotel(String hotelname,LocalDate localDate,LocalDate localDate2)
	
	{		

		 Response response =given().pathParams("hotelname", hotelname,"fromdate",localDate,"todate",localDate2)
	                .contentType("ContentType.JSON")
		 .when()
		.get(Routes.gethotel);
		 return response; 
	}}
	