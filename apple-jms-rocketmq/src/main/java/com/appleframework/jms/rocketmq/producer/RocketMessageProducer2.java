package com.appleframework.jms.rocketmq.producer;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.rocketmq.client.exception.MQBrokerException;
import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import com.alibaba.rocketmq.remoting.exception.RemotingException;
import com.appleframework.jms.core.exception.MQException;
import com.appleframework.jms.core.producer.MessageProducer2;
import com.appleframework.jms.core.utils.ByteUtils;
import com.appleframework.jms.rocketmq.RocketMQProducer;

/**
 * @author Cruise.Xu
 * 
 */
public class RocketMessageProducer2 implements MessageProducer2 {
	
	private final static Logger logger = LoggerFactory.getLogger(RocketMessageProducer2.class);

	private RocketMQProducer producer;
	private String tag, key;	

	public void sendByte(String topic, byte[] message) throws MQException {
        Message msg = new Message(topic, tag, key, message);
        try {
        	SendResult result = producer.send(msg);
        	logger.info("msgId=" + result.getMsgId());
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			throw new MQException(e);
		}
	}

	public void sendObject(String topic, Serializable message) throws MQException {		
		Message msg = new Message(topic, tag, key, ByteUtils.toBytes(message));
		try {
			SendResult result = producer.send(msg);
			logger.info("msgId=" + result.getMsgId());
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			throw new MQException(e);
		}
	}

	public void sendText(String topic, String message) throws MQException {		
		Message msg = new Message(topic, tag, key, ByteUtils.toBytes(message));
		try {
			SendResult result = producer.send(msg);
			logger.info("msgId=" + result.getMsgId());
		} catch (MQClientException | RemotingException | MQBrokerException
				| InterruptedException e) {
			throw new MQException(e);
		}
	}

	public void setProducer(RocketMQProducer producer) {
		this.producer = producer;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
