package test.rabbitmq.demo.worker;

import java.io.IOException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * <p>Title:Recv</p>
 * <p>Description:</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月28日 下午2:45:35
 * 
 */
public class Recv {

	private static final String QUEUE_NAME="test_queue_worke";
	
	public static void main(String[] args) throws Exception {
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();
		
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		
		//声明同一时刻服务器只 会发送一条消息给消费者
		channel.basicQos(1);
//		int prefetchCount = 2;
//		channel.basicQos(prefetchCount);
		
		//定义消费者
		QueueingConsumer consumer = new QueueingConsumer(channel);
		
		//鉴定队列，false为手动返回完成状态，设置为true的时候为自动反馈状态
		channel.basicConsume(QUEUE_NAME, false, consumer);
		
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String message = new String(delivery.getBody());
			System.out.println(" [x] Received '" +message+"'");
			
			Thread.sleep(2000);
			
			//返回确认状态
			channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
		}
		
		
	}

}
