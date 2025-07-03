package com.gwenneg.hackathon.prompttuner.rest;

import com.gwenneg.hackathon.prompttuner.SystemPromptsLoader;
import com.gwenneg.hackathon.prompttuner.model.Model;
import com.gwenneg.hackathon.prompttuner.model.ModelsProvider;
import com.gwenneg.hackathon.prompttuner.openai.PromptSender;
import io.vertx.core.json.JsonObject;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;

import java.util.List;
import java.util.Set;

@Path("/prompt-tuner")
public class PromptTunerResource {

    String SYSTEM = "system";
    String USER = "user";
    String ASSISTANT = "assistant";

    @Inject
    PromptSender promptSender;

    @GET
    @Path("/models")
    public List<Model> getModels() {
        return ModelsProvider.getModels().values().stream().toList();
    }

    @GET
    @Path("/system-prompts")
    public Set<String> getSystemPrompts() {
        return SystemPromptsLoader.getSystemPrompts().keySet();
    }

    @PUT
    public JsonObject process(Request request) {

        String systemPrompt = SystemPromptsLoader.getSystemPrompts().get(request.systemPrompt());
        Model model = ModelsProvider.getModels().get(request.model());

        String rawResponse = promptSender.send(model, null, request.prompt());
        String intermediateResponse = promptSender.send(model, systemPrompt, request.prompt());
        String tunedResponse = promptSender.send(model, null, intermediateResponse);

        return JsonObject.of(
            "init", JsonObject.of(
                SYSTEM, systemPrompt,
                USER, request.prompt(),
                ASSISTANT, intermediateResponse
            ),
            "raw-prompt", JsonObject.of(
                USER, request.prompt(),
                ASSISTANT, rawResponse
            ),
            "tuned-prompt", JsonObject.of(
                USER, intermediateResponse,
                ASSISTANT, tunedResponse
            )
        );
    }
}
