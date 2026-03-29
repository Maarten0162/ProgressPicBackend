package com.Maarten0162.ProgressPicBackend.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Maarten0162.ProgressPicBackend.DAL.RecordRepo;
import com.Maarten0162.ProgressPicBackend.model.Record;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepo repo;
    private final CloudinaryService cloudinaryService;

    public List<Record> getAllImagesOfUser(UUID userUUID){
        return repo.findByUserUUID(userUUID);
    }

    @Transactional(rollbackFor = Exception.class)
    public Record createRecord(UUID userUUID, MultipartFile front, MultipartFile side, MultipartFile back) throws Exception{

        try {
            Record record = new Record();
            record.setUserUUID(userUUID);
            if (!front.isEmpty()){
                Map result = cloudinaryService.uploadImage(userUUID, front);
                
                record.setFrontImageUrl(result.get("secure_url").toString());
                record.setFrontImageId(result.get("public_id").toString());
            }
            if (!back.isEmpty()){
                Map result = cloudinaryService.uploadImage(userUUID, back);
                
                record.setBackImageUrl(result.get("secure_url").toString());
                record.setBackImageId(result.get("public_id").toString());
            }
            if (!side.isEmpty()){
                Map result = cloudinaryService.uploadImage(userUUID, side);
                
                record.setSideImageUrl(result.get("secure_url").toString());
                record.setSideImageId(result.get("public_id").toString());
            }
            return repo.save(record);
        } catch (Exception e) {
            throw new Exception("An Error Occured: " + e);
        }
    }
}
