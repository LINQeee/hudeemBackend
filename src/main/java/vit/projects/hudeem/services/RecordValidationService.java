package vit.projects.hudeem.services;

import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.exceptions.ValidationException;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class RecordValidationService {
    private void validateFutureDate(RecordEntity toSave) {
        if (toSave.getDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Нельзя сохранить запись с датой в будущем");
        }
    }

    private void validateExistingDate(RecordEntity toSave) {
        //no validation if we update record without date change
        if (toSave.getId() != null && toSave.getDate().equals(
                toSave.getUser().getRecords()
                        .stream()
                        .filter(r -> Objects.equals(r.getId(), toSave.getId()))
                        .findFirst()
                        .get()
                        .getDate()
        )) {
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

    private void validateWeight(RecordEntity toSave) {
        if (toSave.getCurrentWeight() <= 0) {
            throw new ValidationException("Вы ввели неверный вес");
        }
    }

    public void validate(RecordEntity toSave) {
        validateWeight(toSave);
        validateFutureDate(toSave);
        validateExistingDate(toSave);
    }
}
