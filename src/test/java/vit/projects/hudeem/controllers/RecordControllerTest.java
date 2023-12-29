package vit.projects.hudeem.controllers;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@AutoConfigureMockMvc
@SpringBootTest
class RecordControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//    @Autowired
//    ObjectMapper objectMapper;
//
//    @MockBean
//    RecordService recordService;
//
//    @Test
//    void saveRecord() throws Exception {
//        RecordDTO request = new RecordDTO();
//        request.setCurrentWeight(170);
//
//        Mockito.when(recordService.saveRecord(request))
//                .thenReturn("good");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/record")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsBytes(request)))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("good"));
//    }
//
//    @Test
//    void deleteRecord() throws Exception {
//        long id = 10L;
//
//        Mockito.when(recordService.deleteRecord(id)).thenReturn("success");
//
//        mockMvc.perform(MockMvcRequestBuilders.delete("/record?id=" + id))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("success"));
//    }
}