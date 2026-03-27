package com.Maarten0162.ProgressPicBackend.service;

import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.util.Map;
import java.util.List;
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

    public String[] GetAllImagesOfUser(String id) throws Exception {
        // Get all images in the user's folder
        List<Map<String, String>> list = getImagesInFolder(id);

        // Initialize a list to store the URLs
        List<String> urls = new ArrayList<>();

        for (Map<String, String> map : list) {
            urls.add(map.get("url")); // get the secure URL
        }

        // Convert List<String> to String[]
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
