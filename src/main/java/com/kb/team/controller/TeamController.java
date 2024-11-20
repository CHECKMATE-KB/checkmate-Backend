package com.kb.team.controller;

import com.kb.team.dto.*;
import com.kb.team.service.TeamService;
import io.swagger.annotations.Api;
import io.swagger.models.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/team")
@Api(value = "TeamController", tags = "팀 정보") // swagger

public class TeamController {

    private final TeamService service;

    @GetMapping("/member/{teamNo}")
    public ResponseEntity<List<TeamMember>> getTeamInfo(@PathVariable long teamNo) {
        return ResponseEntity.ok(service.getTeamInfo(teamNo));
    }

    @GetMapping("/challenge/{teamNo}")
    public ResponseEntity<List<CurChallenge>> getChallengeInfo(@PathVariable long teamNo) {
        return ResponseEntity.ok(service.getCurChallenge(teamNo));
    }


    @GetMapping("/score/{teamNo}")
    public ResponseEntity<List<TeamRank>> getTeamRankInfo(@PathVariable long teamNo) {
        return ResponseEntity.ok(service.getTeamRank(teamNo));
    }

    @GetMapping("/spend/{teamNo}")
    public ResponseEntity<List<TeamHistory>> getTeamSpendHistory(@PathVariable long teamNo) {
        return ResponseEntity.ok(service.getTeamHistory(teamNo));
    }
    @GetMapping("/spend/category/{teamNo}")
    public ResponseEntity<List<TeamSpend>> getTeamSpendCategory(@PathVariable long teamNo) {
        return ResponseEntity.ok(service.getTeamSpendCategory(teamNo));
    }
}
