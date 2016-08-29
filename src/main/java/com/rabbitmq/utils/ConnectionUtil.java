package com.rabbitmq.utils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * <p>Title:ConnectionUtil</p>
 * <p>Description:链接工厂类</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月28日 上午1:02:00
 * 
 */
public class ConnectionUtil {
	
	public static final String USER_NAME = "admin";//登录名
	public static final String PASSWORD = "admin";//登录的密码
	private static final String HOST = "127.0.0.1";//服务器地址
	private static final int PORT = 5672;//请求的端口号
	private static final String VIRTUAL_HOST = "admin";//队列的应用名
	

	public ConnectionUtil() {
	}
	
	/**
	 * <p>Title: getConnection</p>
	 * <p>Description:获取连接（rabbitmq） </p>
	 * @return
	 * @throws TimeoutException 
	 * @throws IOException 
	 */
	public static Connection getConnection() throws IOException, TimeoutException{
		ConnectionFactory connectionFactory = new ConnectionFactory();//链接工厂
		
		connectionFactory.setHost(HOST);
		connectionFactory.setPort(PORT);
		connectionFactory.setUsername(USER_NAME);
		connectionFactory.setPassword(PASSWORD);
		connectionFactory.setVirtualHost(VIRTUAL_HOST);
		Connection conn = connectionFactory.newConnection();
		/*下面同样的效果获取连接
		  	ConnectionFactory factory = new ConnectionFactory();
			factory.setUri("amqp://userName:password@hostName:portNumber/virtualHost");
			Connection conn = factory.newConnection();
		 */
		return conn;
	}

}
