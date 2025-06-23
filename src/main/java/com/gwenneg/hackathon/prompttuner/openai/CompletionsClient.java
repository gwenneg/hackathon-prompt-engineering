package com.gwenneg.hackathon.prompttuner.openai;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "openai")
public interface CompletionsClient {

    String SYSTEM = "system";
    String USER = "user";
    String ASSISTANT = "assistant";

    @POST
    @Path("/v1/chat/completions")
    Response post(Request request);
}
