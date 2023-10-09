package com.bank_app.controllers;

import com.bank_app.services.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/sum-by-date{userId}")
   public ResponseEntity<Map<LocalDate, BigDecimal>>  findSumTransactionByDate(

           @PathVariable("userId") Integer userId,
           @RequestParam(" start-date") LocalDate startDate,
           @RequestParam("end-date") LocalDate endDate
          ){
            return ResponseEntity.ok(statisticsService.findSumTransactionByDate(startDate, endDate, userId));
   }

    @GetMapping("/accountBalance{userId}")
  public ResponseEntity<BigDecimal>   getAmountBalance(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(statisticsService.getAmountBalance(userId));
    }

    @GetMapping("/highest-transfer{userId}")
    public ResponseEntity<BigDecimal> highestTransfer(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(statisticsService.highestTransfer(userId));
    }

    @GetMapping("/highest-deposit{userId}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(statisticsService.highestDeposit(userId));

  }


}
