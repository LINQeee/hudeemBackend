package vit.projects.hudeem.services;

import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.exceptions.ValidationException;

import java.time.LocalDate;

@Service
public class RecordValidationService {
    private void validateFutureDate(RecordEntity toSave) {
        if (toSave.getDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Нельзя сохранить запись с датой в будущем");
        }
    }

    private void validateExistingDate(RecordEntity toSave) {
        //validation only for new records
        if (toSave.getId() != null) {
            return;
        }
        boolean isRecordDateExist = toSave.getUser().getRecords()
                .stream().
                anyMatch(entity -> toSave.getDate().equals(entity.getDate()));
        //new record cannot be saved with existing date
        if (isRecordDateExist) {
            throw new ValidationException("Запись с указанной датой уже существует");
        }
    }

    public void validate(RecordEntity toSave) {
        validateFutureDate(toSave);
        validateExistingDate(toSave);
    }
}
