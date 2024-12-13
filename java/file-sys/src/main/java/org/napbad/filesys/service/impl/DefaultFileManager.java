package org.napbad.filesys.service.impl;

import org.napbad.filesys.service.FileManager;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class DefaultFileManager implements FileManager {
    private File workingDirectory;

    @Override
    public void setWorkingDirectory(String path) {
        this.workingDirectory = new File(path);
    }

    @Override
    public List<File> listFiles(String filter, String sortBy) {
        File[] files = workingDirectory.listFiles();
        if (files == null) {
            return new ArrayList<>();
        }

        List<File> filteredFiles = new ArrayList<>();
        for (File file : files) {
            if (filter == null || filter.isEmpty() || file.getName().contains(filter)) {
                filteredFiles.add(file);
            }
        }

        if ("name".equalsIgnoreCase(sortBy)) {
            filteredFiles.sort(Comparator.comparing(File::getName));
        } else if ("size".equalsIgnoreCase(sortBy)) {
            filteredFiles.sort(Comparator.comparingLong(File::length));
        } else if ("date".equalsIgnoreCase(sortBy)) {
            filteredFiles.sort(Comparator.comparingLong(File::lastModified));
        }

        return filteredFiles;
    }

    @Override
    public void viewFileContent(String filePath) {
        File file = new File(workingDirectory + filePath);
        
        if (filePath.startsWith("/")) {
            file = new File(filePath);
        } else {
            file = new File(workingDirectory + "/" + filePath);
        }
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.println("无法读取文件: " + e.getMessage());
        }
    }

    @Override
    public void copyFile(String sourcePath, String targetPath) {
        sourcePath = sourcePath.startsWith("/") ? sourcePath : workingDirectory + "/" + sourcePath;
        targetPath = targetPath.startsWith("/") ? targetPath : workingDirectory + "/" + targetPath;
        File source = new File(sourcePath);
        File target = new File(targetPath);
        if (!target.exists()) {
            try {
                if (target.createNewFile()) {
                    System.out.println("目标文件创建成功:" + targetPath);
                }
            } catch (IOException e) {
                System.out.println("无法创建目标文件: " + e.getMessage());
            }
        }

        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(target)) {
            byte[] buffer = new byte[1024];
            int length;
            long startTime = System.currentTimeMillis();
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            long endTime = System.currentTimeMillis();
            out.flush();
            out.close();
            System.out.println("文件拷贝完成，耗时: " + (endTime - startTime) + "毫秒");
        } catch (IOException e) {
            System.err.println("文件拷贝失败: " + e.getMessage());
        }
    }

    @Override
    public void copyDirectory(String sourcePath, String targetPath) {

        sourcePath = sourcePath.startsWith("/") ? sourcePath : workingDirectory + "/" + sourcePath;
        targetPath = targetPath.startsWith("/") ? targetPath : workingDirectory + "/" + targetPath;

        File sourceDir = new File(sourcePath);
        File targetDir = new File(targetPath);

        if (!sourceDir.isDirectory()) {
            System.err.println("源路径不是一个文件夹");
            return;
        }

        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        File[] files = sourceDir.listFiles();
        if (files != null) {
            for (File file : files) {
                String targetFilePath = targetPath + File.separator + file.getName();
                if (file.isDirectory()) {
                    copyDirectory(file.getAbsolutePath(), targetFilePath);
                } else {
                    copyFile(file.getAbsolutePath(), targetFilePath);
                }
            }
        }
    }

    @Override
    public void encryptFile(String sourcePath, String targetPath) {
        // 这里使用简单的字节异或加密作为示例

        sourcePath = sourcePath.startsWith("/") ? sourcePath : workingDirectory + "/" + sourcePath;
        targetPath = targetPath.startsWith("/") ? targetPath : workingDirectory + "/" + targetPath;

        File source = new File(sourcePath);
        File target = new File(targetPath);

        try (FileInputStream in = new FileInputStream(source);
             FileOutputStream out = new FileOutputStream(target)) {
            int byteRead;
            while ((byteRead = in.read()) != -1) {
                out.write(byteRead ^ 0x55); // 使用0x55作为密钥
            }
        } catch (IOException e) {
            System.err.println("文件加密失败: " + e.getMessage());
        }
    }

    @Override
    public void decryptFile(String sourcePath, String targetPath) {
        sourcePath = sourcePath.startsWith("/") ? sourcePath : workingDirectory + "/" + sourcePath;
        targetPath = targetPath.startsWith("/") ? targetPath : workingDirectory + "/" + targetPath;
        // 解密逻辑与加密逻辑相同，因为使用的是简单的异或操作
        encryptFile(sourcePath, targetPath);
    }

    @Override
    public void compressFiles(List<String> filePaths, String targetPath) {

        targetPath = targetPath.startsWith("/") ? targetPath : workingDirectory + "/" + targetPath;

        File target = new File(targetPath);

        try {
            target.createNewFile();
        } catch (IOException e) {
            System.err.println("无法创建目标文件: " + e.getMessage());
            throw new RuntimeException(e);
        }

        try (ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(targetPath))) {
            for (String filePath : filePaths) {
                File file = new File(filePath);
                ZipEntry zipEntry = new ZipEntry(file.getName());
                zos.putNextEntry(zipEntry);

                try (FileInputStream fis = new FileInputStream(file)) {
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = fis.read(buffer)) > 0) {
                        zos.write(buffer, 0, length);
                    }
                }

                zos.closeEntry();
            }
        } catch (IOException e) {
            System.err.println("文件压缩失败: " + e.getMessage());
        }
    }

    @Override
    public void decompressFile(String sourcePath, String targetPath) {
        sourcePath = sourcePath.startsWith("/") ? sourcePath : workingDirectory + "/" + sourcePath;
        targetPath = targetPath.startsWith("/") ? targetPath : workingDirectory + "/" + targetPath;
        File target = new File(targetPath);
        if (!target.exists()) {
            target.mkdirs();
        }
        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(sourcePath))) {
            ZipEntry zipEntry;
            while ((zipEntry = zis.getNextEntry()) != null) {
                File targetFile = new File(targetPath, zipEntry.getName());
                if (zipEntry.isDirectory()) {
                    targetFile.mkdirs();
                } else {
                    try (FileOutputStream fos = new FileOutputStream(targetFile)) {
                        byte[] buffer = new byte[1024];
                        int length;
                        while ((length = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, length);
                        }
                    }
                }
                zis.closeEntry();
            }
        } catch (IOException e) {
            System.err.println("文件解压失败: " + e.getMessage());
        }
    }
}
