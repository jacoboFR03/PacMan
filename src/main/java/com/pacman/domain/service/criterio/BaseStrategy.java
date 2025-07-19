package com.pacman.domain.service.criterio;

import com.pacman.domain.model.SortingStrategyVO;

public abstract class BaseStrategy {

    final SortingStrategyVO id;
    Double weight;

    public BaseStrategy(SortingStrategyVO id) {
        this.id = id;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public SortingStrategyVO getStrategyId() {
        return this.id;
    }
}
