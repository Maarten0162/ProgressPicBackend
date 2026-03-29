package com.Maarten0162.ProgressPicBackend.DAL;

import com.Maarten0162.ProgressPicBackend.model.Record;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;


public interface RecordRepo extends JpaRepository<Record, Long> {
    List<Record> findByUserUUID(UUID userUUID);
}
