package org.napbad.score.view;

import org.napbad.score.controller.MainController;

import java.util.Scanner;

public class MainView {

    private final MainController controller;
    private final TeachingClassView teachingClassView;

    public MainView(MainController controller, TeachingClassView teachingClassView) {
        this.controller = controller;
        this.teachingClassView = teachingClassView;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("主菜单:");
            System.out.println("1. 初始化数据");
            System.out.println("2. 学生选课");
            System.out.println("3. 生成成绩");
            System.out.println("4. 显示教学班");
            System.out.println("5. 显示教学班成绩");
            System.out.println("6. 显示学生成绩");
            System.out.println("7. 退出");
            System.out.print("请选择: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("请输入学生数量：");
                    int studentCount = scanner.nextInt();
                    System.out.println("请输入课程数量：");
                    int courseCount = scanner.nextInt();
                    controller.generateData(studentCount, courseCount, (short) 3);
                    System.out.println("数据初始化完成");
                    break;
                case 2:
                    controller.generateCourseChoosing();
                    System.out.println("学生选课完成");
                    break;
                case 3:
                    controller.generateGrades();
                    System.out.println("成绩生成完成");
                    break;
                case 4:
                    System.out.println("教学班信息如下：");
                    controller.displayTeachingClasses();
                    break;
                case 5:
                    System.out.println("请输入教学班号：");
                    int classId = scanner.nextInt();
                    teachingClassView.run(classId);
                    break;
                case 6:
                    System.out.println("请输入学号：");
                    int studentId = scanner.nextInt();
                    controller.displayGrade(studentId);
                    break;
                case 7:
                    exit = true;
                    System.out.println("退出程序");
                    break;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }
}
