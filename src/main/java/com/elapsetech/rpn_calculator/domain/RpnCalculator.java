package com.elapsetech.rpn_calculator.domain;

import java.util.Stack;

public class RpnCalculator {

	private Stack<Integer> stack;

	public RpnCalculator() {
		stack = new Stack<Integer>();
	}

	public Integer calculate(String input) {
		for (String substring : input.trim().split(" ")) {
			if (isNumber(substring)) {
				stack.push(Integer.parseInt(substring));
			} else {
				stack.push(applyOperator(stack.pop(), stack.pop(), substring));
			}
		}
		
		if(stack.size() != 1) {
			throw new UnbalancedEquationException();
		}

		return stack.pop();
	}

	private boolean isNumber(String substring) {
		try {
			Integer.parseInt(substring);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private Integer applyOperator(Integer right, Integer left, String operator) {
		switch (operator) {
		case "+":
			return left + right;
		case "-":
			return left - right;
		case "*":
			return left * right;
		case "/":
			return left / right;
		}
		
		throw new InvalidOperatorException(operator);
	}

}
