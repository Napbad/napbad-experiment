package org.napbad.score.view;

import lombok.Getter;
import lombok.Setter;
import org.napbad.score.controller.TeachingClassController;

import java.util.*;

public class TeachingClassView {

    private final TeachingClassController controller;
    @Setter
    @Getter
    private int classId;

    public TeachingClassView(TeachingClassController controller) {
        this.controller = controller;
    }

    public void run(int classId) {
        this.classId = classId;
        controller.setClassId(this.classId);
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("教学班视图菜单:");
            System.out.println("1. 显示学生成绩");
            System.out.println("2. 统计分数段分布");
            System.out.println("3. 返回主菜单");
            System.out.print("请选择: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    controller.displayGradesMenu(scanner);
                    break;
                case 2:
                    controller.displayGradeDistribution();
                    break;
                case 3:
                    exit = true;
                    System.out.println("返回主菜单");
                    break;
                default:
                    System.out.println("无效的选择，请重新输入");
            }
        }
    }
}
