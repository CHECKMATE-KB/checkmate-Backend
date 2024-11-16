package com.kb.team.mapper;

import com.kb.team.dto.CurChallenge;
import com.kb.team.dto.TeamHistory;
import com.kb.team.dto.TeamMember;
import com.kb.team.dto.TeamRank;

import java.util.List;

public interface TeamMapper {
    List<TeamMember> getTeamMembers(Long teamId);
    List<TeamRank> getTeamRanks(Long teamId);
    CurChallenge getCurChallenge(Long teamId);
    List<TeamHistory> getTeamHistory(Long teamId);
}
