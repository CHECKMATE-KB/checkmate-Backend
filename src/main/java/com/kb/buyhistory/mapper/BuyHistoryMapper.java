package com.kb.buyhistory.mapper;

import com.kb.buyhistory.dto.BuyHistoryDTO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface BuyHistoryMapper {
    List<BuyHistoryDTO> findBuyHistoryByUserNo(@Param("userNo") Long userNo);
}
