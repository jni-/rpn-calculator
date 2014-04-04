package com.elapsetech.rpn_calculator.services;

import com.elapsetech.rpn_calculator.domain.RpnCalculator;

public class CalculatorService {
	
	private RpnCalculator rpnCalculator;

	public CalculatorService(RpnCalculator rpnCalculator) {
		this.rpnCalculator = rpnCalculator;
	}
	
	public CalculationResult calculateRpn(String input) {
		if(input.length() == 0) {
			throw new EmptyInputException();
		}
		
		return new CalculationResult(rpnCalculator.calculate(input));
	}

}
