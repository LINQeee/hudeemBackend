package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.entities.RecordEntity;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@RequiredArgsConstructor
public class MetricService {

    private final DecimalFormat twoDigitFormat = new DecimalFormat("#.##", DecimalFormatSymbols.getInstance(Locale.US));

    private double formatDoubleTwoDigit(double number) {
        return Double.parseDouble(twoDigitFormat.format(number));
    }


    private double getPerDay(RecordEntity latestRecord) {
        GoalEntity goalEntity = latestRecord.getGoal();
        long goalDuration = Math.max(DAYS.between(goalEntity.getStartDate(), latestRecord.getDate()), 1);
        double weightDiff = goalEntity.getInitialWeight() - getCurrentWeight(latestRecord);
        double perDay = weightDiff / goalDuration;
        return formatDoubleTwoDigit(perDay);
    }

    private double getPerWeek(RecordEntity latestRecord) {
        double perWeek = getPerDay(latestRecord) * 7;
        return formatDoubleTwoDigit(perWeek);
    }

    private LocalDate getPlannedDate(RecordEntity latestRecord) {
        double perDay = getPerDay(latestRecord);
        if (perDay <= 0) return null;
        double daysLeft = getWeightLeft(latestRecord) / getPerDay(latestRecord);
        return latestRecord.getDate().plusDays((long) daysLeft);
    }

    private double getCurrentWeight(RecordEntity latestRecord) {
        double current = latestRecord.getCurrentWeight();
        return formatDoubleTwoDigit(current);
    }

    private double getWeightLost(RecordEntity latestRecord) {
        double lost = latestRecord.getGoal().getInitialWeight() - latestRecord.getCurrentWeight();
        return formatDoubleTwoDigit(lost);
    }

    private double getWeightLeft(RecordEntity latestRecord) {
        double left = latestRecord.getCurrentWeight() - latestRecord.getGoal().getGoalWeight();
        return formatDoubleTwoDigit(left);
    }

    private double getProgress(RecordEntity latestRecord) {
        GoalEntity goalEntity = latestRecord.getGoal();
        double lost = goalEntity.getInitialWeight() - latestRecord.getCurrentWeight();
        double totalLeft = goalEntity.getInitialWeight() - goalEntity.getGoalWeight();
        double progress = lost / totalLeft;
        return formatDoubleTwoDigit(progress);
    }

    private int getPerDayProgress(RecordEntity latestRecord) {
        List<RecordEntity> recordsDayBeforeLatest = latestRecord.getGoal().getRecords().stream()
                .filter(record -> record.getDate().isBefore(latestRecord.getDate()))
                .sorted(Comparator.comparing(RecordEntity::getDate))
                .toList();

        return recordsDayBeforeLatest.stream()
                .max(Comparator.comparing(RecordEntity::getDate))
                .map(record -> (int) Math.round(getPerDay(latestRecord) / getPerDay(record) * 100))
                .orElse(0);
    }

    private int getPerWeekProgress(RecordEntity latestRecord) {
        List<RecordEntity> recordsWeekBeforeLatest = latestRecord.getGoal().getRecords().stream()
                .filter(record -> record.getDate().isBefore(latestRecord.getDate().minusWeeks(1)))
                .sorted(Comparator.comparing(RecordEntity::getDate))
                .toList();

        return recordsWeekBeforeLatest.stream()
                .max(Comparator.comparing(RecordEntity::getDate))
                .map(record -> (int) Math.round(getPerWeek(latestRecord) / getPerWeek(record) * 100))
                .orElse(0);
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
        goalEntity.setPlannedDate(goalEntity.getStartDate());
        goalEntity.setProgress(0);
        goalEntity.setWeightLeft(goalEntity.getInitialWeight() - goalEntity.getGoalWeight());
        goalEntity.setWeightLost(0);
        goalEntity.setPerDayProgress(0);
        goalEntity.setPerWeekProgress(0);
        return goalEntity;
    }
}
