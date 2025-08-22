package com.woori_bank.be.util.init;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.servlet.ServletContext;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPool {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	private static final String RESOURCES = "/WEB-INF/properties/";
	private static final String scenario_V1 = "application-database.properties";

	public static void init(ServletContext sc){
		driverLoad();
		Properties properties = new Properties();
		try (InputStream fis = sc.getResourceAsStream(RESOURCES+scenario_V1)) {
			properties.load(fis);

			// 필수 설정값 적용
			config.setJdbcUrl(properties.getProperty("jdbcUrl"));
			config.setUsername(properties.getProperty("username"));
			config.setPassword(properties.getProperty("password"));

			// HikariCP 설정 적용
			for (String key : properties.stringPropertyNames()) {
				String value = properties.getProperty(key);
				value = value.split("#")[0].trim();

				switch (key) {
				case "maximumPoolSize":
					System.out.println(value);
					config.setMaximumPoolSize(Integer.parseInt(value));
					break;
				case "minimumIdle":
					config.setMinimumIdle(Integer.parseInt(value));
					break;
				case "connectionTimeout":
					config.setConnectionTimeout(Long.parseLong(value));
					break;
				case "idleTimeout":
					config.setIdleTimeout(Long.parseLong(value));
					break;
				case "keepaliveTime":
					config.setKeepaliveTime(Long.parseLong(value));
					break;
				case "maxLifetime":
					config.setMaxLifetime(Long.parseLong(value));
					break;
				default:
					config.addDataSourceProperty(key, value);
					break;
				}
			}

			printConfigProperties(config);

		} catch (IOException e) {
			throw new RuntimeException("Failed to load hikari.properties", e);
		}
		ds = new HikariDataSource(config);
	}
	
	private static void driverLoad() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}

	private ConnectionPool() {
	}

	public static void printConfigProperties(HikariConfig config) {
		System.out.println();
		System.out.println("HikariCP 설정 정보");
		System.out.printf("Maximum Pool Size: %d (최대 커넥션 개수)%n", config.getMaximumPoolSize());
		System.out.printf("Minimum Idle: %d (최소 유휴 커넥션 개수)%n", config.getMinimumIdle());
		System.out.printf("Connection Timeout: %d ms (커넥션 할당 만료시간)%n", config.getConnectionTimeout());
		System.out.printf("Idle Timeout: %d ms (유휴 만료시간)%n", config.getIdleTimeout());
		System.out.printf("Keepalive Time: %d ms (Keepalive 만료시간)%n", config.getKeepaliveTime());
		System.out.printf("Max Lifetime: %d ms (최대 수명주기)%n", config.getMaxLifetime());
		System.out.println();
	}
}
