package com.gwenneg.hackathon.prompttuner;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Map;
import java.util.TreeMap;

import static java.nio.charset.StandardCharsets.UTF_8;

public class SystemPromptsLoader {

    private static final Map<String, String> systemPrompts = load();

    public static Map<String, String> getSystemPrompts() {
        return systemPrompts;
    }

    private static Map<String, String> load() {
        try (InputStream inputStream = SystemPromptsLoader.class.getResourceAsStream("/system-prompts.json")) {
            String jsonFile = new String(inputStream.readAllBytes(), UTF_8);
            JsonArray jsonArray = new JsonArray(jsonFile);
            Map<String, String> systemPrompts = new TreeMap<>();
            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject systemPrompt = jsonArray.getJsonObject(i);
                systemPrompts.put(systemPrompt.getString("key"), systemPrompt.getString("value"));
            }
            return systemPrompts;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }
}
