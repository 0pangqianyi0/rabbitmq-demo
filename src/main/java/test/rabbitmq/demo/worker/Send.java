package test.rabbitmq.demo.worker;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * <p>Title:Send</p>
 * <p>Description:消息发送</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月28日 下午2:38:17
 * 
 */
public class Send {
	
	private static final String QUEUE_NAME="test_queue_worke";
	
	public static void main(String[] args) throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false,false,false,null);
		
		int i =1;
		do{
			String message ="message : "+i;
			channel.basicPublish(""	,QUEUE_NAME, null, message.getBytes());
			System.out.println("[X] sent '" +message+"'");
			i++;
		//	Thread.sleep(i*10);
		}while(i<=130);
		
		 channel.close();
		 connection.close();
	}
}
