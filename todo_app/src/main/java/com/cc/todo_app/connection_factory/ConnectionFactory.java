package com.cc.todo_app.connection_factory;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class ConnectionFactory {
	private static SqlSessionFactory factory;

	static {
		Reader reader = null;
		try {
			reader = Resources.getResourceAsReader("config/mybatis-config.xml");
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
		factory = new SqlSessionFactoryBuilder().build(reader);
	}

	public static SqlSessionFactory getSqlSessionFactory() {
		return factory;
	}
}
