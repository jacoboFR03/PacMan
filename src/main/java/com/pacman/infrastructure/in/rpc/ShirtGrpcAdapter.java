package com.pacman.infrastructure.in.rpc;

import com.pacman.application.port.in.SortShirtsUseCase;
import com.pacman.infrastructure.in.rest.dto.ShirtDTO;
import com.pacman.infrastructure.in.rest.dto.WeightSortShirtsDTO;
import io.grpc.stub.StreamObserver;
import org.springframework.stereotype.Service;
import pacman.proto.ShirtListResponse;
import pacman.proto.ShirtRequest;
import pacman.proto.ShirtResponse;
import pacman.proto.ShirtServiceGrpc;

import java.util.List;

@Service
public class ShirtGrpcAdapter extends ShirtServiceGrpc.ShirtServiceImplBase {

    SortShirtsUseCase sortShirtsUseCase;

    public ShirtGrpcAdapter(SortShirtsUseCase sortShirtsUseCase) {
        this.sortShirtsUseCase = sortShirtsUseCase;
    }

    @Override
    public void sortShirts(ShirtRequest request, StreamObserver<ShirtListResponse> responseObserver) {
        WeightSortShirtsDTO weightSortShirtsDTO = new WeightSortShirtsDTO(request.getSalesUnitsWeight(), request.getStockWeight());
        List<ShirtDTO> shirtDTOS = this.sortShirtsUseCase.sortShirts(weightSortShirtsDTO);

        ShirtListResponse response = ShirtListResponse.newBuilder().addAllShirts(shirtDTOS.stream().map(
            shirtDTO -> ShirtResponse.newBuilder()
                .setId(shirtDTO.id())
                .setName(shirtDTO.name())
                .build())
            .toList()).build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void sortShirtsServerSideStreaming(ShirtRequest request, StreamObserver<ShirtResponse> responseObserver) {
        WeightSortShirtsDTO weightSortShirtsDTO = new WeightSortShirtsDTO(request.getSalesUnitsWeight(), request.getStockWeight());
        List<ShirtDTO> shirtDTOS = this.sortShirtsUseCase.sortShirts(weightSortShirtsDTO);

        shirtDTOS.forEach(shirtDTO -> {
            ShirtResponse response = ShirtResponse.newBuilder()
                .setId(shirtDTO.id())
                .setName(shirtDTO.name())
                .build();
            responseObserver.onNext(response);
        });

        responseObserver.onCompleted();
    }
}
