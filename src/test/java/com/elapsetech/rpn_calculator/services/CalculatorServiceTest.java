package com.elapsetech.rpn_calculator.services;

import static org.junit.Assert.*;
import static org.mockito.BDDMockito.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.elapsetech.rpn_calculator.domain.RpnCalculator;

@RunWith(MockitoJUnitRunner.class)
public class CalculatorServiceTest {
	
	@Mock
	private RpnCalculator rpnCalculator;
	
	@InjectMocks
	private CalculatorService service;
	
	@Test(expected = EmptyInputException.class)
	public void throwsAnExceptionWhenInputIsEmptyForPrnCalculation() {
		service.calculateRpn("");
	}
	
	@Test 
	public void canCalculatePrnInputWithPrnCalculator() {
		final String input = "anything";
		final Integer expectedResult = 0;
		
		willReturn(0).given(rpnCalculator).calculate(input);
		
		CalculationResult result = service.calculateRpn(input);
		
		assertEquals(new CalculationResult(expectedResult), result);
	}

}
