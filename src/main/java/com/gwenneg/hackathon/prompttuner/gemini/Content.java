package com.gwenneg.hackathon.prompttuner.gemini;

import java.util.List;

public record Content (
    String systemInstruction,
    List<Part> parts
) {}
