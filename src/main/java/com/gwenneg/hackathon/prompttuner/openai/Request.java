package com.gwenneg.hackathon.prompttuner.openai;

import java.util.List;

public record Request (
    String model,
    List<Message> messages
) {}
