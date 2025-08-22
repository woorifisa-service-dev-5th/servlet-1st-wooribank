package com.woori_bank.be.user.util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {
    // 비용(cost) 기본 10, 서버 성능에 맞게 10~12 권장
    private static final int COST = 10;

    // 회원가입: 해시 생성
    public static String hash(String rawPassword) {
        if (rawPassword == null) throw new IllegalArgumentException("password null");
        return BCrypt.hashpw(rawPassword, BCrypt.gensalt(COST));
    }

    // 로그인: 검증
    public static boolean matches(String rawPassword, String hashedFromDb) {
        if (rawPassword == null || hashedFromDb == null) return false;
        return BCrypt.checkpw(rawPassword, hashedFromDb);
    }
}

