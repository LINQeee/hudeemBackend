package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.GoalDTO;
import vit.projects.hudeem.entities.GoalEntity;
import vit.projects.hudeem.mappers.GoalMapper;
import vit.projects.hudeem.repositories.GoalRepository;

import java.time.LocalDate;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;
    private final RecordService recordService;

    public void saveGoal(GoalDTO goalDTO) {
        GoalEntity goalEntity = goalMapper.fromDTO(goalDTO);
        goalEntity.setCurrentWeight(goalEntity.getInitialWeight());
        goalEntity.setStartDate(LocalDate.now());
        goalRepository.save(goalEntity);
    }

    public void deleteGoal(Long goalId) {
        recordService.deleteRecordsByGoalId(goalId);
        goalRepository.deleteById(goalId);
    }

    public Optional<GoalDTO> getGoalDTOById(Long goalId) {
        return Optional.ofNullable(goalMapper.toDTO(goalRepository.findById(goalId).orElse(null)));
    }
}
