/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.babyfish.jimmer.sql.Column
 *  org.babyfish.jimmer.sql.Entity
 *  org.babyfish.jimmer.sql.GeneratedValue
 *  org.babyfish.jimmer.sql.GenerationType
 *  org.babyfish.jimmer.sql.Id
 *  org.babyfish.jimmer.sql.OneToMany
 *  org.babyfish.jimmer.sql.Table
 *  org.jetbrains.annotations.Nullable
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.model.entity.Course
 */
package org.napbad.scoremanager.model.entity;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Entity
@Table(name="course")
public interface Course {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id();

    @Column(name="course_code")
    public String courseCode();

    @Column(name="course_name")
    public String courseName();

    @Column(name="course_id")
    @Nullable
    public Long courseId();

    @Nullable
    public String description();

    @OneToMany(mappedBy="course")
    public List<Class_> classes();
}

