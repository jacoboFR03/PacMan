package com.pacman.domain.service.criterio;

import com.pacman.domain.model.Shirt;
import com.pacman.domain.model.SortingStrategyVO;

public class StockStrategy extends BaseStrategy implements SortingStrategy{

    public StockStrategy(SortingStrategyVO id){
        super(id);
    }

    @Override
    public Double evaluate(Shirt shirt) {
        double numStocks = 0;
        if (shirt.stockS() > 0) numStocks++;
        if (shirt.stockM() > 0) numStocks++;
        if (shirt.stockL() > 0) numStocks++;
        double ratio = numStocks / 3.0;
        return ratio * this.weight;
    }
}
