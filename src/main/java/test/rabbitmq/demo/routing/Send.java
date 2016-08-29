package test.rabbitmq.demo.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * Title:Send
 * Description:路由模式
 * @company sinobest
 * @author pangqianyi
 * @date 2016年8月30日 上午12:52:56
 * 
 */
public class Send {

	//交换机的名称，接收消息的时候，交换机与队列的名称绑定，会更具交换机的名称
	private static final String EXCHANGE_NAME = "test_exchange_direct";
	

	public static void main(String[] args) throws Exception{
		//获取连接
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		//声明交换机，模式为direct
		channel.exchangeDeclare(EXCHANGE_NAME, "direct");
		
		//channel.queueBind(queueName, EXCHANGE_NAME, "");
		
		//消息内容
		String message = "Hello World!" ;
		String routingKey = "key2"; //发送到序列的路由值
		channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());

		/*
		String queueName = channel.queueDeclare().getQueue();
		System.out.println(queueName);
		*/
		
		System.out.println("[x] send "+ message);
		channel.close();
		connection.close();
	}
}
