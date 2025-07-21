package com.pacman.pacman.domain;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.criterio.SalesUnitsStrategy;
import com.pacman.domain.service.criterio.SortingStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SalesUnitStrategyTest {

    private static SortingStrategy strategy;

    @BeforeAll
    static void setUp() {
        SortingStrategyVO strategyId = SortingStrategyVO.SALES_UNITS;
        strategy = new SalesUnitsStrategy(strategyId);
        strategy.setWeight(1.0);
    }

    @Test
    void evaluateTest(){
        Shirt shirt1 = new Shirt(1,"A",10, 1,1,1);
        Shirt shirt2 = new Shirt(2,"B",100, 1,1,0);
        Shirt shirt3 = new Shirt(3,"C",60, 1,0,0);
        Shirt shirt4 = new Shirt(3,"D",140, 0,0,0);

        double shirt1value = strategy.evaluate(shirt1);
        double shirt2value = strategy.evaluate(shirt2);
        double shirt3value = strategy.evaluate(shirt3);
        double shirt4value = strategy.evaluate(shirt4);

        assertEquals(shirt1value, 10.0, 0.01);
        assertEquals(shirt2value, 100, 0.01);
        assertEquals(shirt3value, 60, 0.01);
        assertEquals(shirt4value, 140, 0.01);

    }
}
