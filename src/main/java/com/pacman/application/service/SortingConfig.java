package com.pacman.application.service;

import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.SortShirtService;
import com.pacman.domain.service.criterio.SalesUnitsStrategy;
import com.pacman.domain.service.criterio.StockStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SortingConfig {

    @Bean
    public SortShirtService sortShirtService() {
        return new SortShirtService(List.of(
            new SalesUnitsStrategy(SortingStrategyVO.SALES_UNITS),
            new StockStrategy(SortingStrategyVO.STOCK)));
    }
}
