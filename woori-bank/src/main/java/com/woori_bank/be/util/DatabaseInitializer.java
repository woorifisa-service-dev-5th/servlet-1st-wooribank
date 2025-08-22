package com.woori_bank.be.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// 데이터베이스를 초기화하는 클래스
public class DatabaseInitializer {

	private static final String SQL_PATH = "WEB-INF/sql/init.sql";
	private static BufferedReader br;
	private static Logger log = LoggerFactory.getLogger("db-init-logger");

	public static void init(ServletContext sc) {
		log.info("데이터베스 초기화 시작");
		InputStream in = sc.getResourceAsStream(SQL_PATH);
		br = new BufferedReader(new InputStreamReader(in));
		sendQuery(readAllLines());
		log.info("데이터베스 초기화 완료");
	}

	private static List<String> readAllLines() {
		List<String> result = br.lines().toList();
		result = result.stream().filter(e -> !"".equals(e))
				.flatMap(line -> Arrays.stream(line.split(";")).map(String::trim)).toList();
		result.forEach(System.out::println);
		return result;
	}

	private static void sendQuery(List<String> lines) {
		try {
			Connection c = ConnectionPool.getConnection();
			for (String line : lines) c.prepareStatement(line).execute();
		} catch (Exception e) {
			log.warn(e.getMessage());
			throw new IllegalStateException("쿼리 전송 실패");
		}
	}
}
