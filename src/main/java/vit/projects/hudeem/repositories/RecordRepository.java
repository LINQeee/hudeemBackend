package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import vit.projects.hudeem.entities.RecordEntity;

import java.util.List;
import java.util.Optional;

public interface RecordRepository extends CrudRepository<RecordEntity, Long> {
    Optional<List<RecordEntity>> findAllByGoalId(Long goalId);
}
