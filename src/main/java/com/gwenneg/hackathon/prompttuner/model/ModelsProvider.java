package com.gwenneg.hackathon.prompttuner.model;

import java.util.Map;
import java.util.TreeMap;

public class ModelsProvider {

    private static final Model CHATGPT_4O = new Model("gpt-4o", "ChatGPT 4o");
    private static final Model CLAUDE_OPUS_4 = new Model("claude-opus-4-20250514", "Claude Opus 4");
    private static final Model GEMINI_25_FLASH = new Model("gemini-2.5-flash", "Gemini 2.5 Flash");
    private static final Model LLAMA_4_SCOUT = new Model("llama-scout-maas", "Llama 4 Scout");
    private static final Model MISTRAL_SMALL = new Model("mistral-small-maas", "Mistral Small");
    private static final Map<String, Model> models = load();

    public static Map<String, Model> getModels() {
        return models;
    }

    private static Map<String, Model> load() {
        Map<String, Model> models = new TreeMap<>();
        models.put(CHATGPT_4O.key(), CHATGPT_4O);
        models.put(CLAUDE_OPUS_4.key(), CLAUDE_OPUS_4);
        models.put(GEMINI_25_FLASH.key(), GEMINI_25_FLASH);
        models.put(LLAMA_4_SCOUT.key(), LLAMA_4_SCOUT);
        models.put(MISTRAL_SMALL.key(), MISTRAL_SMALL);
        return models;
    }
}
