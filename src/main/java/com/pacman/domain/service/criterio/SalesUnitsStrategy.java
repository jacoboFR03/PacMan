package com.pacman.domain.service.criterio;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;

public class SalesUnitsStrategy extends BaseStrategy implements SortingStrategy {

    public SalesUnitsStrategy(SortingStrategyVO id) {
        super(id);
    }

    @Override
    public Double evaluate(Shirt shirt) {
        return shirt.salesUnit() * this.weight;
    }
}
