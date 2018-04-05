package com.cognizant.developer.challenge.rabbitmq;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;

public class StandaloneTest {

	private static final String QUEUE_NAME = "TEST_QUEUE";

	public static void main(String[] args) {
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("localhost");
		Connection connection = null;
		Channel channel = null;
		try {
			connection = factory.newConnection();
			channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			String message = "Hello World!";
			channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
			System.out.println(" [x] Sent '" + message + "'");
			
			Consumer consumer = new DefaultConsumer(channel) {
				  @Override
				  public void handleDelivery(String consumerTag, Envelope envelope,
				                             AMQP.BasicProperties properties, byte[] body)
				      throws IOException {
				    String message = new String(body, "UTF-8");
				    System.out.println(" [x] Received '" + message + "'");
				  }
				};
				channel.basicConsume(QUEUE_NAME, true, consumer);

		} catch (IOException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(channel != null) channel.close();
				if(connection != null) connection.close();
			} catch (IOException | TimeoutException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
