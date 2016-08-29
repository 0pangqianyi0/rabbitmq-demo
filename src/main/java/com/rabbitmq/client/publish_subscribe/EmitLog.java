package com.rabbitmq.client.publish_subscribe;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * 数据的发送
 * <p>Title: EmitLog</p>
 * <p>Description: </p>
 * <p>Company: sinobest </p> 
 * @author	pangqianyi
 * @date	2016年8月30日上午12:55:46
 * @version 1.0
 */
public class EmitLog {

	private static final String EXCHANGE_NAME = "logs";//交换机的名称

	public static void main(String[] argv) throws Exception {
		Connection connection =ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();

		//fanout  类型为广播模式
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//定义交换机交换机,交换机的类型：direct, topic, headers , fanou

		String message = getMessage(argv);

		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes("UTF-8"));
		System.out.println(" [x] Sent '" + message + "'");

		channel.close();
		connection.close();
	}

	private static String getMessage(String[] strings) {
		if (strings.length < 1)
			return "info: Hello World!";
		return joinStrings(strings, " ");
	}

	private static String joinStrings(String[] strings, String delimiter) {
		int length = strings.length;
		if (length == 0)
			return "";
		StringBuilder words = new StringBuilder(strings[0]);
		for (int i = 1; i < length; i++) {
			words.append(delimiter).append(strings[i]);
		}
		return words.toString();
	}
}
