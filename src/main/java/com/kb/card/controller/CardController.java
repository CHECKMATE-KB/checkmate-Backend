package com.kb.card.controller;

import com.kb.card.dto.CardDTO;
import com.kb.card.service.CardService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/card")
@Api(value = "CARD", tags = "카드")
@PropertySource({"classpath:/application.properties"})
public class CardController {

    private final CardService cardService;

    @GetMapping("/list/{userNo}")
    public List<CardDTO> getCardsByUserNo(@PathVariable Long userNo) {
        log.info("Fetching card list for userNo: {}", userNo);
        return cardService.getCardsByUserNo(userNo);
    }

    @PostMapping("/register")
    public String registerCard(@RequestBody CardDTO cardDTO) {
        log.info("Registering new card: {}", cardDTO);
        cardService.registerCard(cardDTO);
        return "Card registered successfully!";
    }
}
