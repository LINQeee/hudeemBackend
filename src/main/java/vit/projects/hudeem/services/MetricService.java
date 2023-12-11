package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.entities.RecordEntity;

import java.text.DecimalFormat;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class MetricService {
    private double getPerDay(RecordEntity latestRecord) {
        GoalEntity goalEntity = latestRecord.getGoal();
        long duration = DAYS.between(goalEntity.getStartDate(), latestRecord.getDate());
        double weightDiff = goalEntity.getInitialWeight() - getCurrentWeight(latestRecord);
        double perDay = weightDiff / duration;

        return Double.parseDouble(new DecimalFormat("#.##").format(perDay).replace(",", "."));
    }

    private double getPerWeek(RecordEntity recordEntity) {
        double perWeek = getPerDay(recordEntity) * 7;
        return Double.parseDouble(new DecimalFormat("#.##").format(perWeek).replace(",", "."));
    }

    private LocalDate getPlannedDate(RecordEntity recordEntity) {
        double daysLeft = getWeightLeft(recordEntity) / getPerDay(recordEntity);
        return recordEntity.getDate().plusDays((long) daysLeft);
    }

    private double getCurrentWeight(RecordEntity recordEntity) {
        double current = recordEntity.getCurrentWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(current).replace(",", "."));
    }

    private double getWeightLost(RecordEntity recordEntity) {
        double lost = recordEntity.getGoal().getInitialWeight() - recordEntity.getCurrentWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(lost).replace(",", "."));
    }

    private double getWeightLeft(RecordEntity recordEntity) {
        double left = recordEntity.getCurrentWeight() - recordEntity.getGoal().getGoalWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(left).replace(",", "."));
    }

    private double getProgress(RecordEntity recordEntity) {
        GoalEntity goalEntity = recordEntity.getGoal();
        double current = goalEntity.getInitialWeight() - recordEntity.getCurrentWeight();
        double total = goalEntity.getInitialWeight() - goalEntity.getGoalWeight();
        double progress = current / total;
        return Double.parseDouble(new DecimalFormat("#.##").format(progress).replace(",", "."));

    }

    public GoalEntity getUpdatedWithAllMetrics(RecordEntity recordEntity) {
        GoalEntity goalEntity = recordEntity.getGoal();
        goalEntity.setProgress(getProgress(recordEntity));
        goalEntity.setPerDay(getPerDay(recordEntity));
        goalEntity.setPerWeek(getPerWeek(recordEntity));
        goalEntity.setPlannedDate(getPlannedDate(recordEntity));
        goalEntity.setCurrentWeight(getCurrentWeight(recordEntity));
        goalEntity.setWeightLost(getWeightLost(recordEntity));
        goalEntity.setWeightLeft(getWeightLeft(recordEntity));
        return goalEntity;
    }
}
