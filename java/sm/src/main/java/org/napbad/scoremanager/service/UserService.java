/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.model.dto.user.UserForLogin
 *  org.napbad.scoresystem.model.dto.user.UserInputDelete
 *  org.napbad.scoresystem.model.dto.user.UserLoginRes
 *  org.napbad.scoresystem.model.dto.user.UserRegisterInput
 *  org.napbad.scoresystem.service.UserService
 */
package org.napbad.scoremanager.service;

import org.napbad.scoremanager.model.dto.user.*;
import org.napbad.scoremanager.model.entity.User;

import java.util.List;

public interface UserService {
    public void register(UserRegisterInput var1);

    public UserLoginRes login(UserForLogin var1);

    public void deleteUser(UserInputDelete var1);

    List<User> generate(Integer number);

    List<UserInfo_student> query(UserSpecification user);
}

