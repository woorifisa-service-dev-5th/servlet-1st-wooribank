package com.woori_bank.be.account.util;

import java.security.SecureRandom;
import java.time.format.DateTimeFormatter;

import com.woori_bank.be.account.model.AccountType;

import java.time.LocalDateTime;

public class AccountNumberGenerator {
    private static final SecureRandom RND = new SecureRandom();
    private static final String BANK = "123"; // 은행코드 예시
    private static final DateTimeFormatter TS = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String generate(AccountType type) {
        String typeCode = switch (type) {
            case SAVING -> "01";
            case LOAN -> "02";
            case CHECKING -> "03";
        };
        
        String ts = LocalDateTime.now().format(TS);
        int rand = 1000 + RND.nextInt(9000);
        // 예: 123-01-20250822103045-4821
        return BANK + "-" + typeCode + "-" + ts + "-" + rand;
    }
}
