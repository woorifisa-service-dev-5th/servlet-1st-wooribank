package com.woori_bank.be.util;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppStartup implements ServletContextListener {
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// 데이터베이스 초기화
		ConnectionPool.init(sce.getServletContext());
		DatabaseInitializer.init(sce.getServletContext());
	}
}
