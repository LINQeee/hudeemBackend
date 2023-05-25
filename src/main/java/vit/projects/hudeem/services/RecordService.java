package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import vit.projects.hudeem.dto.RecordDTO;
import vit.projects.hudeem.entities.RecordEntity;
import vit.projects.hudeem.entities.UserEntity;
import vit.projects.hudeem.mappers.RecordMapper;
import vit.projects.hudeem.repositories.RecordRepository;

import java.text.DecimalFormat;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepository recordRepository;
    private final RecordMapper recordMapper;
    private final MetricService metricService;

    public String saveRecord(RecordDTO recordDTO) {
        RecordEntity recordEntity = recordMapper.fromDTO(recordDTO);
        UserEntity userEntity = recordEntity.getUser();
        if (!recordEntity.getDate().isBefore(getLatestRecord(userEntity).getDate())) {
            recordEntity = metricService.getUpdatedWithAllMetrics(recordEntity);
        }
        recordRepository.save(recordEntity);
        return "successful";
    }

    private RecordEntity getLatestRecord(UserEntity userEntity) {
        return userEntity.getRecords()
                .stream()
                .max(Comparator.comparing(RecordEntity::getDate))
                .get();
    }

    public String saveRecordList(List<RecordDTO> recordDTOList) {
        recordDTOList.forEach(this::saveRecord);
        return "successful";
    }
}
