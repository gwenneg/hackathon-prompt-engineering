package com.gwenneg.hackathon.prompttuner.gemini;

import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.jboss.resteasy.reactive.RestQuery;

@RegisterRestClient(configKey = "gemini")
public interface GeminiClient {

    @POST
    @Path("/v1beta/models/gemini-1.5-pro-latest:generateContent")
    Response post(@RestQuery String key, Request request);
}
