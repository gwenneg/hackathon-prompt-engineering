package com.gwenneg.hackathon.prompttuner.gemini;

import java.util.List;

public record Response(
   List<Candidate> candidates
) {}
