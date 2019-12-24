package payeezy;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import utility.Payoads;

public class PostCreditCardReq {
	
	Properties prop=new Properties();
	String TTag,TID,AAS;
	
	
	@BeforeTest
	public void GetData() throws IOException {
		FileInputStream fis=new FileInputStream("E:\\Work\\WorkSpace-Do not delete or change\\Udemy\\src\\test\\java\\utility\\env.properties");
		prop.load(fis);
				
	}
	
	@Test
	public void PostCreditCardReq() {
		
		RestAssured.baseURI=prop.getProperty("Payeezy_Host");
		
		Response res=given().
			body(Payoads.CCRequestsa()).		//since u parameterized th payload in Payloads class u will have to pass ARGS else error.
			contentType("application/json").
			header("apikey", prop.getProperty("Dev1_APIKEY")).
			header("Token", prop.getProperty("Dev1_Token")).
			log().uri().log().headers().log().body().
		when().
			post(prop.getProperty("Payeezy_CC_Host")).
		then().
		log().all().
			statusCode(201).
			and().
			extract().response();
			String resString=res.asString();
			JsonPath CCResJson=new JsonPath(resString );
			TTag=CCResJson.get("transaction_tag");
			TID=CCResJson.get("transaction_id");
			System.out.println("Response is: " + resString);
			//System.out.println("Success : "+TTag  + TID);
			}
	
	/*
	 * @AfterTest public void PostRefundReq() {
	 * 
	 * //RestAssured.baseURI=prop.getProperty("Payeezy_Host");
	 * RestAssured.baseURI=prop.getProperty("Payeezy_Host");
	 * 
	 * Response res=given(). body(Payoads.CCCaptureReqsa(TTag)).
	 * contentType("application/json"). header("apikey",
	 * prop.getProperty("Dev1_APIKEY")). header("Token",
	 * prop.getProperty("Dev1_Token")). log().uri().log().headers().log().body().
	 * when(). post("/v1/transactions/"+TID).
	 * 
	 * then(). statusCode(201). and(). extract().response(); String
	 * resString=res.asString(); JsonPath CCResJson=new JsonPath(resString );
	 * System.out.println("Response is: " + resString); }
	 */
		}
