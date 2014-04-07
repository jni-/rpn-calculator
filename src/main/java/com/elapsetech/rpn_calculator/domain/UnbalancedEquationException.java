package com.elapsetech.rpn_calculator.domain;

public class UnbalancedEquationException extends RuntimeException {

	/**
	 * @return Always equal to self.
	 */
	@Override
	public boolean equals(Object obj) {
		return obj instanceof UnbalancedEquationException;
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	private static final long serialVersionUID = 1L;

}
