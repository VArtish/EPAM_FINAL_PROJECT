package com.example.finalwebprojectepam.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.service.MediaService;

import java.io.IOException;
import java.util.Map;

public class MediaServiceImpl implements MediaService  {
    private static final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "autoschrott",
            "api_key", "884862746761739",
            "api_secret", "_L51jlX9czVguOaBVTnpS_LLrvw",
            "secure", true));
    private static MediaServiceImpl instance;

    public static MediaServiceImpl getInstance() {
        if(instance == null) {
            instance = new MediaServiceImpl();
        }

        return instance;
    }

    public String storePhoto(String filename) throws ServiceException {
        try {
            Map<String, String> upload = cloudinary.uploader().upload(
                    filename,
                    ObjectUtils.emptyMap()
            );

            return upload.get("public_id");
        } catch (IOException e) {
            throw new ServiceException("File couldn't be stored through Cloudinary service", e);
        }
    }

    public String getPreviewPhoto(String publicId) {
        return cloudinary.url().transformation(new Transformation().width(100).height(75).crop("fill").gravity("center")).generate(publicId);
    }
}
