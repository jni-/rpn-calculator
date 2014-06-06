package com.elapsetech.rpn_calculator.uats.steps;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.elapsetech.rpn_calculator.uats.runners.JettyTestRunner;
import com.jayway.restassured.response.Header;
import com.jayway.restassured.response.Response;

public class RpnCalculatorSteps {

	private static final String EQUATION_PARAMETER = "equation";

	private static final String VALID_EQUATION = "1 2 +";
	private static final Integer VALID_EQUATION_ANSWER = 3;
	private static final String INVALID_EQUATION = "1 2 + 1";
	
	private static final int[] NUMBERS_TO_SUM = new int[]{1, 2, 3, 10};
	private static final int NUMBERS_TO_SUM_ANSWER = 16;

	private Response response;

	@BeforeScenario
	public void clearResults() {
		response = null;
	}

	@When("j'écris un calcul valide")
	public void validEquationInTheRpnCalculator() {
		calculate(VALID_EQUATION);
	}

	@When("j'écris un calcul invalide")
	public void invalidEquationInTheRpnCalculator() {
		calculate(INVALID_EQUATION);
	}

	private void calculate(String equation) {
		response = given().
					port(JettyTestRunner.JETTY_TEST_PORT).
					parameters(EQUATION_PARAMETER, equation).
				   when().
				   	get("/rpn/result");
	}

	@Then("la calculatrice me retourne la réponse")
	public void calculatorDisplaysTheAnswer() {
		response.then().
			statusCode(Status.OK.getStatusCode()).
			body("result", equalTo(VALID_EQUATION_ANSWER));
	}

	@Then("la calculatrice me retourne une erreur")
	public void calculatorDisplaysAndError() {
		response.then().
			statusCode(Status.BAD_REQUEST.getStatusCode()).
			body(not(isEmptyOrNullString()));
	}
	
	@When("j'écris une liste de nombres à additionner")
	public void sumListOfNumbers() {
		response = given().
					port(JettyTestRunner.JETTY_TEST_PORT).
					header(new Header("content-type", MediaType.APPLICATION_JSON)).
					body(NUMBERS_TO_SUM).
				   when().
				    post("/rpn/sum");
			
	}
	
	@Then("la calculatrice me retourne la somme")
	public void properSumIsReturned() {
		response.then().
			statusCode(Status.OK.getStatusCode()).
			body("result", equalTo(NUMBERS_TO_SUM_ANSWER));
	}
}
