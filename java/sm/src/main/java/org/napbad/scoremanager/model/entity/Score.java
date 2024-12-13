/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.babyfish.jimmer.sql.Column
 *  org.babyfish.jimmer.sql.Entity
 *  org.babyfish.jimmer.sql.GeneratedValue
 *  org.babyfish.jimmer.sql.GenerationType
 *  org.babyfish.jimmer.sql.Id
 *  org.babyfish.jimmer.sql.IdView
 *  org.babyfish.jimmer.sql.ManyToOne
 *  org.babyfish.jimmer.sql.Table
 *  org.jetbrains.annotations.Nullable
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.model.entity.Score
 *  org.napbad.scoresystem.model.entity.User
 */
package org.napbad.scoremanager.model.entity;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

@Entity
@Table(name="score")
public interface Score {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    long id();

    @Column(name="regular_score")
    Integer regularScore();

    @Column(name="midterm_score")
    Integer midtermScore();

    @Column(name="lab_score")
    Integer labScore();

    @Column(name="final_score")
    Integer finalScore();

    @Column(name="total_score")
    Integer totalScore();

    @IdView
    long classesId();

    @IdView
    long studentId();

    @ManyToOne
    User student();

    @ManyToOne
    Class_ classes();
}

