package com.pacman.application.port.out;

import com.pacman.domain.model.Shirt;

import java.util.List;

public interface ShirtsRepositoryPort {

    List<Shirt> getShirts();
}
