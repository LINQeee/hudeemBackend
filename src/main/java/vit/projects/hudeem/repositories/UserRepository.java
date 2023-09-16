package vit.projects.hudeem.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import vit.projects.hudeem.entities.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = "records")
    List<UserEntity> findAll();

    Optional<UserEntity> findByEmail(String email);
}
