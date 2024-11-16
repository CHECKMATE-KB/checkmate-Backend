package com.kb.card.service;

import com.kb.card.dto.CardDTO;
import com.kb.card.mapper.CardMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class CardService {

    private final CardMapper cardMapper;

    public List<CardDTO> getCardsByUserNo(Long userNo) {
        return cardMapper.findCardsByUserNo(userNo);
    }

    public void registerCard(CardDTO cardDTO) {
        cardMapper.insertCard(cardDTO);
    }
}
