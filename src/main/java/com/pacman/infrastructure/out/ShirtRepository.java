package com.pacman.infrastructure.out;

import com.pacman.application.port.out.ShirtsRepositoryPort;
import com.pacman.domain.model.Shirt;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ShirtRepository implements ShirtsRepositoryPort {

    final List<Shirt> shirts = new ArrayList<>();

    public ShirtRepository() {
        shirts.addAll(List.of(
            new Shirt(1, "V-NECH BASIC SHIRT",100,4,9,0),
            new Shirt(2, "CONTRASTING FABRIC T-SHIRT",50,35,9,9),
            new Shirt(3, "RAISED PRINT T-SHIRT",80,20,2,20),
            new Shirt(4, "PLEATED T-SHIRT",3,25,30,10),
            new Shirt(5, "CONTRASTING LACE T-SHIRT",650,0,1,0),
            new Shirt(6, "SLOGAN T-SHIRT",20,9,2,5)
        ));
    }

    @Override
    public List<Shirt> getShirts() {
        return this.shirts;
    }
}
