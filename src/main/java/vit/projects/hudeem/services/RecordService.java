package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.repositories.RecordRepository;
import vit.projects.hudeem.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;

    public RecordEntity saveRecord(RecordEntity recordEntity, Long userId) {
        UserEntity userEntity = userRepository.findById(userId).get();
        recordEntity.setUser(userEntity);
        RecordEntity saved = recordRepository.save(recordEntity);
        //calculating and saving progress
        double current = userEntity.getInitialWeight() - recordEntity.getCurrentWeight();
        double total = userEntity.getInitialWeight() - userEntity.getGoalWeight();
        double progress = current / total;
        userEntity.setProgress(progress);
        userRepository.save(userEntity);
        return saved;
    }
}
