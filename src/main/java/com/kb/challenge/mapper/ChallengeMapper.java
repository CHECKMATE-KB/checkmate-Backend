package com.kb.challenge.mapper;

import com.kb.challenge.dto.*;

import java.util.List;

public interface ChallengeMapper {
    int createTeam(ChallengeTeamDTO team);
    int getTeamNumber(String teamName);
    int createChallenge(Challenge challenge);
    List<ChallengeCategory> getAllChallenges();
    int updatePrice(ChallengeDTO challenge);
    int createTeamUser(ChallengeTeamUser teamUser);
}
