/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.babyfish.jimmer.sql.Column
 *  org.babyfish.jimmer.sql.Entity
 *  org.babyfish.jimmer.sql.GeneratedValue
 *  org.babyfish.jimmer.sql.GenerationType
 *  org.babyfish.jimmer.sql.Id
 *  org.babyfish.jimmer.sql.ManyToMany
 *  org.babyfish.jimmer.sql.Table
 *  org.jetbrains.annotations.Nullable
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.model.entity.User
 */
package org.napbad.scoremanager.model.entity;

import org.babyfish.jimmer.sql.*;
import org.jetbrains.annotations.Nullable;

import java.util.List;

@Entity
@Table(name="user")
public interface User {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public long id();

    public String name();

    public String gender();

    public String email();

    public Long identifier();

    @Nullable
    public String password();

    @Column(name="user_role")
    public Integer userRole();

    @ManyToMany
    public List<Class_> classes();
}

