package io.sampath.tinder_ai_backend.conversations;

import java.util.List;

public record Conversation(
        String id,
        String ProfileId,
        List<ChatMessage>messages
) {
}
