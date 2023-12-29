package vit.projects.hudeem.services;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecordServiceTest {
//
//    @Autowired
//    private RecordService recordService;
//    @MockBean
//    private RecordMapper recordMapper;
//    @MockBean
//    private RecordValidationService recordValidationService;
//    @MockBean
//    private RecordRepository recordRepository;
//    @MockBean
//    private MetricService metricService;
//    @MockBean
//    private UserRepository userRepository;
//
//    @Test
//    void saveRecord() {
////        RecordDTO recordDTO = new RecordDTO();
////        recordDTO.setUserId(10L);
////        recordDTO.setCurrentWeight(15);
////
////        UserEntity userEntity = new UserEntity();
////        userEntity.setId(10L);
////        userEntity.setCurrentWeight(20);
////
////        RecordEntity firstRecord = new RecordEntity();
////        firstRecord.setDate(LocalDate.now().minusDays(2));
////
////        RecordEntity secondRecord = new RecordEntity();
////        secondRecord.setDate(LocalDate.now());
////
////        userEntity.setRecords(List.of(firstRecord, secondRecord));
////
////        RecordEntity recordEntity = new RecordEntity();
////        recordEntity.setUser(userEntity);
////        recordEntity.setCurrentWeight(15);
////
////        Mockito.when(recordMapper.fromDTO(recordDTO)).thenReturn(recordEntity);
////
////        String result = recordService.saveRecord(recordDTO);
////        Assertions.assertThat(result).isEqualTo("successful");
////
////        Mockito.verify(recordValidationService).validate(recordEntity);
////        Mockito.verify(recordRepository).save(recordEntity);
////        Mockito.verify(metricService).getUpdatedWithAllMetrics(secondRecord);
//    }
//
//    @Test
//    void deleteRecord() {
////        Long id = 10L;
////
////        RecordEntity recordEntity = new RecordEntity();
////        recordEntity.setId(id);
////        recordEntity.setDate(LocalDate.now());
////
////        UserEntity userEntity = new UserEntity();
////        userEntity.setCurrentWeight(50);
////        userEntity.setId(22L);
////        userEntity.setRecords(List.of(recordEntity));
////
////        recordEntity.setUser(userEntity);
////        Mockito.when(recordRepository.findById(id)).thenReturn(Optional.of(recordEntity));
////
////        String result = recordService.deleteRecord(id);
////
////        Assertions.assertThat(result).isEqualTo("Запись успешно удалена!");
////        Mockito.verify(metricService).getUpdatedWithAllMetrics(recordEntity);
////        Mockito.verify(recordRepository).deleteById(id);
//    }
}