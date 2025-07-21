package com.pacman.pacman.domain;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.SortShirtService;
import com.pacman.domain.service.criterio.SortingStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SortShirtServiceTest {

    private static SortingStrategy strategy1;
    private static SortingStrategy strategy2;
    private static SortingStrategyVO strategy1Id;
    private static SortingStrategyVO strategy2Id;
    private static SortShirtService sortShirtService;

    @BeforeAll
    static void setUp() {
        strategy1 = mock(SortingStrategy.class);
        strategy2 = mock(SortingStrategy.class);
        strategy1Id = mock(SortingStrategyVO.class);
        strategy2Id = mock(SortingStrategyVO.class);

        when(strategy1.getStrategyId()).thenReturn(strategy1Id);
        when(strategy2.getStrategyId()).thenReturn(strategy2Id);

        sortShirtService = new SortShirtService(List.of(strategy1, strategy2));
    }

    @Test
    void sortShirtsTest() {
        Shirt shirtA = new Shirt(1, "A", 1,1,1,1);
        Shirt shirtB = new Shirt(2, "B", 1,1,1,1);
        List<Shirt> shirts = new ArrayList<>(List.of(shirtA, shirtB));

        Map<SortingStrategyVO, Double> weights = new HashMap<>();
        weights.put(strategy1Id, 2.0);
        weights.put(strategy2Id, 2.0);

        when(strategy1.evaluate(shirtA)).thenReturn(1.0);
        when(strategy2.evaluate(shirtA)).thenReturn(1.0);
        when(strategy1.evaluate(shirtB)).thenReturn(9.0);
        when(strategy2.evaluate(shirtB)).thenReturn(1.0);

        sortShirtService.sort(shirts, weights);

        assertEquals(shirtB.id(), shirts.get(0).id());
        assertEquals(shirtA.id(), shirts.get(1).id());
    }
}
