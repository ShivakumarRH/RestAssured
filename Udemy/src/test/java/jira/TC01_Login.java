package jira;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import utility.*;
import utility.Payoads;

public class TC01_Login {
	
	Properties prop=new Properties();
	
	@BeforeTest
	public void getData() throws IOException {
	
		FileInputStream fis=new FileInputStream("E:\\Work\\WorkSpace-Do not delete or change\\Udemy\\src\\test\\java\\utility\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void JustLogin() {
		
		RestAssured.baseURI=prop.getProperty("HOST"); //not using since junk value added during compile
		
		Response res=given().
			contentType("application/json").
			body(Payoads.LoginPayload()).
			log().uri().log().body().
		when().
			post(prop.getProperty("Login_HOST")).
		then().
			statusCode(200).
			//body("session.name", equalTo("JSESSIONID")).	//fetching res & validating tags in below steps. 
			extract().response();
			String resstring=res.asString();
			JsonPath resstringjson=new JsonPath(resstring); 
			System.out.println("Response :"+resstring);
			String SessionName=resstringjson.get("session.name");
			String SessionValue=resstringjson.get("session.value");
			System.out.println("Session name & Value are :"+SessionName + SessionValue);
		}
}