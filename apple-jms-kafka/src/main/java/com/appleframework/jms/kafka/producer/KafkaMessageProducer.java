package com.appleframework.jms.kafka.producer;

import java.io.Serializable;

import kafka.javaapi.producer.Producer;
import kafka.producer.KeyedMessage;

import com.appleframework.jms.core.exception.JmsException;
import com.appleframework.jms.core.exception.MQException;
import com.appleframework.jms.core.producer.MessageProducer;
import com.appleframework.jms.core.utils.ByteUtils;

/**
 * @author Cruise.Xu
 * 
 */
@SuppressWarnings("deprecation")
public class KafkaMessageProducer implements MessageProducer {

	private Producer<String, byte[]> producer;
	
	private String topic;
	
	private String key;
	
	public void setProducer(Producer<String, byte[]> producer) {
		this.producer = producer;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public void setKey(String key) {
		this.key = key;
	}

	public void sendByte(byte[] message) throws JmsException {
		try {
			KeyedMessage<String, byte[]> producerData 
				= new KeyedMessage<String, byte[]>(topic, key, message);
				producer.send(producerData);
		} catch (Exception e) {
			throw new MQException(e);
		}
		
	}

	@Override
	public void sendObject(Serializable message) throws JmsException {
		try {
		KeyedMessage<String, byte[]> producerData 
			= new KeyedMessage<String, byte[]>(topic, key, ByteUtils.toBytes(message));
		producer.send(producerData);
		} catch (Exception e) {
			throw new MQException(e);
		}
	}

	@Override
	public void sendText(String message) throws JmsException {
		try {
			KeyedMessage<String, byte[]> producerData 
				= new KeyedMessage<String, byte[]>(topic, key, message.getBytes());
			producer.send(producerData);
		} catch (Exception e) {
			throw new MQException(e);
		}
	}

}