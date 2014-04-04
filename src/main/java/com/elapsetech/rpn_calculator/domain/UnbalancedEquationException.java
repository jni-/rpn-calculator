package com.elapsetech.rpn_calculator.domain;

public class UnbalancedEquationException extends RuntimeException {
	
	/**
	 * @return Always equal to self.
	 */
	public boolean equals(Object obj) {
		return obj instanceof UnbalancedEquationException;
	}
	
	public int hashCode() {
		return super.hashCode();
	}

	private static final long serialVersionUID = 1L;

}
