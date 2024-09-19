package io.sampath.tinder_ai_backend;

import io.sampath.tinder_ai_backend.conversations.ChatMessage;
import io.sampath.tinder_ai_backend.conversations.Conversation;
import io.sampath.tinder_ai_backend.conversations.ConversationRepository;
import io.sampath.tinder_ai_backend.profiles.Gender;
import io.sampath.tinder_ai_backend.profiles.Profile;
import io.sampath.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ConversationRepository conversationRepository;
    public static void main(String[] args) {
        SpringApplication.run(TinderAiBackendApplication.class, args);

    }

    public void run(String... args) {
        profileRepository.deleteAll();
        conversationRepository.deleteAll();
        Profile profile1 = new Profile(
                "1",
                "sampath",
                "sy",
                24,
                "Asian",
                Gender.MALE,
                "Software Engineer at Deloitte",
                "sampth.png",
                "abcd"
		);
        profileRepository.save(profile1);
        Profile profile2 = new Profile(
                "1",
                "sampath",
                "sy",
                24,
                "Asian",
                Gender.MALE,
                "Software Engineer at Deloitte",
                "sampth.png",
                "abcd"
        );
        profileRepository.save(profile2);
        profileRepository.findAll().forEach(System.out::println);
        Conversation conversation = new Conversation(
                "1",
                "1",
                List.of(new ChatMessage("hello","1", LocalDateTime.now()))
        );
        conversationRepository.save(conversation);
        conversationRepository.findAll().forEach(System.out::println);

    }

}
