package com.smalaca.api.rest;

public class TemplateHelloStoryTest extends AbstractStory {
    String storyName() {
        return "template/template-hello.story";
    }

    Object stepInstance() {
        return new TemplateHelloSteps();
    }
}
