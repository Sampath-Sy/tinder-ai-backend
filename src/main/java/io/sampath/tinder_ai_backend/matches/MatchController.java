package io.sampath.tinder_ai_backend.matches;

import io.sampath.tinder_ai_backend.conversations.Conversation;

import io.sampath.tinder_ai_backend.conversations.ConversationRepository;
import io.sampath.tinder_ai_backend.profiles.Profile;
import io.sampath.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import java.util.UUID;

@RestController
public class MatchController {

    private final ConversationRepository conversationRepository;
    private final ProfileRepository profileRepository;
    private final MatchRepository matchRepository;

    public MatchController(ConversationRepository conversationRepository, ProfileRepository profileRepository, MatchRepository matchRepository) {
        this.conversationRepository = conversationRepository;
        this.profileRepository = profileRepository;
        this.matchRepository = matchRepository;
    }

    public record createMatchRequest(String profileId) {
    }
    @CrossOrigin(origins = "*")
    @PostMapping("/matches")
    public Match createNewConversation(@RequestBody createMatchRequest request) {
        Profile profile = profileRepository.findById(request.profileId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "unable to find the profile"));
        Conversation conversation = new Conversation(
                UUID.randomUUID().toString(),
                profile.id(),
                new ArrayList<>()
        );
        conversationRepository.save(conversation);
        Match match = new Match(
                UUID.randomUUID().toString(),
                profile,
                conversation.id()
        );
        matchRepository.save(match);
        return match;

    }
    @CrossOrigin(origins = "*")
    @GetMapping("/matches")
    public List<Match> getAllMatches() {
        return matchRepository.findAll();
    }
}
