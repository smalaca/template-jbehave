package com.smalaca.api.rest;

public class TemplateHelloStoryParamsTest extends AbstractStory {
    String storyName() {
        return "template/template-hello-params.story";
    }

    Object stepInstance() {
        return new TemplateHelloParamsSteps();
    }
}
