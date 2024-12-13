package org.napbad.score.model;

public class ModelFactory {
    public static Student createStudent(int studentId, String name, String gender) {
        return new Student(studentId, name, gender);
    }
    public static Teacher createTeacher(int teacherId, String name) {
        return new Teacher(teacherId, name);
    }
    public static Course createCourse(int courseId, String name) {
        return new Course(courseId, name);
    }
    public static TeachingClass createTeachingClass(int classId, Teacher teacher, Course course, int totalStudents, short grade) {
        return new TeachingClass(classId, teacher, course, totalStudents, grade);
    }
    public static Grade createGrade(Student student, TeachingClass teachingClass, int usualScore, int midtermScore, int experimentScore, int finalScore) {
        return new Grade(student, teachingClass, usualScore, midtermScore, experimentScore, finalScore);
    }

    private ModelFactory() {}
}
