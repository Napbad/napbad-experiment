/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.napbad.scoresystem.controller.CourseController
 *  org.napbad.scoresystem.model.dto.course.CourseCreateInput
 *  org.napbad.scoresystem.model.dto.course.CourseQueryRes
 *  org.napbad.scoresystem.model.dto.course.CourseSpecification
 *  org.napbad.scoresystem.model.entity.Course
 *  org.napbad.scoresystem.service.CourseService
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package org.napbad.scoremanager.controller;

import org.babyfish.jimmer.client.meta.Api;
import org.napbad.scoremanager.model.dto.course.CourseCreateInput;
import org.napbad.scoremanager.model.dto.course.CourseQueryRes;
import org.napbad.scoremanager.model.dto.course.CourseSpecification;
import org.napbad.scoremanager.model.entity.Course;
import org.napbad.scoremanager.service.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = {"/course"})
@RestController
class CourseController {
    private final CourseService service;

    public CourseController(CourseService service) {
        this.service = service;
    }

    @Api
    @PostMapping(value = {"/add"})
    public ResponseEntity<Course> add(@RequestBody CourseCreateInput course) {
        return ResponseEntity.ok(this.service.createCourse(course));
    }

    @Api
    @PostMapping(value = {"/query"})
    public ResponseEntity<List<CourseQueryRes>> query(@RequestBody CourseSpecification course) {
        return ResponseEntity.ok(this.service.getAllCourses(course));
    }

    @Api
    @PostMapping(value = {"/generate"})
    public ResponseEntity<List<Course>> generate(@RequestParam Integer number) {
        return ResponseEntity.ok(this.service.generateCourse(number));
    }


}

