/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.model.dto.class_.ClassCreateInput
 *  org.napbad.scoresystem.model.dto.class_.ClassQueryRes
 *  org.napbad.scoresystem.model.dto.class_.ClassSpecification
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.service.ClassService
 */
package org.napbad.scoremanager.service;

import org.napbad.scoremanager.model.dto.class_.ClassCreateInput;
import org.napbad.scoremanager.model.dto.class_.ClassQueryRes;
import org.napbad.scoremanager.model.dto.class_.ClassSpecification;
import org.napbad.scoremanager.model.entity.Class_;

import java.util.List;
import java.util.Optional;

public interface ClassService {
    public List<ClassQueryRes> getAllClasss(ClassSpecification var1);

    public Class_ createClass(ClassCreateInput var1);

    public Optional<Class_> getClassById(Long var1);

    public Class_ updateClass(Long var1, Class_ var2);

    public void deleteClass(Long var1);

    List<Class_> generate(Integer number);
}

