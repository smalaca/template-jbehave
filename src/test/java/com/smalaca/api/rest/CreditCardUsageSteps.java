package com.smalaca.api.rest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

public class CreditCardUsageSteps {

	private static String CREDIT_CARD_JSON ="{\"number\":\"1234123412341234\",\n" + 
			"\"cv2\":\"4893\",\n" + 
			"\"expirationDate\":\"2020/12\"\n" + 
			"}";
	
	private static String INVALID_CREDIT_CARD_JSON ="{\"number\":\"\",\n" + 
			"\"cv2\":\"\",\n" + 
			"\"expirationDate\":\"\"\n" + 
			"}";
	private CloseableHttpResponse execute;
	
	

	@Given(value="w koszyku znajdują się produkty")
	public void basketContainsProducts() throws ClientProtocolException, IOException {
        HttpUriRequest request = new HttpPost("http://localhost:10080/cart/add/product1");
        HttpClientBuilder
                .create()
                .build()
                .execute(request);
	}
	
	@When(value="podane zostaną poprawne dane karty kredytowej")
	public void hasValidCreditCard() throws ClientProtocolException, IOException {
		sendCreditCardDetails(CREDIT_CARD_JSON);
	}

	private void sendCreditCardDetails(String cREDIT_CARD_JSON2) throws IOException, ClientProtocolException {
		CloseableHttpClient httpClient = HttpClients.createDefault();
		
		HttpPost request = new HttpPost("http://localhost:10080/payment/creditcard");
		
		request.setEntity(new StringEntity(cREDIT_CARD_JSON2,ContentType.APPLICATION_JSON));
		
		execute = httpClient.execute(request);
	}

	@Then(value="następuje blokada środków na karcie kredytowej")
	public void fundsHasBeenBlocked() {
		StatusLine statusLine = execute.getStatusLine();
		int statusCode = statusLine.getStatusCode();
		assertEquals(202, statusCode);
	}
	
//	@When(value="podane zostaną niepoprawne dane karty kredytowej")
//	public void hasInvalidCreditCard() throws ClientProtocolException, IOException {
//		sendCreditCardDetails(INVALID_CREDIT_CARD_JSON);
//	}
//	
//	@Then(value="Klient informowany jest niepowodzeniu operacji blokady środków")
//	public void fundsHasNotBeenBlocked() {
//		int statusCode = execute.getStatusLine().getStatusCode();
//		assertEquals(403, statusCode);
//	}
}
