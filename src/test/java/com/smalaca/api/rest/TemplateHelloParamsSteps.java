package com.smalaca.api.rest;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateHelloParamsSteps {

    private HttpResponse response;

    @Given("a working application")
    public void givenWorkingApplication() {

    }

    @When("the user visits template main page")
    public void whenUserVisitsMainPage() throws IOException {
        HttpUriRequest request = new HttpGet("http://localhost:8080/template/hello");
        response = HttpClientBuilder
                .create()
                .build()
                .execute(request);
    }

    @Then("they see $messagePart inside presentation")
    public void thenUserSeeMessage(String messagePart) throws IOException {
        assertThat(response.getStatusLine().getStatusCode()).isEqualTo(200);
        String content = EntityUtils.toString(response.getEntity());
        assertThat(content).contains(messagePart);
    }
}
