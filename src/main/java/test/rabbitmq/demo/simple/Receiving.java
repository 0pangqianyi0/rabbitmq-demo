package test.rabbitmq.demo.simple;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * <p>Title:Receiving</p>
 * <p>Description:</p>
 * @company sinobest 
 * @author pangqianyi
 * @date 2016年8月28日 上午1:43:10
 * 
 */
public class Receiving {

	private final static String QUEUE_NAME = "queue_name_hello_world";//队列的名称，要存在创建的的队列

	  @SuppressWarnings("all")
	public static void main(String[] argv)  throws java.io.IOException, java.lang.InterruptedException, TimeoutException {

	    Connection connection = ConnectionUtil.getConnection();
	    Channel channel = connection.createChannel();
	    //队列的声明
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
	    //消息队列的消费者,一直处于监听状态
	    Consumer consumer = new DefaultConsumer(channel) {
	    	private int i=0;
	        @Override
	        public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body)
	            throws IOException {
	        	
	        	i++;
	        	//System.out.println("当前线程数："+Thread.currentThread().activeCount());
	        	String message = new String(body, "UTF-8");
	        	System.out.println("Received '" + message + "'");
	        	/*try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}*/
	        }
	      };
	      //监听队列
	      channel.basicConsume(QUEUE_NAME, true, consumer);
	      
	     /* QueueingConsumer consumer2 = new QueueingConsumer(channel);
	      channel.basicConsume(QUEUE_NAME, true, consumer2);
	     
	      long i=0;
	      while(true){
	    	  i++;
	    	  System.out.println("当前线程数："+Thread.currentThread().activeCount());
	    	  QueueingConsumer.Delivery nextDelivery = consumer2.nextDelivery();//阻塞状态
	    	  byte[] body = nextDelivery.getBody();
	    	  String message = new String(body);
	    	  System.out.println(" ["+i+"] Received '" + message + "'");
	      }*/
	      
	      
	}
}
