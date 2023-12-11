package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.repositories.RecordRepository;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class MetricService {

    private double getPerDay(RecordEntity latestRecord) {
        GoalEntity goalEntity = latestRecord.getGoal();
        long duration = DAYS.between(goalEntity.getStartDate(), latestRecord.getDate());
        if (duration <= 0) duration = 1;
        double weightDiff = goalEntity.getInitialWeight() - getCurrentWeight(latestRecord);
        double perDay = weightDiff / duration;
        return Double.parseDouble(new DecimalFormat("#.##").format(perDay).replace(",", "."));
    }

    private double getPerWeek(RecordEntity latestRecord) {
        double perWeek = getPerDay(latestRecord) * 7;
        return Double.parseDouble(new DecimalFormat("#.##").format(perWeek).replace(",", "."));
    }

    private LocalDate getPlannedDate(RecordEntity latestRecord) {
        double daysLeft = getWeightLeft(latestRecord) / getPerDay(latestRecord);
        return latestRecord.getDate().plusDays((long) daysLeft);
    }

    private double getCurrentWeight(RecordEntity latestRecord) {
        double current = latestRecord.getCurrentWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(current).replace(",", "."));
    }

    private double getWeightLost(RecordEntity latestRecord) {
        double lost = latestRecord.getGoal().getInitialWeight() - latestRecord.getCurrentWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(lost).replace(",", "."));
    }

    private double getWeightLeft(RecordEntity latestRecord) {
        double left = latestRecord.getCurrentWeight() - latestRecord.getGoal().getGoalWeight();
        return Double.parseDouble(new DecimalFormat("#.##").format(left).replace(",", "."));
    }

    private double getProgress(RecordEntity latestRecord) {
        GoalEntity goalEntity = latestRecord.getGoal();
        double current = goalEntity.getInitialWeight() - latestRecord.getCurrentWeight();
        double total = goalEntity.getInitialWeight() - goalEntity.getGoalWeight();
        double progress = current / total;
        return Double.parseDouble(new DecimalFormat("#.##").format(progress).replace(",", "."));
    }

    private int getPerDayProgress(RecordEntity latestRecord) {
        List<RecordEntity> recordsDayBefore = latestRecord.getGoal().getRecords().stream()
                .filter(record -> record.getDate().isBefore(latestRecord.getDate()))
                .sorted(Comparator.comparing(RecordEntity::getDate))
                .toList();

        if (recordsDayBefore.isEmpty()) return 0;
        else {
            double perDay = getPerDay(latestRecord);
            double perDayBefore = getPerDay(recordsDayBefore.get(recordsDayBefore.size() - 1));
            return (int) Math.round(perDay / perDayBefore * 100);
        }
    }

    private int getPerWeekProgress(RecordEntity latestRecord) {
        List<RecordEntity> recordsWeekBefore = latestRecord.getGoal().getRecords().stream()
                .filter(record -> record.getDate().isBefore(latestRecord.getDate().minusWeeks(1)))
                .sorted(Comparator.comparing(RecordEntity::getDate))
                .toList();

        if (recordsWeekBefore.isEmpty()) return 0;
        else {
            double perWeek = getPerWeek(latestRecord);
            double perWeekBefore = getPerWeek(recordsWeekBefore.get(recordsWeekBefore.size() - 1));
            return (int) Math.round(perWeek / perWeekBefore * 100);
        }
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
        goalEntity.setPerDayProgress(getPerDayProgress(recordEntity));
        goalEntity.setPerWeekProgress(getPerWeekProgress(recordEntity));
        return goalEntity;
    }

    public GoalEntity resetMetrics(GoalEntity goalEntity) {
        goalEntity.setCurrentWeight(goalEntity.getInitialWeight());
        goalEntity.setPerDay(0);
        goalEntity.setPerWeek(0);
        goalEntity.setPlannedDate(LocalDate.now());
        goalEntity.setProgress(0);
        goalEntity.setWeightLeft(goalEntity.getInitialWeight() - goalEntity.getGoalWeight());
        goalEntity.setWeightLost(0);
        goalEntity.setPerDayProgress(0);
        goalEntity.setPerWeekProgress(0);
        return goalEntity;
    }
}
