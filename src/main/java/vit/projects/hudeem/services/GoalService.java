package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.GoalDTO;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.mappers.GoalMapper;
import vit.projects.hudeem.repositories.GoalRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final RecordService recordService;

    public String saveGoal(GoalDTO goalDTO) {
        GoalEntity goalEntity = goalMapper.fromDTO(goalDTO);
        goalEntity.setCurrentWeight(goalEntity.getInitialWeight());
        goalEntity.setStartDate(LocalDate.now());
        goalRepository.save(goalEntity);
        return "success";
    }

    public String deleteGoal(Long goalId) {
        recordService.deleteRecordsByGoalId(goalId);
        goalRepository.deleteById(goalId);
        return "success";
    }

    public GoalDTO getGoalDTOById(Long goalId) {
        return goalMapper.toDTO(goalRepository.findById(goalId).get());
    }
}
