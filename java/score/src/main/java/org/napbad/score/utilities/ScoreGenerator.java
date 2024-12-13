package org.napbad.score.utilities;

import org.napbad.score.model.DataSource;
import org.napbad.score.model.Grade;
import org.napbad.score.model.Student;

import java.util.Random;

/**
 * 生成学生成绩的工具类。
 */
public class ScoreGenerator {
    /**
     * 随机数生成器，用于生成随机成绩。
     */
    private static final Random random = new Random();

    /**
     * 根据指定的生成类型生成学生成绩。
     *
     * @param generatorType 生成类型，可以是 Low, Medium, High, 或 Random。
     * @param dataSource 数据源，包含学生和教学班信息。
     */
    public void generator(GeneratorType generatorType, DataSource dataSource) {
        switch (generatorType) {
            case Low:
                generateLowScores(dataSource);
                break;
            case Medium:
                generateMediumScores(dataSource);
                break;
            case High:
                generateHighScores(dataSource);
                break;
            case Random:
                generateRandomScores(dataSource);
                break;
            default:
                throw new IllegalArgumentException("Unknown generator type: " + generatorType);
        }
    }

    /**
     * 生成类型的枚举，定义了四种生成方式。
     */
    enum GeneratorType {
        /**
         * 生成低分，成绩范围在 40-60 分之间。
         */
        Low,
        /**
         * 生成中等分数，成绩范围在 60-100 分之间。
         */
        Medium,
        /**
         * 生成高分，成绩范围在 40-100 分之间，但倾向于较高分数。
         */
        High,
        /**
         * 生成随机分数，成绩范围在 0-100 分之间。
         */
        Random
    }

    /**
     * 生成高分的内部类。
     */
    static class HighScoreGenerator {
        /**
         * 生成高分成绩。
         *
         * @param dataSource 数据源，包含学生和教学班信息。
         */
        public static void gen(DataSource dataSource) {
            for (Student student : dataSource.getStudents()) {
                for (int i = 0; i < student.getTeachingClass().size(); i++) {
                    Grade grade = new Grade(student, student.getTeachingClass().get(i),
                            random.nextInt(60) + 40,
                            random.nextInt(60) + 40,
                            random.nextInt(60) + 40,
                            random.nextInt(60) + 40);
                    student.addGrade(grade);
                }
            }
        }
    }

    /**
     * 生成低分成绩。
     *
     * @param dataSource 数据源，包含学生和教学班信息。
     */
    private void generateLowScores(DataSource dataSource) {
        for (Student student : dataSource.getStudents()) {
            for (int i = 0; i < student.getTeachingClass().size(); i++) {
                Grade grade = new Grade(student, student.getTeachingClass().get(i),
                        random.nextInt(20) + 40,
                        random.nextInt(20) + 40,
                        random.nextInt(20) + 40,
                        random.nextInt(20) + 40);
                student.addGrade(grade);
            }
        }
    }

    /**
     * 生成中等分数成绩。
     *
     * @param dataSource 数据源，包含学生和教学班信息。
     */
    private void generateMediumScores(DataSource dataSource) {
        for (Student student : dataSource.getStudents()) {
            for (int i = 0; i < student.getTeachingClass().size(); i++) {
                Grade grade = new Grade(student, student.getTeachingClass().get(i),
                        random.nextInt(40) + 60,
                        random.nextInt(40) + 60,
                        random.nextInt(40) + 60,
                        random.nextInt(40) + 60);
                student.addGrade(grade);
            }
        }
    }

    /**
     * 生成高分成绩。
     *
     * @param dataSource 数据源，包含学生和教学班信息。
     */
    private void generateHighScores(DataSource dataSource) {
        HighScoreGenerator.gen(dataSource);
    }

    /**
     * 生成随机分数成绩。
     *
     * @param dataSource 数据源，包含学生和教学班信息。
     */
    private void generateRandomScores(DataSource dataSource) {
        for (Student student : dataSource.getStudents()) {
            for (int i = 0; i < student.getTeachingClass().size(); i++) {
                Grade grade = new Grade(student, student.getTeachingClass().get(i),
                        random.nextInt(100),
                        random.nextInt(100),
                        random.nextInt(100),
                        random.nextInt(100));
                student.addGrade(grade);
            }
        }
    }
}
