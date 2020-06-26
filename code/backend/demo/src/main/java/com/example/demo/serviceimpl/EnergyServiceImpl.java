package com.example.demo.serviceimpl;

import java.sql.Timestamp;
import java.util.List;
import com.example.demo.message.response.EnergyReport;
import com.example.demo.model.Record;
import com.example.demo.repository.RecordRepository;
import com.example.demo.service.EnergyService;
import com.example.demo.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */
@Service
public class EnergyServiceImpl implements EnergyService {

    private static final double ENERGY_PER_MOVE = 0.035835;

    private final RecordRepository recordRepository;

    @Autowired
    public EnergyServiceImpl(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    @Override
    public EnergyReport getEnergyReport(Long userId) {
        List<Record> records = recordRepository.findAllByRecordPrimaryKey_User_Id(userId);
        Timestamp today = new Timestamp(DateUtil.getTodayStartTime());
        double total = 0;
        for(Record record:records) {
            if(record.getRecordPrimaryKey().getTimestamp().after(today)) {
                total += record.getScore() * ENERGY_PER_MOVE;
            }
        }

        return new EnergyReport(total);
    }
}
