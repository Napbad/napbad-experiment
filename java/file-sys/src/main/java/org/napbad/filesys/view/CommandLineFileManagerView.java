package org.napbad.filesys.view;

import org.napbad.filesys.controller.FileManageController;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CommandLineFileManagerView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final FileManageController fileManager = new FileManageController();

    public void run() {
        while (true) {
            System.out.println("\n ======== 欢迎使用文件管理系统！ ========");
            System.out.println("1. 设置当前工作文件夹");
            System.out.println("2. 罗列当前文件夹内容");
            System.out.println("3. 查看文件内容");
            System.out.println("4. 拷贝文件/文件夹");
            System.out.println("5. 加密文件");
            System.out.println("6. 解密文件");
            System.out.println("7. 压缩文件");
            System.out.println("8. 解压文件");
            System.out.println("9. 退出");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("请输入工作文件夹路径: ");
                    String path = scanner.nextLine();
                    if (path.isEmpty()) {
                        path = System.getProperty("user.dir");
                        System.out.println("当前工作文件夹已设置为默认路径: " + path);
                    }
                    fileManager.setWorkingDirectory(path);
                    System.out.println("当前工作文件夹已设置为: " + path);
                    break;
                case 2:
                    System.out.print("请输入过滤条件 (文件名/大小/类型/日期): ");
                    String filter = scanner.nextLine();
                    System.out.print("请输入排序方式 (名称/大小/时间): ");
                    String sortBy = scanner.nextLine();
                    List<java.io.File> files = fileManager.listFiles(filter, sortBy);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    System.out.printf("\n%-30s %-15s %-25s%n", "文件名", "大小 (字节)", "最后修改时间");
                    System.out.println("------------------------------------------------------------------------");
                    for (File file : files) {
                        String name = file.getName();
                        long size = file.length();
                        String lastModified = dateFormat.format(new java.util.Date(file.lastModified()));
                        System.out.printf("%-30s %-15d %-25s%n", name, size, lastModified);
                    }
                    break;
                case 3:
                    System.out.print("请输入文件路径: ");
                    String filePath = scanner.nextLine();
                    fileManager.viewFileContent(filePath);
                    break;
                case 4:
                    System.out.print("请输入源文件/文件夹路径: ");
                    String sourcePath = scanner.nextLine();
                    System.out.print("请输入目标文件/文件夹路径: ");
                    String targetPath = scanner.nextLine();
                    fileManager.copyFileOrDirectory(sourcePath, targetPath);
                    break;
                case 5:
                    System.out.print("请输入源文件路径: ");
                    String sourcePathForEncrypt = scanner.nextLine();
                    System.out.print("请输入目标文件路径: ");
                    String targetPathForEncrypt = scanner.nextLine();
                    fileManager.encryptFile(sourcePathForEncrypt, targetPathForEncrypt);
                    break;
                case 6:
                    System.out.print("请输入源文件路径: ");
                    String sourcePathForDecrypt = scanner.nextLine();
                    System.out.print("请输入目标文件路径: ");
                    String targetPathForDecrypt = scanner.nextLine();
                    fileManager.decryptFile(sourcePathForDecrypt, targetPathForDecrypt);
                    break;
                case 7:
                    System.out.print("请输入文件路径 (多个文件用逗号分隔): ");
                    String filePathsStr = scanner.nextLine();
                    List<String> filePaths = new ArrayList<>();
                    Collections.addAll(filePaths, filePathsStr.split(","));
                    System.out.print("请输入目标压缩文件路径: ");
                    String targetPathForCompress = scanner.nextLine();
                    fileManager.compressFiles(filePaths, targetPathForCompress);
                    break;
                case 8:
                    System.out.print("请输入压缩文件路径: ");
                    String sourcePathForDecompress = scanner.nextLine();
                    System.out.print("请输入目标文件夹路径: ");
                    String targetPathForDecompress = scanner.nextLine();
                    fileManager.decompressFile(sourcePathForDecompress, targetPathForDecompress);
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("无效的选择，请重新输入。");
            }
        }
    }
}