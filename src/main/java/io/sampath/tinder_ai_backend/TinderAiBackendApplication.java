package io.sampath.tinder_ai_backend;

import io.sampath.tinder_ai_backend.conversations.ChatMessage;
import io.sampath.tinder_ai_backend.conversations.Conversation;
import io.sampath.tinder_ai_backend.conversations.ConversationRepository;
import io.sampath.tinder_ai_backend.matches.MatchRepository;
import io.sampath.tinder_ai_backend.profiles.Gender;
import io.sampath.tinder_ai_backend.profiles.Profile;
import io.sampath.tinder_ai_backend.profiles.ProfileCreationService;
import io.sampath.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ai.openai.OpenAiChatModel;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired

    private ConversationRepository conversationRepository;
    @Autowired
    private MatchRepository matchRepository;

    public static void main(String[] args) {
        SpringApplication.run(TinderAiBackendApplication.class, args);

    }

    @Autowired
    private OpenAiChatModel chatModel;

    public void run(String... args) {
        clearAllData();
        profileCreationService.saveProfilesToDB();

    }

    private void clearAllData() {
        profileRepository.deleteAll();
        conversationRepository.deleteAll();
        matchRepository.deleteAll();
    }

}
