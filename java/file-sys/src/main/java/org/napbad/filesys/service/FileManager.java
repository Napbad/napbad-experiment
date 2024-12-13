package org.napbad.filesys.service;

import java.io.File;
import java.util.List;

public interface FileManager {
    void setWorkingDirectory(String path);
    List<File> listFiles(String filter, String sortBy);
    void viewFileContent(String filePath);
    void copyFile(String sourcePath, String targetPath);
    void copyDirectory(String sourcePath, String targetPath);
    void encryptFile(String sourcePath, String targetPath);
    void decryptFile(String sourcePath, String targetPath);
    void compressFiles(List<String> filePaths, String targetPath);
    void decompressFile(String sourcePath, String targetPath);
}
