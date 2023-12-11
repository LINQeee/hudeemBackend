package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vit.projects.hudeem.entities.GoalEntity;

@Repository
public interface GoalRepository extends CrudRepository<GoalEntity, Long> {
}
