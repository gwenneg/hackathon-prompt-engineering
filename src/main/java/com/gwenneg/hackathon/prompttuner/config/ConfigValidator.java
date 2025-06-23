package com.gwenneg.hackathon.prompttuner.config;

import io.quarkus.logging.Log;
import io.quarkus.runtime.Startup;
import jakarta.annotation.PostConstruct;

@Startup
public class ConfigValidator {

    private static final String OPENAI_API_KEY = "OPENAI_API_KEY";

    @PostConstruct
    void postConstruct() {
        if (System.getenv(OPENAI_API_KEY) == null) {
            Log.warnf("%s not found in environment variables", OPENAI_API_KEY);
        }
    }
}
