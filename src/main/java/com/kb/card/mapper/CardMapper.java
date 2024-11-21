package com.kb.card.mapper;

import com.kb.card.dto.CardDTO;
import java.util.List;

public interface CardMapper {

    List<CardDTO> findCardsByUserNo(Long userNo);
    void insertCard(CardDTO cardDTO);

    CardDTO findCardNumByNo(Long cardNo);
}
