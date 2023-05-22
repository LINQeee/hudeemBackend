package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vit.projects.hudeem.entities.UserEntity;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
