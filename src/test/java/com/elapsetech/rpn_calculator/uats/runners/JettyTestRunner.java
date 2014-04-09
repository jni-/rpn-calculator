package com.elapsetech.rpn_calculator.uats.runners;

import org.eclipse.jetty.server.Server;
import org.jbehave.core.annotations.AfterStories;
import org.jbehave.core.annotations.BeforeStories;

import com.elapsetech.rpn_calculator.interfaces.JettyServer;

public class JettyTestRunner {

	public static final int JETTY_TEST_PORT = 8181;
	private Server server;

	@BeforeStories
	public void startJetty() throws Exception {
		server = new JettyServer().create(JETTY_TEST_PORT);
		server.start();
	}

	@AfterStories
	public void stopJetty() throws Exception {
		server.stop();
	}
}