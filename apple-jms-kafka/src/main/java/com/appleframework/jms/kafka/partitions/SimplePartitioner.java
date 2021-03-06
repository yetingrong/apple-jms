package com.appleframework.jms.kafka.partitions;

import com.appleframework.jms.kafka.utils.RandomUtility;
import com.appleframework.jms.kafka.utils.StringUtils;

import kafka.producer.Partitioner;
import kafka.utils.VerifiableProperties;

/**
 * @author cruise.xu
 * 
 */
@SuppressWarnings("deprecation")
public class SimplePartitioner implements Partitioner {

	public SimplePartitioner(VerifiableProperties props) {
	}

	@Override
	public int partition(Object key, int numPartitions) {
		if (StringUtils.isEmpty(key) || key.equals("-1")) {
			return RandomUtility.genRandom(numPartitions);
		} else {
			int partitionKey = key.hashCode();
			int partition = partitionKey % numPartitions;
			return Math.abs(partition);
		}
	}

}
