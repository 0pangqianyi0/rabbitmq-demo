package test.rabbitmq.demo.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * <p>Title:Sender</p>
 * <p>Description:消息队列的发送者</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月28日 上午1:27:49
 * 
 */
public class Sender {
	private final static String QUEUE_NAME = "queue_name_hello_world"; //创建队列的名称
	
	public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
		Connection connection = ConnectionUtil.getConnection();//创建连接
		Channel channel =connection.createChannel();//创建通道
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    int i=0;
	    do{
	    	String message = "["+i+"]"+"Hello World!";
	    	channel.basicPublish("", QUEUE_NAME, null, message.getBytes());//想队列发送数据
	    	System.out.println("Sent '" + message + "'");
	    	Thread.sleep(100);
	    	i++;
	    }while(i<50);
	    //关闭通道和连接
	    channel.close();
	    connection.close();
	}

}
