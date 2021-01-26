package com.javaschool.onlineshop.exception;

/**
 * This exception is thrown when the error occurs during file transfer.
 */
public class FileTransferException extends RuntimeException{

    public FileTransferException(String message){
        super(message);
    }
}
