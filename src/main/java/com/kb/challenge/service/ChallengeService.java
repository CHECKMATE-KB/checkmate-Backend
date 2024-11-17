package com.kb.challenge.service;


import com.kb.challenge.dto.*;
import com.kb.challenge.mapper.ChallengeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ChallengeService {
    private final ChallengeMapper mapper;

    public int createTeam(ChallengeTeamDTO team) {
        // 팀생성
        int result=mapper.createTeam(team);
        if(result==0) throw new NoSuchElementException();
        int teamNumber= mapper.getTeamNumber(team.getTeamName());

        // 팀유저 생성
        for(int memberId : team.getMembers()) {
            ChallengeTeamUser teamUser = new ChallengeTeamUser();
            teamUser.setChTotal(0);
            teamUser.setQzPoint(0);
            teamUser.setChTotal(0);
            teamUser.setTeamId(teamNumber);
            teamUser.setUserId(memberId);
            result = mapper.createTeamUser(teamUser);
            if(result==0) throw new NoSuchElementException();
        }

        // 챌린지 생성
        Challenge challenge = new Challenge();
        challenge.setTeamNo(teamNumber);
        challenge.setCcNo(team.getCcNo());
        challenge.setChLimit(team.getChLimit());
        challenge.setChStart(team.getTeamStart());
        challenge.setChEnd(team.getTeamEnd());
        result=mapper.createChallenge(challenge);



        return result;
    }

    public List<ChallengeCategory> getAllChallenges() {
        List<ChallengeCategory> result=mapper.getAllChallenges();
        if(result==null) throw new NoSuchElementException();
        return result;
    }

    public int updateChallengePrice(ChallengeDTO challenge) {
        int result=mapper.updatePrice(challenge);
        if(result==0) throw new NoSuchElementException();
        return result;
    }

}
