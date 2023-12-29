package vit.projects.hudeem.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vit.projects.hudeem.entities.IpEntity;

import java.util.Optional;

@Repository
public interface IpRepository extends CrudRepository<IpEntity, Long> {

    Optional<IpEntity> findByUserIdAndIp(Long userId, String ip);
}
