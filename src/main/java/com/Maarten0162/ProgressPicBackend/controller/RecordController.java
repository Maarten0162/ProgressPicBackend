package com.Maarten0162.ProgressPicBackend.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import com.Maarten0162.ProgressPicBackend.service.RecordService;
import com.Maarten0162.ProgressPicBackend.model.Record;

@RestController
@RequestMapping("/api/record")
public class RecordController {

    private final RecordService service;

    public RecordController(RecordService RecService){
        this.service = RecService;
    }


    @GetMapping("/{uuid}")
    public List<Record> getAllImagesById(@PathVariable UUID uuid){
        return service.getAllImagesOfUser(uuid);
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Record createRecord(
        @RequestParam("userUUID") UUID userUUID,
        @RequestParam(value = "date", required = false) String dateString,
        @RequestParam(value = "front", required = false) MultipartFile front,
        @RequestParam(value = "side", required = false) MultipartFile side,
        @RequestParam(value = "back", required = false) MultipartFile back
        ) throws Exception {
            
        return service.createRecord(userUUID, front, side, back, dateString);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Record updateRecord(
        @PathVariable Long id,
        @RequestParam("userUUID") UUID userUUID,
        @RequestParam("front") MultipartFile front,
        @RequestParam("side") MultipartFile side,
        @RequestParam("back") MultipartFile back
    ) throws Exception{
        return service.updateRecord(id, userUUID, front, side, back);
    }

    
}
