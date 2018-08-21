package com.smalaca.api.rest;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.MostUsefulConfiguration;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.LoadFromClasspath;
import org.jbehave.core.junit.JUnitStories;
import org.jbehave.core.reporters.CrossReference;
import org.jbehave.core.reporters.FilePrintStreamFactory;
import org.jbehave.core.reporters.StoryReporterBuilder;
import org.jbehave.core.steps.InjectableStepsFactory;
import org.jbehave.core.steps.InstanceStepsFactory;

import java.util.List;
import java.util.Properties;

import static java.util.Arrays.asList;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.CONSOLE;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.HTML;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.TXT;
import static org.jbehave.core.reporters.StoryReporterBuilder.Format.XML;

public abstract class AbstractStory extends JUnitStories {
    private final CrossReference xref = new CrossReference();

    abstract String storyName();

    @Override
    public Configuration configuration() {
        Properties viewResources = new Properties();
        viewResources.put("decorateNonHtml", "true");

        return new MostUsefulConfiguration()
                .useStoryLoader(new LoadFromClasspath(this.getClass()))
                .useStoryReporterBuilder(new StoryReporterBuilder()
                        .withCodeLocation(CodeLocations.codeLocationFromClass(this.getClass()))
                        .withDefaultFormats().withPathResolver(new FilePrintStreamFactory.ResolveToPackagedName())
                        .withViewResources(viewResources).withFormats(CONSOLE, TXT, HTML, XML)
                        .withFailureTrace(true).withFailureTraceCompression(true).withCrossReference(xref));
    }

    abstract Object stepInstance();

    @Override
    public InjectableStepsFactory stepsFactory() {
        return new InstanceStepsFactory(configuration(), stepInstance());
    }

    @Override
    protected List<String> storyPaths() {
        return asList(storyName());
    }
}
