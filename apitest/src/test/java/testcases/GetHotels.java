package testcases;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import apitest.endpoints.EndPoints;
import io.restassured.response.Response;
import payload.SearchForHotelRequestData;
import payload.ValidateiIdentifierRequestData;

public class GetHotels {
	SearchForHotelRequestData searchdata;
	@BeforeMethod
	
	public void setData()
	{
	
	  searchdata = new SearchForHotelRequestData();
	
       Random randomDays = new Random();
       LocalDate fromdate = LocalDate.now().plusDays(randomDays.nextInt(7));
       LocalDate todate = fromdate.plusDays(randomDays.nextInt(5));
	
		searchdata.sethotel("Galata");
		searchdata.setTodate(fromdate);
		searchdata.setfromdate(todate);

	}
//@Test
public  void gethotelwithvalidates()

{      

Response response =EndPoints.gethotel( searchdata.gethotel(),searchdata.getfromdate(),searchdata.getTodate());
response.then().log().all();
Assert.assertEquals(response.getStatusCode(), 200);
}
@Test
public  void gethotelwithinvalidates()

{
	   //dates the from date is after now
    Random randomDays = new Random();
    LocalDate fromdate = LocalDate.now().plusDays(randomDays.nextInt(7));
    LocalDate todate = LocalDate.now();
    searchdata.setTodate(fromdate);
	searchdata.setfromdate(todate);
	
Response response =EndPoints.gethotel( searchdata.gethotel(),searchdata.getfromdate(),searchdata.getTodate());
response.then().log().all();

//per the system its 200 
Assert.assertEquals(response.getStatusCode(), 200);
}


@Test
public  void notexiesthotel()

{
    		
	searchdata.sethotel("xxxxxx");

Response response =EndPoints.gethotel( searchdata.gethotel(),searchdata.getfromdate(),searchdata.getTodate());
response.then().log().all();

//per the system its 200 
Assert.assertEquals(response.getStatusCode(), 200);
}

}
