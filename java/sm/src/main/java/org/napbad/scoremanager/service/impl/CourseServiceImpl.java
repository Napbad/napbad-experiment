/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.babyfish.jimmer.Input
 *  org.babyfish.jimmer.sql.JSqlClient
 *  org.babyfish.jimmer.sql.ast.query.specification.JSpecification
 *  org.babyfish.jimmer.sql.ast.table.spi.TableProxy
 *  org.napbad.scoresystem.model.dto.course.CourseCreateInput
 *  org.napbad.scoresystem.model.dto.course.CourseQueryRes
 *  org.napbad.scoresystem.model.dto.course.CourseSpecification
 *  org.napbad.scoresystem.model.entity.Course
 *  org.napbad.scoresystem.model.entity.CourseTable
 *  org.napbad.scoresystem.service.CourseService
 *  org.napbad.scoresystem.service.impl.CourseServiceImpl
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.napbad.scoremanager.service.impl;

import org.babyfish.jimmer.Input;
import org.babyfish.jimmer.sql.JSqlClient;
import org.babyfish.jimmer.sql.ast.query.specification.JSpecification;
import org.babyfish.jimmer.sql.ast.table.spi.TableProxy;
import org.napbad.scoremanager.model.dto.course.CourseCreateInput;
import org.napbad.scoremanager.model.dto.course.CourseQueryRes;
import org.napbad.scoremanager.model.dto.course.CourseSpecification;
import org.napbad.scoremanager.model.entity.Course;
import org.napbad.scoremanager.model.entity.CourseTable;
import org.napbad.scoremanager.service.CourseService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class CourseServiceImpl
implements CourseService {
    private final JSqlClient client;
    private final CourseTable table = CourseTable.$;

    public CourseServiceImpl(JSqlClient client) {
        this.client = client;
    }

    public List<CourseQueryRes> getAllCourses(CourseSpecification course) {
        return (List)this.client.createQuery((TableProxy)this.table).where((JSpecification)course).select(this.table.fetch(CourseQueryRes.class)).execute();
    }

    public Course getCourseById(Long id) {
        return null;
    }

    public Course createCourse(CourseCreateInput course) {
        return (Course)this.client.insert((Input)course).getModifiedEntity();
    }

    public Course updateCourse(Long id, Course courseDetails) {
        return null;
    }

    public void deleteCourse(Long id) {
    }

    @Override
    public List<Course> generateCourse(Integer number) {
        List<Course> courses = new ArrayList<>();

        List<Long> list = client.createQuery(table).orderBy(table.id().desc()).select(table.id()).limit(1).execute();
        int maxCourseId = list.isEmpty() ? 0 : list.getFirst().intValue();
        for (int i = 0; i < number; i++) {
            Course course = generateSingleCourse(1 + i + maxCourseId);
            courses.add(course);
        }

        // 将插入的课程转换为查询结果
        return courses;
    }

    private Course generateSingleCourse(int index) {
        CourseCreateInput input = new CourseCreateInput();
        input.setId((long)index);
        input.setCourseCode("C-" + index);
        input.setCourseId((long)index);

        input.setCourseName("Course " + index);
        input.setDescription("This is a generated course number " + index);
        // 设置其他必要的字段

        return client.insert(input).getModifiedEntity();
    }
}

