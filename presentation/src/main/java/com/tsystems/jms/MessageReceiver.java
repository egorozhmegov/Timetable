package com.tsystems.jms;

import com.tsystems.jsf.bean.ScheduleBean;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import javax.websocket.*;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.logging.Logger;

@ServerEndpoint(value = "/message")
public class MessageReceiver {

    private static final String URL = ActiveMQConnection.DEFAULT_BROKER_URL;
    private static final String SUBJECT = "RWS_QUEUE";

    private Logger logger = Logger.getLogger(this.getClass().getName());
    private ConnectionFactory connectionFactory;
    private Connection connection;

    @OnOpen
    public void onOpen(final Session session) throws JMSException {
        logger.info("Connected ... " + session.getId());
        connectionFactory = new ActiveMQConnectionFactory(URL);
        connection = connectionFactory.createConnection();
        connection.start();

        javax.jms.Session mqsession = connection.createSession(false,
                javax.jms.Session.AUTO_ACKNOWLEDGE);

        Queue destination = mqsession.createQueue(SUBJECT);

        MessageConsumer consumer = mqsession.createConsumer(destination);

        MessageListener listener = new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    if (message instanceof TextMessage) {
                        TextMessage textMessage = (TextMessage) message;
                        session.getAsyncRemote().sendText(textMessage.getText());
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
    }

    @OnMessage
    public void shout(String text, Session session) throws JMSException {

    }

    @OnClose
    public void onClose(Session session, CloseReason closeReason) throws JMSException {
        connection.close();
        logger.info(String.format("Session %s closed because of %s", session.getId(), closeReason));
    }

}
