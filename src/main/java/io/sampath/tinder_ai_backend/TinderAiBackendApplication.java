package io.sampath.tinder_ai_backend;

import io.sampath.tinder_ai_backend.profiles.Gender;
import io.sampath.tinder_ai_backend.profiles.Profile;
import io.sampath.tinder_ai_backend.profiles.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TinderAiBackendApplication implements CommandLineRunner {
    @Autowired
    private ProfileRepository profileRepository;

    public static void main(String[] args) {
        SpringApplication.run(TinderAiBackendApplication.class, args);
        System.out.println("hello world");
    }

    public void run(String... args) {
        Profile profile = new Profile(
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
        profileRepository.save(profile);
        profileRepository.findAll().forEach(System.out::println);


    }

}
