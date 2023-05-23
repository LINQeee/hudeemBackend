package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.repositories.RecordRepository;
import vit.projects.hudeem.repositories.UserRepository;

import java.text.DecimalFormat;
import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final RecordMapper recordMapper;

    public RecordDTO saveRecord(RecordDTO recordDTO) {
        //get record entity for update
        RecordEntity recordEntity = recordMapper.fromDTO(recordDTO);
        //get associated user for update progress
        UserEntity userEntity = userRepository.findById(recordDTO.getUserId()).get();
        updateProgress(userEntity, recordEntity);
        //set user to record and save both entities
        recordEntity.setUser(userEntity);
        RecordEntity saved = recordRepository.save(recordEntity);
        //convert saved record to dto
        RecordDTO savedDTO = recordMapper.toDTO(saved);
        //set userId to record dto
        savedDTO.setUserId(saved.getUser().getId());
        return savedDTO;
    }

    private void updateProgress(UserEntity userEntity, RecordEntity recordEntity) {
        //calculate progress only if record is new
        if (!recordEntity.getDate().isBefore(getLatestRecord(userEntity).getDate())) {
            double current = userEntity.getInitialWeight() - recordEntity.getCurrentWeight();
            double total = userEntity.getInitialWeight() - userEntity.getGoalWeight();
            double progress = current / total;
            String formattedProgress = new DecimalFormat("#.##").format(progress);
            userEntity.setProgress(Double.parseDouble(formattedProgress));
            //userRepository.save(userEntity);
        }
    }

    private RecordEntity getLatestRecord(UserEntity userEntity) {
        return userEntity.getRecords()
                .stream()
                .max(Comparator.comparing(RecordEntity::getDate))
                .get();
    }
}
