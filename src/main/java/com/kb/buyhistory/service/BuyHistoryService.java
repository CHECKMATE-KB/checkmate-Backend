package com.kb.buyhistory.service;

import com.kb.buyhistory.dto.BuyHistoryDTO;
import com.kb.buyhistory.mapper.BuyHistoryMapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@PropertySource({"classpath:/application.properties"})
public class BuyHistoryService {

    private final BuyHistoryMapper buyHistoryMapper;

    public List<BuyHistoryDTO> getBuyHistoryByUserNo(Long userNo) {
        return buyHistoryMapper.findBuyHistoryByUserNo(userNo);
    }
}
