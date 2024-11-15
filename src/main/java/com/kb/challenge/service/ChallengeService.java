package com.kb.challenge.service;


import com.kb.challenge.dto.Challenge;
import com.kb.challenge.dto.ChallengeDTO;
import com.kb.challenge.dto.ChallengeTeam;
import com.kb.challenge.mapper.ChallengeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ChallengeService {
    private final ChallengeMapper mapper;

    public int createTeam(ChallengeTeam team) {
        int result=mapper.createTeam(team);
        if(result==0) throw new NoSuchElementException();

        return result;
    }

    public List<Challenge> getAllChallenges() {
        List<Challenge> result=mapper.getAllChallenges();
        if(result==null) throw new NoSuchElementException();
        return result;
    }

    public int createChallenge(ChallengeDTO challenge) {
        int result=mapper.createChallenge(challenge);
        if(result==0) throw new NoSuchElementException();
        return result;
    }

}
