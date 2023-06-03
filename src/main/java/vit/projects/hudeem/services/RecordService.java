package vit.projects.hudeem.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
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
        //update when new user (doesn't have any records) or the record is the latest
        if (CollectionUtils.isEmpty(userEntity.getRecords())
                || !recordEntity.getDate().isBefore(getLatestRecord(userEntity).getDate())) {
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

    public String deleteRecord(long id) {
        recordRepository.deleteById(id);
        return "successful";
    }
}
