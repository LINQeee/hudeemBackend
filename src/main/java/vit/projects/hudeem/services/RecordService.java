package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.repositories.RecordRepository;
import vit.projects.hudeem.repositories.UserRepository;

import java.util.Comparator;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final UserRepository userRepository;
    private final RecordMapper recordMapper;
    private final MetricService metricService;
    private final RecordValidationService recordValidationService;

    public String saveRecord(RecordDTO recordDTO) {
        RecordEntity recordEntity = recordMapper.fromDTO(recordDTO);
        recordValidationService.validate(recordEntity);
        recordRepository.save(recordEntity);
        updateMetricsAndSave(recordEntity.getUser());
        return "successful";
    }

    private RecordEntity getLatestRecord(UserEntity userEntity) {
        return userEntity.getRecords()
                .stream()
                .max(Comparator.comparing(RecordEntity::getDate))
                .get();
    }

    public String deleteRecord(long id) {
        UserEntity userEntity = recordRepository.findById(id).get().getUser();
        recordRepository.deleteById(id);
        updateMetricsAndSave(userEntity);
        return "Запись успешно удалена";
    }

    private void updateMetricsAndSave(UserEntity userEntity) {
        RecordEntity latestRecord = getLatestRecord(userEntity);
        UserEntity updatedUserEntity = metricService.getUpdatedWithAllMetrics(latestRecord);
        userRepository.save(updatedUserEntity);
    }
}