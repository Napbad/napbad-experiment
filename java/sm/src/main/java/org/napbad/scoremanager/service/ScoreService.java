/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.model.dto.score.ScoreCreateInput
 *  org.napbad.scoresystem.model.dto.score.ScoreQueryRes
 *  org.napbad.scoresystem.model.dto.score.ScoreSpecification
 *  org.napbad.scoresystem.model.dto.score.ScoreUpdateInput
 *  org.napbad.scoresystem.model.entity.Score
 *  org.napbad.scoresystem.service.ScoreService
 */
package org.napbad.scoremanager.service;

import org.napbad.scoremanager.model.dto.score.ScoreCreateInput;
import org.napbad.scoremanager.model.dto.score.ScoreQueryRes;
import org.napbad.scoremanager.model.dto.score.ScoreSpecification;
import org.napbad.scoremanager.model.dto.score.ScoreUpdateInput;
import org.napbad.scoremanager.model.entity.Score;

import java.util.List;

public interface ScoreService {
    public List<Score> getAllScores();

    public Score getScoreById(Long var1);

    public Score createScore(ScoreCreateInput var1);

    public Score updateScore(Long var1, Score var2);

    public void deleteScore(Long var1);

    public List<ScoreQueryRes> queryScore(ScoreSpecification var1);

    public Object updateScore(ScoreUpdateInput var1);

    List<Score> generate(Integer number);
}

