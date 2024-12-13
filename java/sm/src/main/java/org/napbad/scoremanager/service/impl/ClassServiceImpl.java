/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.babyfish.jimmer.Input
 *  org.babyfish.jimmer.sql.JSqlClient
 *  org.babyfish.jimmer.sql.ast.query.specification.JSpecification
 *  org.babyfish.jimmer.sql.ast.table.spi.TableProxy
 *  org.napbad.scoresystem.model.dto.class_.ClassCreateInput
 *  org.napbad.scoresystem.model.dto.class_.ClassQueryRes
 *  org.napbad.scoresystem.model.dto.class_.ClassSpecification
 *  org.napbad.scoresystem.model.entity.Class_
 *  org.napbad.scoresystem.model.entity.Class_Table
 *  org.napbad.scoresystem.service.ClassService
 *  org.napbad.scoresystem.service.impl.ClassServiceImpl
 *  org.springframework.stereotype.Service
 *  org.springframework.transaction.annotation.Transactional
 */
package org.napbad.scoremanager.service.impl;

import org.babyfish.jimmer.sql.JSqlClient;
import org.napbad.scoremanager.model.dto.class_.ClassCreateInput;
import org.napbad.scoremanager.model.dto.class_.ClassQueryRes;
import org.napbad.scoremanager.model.dto.class_.ClassSpecification;
import org.napbad.scoremanager.model.dto.user.UserRegisterInput;
import org.napbad.scoremanager.model.entity.*;
import org.napbad.scoremanager.service.ClassService;
import org.napbad.scoremanager.util.SHA256Encryption;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Transactional
@Service
public class ClassServiceImpl
        implements ClassService {
    private final JSqlClient client;
    private final Class_Table table = Class_Table.$;

    public ClassServiceImpl(JSqlClient client) {
        this.client = client;
    }

    public List<ClassQueryRes> getAllClasss(ClassSpecification class_) {
        return this.client.createQuery(this.table).where(class_).select(this.table.fetch(ClassQueryRes.class)).execute();
    }

    public Class_ createClass(ClassCreateInput class_) {
        return this.client.insert(class_).getModifiedEntity();
    }

    public Optional<Class_> getClassById(Long id) {
        return Optional.empty();
    }

    public Class_ updateClass(Long id, Class_ courseDetails) {
        return null;
    }

    public void deleteClass(Long id) {
    }

    @Override
    public List<Class_> generate(Integer number) {
        List<Course> courseList = client.createQuery(CourseTable.$)
                .select(
                        CourseTable.$
                ).execute();

        List<Class_> class_list = client.createQuery(table).orderBy(table.id().desc()).select(table).limit(1).execute();
        int maxClassId = class_list.isEmpty() ? 0 : (int) class_list.get(0).id();

        List<Class_> classes = new ArrayList<>();

        for (int i = 0; i < number; i++) {
            Class_ clazz = generateSingleClass(maxClassId + i, courseList);
            classes.add(clazz);
        }

        // 将插入的班级转换为查询结果
        return classes;
    }

    private Class_ generateSingleClass(int index, List<Course> courseList) {

        User teacher = UserDraft.$.produce(
                user -> {
                    user.setUserRole(2);
                    user.setGender("Male");
                    user.setPassword(SHA256Encryption.getSHA256("password"));
                    user.setEmail("teacher" + index + "@gmail.com");
                    user.setName("Teacher " + index);
                    user.setIdentifier((long) index + 100000);
                }
        );


        Class_ produce = Class_Draft.$.produce(
                clazz -> {
                    Random random = new Random();
                    clazz.setCourseId(index);
                    clazz.setSemester("Semester " + index);
                    clazz.setClassId((long) index);
                    clazz.setCourseId(courseList.get(random.nextInt(courseList.size())).id());
                    clazz.setUsers(List.of(teacher));
                }
        );

        return client.insert(produce).getModifiedEntity();
    }

}

