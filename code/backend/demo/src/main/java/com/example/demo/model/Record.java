package com.example.demo.model;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.util.Objects;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */
@Entity
public class Record {

    @EmbeddedId
    private RecordPrimaryKey recordPrimaryKey;

    private double score;

    private SportType type;

    public RecordPrimaryKey getRecordPrimaryKey() {
        return recordPrimaryKey;
    }

    public void setRecordPrimaryKey(RecordPrimaryKey recordPrimaryKey) {
        this.recordPrimaryKey = recordPrimaryKey;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public SportType getType() {
        return type;
    }

    public void setType(SportType type) {
        this.type = type;
    }

    public Record() {
    }

    public Record(RecordPrimaryKey recordPrimaryKey, double score, SportType type) {
        this.recordPrimaryKey = recordPrimaryKey;
        this.score = score;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return recordPrimaryKey.equals(record.recordPrimaryKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recordPrimaryKey);
    }
}
