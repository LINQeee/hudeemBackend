package vit.projects.hudeem.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vit.projects.hudeem.dto.UserDTO;

@AutoConfigureMockMvc
@SpringBootTest
class PerformanceTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void loadPerformance() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("username");
        userDTO.setPassword("password");
        userDTO.setIp("127.0.0.1");

        for (int i = 0; i < 100; i++) {
            userDTO.setEmail(String.valueOf(Math.random() * 10000));
            mockMvc.perform(MockMvcRequestBuilders.post("/user")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(objectMapper.writeValueAsBytes(userDTO)))
                    .andExpect(MockMvcResultMatchers.status().isOk());
        }
    }
}
