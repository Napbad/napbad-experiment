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
 *  org.babyfish.jimmer.sql.ManyToMany
 *  org.babyfish.jimmer.sql.ManyToOne
 *  org.babyfish.jimmer.sql.OneToMany
 *  org.babyfish.jimmer.sql.Table
 *  org.jetbrains.annotations.Nullable
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.model.entity.Course
 *  org.napbad.scoresystem.model.entity.Score
 *  org.napbad.scoresystem.model.entity.User
 */
package org.napbad.scoremanager.model.entity;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Entity
@Table(name="class")
public interface Class_ {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id();

    @IdView
    public long courseId();

    @Column(name="total_students")
    public Integer totalStudents();

    public String semester();

    @Column(name="class_id")
    public Long classId();

    @ManyToOne
    public Course course();

    @ManyToMany(mappedBy="classes")
    public List<User> users();

    @OneToMany(mappedBy="classes")
    public List<Score> scores();
}

