package com.smalaca.api.rest;

public class CreditCardUsageTest extends AbstractStory {

	@Override
	String storyName() {
		return "payment/credit-card.story";
	}
	
	@Override
	Object stepInstance() {
		return new CreditCardUsageSteps();
	}
	
}
