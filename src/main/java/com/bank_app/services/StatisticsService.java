package com.bank_app.services;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

public interface StatisticsService {

    Map<LocalDate, BigDecimal> findSumTransactionByDate(LocalDate startDate, LocalDate endDate, Integer userId);

    BigDecimal getAmountBalance(Integer userId);

    BigDecimal highestTransfert(Integer userId);

    BigDecimal highestDeposit(Integer userId);
}
