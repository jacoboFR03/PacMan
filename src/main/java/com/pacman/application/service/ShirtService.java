package com.pacman.application.service;

import com.pacman.application.port.in.SortShirtsUseCase;
import com.pacman.application.port.out.ShirtsRepositoryPort;
import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.SortShirtService;
import com.pacman.infrastructure.in.dto.ShirtDTO;
import com.pacman.infrastructure.in.dto.WeightSortShirtsDTO;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class ShirtService implements SortShirtsUseCase {

    private final SortShirtService sortShitService;
    private final ShirtsRepositoryPort shirtsRepositoryPort;

    public ShirtService(SortShirtService sortShitService, ShirtsRepositoryPort shirtsRepositoryPort) {
        this.shirtsRepositoryPort = shirtsRepositoryPort;
        this.sortShitService = sortShitService;
    }

    public List<ShirtDTO> sortShirts(WeightSortShirtsDTO weightSortShirtsDto){
        List<Shirt> shirts = this.shirtsRepositoryPort.getShirts();
        this.sortShitService.sort(shirts, this.getWeights(weightSortShirtsDto));
        return shirts.stream().map(shirt -> new ShirtDTO(shirt.id(), shirt.name())).toList();
    }

    private HashMap<SortingStrategyVO, Double> getWeights(WeightSortShirtsDTO weightSortShirtsDto){
        HashMap<SortingStrategyVO, Double> pesos = new HashMap<>();
        pesos.put(SortingStrategyVO.SALES_UNITS, weightSortShirtsDto.salesUnitsWeight() != null ? weightSortShirtsDto.salesUnitsWeight() : 0.0);
        pesos.put(SortingStrategyVO.STOCK, weightSortShirtsDto.stockWeight() != null ? weightSortShirtsDto.stockWeight() : 0.0);
        return pesos;
    }
}

