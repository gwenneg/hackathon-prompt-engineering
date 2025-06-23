package com.gwenneg.hackathon.prompttuner.rest;

public record Request (
    String model,
    String prompt,
    String systemPrompt
) {}
