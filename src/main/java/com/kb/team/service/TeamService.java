package com.kb.team.service;


import com.kb.team.dto.*;
import com.kb.team.mapper.TeamMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class TeamService {
    private final TeamMapper mapper;

    public List<TeamMember> getTeamInfo(long teamId) {
        List<TeamMember> teamMembers = mapper.getTeamMembers(teamId);

        return teamMembers;
    }

    public CurChallenge getCurChallenge(long teamId) {
        return mapper.getCurChallenge(teamId);
    }

    public List<TeamRank> getTeamRank(long teamId) {
        List<TeamRank> teamRanks = mapper.getTeamRanks(teamId);

        return teamRanks;
    }

    public List<TeamHistory> getTeamHistory(long teamId) {
        List<Integer> memberIds= mapper.getTeamMembersIds(teamId);
        List<TeamHistory> teamHistories = new ArrayList<>();
        for(Integer memberId : memberIds) {
            List<Integer> cardNos= mapper.getCardNo(memberId);
            List<CardUsage> cardUsages = new ArrayList<>();
            TeamHistory teamHistory = new TeamHistory();
            for(Integer cardNo : cardNos) {
                List<CardUsage> curCards = mapper.getCardUsage(cardNo);

                cardUsages.addAll(curCards);
            }
            teamHistory.setUserName(mapper.getUserName(memberId));
            teamHistory.setUserNo(memberId);
            teamHistory.setTeamNo(teamId);
            teamHistory.setCardUsages(cardUsages);
            teamHistories.add(teamHistory);
        }

//        List<TeamHistory> teamHistories = mapper.getTeamHistory(teamId);

        return teamHistories;
    }
}
