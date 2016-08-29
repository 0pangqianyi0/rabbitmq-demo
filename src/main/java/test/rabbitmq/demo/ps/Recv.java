package test.rabbitmq.demo.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * 发布订阅模式
 * <p>Title:Recv</p>
 * <p>Description:消息的消费者</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月30日 上午12:32:00
 * 
 */
public class Recv {

	private final static String EXCHANGE_NAME = "test_exchange_fanout";// 定义交换机的名称
	private final static String QUEUE_NAME = "test_queue_name";
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//绑定到交换机上
		channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");
		
		//同一时刻服务器只会发送一条消息给消费者
		channel.basicQos(1);
		
		//定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//进入监听模式
		channel.basicConsume(QUEUE_NAME,consumer);
		
		while(true){
			byte[] body = consumer.nextDelivery().getBody();
			String message = new String(body);
			 
			System.out.println("[x] recv  "+message);
		}
		   
	}
}
