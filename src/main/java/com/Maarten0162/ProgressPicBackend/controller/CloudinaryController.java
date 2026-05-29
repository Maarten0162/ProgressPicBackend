package com.Maarten0162.ProgressPicBackend.controller;

import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Maarten0162.ProgressPicBackend.service.CloudinaryService;


@RestController
@RequestMapping("/api/cloudinary")
public class CloudinaryController {

    private final CloudinaryService cloudinaryService;

    // ✅ Constructor injection — Spring automatically injects the service
    public CloudinaryController(CloudinaryService cloudinaryService) {
        this.cloudinaryService = cloudinaryService;
    }
    
    @GetMapping("/")
    public String test(){
        return cloudinaryService.test();
    }

    @GetMapping("/getImage/")
    public String getImage(){
        return cloudinaryService.GetImage();
    }

    @GetMapping("/getImageOfUser/{userId}")
    public String[] getImageOfUser(@PathVariable String userId) {
        try {
            return cloudinaryService.GetAllImagesOfUser(userId);
        } catch (Exception e) {
            e.printStackTrace();
            return new String[] { "ERROR!: " + e}; 
        }
    }

    @PostMapping()
    public Map uploadImage(
        @RequestParam("userUUID") UUID userUUID,
        @RequestParam("front") MultipartFile front
        ) throws Exception {
        return cloudinaryService.uploadImage(userUUID, front);
    }
    
}
