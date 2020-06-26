package com.example.demo.model;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * @author Zhe Li
 * @date 2020/06/26
 */

@Embeddable
public class RecordPrimaryKey implements Serializable {

    private static final Long serialVersionUID = 1L;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @JsonBackReference
    Timestamp timestamp;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public RecordPrimaryKey() {
    }

    public RecordPrimaryKey(User user, Timestamp timestamp) {
        this.user = user;
        this.timestamp = timestamp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecordPrimaryKey that = (RecordPrimaryKey) o;
        return user.equals(that.user) &&
                timestamp.equals(that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, timestamp);
    }
}
