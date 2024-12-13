/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  lombok.Generated
 *  org.napbad.scoresystem.controller.ScoreController
 *  org.napbad.scoresystem.model.dto.score.ScoreCreateInput
 *  org.napbad.scoresystem.model.dto.score.ScoreQueryRes
 *  org.napbad.scoresystem.model.dto.score.ScoreSpecification
 *  org.napbad.scoresystem.model.dto.score.ScoreUpdateInput
 *  org.napbad.scoresystem.model.entity.Score
 *  org.napbad.scoresystem.service.ScoreService
 *  org.slf4j.Logger
 *  org.slf4j.LoggerFactory
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package org.napbad.scoremanager.controller;

import lombok.Generated;
import org.babyfish.jimmer.client.meta.Api;
import org.napbad.scoremanager.model.dto.score.ScoreCreateInput;
import org.napbad.scoremanager.model.dto.score.ScoreQueryRes;
import org.napbad.scoremanager.model.dto.score.ScoreSpecification;
import org.napbad.scoremanager.model.dto.score.ScoreUpdateInput;
import org.napbad.scoremanager.model.entity.Score;
import org.napbad.scoremanager.service.ScoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api
// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = {"/score"})
public class ScoreController {
    @Generated
    private static final Logger log = LoggerFactory.getLogger(ScoreController.class);
    private final ScoreService service;

    public ScoreController(ScoreService service) {
        this.service = service;
    }

    @Api
    @PostMapping(value = {"/query"})
    public ResponseEntity<List<ScoreQueryRes>> query(@RequestBody ScoreSpecification score) {
        return ResponseEntity.ok(this.service.queryScore(score));
    }

    @Api
    @PostMapping(value = {"/update"})
    public ResponseEntity<Object> update(@RequestBody ScoreUpdateInput score) {
        return ResponseEntity.ok(this.service.updateScore(score));
    }

    @Api
    @PostMapping(value = {"/add"})
    public ResponseEntity<Score> add(@RequestBody ScoreCreateInput score) {
        score.setTotalScore(Integer.valueOf(score.getRegularScore() + score.getMidtermScore() + score.getLabScore() + score.getFinalScore()));
        return ResponseEntity.ok(this.service.createScore(score));
    }

    @Api
    @PostMapping(value = {"/generate"})
    public ResponseEntity<List<Score>> generate(@RequestParam Integer number) {
        return ResponseEntity.ok(this.service.generate(number));
    }
}

