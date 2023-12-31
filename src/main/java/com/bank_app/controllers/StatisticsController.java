package com.bank_app.controllers;

import com.bank_app.dto.TransactionSumDetails;
import com.bank_app.services.StatisticsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/statistics")
@Tag(name = "statistics")
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping("/sum-by-date/{userId}")
   public ResponseEntity <List<TransactionSumDetails>>  findSumTransactionByDate(

           @PathVariable("userId") Integer userId,
           @RequestParam("start-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
           @RequestParam("end-date") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate
          ){
            return ResponseEntity.ok(statisticsService.findSumTransactionByDate(startDate, endDate, userId));
   }

    @GetMapping("/accountBalance/{userId}")
  public ResponseEntity<BigDecimal>   getAmountBalance(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(statisticsService.getAmountBalance(userId));
    }

    @GetMapping("/highest-transfer/{userId}")
    public ResponseEntity<BigDecimal> highestTransfer(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(statisticsService.highestTransfer(userId));
    }

    @GetMapping("/highest-deposit/{userId}")
    public ResponseEntity<BigDecimal> highestDeposit(@PathVariable("userId") Integer userId){

        return ResponseEntity.ok(statisticsService.highestDeposit(userId));

  }


}
