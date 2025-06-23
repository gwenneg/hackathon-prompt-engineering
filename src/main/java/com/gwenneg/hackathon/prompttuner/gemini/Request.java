package com.gwenneg.hackathon.prompttuner.gemini;

import java.util.List;

public record Request (
    List<Content> contents
) {}
