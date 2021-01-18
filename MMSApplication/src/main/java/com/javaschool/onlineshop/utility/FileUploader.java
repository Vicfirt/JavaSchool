package com.javaschool.onlineshop.utility;

import com.javaschool.onlineshop.Exception.MyException;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class FileUploader {

    private static final String PATH =
            "C:\\Users\\IvaNi\\IdeaProjects\\TsystemsProject\\" +
                    "JavaSchool\\MMSApplication\\src\\main\\resources\\static\\images\\";

    public static void UploadFile(MultipartFile multipartFile, String id) {
        if (!new File(PATH).exists()) {
            new File(PATH).mkdirs();
        }

        try {
            multipartFile.transferTo(new File(PATH + id + ".jpg"));
        } catch (IllegalStateException e) {
            throw new MyException(91, "IllegalStateException");
        } catch (IOException e) {
            throw new MyException(90, "File not found");
        }
    }
}
