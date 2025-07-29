package com.pacman.pacman.infraestructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pacman.application.port.in.SortShirtsUseCase;
import com.pacman.application.port.out.ShirtsRepositoryPort;
import com.pacman.domain.service.SortShirtService;
import com.pacman.infrastructure.in.rest.ShirtController;
import com.pacman.infrastructure.in.rest.dto.ShirtDTO;
import com.pacman.infrastructure.in.rest.dto.WeightSortShirtsDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShirtController.class)
public class ShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SortShirtsUseCase sortShirtsUseCase;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void sortShirts_shouldReturnSortedShirts() throws Exception {
        WeightSortShirtsDTO inputDto = new WeightSortShirtsDTO(0.6, 0.4);

        List<ShirtDTO> responseData = List.of(
            new ShirtDTO(1, "A"),
            new ShirtDTO(2, "B")
        );

        when(sortShirtsUseCase.sortShirts(inputDto)).thenReturn(responseData);

        var response = mockMvc.perform(post("/shirt/sort")
            .contentType(String.valueOf(MediaType.APPLICATION_JSON))
            .content(objectMapper.writeValueAsString(inputDto)))
            .andExpect(status().isOk())
            .andReturn();

        String jsonResponse = response.getResponse().getContentAsString();
        List<ShirtDTO> responseList = objectMapper.readValue(jsonResponse,
                objectMapper.getTypeFactory().constructCollectionType(List.class, ShirtDTO.class));

        assertEquals(2, responseList.size());
        assertEquals(new ShirtDTO(1, "A"), responseList.get(0));
        assertEquals(new ShirtDTO(2, "B"), responseList.get(1));
    }
}
