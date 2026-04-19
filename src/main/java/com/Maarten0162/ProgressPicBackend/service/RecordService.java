package com.Maarten0162.ProgressPicBackend.service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.Maarten0162.ProgressPicBackend.DAL.RecordRepo;
import com.Maarten0162.ProgressPicBackend.model.Image;
import com.Maarten0162.ProgressPicBackend.model.ImageType;
import com.Maarten0162.ProgressPicBackend.model.Record;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class RecordService {
    private final RecordRepo repo;
    private final CloudinaryService cloudinaryService;

    public List<Record> getAllImagesOfUser(UUID userUUID){
        return repo.findByUserUUIDOrderByDateDesc(userUUID);
    }

    @Transactional(rollbackFor = Exception.class)
    public Record createRecord(UUID userUUID, MultipartFile front, MultipartFile side, MultipartFile back) throws Exception{

        try {
            Record record = new Record();
            record.setUserUUID(userUUID);
            if (!front.isEmpty()){
                record.addImage(UploadImage(userUUID, front, ImageType.FRONT));
            }
            if (!back.isEmpty()){
                record.addImage(UploadImage(userUUID, back, ImageType.BACK));
            }
            if (!side.isEmpty()){
                record.addImage(UploadImage(userUUID, side, ImageType.SIDE));
            }
            return repo.save(record);
        } catch (Exception e) {
            throw new Exception("An Error Occured: " + e);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public Record updateRecord(Long id, UUID userUUID, MultipartFile front, MultipartFile side, MultipartFile back) throws Exception{

        try {
            Record OGrecord = repo.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));

            //TODO needs some handeling for non replaced images so no duplicate uploads upon edit

            Record record = new Record();
            record.setUserUUID(userUUID);
            if (!front.isEmpty()){
                record.addImage(UploadImage(userUUID, front, ImageType.FRONT));
            }
            if (!back.isEmpty()){
                record.addImage(UploadImage(userUUID, back, ImageType.BACK));
            }
            if (!side.isEmpty()){
                record.addImage(UploadImage(userUUID, side, ImageType.SIDE));
            }

            OGrecord.setImages(record.getImages());
            return repo.save(OGrecord);
        } catch (Exception e) {
            throw new Exception("An Error Occured: " + e);
        }
    }

    private Image UploadImage(UUID userUUID, MultipartFile image, ImageType type) throws Exception{
        Map result = cloudinaryService.uploadImage(userUUID, image);
        Image img = new Image();
        img.setImageId(result.get("public_id").toString());
        img.setImageUrl(result.get("secure_url").toString());
        img.setType(type);

        return img;
    }
}
