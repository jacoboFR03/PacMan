package com.pacman.domain.service;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;
import com.pacman.domain.service.criterio.SortingStrategy;

import java.util.List;
import java.util.Map;

public class SortShirtService {

    private final List<SortingStrategy> strategies;

    public SortShirtService(List<SortingStrategy> strategies) {
        this.strategies = strategies;
    }

    public void sort(List<Shirt> shirts, Map<SortingStrategyVO, Double> weights) {
        shirts.sort((s1, s2) -> Double.compare(this.calculateScore(s2, weights), this.calculateScore(s1, weights)));
    }

    private double calculateScore(Shirt shirt, Map<SortingStrategyVO, Double> weights) {
        return this.strategies.stream().map(strategy -> {
            strategy.setWeight(weights.get(strategy.getStrategyId()));
            return strategy.evaluate(shirt);
        }).reduce(0.0, Double::sum);

//        return this.strategies.stream().mapToDouble(c -> {
//            c.setPeso(pesos.get(c.getStrategyId()));
//            return c.evaluar(shirt);
//        }).sum();
    }

}
