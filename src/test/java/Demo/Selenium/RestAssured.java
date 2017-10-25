package Demo.Selenium;

import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestAssured {
	RequestSpecification httpRequest;
	
	
	@Test
	public void apiTesting() {
		/*io.restassured.RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		
		RequestSpecification httprequest = io.restassured.RestAssured.given();
		
		Response response = httprequest.request(Method.GET,"/Hyderabad");
		
		String body = response.getBody().asString();
		System.out.println(body);
		*/
		
		// Specify the base URL to the RESTful web service
		io.restassured.RestAssured.baseURI = "http://restapi.demoqa.com/utilities/weather/city";
		io.restassured.RestAssured.basePath = "/Hyderabad";
		
				// Get the RequestSpecification of the request that you want to sent
				// to the server. The server is specified by the BaseURI that we have
				// specified in the above step.
				RequestSpecification httpRequest = io.restassured.RestAssured.given();
		 
				// Make a GET request call directly by using RequestSpecification.get() method.
				// Make sure you specify the resource name.
				//Response response = httpRequest.request(Method.GET, "/Hyderabad");
				Response response = httpRequest.get("http://restapi.demoqa.com/utilities/weather/city");
				
				
				JsonPath jp = response.jsonPath();
				String city = jp.getString("City");
				// Response.asString method will directly return the content of the body
				// as String.
				System.out.println("Response Body is =>  " + response.asString());
				
				
		        
				/*JsonObject fromJson = new Gson().fromJson("http://restapi.demoqa.com/utilities/weather/city/Hyderabad",JsonObject.class);
				System.out.println(fromJson.get("City").toString());*/
		
		
	}
	
	//@Test
	public void exampleJsonPathTest() {
	  Response res = httpRequest.get("/service/example");
	  assertEquals(200, res.getStatusCode());
	  String json = res.asString();
	  JsonPath jp = new JsonPath(json);
	  assertEquals("onur@swtestacademy", jp.get("email"));
	  assertEquals("Onur", jp.get("firstName"));
	  assertEquals("Baskirt", jp.get("lastName"));
	}


}
