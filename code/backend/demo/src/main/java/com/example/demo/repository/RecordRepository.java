package com.example.demo.repository;

import java.util.List;
import com.example.demo.model.Record;
import com.example.demo.model.RecordPrimaryKey;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */

public interface RecordRepository extends JpaRepository<Record, RecordPrimaryKey> {
    List<Record> findAllByRecordPrimaryKey_User_Id(Long userId);
}
