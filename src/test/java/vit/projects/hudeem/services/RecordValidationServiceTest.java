package vit.projects.hudeem.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecordValidationServiceTest {

    @Autowired
    private RecordValidationService recordValidationService;

    @Test
    void validate_Positive_Result() {
//        RecordEntity toSave = new RecordEntity();
//        toSave.setDate(LocalDate.now());
//        toSave.setCurrentWeight(25);
//        toSave.setId(10L);
//
//        RecordEntity recordEntity = new RecordEntity();
//        recordEntity.setDate(LocalDate.now().minusDays(1));
//        recordEntity.setCurrentWeight(25);
//        recordEntity.setId(10L);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setRecords(List.of(recordEntity));
//
//        toSave.setUser(userEntity);
//
//        assertDoesNotThrow(() -> recordValidationService.validate(toSave));
    }

    @Test
    void validate_Future_Date_Exception() {
//        RecordEntity toSave = new RecordEntity();
//        toSave.setDate(LocalDate.now().plusDays(2));
//        toSave.setCurrentWeight(25);
//        toSave.setId(10L);
//
//        RecordEntity recordEntity = new RecordEntity();
//        recordEntity.setDate(LocalDate.now().minusDays(1));
//        recordEntity.setCurrentWeight(25);
//        recordEntity.setId(10L);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setRecords(List.of(recordEntity));
//
//        toSave.setUser(userEntity);
//
//        Assertions.assertThatThrownBy(() -> recordValidationService.validate(toSave))
//                .isInstanceOf(ValidationException.class)
//                .hasMessage("Нельзя сохранить запись с датой в будущем")
//                .hasFieldOrPropertyWithValue("fieldType", InputFieldType.DATE);
    }

    @Test
    void validate_Wrong_Weight_Exception() {
//        RecordEntity toSave = new RecordEntity();
//        toSave.setDate(LocalDate.now());
//        toSave.setCurrentWeight(-25);
//        toSave.setId(10L);
//
//        RecordEntity recordEntity = new RecordEntity();
//        recordEntity.setDate(LocalDate.now().minusDays(1));
//        recordEntity.setCurrentWeight(25);
//        recordEntity.setId(10L);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setRecords(List.of(recordEntity));
//
//        toSave.setUser(userEntity);
//
//        Assertions.assertThatThrownBy(() -> recordValidationService.validate(toSave))
//                .isInstanceOf(ValidationException.class)
//                .hasMessage("Введите корректное значение веса")
//                .hasFieldOrPropertyWithValue("fieldType", InputFieldType.WEIGHT);
    }

    @Test
    void validate_Existing_Date_Exception() {
//        RecordEntity toSave = new RecordEntity();
//        toSave.setDate(LocalDate.now());
//        toSave.setCurrentWeight(25);
//        toSave.setId(10L);
//
//        RecordEntity firstRecord = new RecordEntity();
//        firstRecord.setDate(LocalDate.now().minusDays(1));
//        firstRecord.setCurrentWeight(25);
//        firstRecord.setId(10L);
//
//        RecordEntity secondRecord = new RecordEntity();
//        secondRecord.setDate(LocalDate.now());
//        secondRecord.setCurrentWeight(25);
//        secondRecord.setId(10L);
//
//        UserEntity userEntity = new UserEntity();
//        userEntity.setRecords(List.of(firstRecord, secondRecord));
//
//        toSave.setUser(userEntity);
//
//        Assertions.assertThatThrownBy(() -> recordValidationService.validate(toSave))
//                .isInstanceOf(ValidationException.class)
//                .hasMessage("Запись с указанной датой уже существует")
//                .hasFieldOrPropertyWithValue("fieldType", InputFieldType.DATE);
    }
}