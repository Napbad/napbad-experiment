/*
 * Decompiled with CFR 0.152.
 * 
 * Could not load the following classes:
 *  org.napbad.scoresystem.util.UserContext
 */
package org.napbad.scoremanager.util;

public class UserContext {
    public static final ThreadLocal<Integer> THREAD_LOCAL_USER_ID = ThreadLocal.withInitial(() -> null);

    public static Integer getUserId() {
        return (Integer)THREAD_LOCAL_USER_ID.get();
    }

    public static void setUserId(Integer userId) {
        THREAD_LOCAL_USER_ID.set(userId);
    }

    private UserContext() {
    }

    public static void remove() {
        THREAD_LOCAL_USER_ID.remove();
    }
}

