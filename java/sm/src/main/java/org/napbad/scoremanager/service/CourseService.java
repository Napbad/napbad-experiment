/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.model.dto.course.CourseCreateInput
 *  org.napbad.scoresystem.model.dto.course.CourseQueryRes
 *  org.napbad.scoresystem.model.dto.course.CourseSpecification
 *  org.napbad.scoresystem.model.entity.Course
 *  org.napbad.scoresystem.service.CourseService
 */
package org.napbad.scoremanager.service;

import org.napbad.scoremanager.model.dto.course.CourseCreateInput;
import org.napbad.scoremanager.model.dto.course.CourseQueryRes;
import org.napbad.scoremanager.model.dto.course.CourseSpecification;
import org.napbad.scoremanager.model.entity.Course;

import java.util.List;

public interface CourseService {
    public List<CourseQueryRes> getAllCourses(CourseSpecification var1);

    public Course getCourseById(Long var1);

    public Course createCourse(CourseCreateInput var1);

    public Course updateCourse(Long var1, Course var2);

    public void deleteCourse(Long var1);

    List<Course> generateCourse(Integer number);
}

