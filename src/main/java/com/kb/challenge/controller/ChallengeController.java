package com.kb.challenge.controller;


import com.kb.challenge.dto.Challenge;
import com.kb.challenge.dto.ChallengeTeam;
import com.kb.challenge.dto.ChallengeDTO;
import com.kb.challenge.service.ChallengeService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/challenge")
@Api(value = "ChallengeController", tags = "챌린지 정보") // swagger
public class ChallengeController {

    private final ChallengeService service;

    @PostMapping("/team/create")
    public ResponseEntity<Integer> createTeam(@RequestBody ChallengeTeam team) {
        return ResponseEntity.ok(service.createTeam(team));
    }

    @GetMapping("/available")
    public ResponseEntity<List<Challenge>> getAvailableTeams() {
        return ResponseEntity.ok().body(service.getAllChallenges());
    }

    @PostMapping("/select")
    public ResponseEntity<Integer> createTeam(@RequestBody ChallengeDTO challenge) {
        return ResponseEntity.ok(service.createChallenge(challenge));
    }
}
