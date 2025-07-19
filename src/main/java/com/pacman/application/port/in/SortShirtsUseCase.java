package com.pacman.application.port.in;

import com.pacman.infrastructure.in.dto.ShirtDTO;
import com.pacman.infrastructure.in.dto.WeightSortShirtsDTO;

import java.util.List;

public interface SortShirtsUseCase {

    List<ShirtDTO> sortShirts(WeightSortShirtsDTO weightSortShirtsDto);
}
