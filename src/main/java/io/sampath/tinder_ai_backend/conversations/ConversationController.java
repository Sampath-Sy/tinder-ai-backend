package io.sampath.tinder_ai_backend.conversations;

import io.sampath.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import java.time.LocalDateTime;

@RestController
public class ConversationController {

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;

    public ConversationController(ConversationRepository conversationRepository, ProfileRepository profileRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
    }


    @GetMapping("/conversations/{conversationId}")
    public Conversation getConversationById(@PathVariable String conversationId) {
        return conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "unable to find the conversation Id"));
    }

    @PostMapping("/conversations/{conversationId}")
    public Conversation addMessageToConversation(
            @PathVariable String conversationId,
            @RequestBody ChatMessage message) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "unable to find the conversation Id"));
        profileRepository.findById(message.authorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find the profile"));
        ChatMessage chatMessageWithTime = new ChatMessage(
                message.messageText(),
                message.authorId(),
                LocalDateTime.now()
        );
        conversation.messages().add(chatMessageWithTime);
        conversationRepository.save(conversation);
        return conversation;

    }
}
