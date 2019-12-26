package payeezy;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jvnet.staxex.StAxSOAPBody.Payload;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.*;
import utility.Payoads;
import static org.hamcrest.Matcher.*;   	//iMP manually import these static packages. else keywords won't work
import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PostCreditCardWithExcelData {
	
	Properties prop=new Properties();
	String TTag,TID;	
	
	@BeforeTest
	public void GetData() throws IOException {
		FileInputStream fis=new FileInputStream("E:\\Work\\WorkSpace-Do not delete or change\\Udemy\\src\\test\\java\\utility\\env.properties");
		prop.load(fis);
	}
	
	@Test
	public void PostCreditCardReq() throws IOException {
		
		FileInputStream file=new FileInputStream("E:\\Work\\TestData\\Data.xlsx");
		
		XSSFWorkbook workbook=new XSSFWorkbook(file); 
		
		XSSFSheet sheet=workbook.getSheet("Sheet1");
		
				
		int rowcount=sheet.getLastRowNum();
		
		
		for(int row =1; row<=rowcount; row++) {
			try {
			XSSFRow entirerow=sheet.getRow(row);  //storing all row data in entirerow variable.
			
			String merchant_ref=entirerow.getCell(2).getStringCellValue();
			String transaction_type=entirerow.getCell(3).getStringCellValue();
			String method=entirerow.getCell(4).getStringCellValue();
			int amount=(int) entirerow.getCell(5).getNumericCellValue(); 
			String currency_code=entirerow.getCell(6).getStringCellValue(); 
			String credit_card_type=entirerow.getCell(7).getStringCellValue(); 
			String credit_card_cardholder_name=entirerow.getCell(8).getStringCellValue(); 
			String credit_card_card_number=entirerow.getCell(9).getStringCellValue();
			int credit_card_exp_date=(int) entirerow.getCell(10).getNumericCellValue();
			int cvv=(int) entirerow.getCell(11).getNumericCellValue();
			String sub_transaction_type=entirerow.getCell(12).getStringCellValue();
			 
			/* Performing Authorize Request */
			RestAssured.baseURI=prop.getProperty("Payeezy_Host");
			
			Response res=given().
					body(Payoads.CCRequest(merchant_ref, transaction_type, method, amount, currency_code,credit_card_type,credit_card_cardholder_name,credit_card_card_number,credit_card_exp_date,cvv )).
				contentType("application/json").
				header("apikey", prop.getProperty("Dev1_APIKEY")).
				header("Token", prop.getProperty("Dev1_Token")).
				log().uri().log().headers().log().body().
			when().
				post(prop.getProperty("Payeezy_CC_Host")).
			then().
				statusCode(201).
				and().
				extract().response();
				String resString=res.asString();
				JsonPath CCResJson=new JsonPath(resString);
				TTag=CCResJson.get("transaction_tag");
				TID=CCResJson.get("transaction_id");
				System.out.println("Response is: " + resString);
				
					/* Performing Capture Request */
				Response res1=given(). 
						  body(Payoads.CCCaptureReq(TTag,merchant_ref,sub_transaction_type, method, amount, currency_code)).
						  contentType("application/json"). 
						  header("apikey",prop.getProperty("Dev1_APIKEY")).
						  header("Token",prop.getProperty("Dev1_Token")). 
						  log().uri().log().headers().log().body().
						  when(). 
						  post("/v1/transactions/"+TID).						  
						  then(). 
						  statusCode(201). and().
					      extract().response(); 
						  String resString1=res1.asString(); 
						  JsonPath CCResJson1=new JsonPath(resString );
					      System.out.println("Response is: " + resString);
					      					         
			}
		
		catch(AssertionError a)
		{
		System.out.println(a);	
		}
		 }
}
}