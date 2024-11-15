package com.kb.challenge.mapper;

import com.kb.challenge.dto.Challenge;
import com.kb.challenge.dto.ChallengeTeam;
import com.kb.challenge.dto.ChallengeDTO;

import java.util.List;

public interface ChallengeMapper {
    int createTeam(ChallengeTeam team);
    List<Challenge> getAllChallenges();
    int createChallenge(ChallengeDTO challenge);
}
