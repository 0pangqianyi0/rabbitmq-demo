package test.rabbitmq.demo.routing;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * <p>Title:Recv</p>
 * <p>Description:</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月30日 上午1:11:36
 * 
 */
public class Recv2 {

	private final static String QUEUE_NAME = "test_queue_name";
	private final static String EXCHANGE_NAME = "test_exchange_direct";
 
	public static void main(String[] args) throws Exception{
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false,false,false, null);
		
		String routingKey ="key2"; //绑定到发送消息的生产者指定的key ，也就是路由模式,key不同，则不会接受
		//绑定队列到交换机
		channel.queueBind(QUEUE_NAME,EXCHANGE_NAME, routingKey );
		
		//同一时刻服务器只会发一条消息给消费者
		channel.basicQos(1);
		
		//定义队列的消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//监听队列，手动返回完成
		channel.basicConsume(QUEUE_NAME, false,consumer);
		
		//获取消息
		while (true) {
			byte[] body = consumer.nextDelivery().getBody();
			String message = new String(body);
			System.out.println("[x] resv "+message);
		}
		
	}
}
