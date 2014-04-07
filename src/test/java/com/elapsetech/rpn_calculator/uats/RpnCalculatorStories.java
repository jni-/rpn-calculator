package com.elapsetech.rpn_calculator.uats;

import static java.util.Arrays.asList;
import static org.jbehave.core.io.CodeLocations.codeLocationFromClass;
import static org.jbehave.core.reporters.Format.CONSOLE;

import java.util.List;
import java.util.Locale;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.failures.FailingUponPendingStep;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.io.StoryFinder;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.parsers.RegexStoryParser;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;
import org.junit.runner.RunWith;

import com.elapsetech.rpn_calculator.uats.steps.RpnCalculatorSteps;

import de.codecentric.jbehave.junit.monitoring.JUnitReportingRunner;

@RunWith(JUnitReportingRunner.class)
public class RpnCalculatorStories extends JUnitStories {

	private static final LocalizedKeywords keywords = new LocalizedKeywords( Locale.FRENCH);

	@Override
	public Configuration configuration() {
		StoryReporterBuilder reporterBuilder = new StoryReporterBuilder()
				.withKeywords(keywords)
				.withCodeLocation(codeLocationFromClass(RpnCalculatorStories.class))
				.withFailureTrace(true).withFailureTraceCompression(true)
				.withDefaultFormats().withFormats(CONSOLE);

		return new MostUsefulConfiguration()
				.useKeywords(keywords)
				.usePendingStepStrategy(new FailingUponPendingStep())
				.useStoryParser(new RegexStoryParser(keywords))
				.useStoryLoader(new LoadFromClasspath(RpnCalculatorStories.class))
				.useStoryReporterBuilder(reporterBuilder);
	}

	@Override
	public InjectableStepsFactory stepsFactory() {
		return new InstanceStepsFactory(configuration(),
				new RpnCalculatorSteps());
	}

	@Override
	protected List<String> storyPaths() {
		return new StoryFinder().findPaths( codeLocationFromClass(this.getClass()).getFile(),
				asList("**/*.story", "*.story"), null);
	}

}
