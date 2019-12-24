package utility;

public class Payoads {
	
	public static String LoginPayload() {
		
		String LGPayload="{\r\n" + 
				"    \"username\": \"shivakumar.handral\",\r\n" + 
				"    \"password\": \"Welcome@12.\"\r\n" + 
				"}";
	 	return LGPayload;
		
	}
	
	public static String CCRequestsa() {
		
		String CCRequest="{\r\n" + 
				"  \"merchant_ref\": \"Astonishing-Sale\",\r\n" + 
				"  \"transaction_type\": \"authorize\",\r\n" + 
				"  \"method\": \"credit_card\",\r\n" + 
				"  \"amount\": \"1299\",\r\n" + 
				"  \"currency_code\": \"USD\",\r\n" + 
				"  \"credit_card\": {\r\n" + 
				"    \"type\": \"visa\",\r\n" + 
				"    \"cardholder_name\": \"John Smith\",\r\n" + 
				"    \"card_number\": \"4788250000028291\",\r\n" + 
				"    \"exp_date\": \"1020\",\r\n" + 
				"    \"cvv\": \"123\"\r\n" + 
				"  }\r\n" + 
				"}";
		
		return CCRequest;
	}
	
	public static String CCRequest(String merchant_ref, String transaction_type,String method, int amount, String currency_code, String credit_card_type,String credit_card_cardholder_name, String credit_card_card_number, int credit_card_exp_date, int cvv ) {
			
		String CCRequest="{\r\n" + 
				"  \"merchant_ref\": \""+merchant_ref+"\",\r\n" + 
				"  \"transaction_type\": \""+transaction_type+"\",\r\n" + 
				"  \"method\": \""+method+"\",\r\n" + 
				"  \"amount\": \""+amount+"\",\r\n" + 
				"  \"currency_code\": \""+currency_code+"\",\r\n" + 
				"  \"credit_card\": {\r\n" + 
				"    \"type\": \""+credit_card_type+"\",\r\n" +  
				"      \"cardholder_name\": \""+credit_card_cardholder_name+"\",\r\n" + 
				"    \"card_number\": \""+credit_card_card_number+"\",\r\n" + 
				"      \"exp_date\": \""+credit_card_exp_date+"\",\r\n" + 
				"    \"cvv\": \""+cvv+"\"\r\n" + 
				"  }\r\n" + 
				"}";
		
		return CCRequest;
	}
	
	
	public static String CCCaptureReq(String txntag,String merchant_ref, String sub_transaction_type,String method, int amount, String currency_code) {
		String CaptureReq="{\r\n" + 
				"  \"merchant_ref\": \""+merchant_ref+"\",\r\n" + 
				"  \"transaction_tag\": \""+txntag+"\",\r\n" + 
				"  \"transaction_type\": \""+sub_transaction_type+"\",\r\n" + 
				"  \"method\": \""+method+"\",\r\n" + 
				"  \"amount\": \""+amount+"\",\r\n" + 
				"  \"currency_code\": \""+currency_code+"\"\r\n" + 
				"}";
		return CaptureReq;
	}

}