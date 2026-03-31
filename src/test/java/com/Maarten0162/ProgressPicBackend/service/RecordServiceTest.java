package com.Maarten0162.ProgressPicBackend.service;


import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.Maarten0162.ProgressPicBackend.DAL.RecordRepo;
import com.Maarten0162.ProgressPicBackend.model.Record;


@ExtendWith(MockitoExtension.class)
public class RecordServiceTest {

    @Mock
    private RecordRepo repo;

    @InjectMocks
    private RecordService service;

    @Test
    void shouldReturnAllImagesOfUser() {
        // Arrange
        UUID userId = UUID.randomUUID();

        Record record1 = new Record();
        Record record2 = new Record();
        List<Record> mockRecords = List.of(record1, record2);

        when(repo.findByUserUUID(userId)).thenReturn(mockRecords);

        // Act
        List<Record> result = service.getAllImagesOfUser(userId);

        // Assert
        assertEquals(2, result.size());
        assertTrue(result.contains(record1));
        assertTrue(result.contains(record2));
    }
}
