package vit.projects.hudeem.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vit.projects.hudeem.entities.UserEntity;

import java.util.List;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "records")
    List<UserEntity> findAll();
}
