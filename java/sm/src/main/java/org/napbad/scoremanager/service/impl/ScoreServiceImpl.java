/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.babyfish.jimmer.Input
 *  org.babyfish.jimmer.sql.JSqlClient
 *  org.babyfish.jimmer.sql.ast.query.specification.JSpecification
 *  org.babyfish.jimmer.sql.ast.table.spi.TableProxy
 *  org.napbad.scoresystem.model.dto.score.ScoreCreateInput
 *  org.napbad.scoresystem.model.dto.score.ScoreQueryRes
 *  org.napbad.scoresystem.model.dto.score.ScoreSpecification
 *  org.napbad.scoresystem.model.dto.score.ScoreUpdateInput
 *  org.napbad.scoresystem.model.entity.Score
 *  org.napbad.scoresystem.model.entity.ScoreTable
 *  org.napbad.scoresystem.service.ScoreService
 *  org.napbad.scoresystem.service.impl.ScoreServiceImpl
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.napbad.scoremanager.service.impl;

import org.babyfish.jimmer.sql.JSqlClient;
import org.napbad.scoremanager.model.dto.score.ScoreCreateInput;
import org.napbad.scoremanager.model.dto.score.ScoreQueryRes;
import org.napbad.scoremanager.model.dto.score.ScoreSpecification;
import org.napbad.scoremanager.model.dto.score.ScoreUpdateInput;
import org.napbad.scoremanager.model.entity.*;
import org.napbad.scoremanager.service.ScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Transactional
@Service
public class ScoreServiceImpl
        implements ScoreService {
    private final JSqlClient client;
    private final ScoreTable table = ScoreTable.$;

    public ScoreServiceImpl(JSqlClient client) {
        this.client = client;
    }

    public List<Score> getAllScores() {
        return List.of();
    }

    public Score getScoreById(Long id) {
        return null;
    }

    public Score createScore(ScoreCreateInput score) {
        return client.insert(score).getModifiedEntity();
    }

    public Score updateScore(Long id, Score scoreDetails) {
        return null;
    }

    public void deleteScore(Long id) {
    }

    public List<ScoreQueryRes> queryScore(ScoreSpecification score) {
        return client.createQuery(table)
                .where(score)
                .orderByIf(
                        score.getREGULAR_SCORE_ASC() != null,
                        table.regularScore().asc()
                )
                .orderByIf(
                        score.getREGULAR_SCORE_DESC() != null,
                        table.regularScore().desc()
                )
                .orderByIf(
                        score.getMIDTERM_SCORE_ASC() != null,
                        table.midtermScore().asc()
                )
                .orderByIf(
                        score.getMIDTERM_SCORE_DESC() != null,
                        table.midtermScore().desc()
                )
                .orderByIf(
                        score.getLAB_SCORE_ASC() != null,
                        table.labScore().asc()
                )
                .orderByIf(
                        score.getLAB_SCORE_DESC() != null,
                        table.labScore().desc()
                )
                .orderByIf(
                        score.getFINAL_SCORE_ASC() != null,
                        table.finalScore().asc()
                )
                .orderByIf(
                        score.getFINAL_SCORE_DESC() != null,
                        table.finalScore().desc()
                )
                .orderByIf(
                        score.getTOTAL_SCORE_ASC() != null,
                        table.totalScore().asc()
                )
                .orderByIf(
                        score.getTOTAL_SCORE_DESC() != null,
                        table.totalScore().desc()
                )
                .orderByIf(
                        score.getCLASS_ID_ASC() != null,
                        table.classesId().asc()
                )
                .orderByIf(
                        score.getCLASS_ID_DESC() != null,
                        table.classesId().desc()
                )
                .orderByIf(
                        score.getSTUDENT_ID_ASC() != null,
                        table.studentId().asc()
                )
                .orderByIf(
                        score.getSTUDENT_ID_DESC() != null,
                        table.studentId().desc()
                )
                .orderByIf(
                        score.getCOURSE_ID_ASC() != null,
                        table.classes().courseId().asc()
                )
                .orderByIf(
                        score.getCOURSE_ID_DESC() != null,
                        table.classes().courseId().desc()
                )
                .orderByIf(
                        score.getSEMESTER_ASC() != null,
                        table.classes().semester().asc()
                )
                .orderByIf(
                        score.getSEMESTER_DESC() != null,
                        table.classes().semester().desc()
                )

                .select(
                        table.fetch(ScoreQueryRes.class)
                )
                .execute();
    }

    public Object updateScore(ScoreUpdateInput score) {
        return client.update(score).getModifiedEntity();
    }

    @Override
    public List<Score> generate(Integer number) {
        List<Score> scores = new ArrayList<>();
        List<Class_> list = client.createQuery(Class_Table.$).select(Class_Table.$).limit(1).execute();
        List<User> list2 = client.createQuery(UserTable.$).select(UserTable.$).limit(1).execute();

        for (int i = 0; i < number; i++) {
            Score score = generateSingleScore(i, list.get((new Random()).nextInt(list.size())), list2.get((new Random()).nextInt(list2.size())));
            scores.add(score);
        }

        // 将插入的成绩记录转换为查询结果
        return scores;
    }

    private Score generateSingleScore(int index, Class_ aClass, User user) {
        ScoreCreateInput input = new ScoreCreateInput();
        input.setRegularScore(80 + index % 20);
        input.setMidtermScore(70 + index % 20);
        input.setLabScore(60 + index % 20);
        input.setFinalScore(90 + index % 20);

        input.setTotalScore(input.getRegularScore() + input.getMidtermScore() + input.getLabScore() + input.getFinalScore());
        input.setStudentId(user.id());
        input.setClassesId(aClass.id());


        return client.insert(input).getModifiedEntity();
    }

}

