package com.elapsetech.rpn_calculator.interfaces.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.elapsetech.rpn_calculator.domain.InvalidOperatorException;
import com.elapsetech.rpn_calculator.domain.RpnCalculator;
import com.elapsetech.rpn_calculator.domain.UnbalancedEquationException;
import com.elapsetech.rpn_calculator.services.CalculationResult;
import com.elapsetech.rpn_calculator.services.CalculatorService;

@Path("/rpn")
public class RpnResource {

	private CalculatorService service;

	public RpnResource() {
		service = new CalculatorService(new RpnCalculator());
	}

	@GET
	@Path("/result")
	public Response calculate(@QueryParam("equation") String equation) {
		try {
			CalculationResult result = service.calculateRpn(equation);
			return Response.ok(result).build();
		} catch(UnbalancedEquationException | InvalidOperatorException e) {
			return Response.status(Status.BAD_REQUEST).entity(e.getMessage()).build();
		}
	}

}
