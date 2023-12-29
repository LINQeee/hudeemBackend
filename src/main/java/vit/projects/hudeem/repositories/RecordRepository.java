package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vit.projects.hudeem.entities.RecordEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecordRepository extends CrudRepository<RecordEntity, Long> {
    Optional<List<RecordEntity>> findAllByGoalId(Long goalId);
}
