package com.qa.codedog.testCases;


import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.qa.codedog.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;

public class TC02_Hacienda_compliance extends TestBase
{

	@BeforeClass
	void hacienda_compliance() throws InterruptedException
	{
	
	logger.info("*********Started TC02_Hacienda_compliance **********");
		
	RestAssured.baseURI = "https://tapstaging.hacienda.pr.gov/TAS/Services/External";
	httpRequest = RestAssured.given().auth().preemptive().basic("ideal_dev", "q?3:t>5bi1J~>6~");
	
	JSONObject requestParams = new JSONObject();
	requestParams.put("idType", "2"); // Cast
	requestParams.put("id", "660962981");
	requestParams.put("firstName", "Tom");
	requestParams.put("lastName", "Jerry");
	requestParams.put("secondLastName", "Micky");
	requestParams.put("businessName", "Code Dog Technology group LLC");
	requestParams.put("merchantId", " ");
	
	
	
		
	// Add a header stating the Request body is a JSON
	httpRequest.header("Content-Type", "application/json");
	
	
	// Add the Json to the body of the request
	httpRequest.body(requestParams.toJSONString());

	response = httpRequest.request(Method.POST, "/compliance");
	
	Thread.sleep(5000);

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
		logger.info("*********  Finished TC02_Hacienda_compilance **********");
	}
	
	
}
