package com.tsystems.service.jms;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class MessageReceiver {

    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;

    private static final String SUBJECT = "RWS_QUEUE";

    public static void main(String[] args) throws URISyntaxException, Exception {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        Connection connection = connectionFactory.createConnection();
        connection.start();

        Session session = connection.createSession(false,
                Session.AUTO_ACKNOWLEDGE);

        Destination destination = session.createQueue(SUBJECT);

        MessageConsumer consumer = session.createConsumer(destination);

        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        System.out.println("Received message"
                                + textMessage.getText() + "'");
                    }
                } catch (JMSException e) {
                    System.out.println("Caught:" + e);
                    e.printStackTrace();
                }
            }
        };
        consumer.setMessageListener(listener);

        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        connection.close();
    }

}
