package org.napbad.score.utilities;

import org.napbad.score.model.*;
import java.util.*;

/**
 * 生成学生成绩数据的工具类。
 */
public class Generator {

    /**
     * 男性标识符。
     */
    private static final String MALE = "男";

    /**
     * 女性标识符。
     */
    private static final String FEMALE = "女";

    /**
     * 随机数生成器，用于生成随机数据。
     */
    private static final Random random = new Random();

    /**
     * 教学班ID的初始值。
     */
    private static int teachingClassId = 1;

    /**
     * 教师ID的初始值。
     */
    private static int teacherId = 1;

    /**
     * 已生成的名字集合，用于确保名字唯一性。
     */
    private static final Set<String> generatedNames = new HashSet<>();

    /**
     * 中文字符的最小Unicode值。
     */
    private static final int MIN_CODE_POINT = 0x4E00;

    /**
     * 中文字符的最大Unicode值。
     */
    private static final int MAX_CODE_POINT = 0x9FA5;

    /**
     * 所有课程的映射表。
     */
    private static final Map<String, Course> allCourses = new HashMap<>();

    static {
        // 初始化一些中文课程
        allCourses.put("CS101", ModelFactory.createCourse(1, "计算机科学导论"));
        allCourses.put("MA101", ModelFactory.createCourse(2, "高等数学"));
        allCourses.put("PH101", ModelFactory.createCourse(3, "大学物理"));
        allCourses.put("EN101", ModelFactory.createCourse(4, "英语"));
        allCourses.put("CH101", ModelFactory.createCourse(5, "化学"));
        allCourses.put("EC101", ModelFactory.createCourse(6, "经济学原理"));
        allCourses.put("PS101", ModelFactory.createCourse(7, "心理学导论"));
        allCourses.put("HI101", ModelFactory.createCourse(8, "中国历史"));
        allCourses.put("AR101", ModelFactory.createCourse(9, "艺术欣赏"));
        allCourses.put("SP101", ModelFactory.createCourse(10, "体育"));
    }

    /**
     * 数据源对象，用于存储生成的数据。
     */
    private final DataSource dataSource;

    /**
     * 构造函数，初始化数据源。
     *
     * @param dataSource 数据源对象。
     */
    public Generator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * 生成指定数量的学生、课程和成绩数据。
     *
     * @param studentCount 学生数量。
     * @param courseCount 课程数量。
     * @param gradeCount 成绩数量。
     */
    public void generateData(int studentCount, int courseCount, short gradeCount) {
        dataSource.setStudentCounts(studentCount);
        dataSource.setCourseCounts(courseCount);
        dataSource.setGradeCounts(gradeCount);

        // 生成学生、课程和学生的课程
        dataSource.setStudents(generateStudents(studentCount));
        dataSource.setCourses(generateCourses(courseCount));
        dataSource.getCourses().forEach(course -> {
            // 生成教学班
            int number = 1 + random.nextInt((studentCount - 60) / 20);

            for (int i = 0; i < number; i++) {
                Teacher teacher = ModelFactory.createTeacher(teacherId++, getRandomName());
                TeachingClass teachingClass = ModelFactory.createTeachingClass(teachingClassId++,
                        teacher,
                        course,
                        random.nextInt(30) + 20,
                        (short) (random.nextInt(5) + 1)
                );
                teacher.setTeachingClass(teachingClass);
                dataSource.getTeachingClasses().add(teachingClass);
                dataSource.getTeachers().add(teacher);
                course.getTeachingClasses().add(teachingClass);
            }
        });
    }

    /**
     * 生成学生的选课数据。
     */
    public void generateCourseChoosing() {
        dataSource.getCourses().forEach(course -> {
            int studentCount = 0;
            for (int i = 0; i < course.getTeachingClasses().size(); i++) {
                TeachingClass teachingClass = course.getTeachingClasses().get(i);
                teachingClass.setTotalStudents(random.nextInt(20) + 20);
                studentCount += (teachingClass.getTotalStudents());
            }
            List<Integer> students = generateUniqueRandomInts(0, dataSource.getStudentCounts(), studentCount);
            int studentIdx = 0;
            for (TeachingClass teachingClass : course.getTeachingClasses()) {
                for (int j = 0; j < teachingClass.getTotalStudents(); j++) {
                    Student student = dataSource.getStudents().get(students.get(studentIdx));
                    student.getTeachingClass().add(teachingClass);
                    teachingClass.addStudent(student);
                    studentIdx++;
                }
            }
        });
    }

    /**
     * 生成指定数量的学生。
     *
     * @param count 学生数量。
     * @return 生成的学生列表。
     */
    private List<Student> generateStudents(int count) {
        ArrayList<Student> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(ModelFactory.createStudent(i, getRandomName(), getRandomGender()));
        }

        return list;
    }

    /**
     * 生成随机的名字。
     *
     * @return 随机生成的名字。
     */
    private String getRandomName() {
        while (true) {
            StringBuilder name = new StringBuilder();
            int nameLength = random.nextInt(2) + 2; // 名字长度为2或3个字符
            for (int i = 0; i < nameLength; i++) {
                int codePoint = random.nextInt(MAX_CODE_POINT - MIN_CODE_POINT + 1) + MIN_CODE_POINT;
                name.appendCodePoint(codePoint);
            }
            if (generatedNames.add(name.toString())) {
                return name.toString();
            }
        }
    }

    /**
     * 生成随机的性别。
     *
     * @return 随机生成的性别。
     */
    private String getRandomGender() {
        return random.nextBoolean() ? MALE : FEMALE;
    }

    /**
     * 生成指定数量的课程。
     *
     * @param count 课程数量。
     * @return 生成的课程列表。
     */
    private List<Course> generateCourses(int count) {
        List<Course> list = new ArrayList<>();
        List<Course> allCoursesList = new ArrayList<>(allCourses.values());
        Random random = new Random();

        for (int i = 0; i < count && !allCoursesList.isEmpty(); i++) {
            int index = random.nextInt(allCoursesList.size());
            list.add(allCoursesList.remove(index));
        }

        return list;
    }

    /**
     * 生成指定数量的教师。
     *
     * @param count 教师数量。
     * @return 生成的教师列表。
     */
    private List<Teacher> generateTeachers(int count) {
        List<Teacher> list = new ArrayList<>();
        List<String> allCoursesList = new ArrayList<>(allCourses.keySet());
        Random random = new Random();

        for (int i = 0; i < count && !allCoursesList.isEmpty(); i++) {
            int index = random.nextInt(allCoursesList.size());
            String courseCode = allCoursesList.remove(index);
            list.add(ModelFactory.createTeacher(i, courseCode));
        }
        return list;
    }

    /**
     * 从给定的课程列表中随机选择指定数量的课程。
     *
     * @param count 要选择的课程数量。
     * @param list 课程列表。
     * @return 选择的课程列表。
     */
    private ArrayList<Course> getRandomCourses(int count, List<Course> list) {
        if (count > list.size()) {
            System.out.println("Invalid course count. Maximum allowed: " + list.size());
        }
        // 初始化布尔值列表
        ArrayList<Boolean> booleans = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            booleans.add(false);
        }
        ArrayList<Course> courses = new ArrayList<>();
        while (courses.size() < count) {
            int randomInt = random.nextInt(list.size());
            if (!booleans.get(randomInt)) {
                courses.add(list.get(randomInt));
                booleans.set(randomInt, true);
            }
        }

        return courses;
    }

           /**
         * 生成指定数量的唯一随机整数。
         *
         * @param min 最小值。
         * @param max 最大值。
         * @param count 数量。
         * @return 生成的唯一随机整数列表。
         */
        private List<Integer> generateUniqueRandomInts(int min, int max, int count) {
            if (count > (max - min + 1)) {
                throw new IllegalArgumentException("too many random counts!");
            }

            Set<Integer> uniqueInts = new HashSet<>();
            Random random = new Random();

            while (uniqueInts.size() < count) {
                int randomNum = random.nextInt((max - min)) + min;
                uniqueInts.add(randomNum);
            }

            return new ArrayList<>(uniqueInts);
        }

        /**
         * 生成学生的成绩数据。
         */
        public void generateGrades() {
            for (Student student : dataSource.getStudents()) {
                for (int i = 0; i < student.getTeachingClass().size(); i++) {
                    TeachingClass teachingClass = student.getTeachingClass().get(i);
                    Grade grade = ModelFactory.createGrade(student, teachingClass,
                            random.nextInt(60) + 40,
                            random.nextInt(60) + 40,
                            random.nextInt(60) + 40,
                            random.nextInt(60) + 40);
                    student.addGrade(grade);
                }
            }
        }
    }
