package com.javaschool.onlineshop.utility;

import com.javaschool.onlineshop.exception.FileTransferException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * This class is responsible for loading an image by a user.
 */
@Component
public class FileUploader {

    private static String PATH;

    @Value("${path}")
    public void setPATH(String PATH) {
        FileUploader.PATH = PATH;
    }

    /**
     * This method tries to transfer created image in specified directory.
     * @param multipartFile             file received from the form
     * @param fileName                  name to be assigned
     */
    public static void uploadFile(MultipartFile multipartFile, String fileName) {
        try {
            multipartFile.transferTo(new File(PATH + fileName + ".jpg"));
        } catch (IOException e) {
            throw new FileTransferException("Error during file transfer. File name: " + fileName);
        }
    }
}
