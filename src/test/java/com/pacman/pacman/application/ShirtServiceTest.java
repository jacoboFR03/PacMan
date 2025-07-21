package com.pacman.pacman.application;

import com.pacman.application.port.out.ShirtsRepositoryPort;
import com.pacman.application.service.ShirtService;
import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.SortShirtService;
import com.pacman.domain.service.criterio.SalesUnitsStrategy;
import com.pacman.domain.service.criterio.SortingStrategy;
import com.pacman.infrastructure.in.dto.ShirtDTO;
import com.pacman.infrastructure.in.dto.WeightSortShirtsDTO;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShirtServiceTest {

    private static ShirtsRepositoryPort shirtsRepositoryPort;
    private static ShirtService shirtService;

    @BeforeAll
    static void setUp() {
        SortingStrategyVO strategyId = SortingStrategyVO.SALES_UNITS;
        SortingStrategy strategy = new SalesUnitsStrategy(strategyId);
        strategy.setWeight(1.0);
        SortShirtService sortShirtService = new SortShirtService(List.of(strategy));
        shirtsRepositoryPort = mock(ShirtsRepositoryPort.class);
        shirtService = new ShirtService(sortShirtService, shirtsRepositoryPort);
    }

    @Test
    void sortShirtsTest() throws NoSuchMethodException {
        Shirt shirt1 = new Shirt(1,"A",10, 1,1,1);
        Shirt shirt2 = new Shirt(2,"B",100, 1,1,0);
        List<Shirt> mockShirts = new ArrayList<>(List.of(shirt1, shirt2));

        when(shirtsRepositoryPort.getShirts()).thenReturn(mockShirts);

        WeightSortShirtsDTO weightDto = new WeightSortShirtsDTO(1.0, 1.0);

        List<ShirtDTO> result = shirtService.sortShirts(weightDto);

        assertEquals(2, result.get(0).id());
        assertEquals("B", result.get(0).name());
        assertEquals(1, result.get(1).id());
        assertEquals("A", result.get(1).name());
    }
}
