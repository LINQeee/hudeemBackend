package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class MetricService {
    private double getPerDay(RecordEntity latestRecord) {
        UserEntity userEntity = latestRecord.getUser();
        long duration = DAYS.between(userEntity.getStartDate(), latestRecord.getDate());
        double weightDiff = userEntity.getInitialWeight() - getCurrentWeight(latestRecord);
        double perDay = weightDiff / duration;
        return Double.parseDouble(new DecimalFormat("#.##").format(perDay));
    }

    private double getPerWeek(RecordEntity recordEntity) {
        double perWeek = getPerDay(recordEntity) * 7;
        return Double.parseDouble(new DecimalFormat("#.##").format(perWeek));
    }

    private LocalDate getPlannedDate(RecordEntity recordEntity) {
        double daysLeft = getWeightLeft(recordEntity) / getPerDay(recordEntity);
        return recordEntity.getDate().plusDays((long) daysLeft);
    }

    private double getCurrentWeight(RecordEntity recordEntity) {
        double current = recordEntity.getCurrentWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(current));
    }

    private double getWeightLost(RecordEntity recordEntity) {
        double lost = recordEntity.getUser().getInitialWeight() - recordEntity.getCurrentWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(lost));
    }

    private double getWeightLeft(RecordEntity recordEntity) {
        double left = recordEntity.getCurrentWeight() - recordEntity.getUser().getGoalWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(left));
    }

    private double getProgress(RecordEntity recordEntity) {
        UserEntity userEntity = recordEntity.getUser();
        double current = userEntity.getInitialWeight() - recordEntity.getCurrentWeight();
        double total = userEntity.getInitialWeight() - userEntity.getGoalWeight();
        double progress = current / total;
        return Double.parseDouble(new DecimalFormat("#.##").format(progress));

    }

    public UserEntity getUpdatedWithAllMetrics(RecordEntity recordEntity) {
        UserEntity userEntity = recordEntity.getUser();
        userEntity.setProgress(getProgress(recordEntity));
        userEntity.setPerDay(getPerDay(recordEntity));
        userEntity.setPerWeek(getPerWeek(recordEntity));
        userEntity.setPlannedDate(getPlannedDate(recordEntity));
        userEntity.setCurrentWeight(getCurrentWeight(recordEntity));
        userEntity.setWeightLost(getWeightLost(recordEntity));
        userEntity.setWeightLeft(getWeightLeft(recordEntity));
        return userEntity;
    }
}
