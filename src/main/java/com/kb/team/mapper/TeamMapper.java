package com.kb.team.mapper;

import com.kb.team.dto.*;

import java.util.List;

public interface TeamMapper {
    List<TeamMember> getTeamMembers(Long teamId);
    List<Integer> getTeamMembersIds(Long teamId);
    List<TeamRank> getTeamRanks(Long teamId);
    CurChallenge getCurChallenge(Long teamId);
    String getUserName(int userId);
    List<Integer> getCardNo(int userId);
    List<CardUsage> getCardUsage(int cardNo);
}
