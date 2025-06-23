package com.gwenneg.hackathon.prompttuner.openai;

public record Message (
    String role,
    String content
) {}
