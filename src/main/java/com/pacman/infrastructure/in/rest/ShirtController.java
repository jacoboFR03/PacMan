package com.pacman.infrastructure.in.rest;

import com.pacman.application.port.in.SortShirtsUseCase;
import com.pacman.infrastructure.in.rest.dto.ShirtDTO;
import com.pacman.infrastructure.in.rest.dto.WeightSortShirtsDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/shirt")
@Tag(name = "Shirt API", description = "Operaciones sobre camisetas")
public class ShirtController {

    private final SortShirtsUseCase sortShirtsUseCase;

    public ShirtController(SortShirtsUseCase sortShirtsUseCase) {
        this.sortShirtsUseCase = sortShirtsUseCase;
    }

    @PostMapping("/sort")
    @Operation(summary = "Ordenar camisetas", description = "Ordena las camisetas según los pesos de criterios recibidos")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de camisetas ordenadas",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ShirtDTO.class))),
        @ApiResponse(responseCode = "400", description = "Petición inválida", content = @Content),
        @ApiResponse(responseCode = "500", description = "Error interno del servidor", content = @Content)
    })
    public List<ShirtDTO> sortShirts(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Pesos de los criterios",
            required = true,
            content = @Content(schema = @Schema(implementation = WeightSortShirtsDTO.class))
        )@RequestBody WeightSortShirtsDTO weightSortShirtsDto) {
        return this.sortShirtsUseCase.sortShirts(weightSortShirtsDto);
    }
}
