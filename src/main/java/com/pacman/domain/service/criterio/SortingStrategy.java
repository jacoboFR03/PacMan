package com.pacman.domain.service.criterio;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;

public interface SortingStrategy {

    Double evaluate(Shirt shirt);

    void setWeight(Double weight);

    SortingStrategyVO getStrategyId();
}
