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
import vit.projects.hudeem.dto.UserDTO;
import vit.projects.hudeem.services.UserService;

@AutoConfigureMockMvc
@SpringBootTest
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private UserService userService;

    @Test
    void saveUser() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");
        userDTO.setId(10L);
        userDTO.setAge(25);
        userDTO.setUsername("username");

        Mockito.when(userService.saveUser(userDTO)).thenReturn(userDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void updateUserBio() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");
        userDTO.setId(10L);
        userDTO.setAge(25);
        userDTO.setUsername("username");

        mockMvc.perform(MockMvcRequestBuilders.post("/user-bio")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService).updateUserBio(userDTO);
    }

    @Test
    void getUserInfo() throws Exception {
        Long id = 25L;

        Mockito.when(userService.getSummary(id)).thenReturn(null);
        mockMvc.perform(MockMvcRequestBuilders.get("/summary")
                        .queryParam("id", String.valueOf(id)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void userLogin() throws Exception {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("email");
        userDTO.setId(10L);
        userDTO.setAge(25);
        userDTO.setUsername("username");

        mockMvc.perform(MockMvcRequestBuilders.post("/user-login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsBytes(userDTO)))
                .andExpect(MockMvcResultMatchers.status().isOk());

        Mockito.verify(userService).checkIsUserAbleToLogin(userDTO);
    }
}