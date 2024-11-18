package com.kb.challenge.service;


import com.kb.challenge.dto.*;
import com.kb.challenge.mapper.ChallengeMapper;
import com.kb.member.mapper.UserMapper;
import com.kb.member.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class ChallengeService {
    private final ChallengeMapper mapper;
    private final UserMapper userMapper;
    public int createTeam(ChallengeTeamDTO team) {
        // 팀생성
        int result=mapper.createTeam(team);
        if(result==0) throw new NoSuchElementException();
        int teamNumber= mapper.getTeamNumber(team.getTeamName());

        // 팀유저 생성

        for(String member : team.getMembers()) {
            // member -> UserNo 로 바꿔주기
            int userNo=userMapper.selectByUserID(member);

            System.out.println("ddd : " + member);
            System.out.println("ooo : "+userNo);

            ChallengeTeamUser teamUser = new ChallengeTeamUser();
            teamUser.setChTotal(0);
            teamUser.setQzPoint(0);
            teamUser.setChTotal(0);
            teamUser.setTeamId(teamNumber);
            teamUser.setUserId(userNo);
            result = mapper.createTeamUser(teamUser);
            if(result==0) throw new NoSuchElementException();
        }

        // 챌린지 생성
        List<Integer> ccNos = team.getCcNo();
        List<Integer> chLimits= team.getChLimit();
        for(int i=0; i<ccNos.size(); i++) {
            int ccNo=ccNos.get(i);
            int chLimit=chLimits.get(i);
            Challenge challenge = new Challenge();
            challenge.setTeamNo(teamNumber);
            challenge.setCcNo(ccNo);
            challenge.setChLimit(chLimit);
            challenge.setChStart(team.getTeamStart());
            challenge.setChEnd(team.getTeamEnd());
            result=mapper.createChallenge(challenge);
            if(result==0) throw new NoSuchElementException();
        }


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
