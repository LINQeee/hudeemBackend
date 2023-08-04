package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import vit.projects.hudeem.entities.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
