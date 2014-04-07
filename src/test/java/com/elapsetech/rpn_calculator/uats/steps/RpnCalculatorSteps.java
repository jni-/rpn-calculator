package com.elapsetech.rpn_calculator.uats.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;

import com.elapsetech.rpn_calculator.domain.RpnCalculator;

public class RpnCalculatorSteps {

	private static final String VALID_EQUATION = "1 2 +";
	private static final Integer VALID_EQUATION_ANSWER = 3;
	private static final String INVALID_EQUATION = "1 2 + + + + + + + 1";

	private RpnCalculator calculator;
	private Integer lastAnswer;
	private Throwable lastError;

	public RpnCalculatorSteps() {
		calculator = new RpnCalculator();
	}

	@BeforeScenario
	public void clearResults() {
		lastAnswer = null;
		lastError = null;
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
		try {
			lastAnswer = calculator.calculate(equation);
		} catch(Throwable e) {
			lastError = e;
		}
	}

	@Then("la calculatrice me retourne la réponse")
	public void calculatorDisplaysTheAnswer() {
		assertNull(lastError);
		assertEquals(VALID_EQUATION_ANSWER, lastAnswer);
	}

	@Then("la calculatrice me retourne une erreur")
	public void calculatorDisplaysAndError() {
		assertNull(lastAnswer);
		assertNotNull(lastError);
	}
}
