package org.napbad.filesys.service.impl;

public class AsyncFileManager extends DefaultFileManager {
    @Override
    public void copyFile(String sourcePath, String targetPath) {
        new Thread(() -> super.copyFile(sourcePath, targetPath)).start();
    }

    @Override
    public void copyDirectory(String sourcePath, String targetPath) {
        new Thread(() -> super.copyDirectory(sourcePath, targetPath)).start();
    }
}
