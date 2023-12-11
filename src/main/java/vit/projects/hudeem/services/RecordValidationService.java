package vit.projects.hudeem.services;

import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.exceptions.ValidationException;
import vit.projects.hudeem.utils.InputFieldType;

import java.time.LocalDate;
import java.util.Objects;

@Service
public class RecordValidationService {
    private void validateFutureDate(RecordEntity toSave) {
        if (toSave.getDate().isAfter(LocalDate.now())) {
            throw new ValidationException("Нельзя использовать дату из будущего!", InputFieldType.DATE);
        }
    }

    private void validateExistingDate(RecordEntity toSave) {
        //no validation if we update record without date change
        if (toSave.getId() != null && toSave.getDate().equals(
                toSave.getGoal().getRecords()
                        .stream()
                        .filter(r -> Objects.equals(r.getId(), toSave.getId()))
                        .findFirst()
                        .get()
                        .getDate())) {
            return;
        }

        toSave.getGoal().getRecords()
                .stream()
                .filter(entity -> toSave.getDate().equals(entity.getDate()))
                .findAny()
                .ifPresent(entity -> {
                    throw new ValidationException("Запись с указанной датой уже существует!", InputFieldType.DATE);
                });
    }

    private void validateDateBeforeStart(RecordEntity toSave) {
        if (toSave.getDate().isBefore(toSave.getGoal().getStartDate())) {
            throw new ValidationException("Нельзя указать дату до старта цели!", InputFieldType.DATE);
        }
    }

    private void validateWeight(RecordEntity toSave) {
        if (toSave.getCurrentWeight() <= 0) {
            throw new ValidationException("Введите корректное значение веса!", InputFieldType.WEIGHT);
        }
    }

    public void validate(RecordEntity toSave) {
        validateWeight(toSave);
        validateFutureDate(toSave);
        validateExistingDate(toSave);
        validateDateBeforeStart(toSave);
    }
}
