package com.gwenneg.hackathon.prompttuner.openai;

import com.gwenneg.hackathon.prompttuner.model.Model;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseOutputText;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PromptSender {

    public String send(Model model, String instructions, String prompt) {

        OpenAIClient client = OpenAIOkHttpClient.fromEnv();

        ResponseCreateParams params = ResponseCreateParams.builder()
                .model(model.key())
                .instructions(instructions)
                .input(prompt)
                .build();

        return client
                .responses()
                .create(params)
                .output()
                .stream()
                .flatMap(item -> item.message().stream())
                .flatMap(message -> message.content().stream())
                .flatMap(content -> content.outputText().stream())
                .map(ResponseOutputText::text)
                .findFirst().get();
    }
}
