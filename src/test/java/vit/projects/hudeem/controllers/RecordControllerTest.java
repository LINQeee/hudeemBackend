package vit.projects.hudeem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.services.RecordService;

@AutoConfigureMockMvc
@SpringBootTest
class RecordControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    RecordService recordService;

    @Test
    void saveRecord() throws Exception {
        RecordDTO request = new RecordDTO();
        request.setCurrentWeight(170);

        Mockito.when(recordService.saveRecord(request))
                .thenReturn("good");

        mockMvc.perform(MockMvcRequestBuilders.post("/record")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(request)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("good"));
    }

    @Test
    void deleteRecord() throws Exception {
        long id = 10L;

        Mockito.when(recordService.deleteRecord(id)).thenReturn("success");

        mockMvc.perform(MockMvcRequestBuilders.delete("/record?id=" + id))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string("success"));
    }
}