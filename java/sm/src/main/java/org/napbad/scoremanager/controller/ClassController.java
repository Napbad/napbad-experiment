/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.napbad.scoresystem.controller.ClassController
 *  org.napbad.scoresystem.model.dto.class_.ClassCreateInput
 *  org.napbad.scoresystem.model.dto.class_.ClassQueryRes
 *  org.napbad.scoresystem.model.dto.class_.ClassSpecification
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.service.ClassService
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package org.napbad.scoremanager.controller;

import org.babyfish.jimmer.client.meta.Api;
import org.napbad.scoremanager.model.dto.class_.ClassCreateInput;
import org.napbad.scoremanager.model.dto.class_.ClassQueryRes;
import org.napbad.scoremanager.model.dto.class_.ClassSpecification;
import org.napbad.scoremanager.model.entity.Class_;
import org.napbad.scoremanager.service.ClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Api
// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(value = {"/classes"})
@RestController
public class ClassController {
    private final ClassService service;

    public ClassController(ClassService service) {
        this.service = service;
    }

    @Api
    @PostMapping(value = {"/add"})
    public ResponseEntity<Class_> add(@RequestBody ClassCreateInput class_) {
        return ResponseEntity.ok(this.service.createClass(class_));
    }

    @Api
    @PostMapping(value = {"/query"})
    public ResponseEntity<List<ClassQueryRes>> query(@RequestBody ClassSpecification class_) {
        return ResponseEntity.ok(this.service.getAllClasss(class_));
    }

    @Api
    @PostMapping(value = {"/generate"})
    public ResponseEntity<List<Class_>> generate(@RequestParam Integer number) {
        return ResponseEntity.ok(this.service.generate(number));
    }


}

