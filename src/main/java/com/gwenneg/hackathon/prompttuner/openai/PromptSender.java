package com.gwenneg.hackathon.prompttuner.openai;

import com.gwenneg.hackathon.prompttuner.model.Model;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.ArrayList;
import java.util.List;

import static com.gwenneg.hackathon.prompttuner.openai.CompletionsClient.SYSTEM;
import static com.gwenneg.hackathon.prompttuner.openai.CompletionsClient.USER;

@ApplicationScoped
public class PromptSender {

    @Inject
    @RestClient
    CompletionsClient completionsClient;

    public String send(Model model, String systemPrompt, String prompt) {

        List<Message> messages = new ArrayList<>();
        if (systemPrompt != null) {
            messages.add(new Message(SYSTEM, systemPrompt));
        }
        messages.add(new Message(USER, prompt));

        Request request = new Request(model.key(), messages);
        Response response = completionsClient.post(request);

        return response.choices()[response.choices().length - 1].message().content();
    }
}
