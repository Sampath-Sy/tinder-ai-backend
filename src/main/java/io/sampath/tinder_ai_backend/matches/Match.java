package io.sampath.tinder_ai_backend.matches;


import io.sampath.tinder_ai_backend.profiles.Profile;

public record Match(String id, Profile profile, String conversationId) {
}
