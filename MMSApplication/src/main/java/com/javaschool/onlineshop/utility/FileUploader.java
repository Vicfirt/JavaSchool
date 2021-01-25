package com.javaschool.onlineshop.utility;

import com.javaschool.onlineshop.exception.FileUploadException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Component
public class FileUploader {

    private static String PATH;

    @Value("${path}")
    public void setPATH(String PATH) {
        FileUploader.PATH = PATH;
    }

    public static void uploadFile(MultipartFile multipartFile, String fileName) {
        try {
            multipartFile.transferTo(new File(PATH + fileName + ".jpg"));
        } catch (IOException e) {
            throw new FileUploadException("Error during file transfer. File name: " + fileName);
        }
    }
}
