package test.rabbitmq.demo.ps;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.utils.ConnectionUtil;

/**
 * Title:Send
 * Description:消息的发布订阅模式
 * 
 * @company sinobest
 * @author pangqianyi
 * @date 2016年8月30日 上午12:21:40
 * 
 */
public class Send {

	private final static String EXCHANGE_NAME = "test_exchange_fanout";// 定义交换机的名称

	public static void main(String[] args) throws Exception{
		
		Connection connection = ConnectionUtil.getConnection();
		Channel channel = connection.createChannel();//创建通道
		
		//声明exchange（交换机），参数1：交换机的名称，参数2：交换机的类型（ exchange types available: direct, topic, headers and fanout）
		channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//广播模式的交换机，发送消息到绑定的序列上
		
		//消息的内容
		String message ="Hello  World! fanout demo !";
		//发布消息
		channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
		
		System.out.println("[x] sent '"+message+"'");
		
		channel.close();
		connection.close();
	}

}
