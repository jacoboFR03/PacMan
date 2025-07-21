package com.pacman.pacman.domain;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.criterio.SortingStrategy;
import com.pacman.domain.service.criterio.StockStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class StockStrategyTest {

    private static SortingStrategy strategy;

    @BeforeAll
    static void setUp() {
        SortingStrategyVO strategyId = SortingStrategyVO.STOCK;
        strategy = new StockStrategy(strategyId);
        strategy.setWeight(1.0);
    }

    @Test
    void evaluateTest(){
        Shirt shirt3stocks = new Shirt(1,"A",1, 1,1,1);
        Shirt shirt2stocks = new Shirt(2,"B",1, 1,1,0);
        Shirt shirt1stocks = new Shirt(3,"C",1, 1,0,0);
        Shirt shirt0stocks = new Shirt(3,"D",1, 0,0,0);

        double shirt3value = strategy.evaluate(shirt3stocks);
        double shirt2value = strategy.evaluate(shirt2stocks);
        double shirt1value = strategy.evaluate(shirt1stocks);
        double shirt0value = strategy.evaluate(shirt0stocks);

        assertEquals(shirt3value, 1.0, 0.01);
        assertEquals(shirt2value, 0.66, 0.01);
        assertEquals(shirt1value, 0.33, 0.01);
        assertEquals(shirt0value, 0, 0.01);

    }
}
