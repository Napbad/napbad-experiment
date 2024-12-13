package org.napbad.filesys.controller;

import org.napbad.filesys.service.FileManager;
import org.napbad.filesys.service.impl.AsyncFileManager;

import java.io.File;
import java.util.List;

public class FileManageController {
    private final FileManager fileManager = new AsyncFileManager();

    public void setWorkingDirectory(String path) {
        fileManager.setWorkingDirectory(path);
    }

    public List<File> listFiles(String filter, String sortBy) {
        return fileManager.listFiles(filter, sortBy);
    }

    public void viewFileContent(String filePath) {
        fileManager.viewFileContent(filePath);
    }

    public void copyFileOrDirectory(String sourcePath, String targetPath) {
        if (new File(sourcePath).isDirectory()) {
            fileManager.copyDirectory(sourcePath, targetPath);
        } else {
            fileManager.copyFile(sourcePath, targetPath);
        }
    }

    public void encryptFile(String sourcePath, String targetPath) {
        fileManager.encryptFile(sourcePath, targetPath);
    }

    public void decryptFile(String sourcePath, String targetPath) {
        fileManager.decryptFile(sourcePath, targetPath);
    }

    public void compressFiles(List<String> filePaths, String targetPath) {
        fileManager.compressFiles(filePaths, targetPath);
    }

    public void decompressFile(String sourcePath, String targetPath) {
        fileManager.decompressFile(sourcePath, targetPath);
    }
}