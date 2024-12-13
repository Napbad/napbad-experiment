/*
 * Decompiled with CFR 0.152.
 *
 * Could not load the following classes:
 *  org.napbad.scoresystem.controller.UserController
 *  org.napbad.scoresystem.model.dto.user.UserForLogin
 *  org.napbad.scoresystem.model.dto.user.UserForVerify
 *  org.napbad.scoresystem.model.dto.user.UserInfo_student
 *  org.napbad.scoresystem.model.dto.user.UserInputDelete
 *  org.napbad.scoresystem.model.dto.user.UserLoginRes
 *  org.napbad.scoresystem.model.dto.user.UserRegisterInput
 *  org.napbad.scoresystem.service.UserService
 *  org.napbad.scoresystem.util.JwtUtil
 *  org.springframework.data.redis.core.StringRedisTemplate
 *  org.springframework.http.ResponseEntity
 *  org.springframework.web.bind.annotation.CrossOrigin
 *  org.springframework.web.bind.annotation.PostMapping
 *  org.springframework.web.bind.annotation.RequestBody
 *  org.springframework.web.bind.annotation.RequestMapping
 *  org.springframework.web.bind.annotation.RestController
 */
package org.napbad.scoremanager.controller;

import org.babyfish.jimmer.client.meta.Api;
import org.napbad.scoremanager.model.dto.user.*;
import org.napbad.scoremanager.model.entity.User;
import org.napbad.scoremanager.service.UserService;
import org.napbad.scoremanager.util.CaptchaGenerator;
import org.napbad.scoremanager.util.JwtUtil;
import org.napbad.scoremanager.util.MailUtil;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Api
// @CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(value = {"/user"})
public class UserController {
    private final UserService service;
    private final StringRedisTemplate redisTemplate;
    private final MailUtil mailUtil;

    public UserController(UserService service, StringRedisTemplate redisTemplate, MailUtil mailUtil) {
        this.service = service;
        this.redisTemplate = redisTemplate;
        this.mailUtil = mailUtil;
    }

    @Api
    @PostMapping(value = {"/register"})
    public ResponseEntity<String> register(@RequestBody UserRegisterInput user) {
        if (user.getIdentifier().equals(0L)) {
            throw new RuntimeException("要指定注册用户");
        }
        if (user.getUserRole() <= 1) {
            throw new RuntimeException("没有权限");
        }
        if (!Objects.equals(user.getCaptcha(), redisTemplate.opsForValue().get(user.getEmail()))) {
            throw new RuntimeException("验证码错误");
        }
        this.service.register(user);
        return ResponseEntity.ok("register");
    }

    @Api
    @PostMapping(value = {"/verify"})
    public ResponseEntity<String> verify(@RequestBody UserForVerify user) {
        String generatedCaptcha = CaptchaGenerator.generateCaptcha();
        this.mailUtil.sendCaptcha(generatedCaptcha, user.getEmail());
        this.redisTemplate.opsForValue().set(user.getEmail(), generatedCaptcha, 300L);

        return ResponseEntity.ok("已经发送");
    }

    @Api
    @PostMapping(value = {"/login"})
    public ResponseEntity<UserLoginRes> login(@RequestBody UserForLogin user) {

        if (user.getIdentifier().equals(0)) {
            throw new RuntimeException("要指定登录用户");
        }

        UserLoginRes userRes = this.service.login(user);
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", userRes.getId());
        String jwt = JwtUtil.createJWT("napbad", 604800000L, map);
        userRes.setToken(jwt);
        return ResponseEntity.ok(userRes);
    }

    @Api
    @PostMapping(value = {"/delete"})
    public ResponseEntity<String> delete(@RequestBody UserInputDelete user) {
        this.service.deleteUser(user);
        return ResponseEntity.ok("删除成功");
    }

    @Api
    @PostMapping(value = {"/query"})
    public ResponseEntity<List<UserInfo_student>> query(
        @RequestBody UserSpecification user
    ) {
        return ResponseEntity.ok(service.query(user));
    }

    @Api
    @PostMapping(value = {"/generate"})
    public ResponseEntity<List<User>> generate(@RequestParam Integer number) {
        return ResponseEntity.ok(this.service.generate(number));
    }
}

