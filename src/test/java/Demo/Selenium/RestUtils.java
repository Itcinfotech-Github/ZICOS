package Demo.Selenium;

import java.util.ListIterator;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestUtils {
	
	public void setBaseURI(String baseURI) {
		RestAssured.baseURI = baseURI;
		
	}
	
	public void setBasePath(String basePath) {
		RestAssured.basePath = basePath;
		
	}
	
	public void resetBaseURI() {
		RestAssured.baseURI = null;
	}
	
	public void resetBasePath() {
		RestAssured.basePath = null;
	}
	
	public void setContentType(ContentType type) {
		RestAssured.given().contentType(type);
	}
	
	public int getStatusCode(Response res) {
		return res.getStatusCode();
	}

}
