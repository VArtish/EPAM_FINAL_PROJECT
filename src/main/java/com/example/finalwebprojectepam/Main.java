package com.example.finalwebprojectepam;

import com.example.finalwebprojectepam.exception.ServiceException;
import com.example.finalwebprojectepam.service.impl.MediaServiceImpl;

public class Main {
    public static void main(String[] args) throws ServiceException {
        MediaServiceImpl mediaService = MediaServiceImpl.getInstance();
        String im =  mediaService.getPreviewPhoto("wfjshbaojcuab91pkbb2");
        System.out.println();
    }
}
