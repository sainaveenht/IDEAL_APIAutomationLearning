package com.qa.codedog.testCases;


import java.util.concurrent.TimeUnit;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.codedog.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC01_Hacienda_ping extends TestBase
{

	@BeforeClass
	void hacienda_ping() throws InterruptedException
	{
	
	logger.info("*********Started TC01_Hacienda_ping **********");
		
	RestAssured.baseURI = "https://staging.hacienda.pr.gov/TAS/Services/Externals";
	httpRequest = RestAssured.given().auth().preemptive().basic("ideal_dev", "q?3:t>5bi1J~>6~");

		
	// Add a header stating the Request body is a JSON
	httpRequest.header("Content-Type", "application/json");

	response = httpRequest.request(Method.GET, "/ping");
	
	//Thread.sleep(5000);

	}
		
	@Test
	void checkStatusCode()
	{
		logger.info("***********  Checking Status Code **********");
		
		int statusCode = response.getStatusCode(); // Gettng status code
		logger.info("Status Code is ==>" + statusCode); //200
		Assert.assertEquals(statusCode, 200);
		
	}
	
	
	@Test
	void checkResponseTime()
	{
		logger.info("***********  Checking Response Time **********");
		
		long responseTime = response.getTime(); // Getting status Line
		logger.info("Response Time is ==>" + responseTime);
		
		if(responseTime>2000)
			logger.warn("Response Time is greater than 2000");
		
		Assert.assertTrue(responseTime<10000);
		System.out.println("Time Taken to get the Response for Ping:"+response.time());
		System.out.println("Time Taken to get the Response for Ping:"+response.getTime());
		System.out.println("Time Taken to get the Response for Ping:"+response.timeIn(TimeUnit.SECONDS));
		System.out.println("Time Taken to get the Response for Ping:"+response.getTimeIn(TimeUnit.SECONDS));	
		System.out.println("Time Taken to get the Response for Ping:"+response.getTimeIn(TimeUnit.MILLISECONDS));
		
		
	}
	
	@Test
	void checkstatusLine()
	{
		logger.info("***********  Checking Status Line **********");
		
		String statusLine = response.getStatusLine(); // Getting status Line
		logger.info("Status Line is ==>" + statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
	
		
	}
	
	
	@Test
	void checkResposeBody()
	{
		logger.info("***********  Checking Respose Body **********");
		
		String responseBody = response.getBody().asString();
		logger.info("Response Body==>"+responseBody);
		Assert.assertTrue(responseBody!=null);
		
	}
		
		
	
	
	@AfterClass
	void tearDown()
	{
		logger.info("*********  Finished TC01_Hacienda_ping **********");
	}
	
	
}
