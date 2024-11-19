package com.kb.buyhistory.controeller;

import com.kb.buyhistory.dto.BuyHistoryDTO;
import com.kb.buyhistory.service.BuyHistoryService;
import io.swagger.annotations.Api;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/buy")
@Api(value = "BuyHistory", tags = "결제 내역")
@PropertySource({"classpath:/application.properties"})
public class BuyHistoryController {

    private final BuyHistoryService buyHistoryService;

    @GetMapping("/{userNo}")
    public ResponseEntity<List<BuyHistoryDTO>> getBuyHistory(@PathVariable("userNo") Long userNo) {
        List<BuyHistoryDTO> buyHistoryList = buyHistoryService.getBuyHistoryByUserNo(userNo);
        if (buyHistoryList.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(buyHistoryList);
    }




}
