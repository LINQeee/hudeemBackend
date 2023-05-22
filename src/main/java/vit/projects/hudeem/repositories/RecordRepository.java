package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import vit.projects.hudeem.entities.RecordEntity;

public interface RecordRepository extends CrudRepository<RecordEntity, Long> {
}
