package com.Maarten0162.ProgressPicBackend.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


@Service
public class CloudinaryService {
    private final Cloudinary cloudinary;

    public CloudinaryService(Cloudinary cloudinary) {
        this.cloudinary = cloudinary; // Spring injects it automatically
    }

    public String test() {
        return cloudinary.config.cloudName; // example usage
    }

    public String GetImage() {
        return cloudinary.url()
                .format("jpg") // optional: force format
                .generate("2fe47cc7-e8fe-494d-aec6-a7d1271c61f9_nttqet");
    }

    public Map uploadImage(UUID userUUID, MultipartFile file) throws Exception{

        if (file.isEmpty()) throw new Exception("Image Does not exist");

        // Convert MultipartFile to java.io.File
        File tempFile = File.createTempFile("upload-", file.getOriginalFilename());
        file.transferTo(tempFile);

        try {
            return cloudinary.uploader().upload(
                tempFile,
                Map.of("folder", userUUID.toString())
            );
        } catch (IOException e) {
            throw new RuntimeException("Upload failed", e);
        }
    }

    public String[] GetAllImagesOfUser(String id) throws Exception {

        List<Map<String, String>> list = getImagesInFolder(id);

        List<String> urls = new ArrayList<>();

        for (Map<String, String> map : list) {
            urls.add(map.get("url"));
        }
        return urls.toArray(new String[0]);
    }


    public List<Map<String, String>> getImagesInFolder(String folder) throws Exception {
        Map result = cloudinary.api().resources(ObjectUtils.asMap(
            "type", "upload",
            "prefix", folder + "/",
            "max_results", 100
        ));
        List<Map> resources = (List<Map>) result.get("resources");
        List<Map<String, String>> images = new ArrayList<>();
        for (Map r : resources) {
            images.add(Map.of(
                "publicId", (String) r.get("public_id"),
                "url", (String) r.get("secure_url")
            ));
        }
        return images;
    }
}
