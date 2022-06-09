package com.example.finalwebprojectepam.service;

import com.example.finalwebprojectepam.exception.ServiceException;

public interface MediaService {
    String storePhoto(String filename) throws ServiceException;

    String getPreviewPhoto(String publicId);
}
