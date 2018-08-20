package com.bestarch.framework.kafkapoc.service;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.bestarch.framework.kafkapoc.bean.Request;
import com.bestarch.framework.kafkapoc.config.TestController;
import com.hazelcast.core.HazelcastInstance;

@Service
//@CacheConfig(cacheNames = "request")
public class DummyService {
	
	@Autowired
    private HazelcastInstance instance; 
	
	@Autowired
	private TestController controller;
	
	@Autowired
	private SimpMessagingTemplate messagingTemplate;

	@KafkaListener(topics = "test")
	public void receive(@Payload Request data, @Headers MessageHeaders headers) throws InterruptedException {
		long t1 = System.currentTimeMillis();
		System.out.println("received data:: " + data);
		Map<Integer,Request> map = instance.getMap("request");
		
		data.setCost(data.getCost()+100);
		data.setDetails(data.getDetails()+":: added from server");
		data.setSerialNumber(data.getSerialNumber()+"::PRC");
		data.setCount(data.getCount()+100);
		
		map.put(data.getRequestId(), data);
		headers.keySet().forEach(key -> {
			System.out.println("[" + key + "], Val: " + headers.get(key));
		});
		
		messagingTemplate.convertAndSend("/topic/processedRequest", data);
		
		System.out.println("********* Time taken to process request from Kafka to Hazelcast ********* "+data.getRequestId()+": "+(System.currentTimeMillis()-t1));
	}
	
	public void postToHazelcast(Request data) {
		System.out.println("received data:: " + data);
		Map<Integer,Request> map = instance.getMap("request");
		map.put(data.getRequestId(), data);
	}


	//@Cacheable
	public Request getRequest(int id) {
		Map<Integer,Request> map = instance.getMap("request");
		Set<Map.Entry<Integer, Request>> entrySet = map.entrySet();
		Iterator<Map.Entry<Integer, Request>> iter = entrySet.iterator();
		while (iter.hasNext()) {
			Entry<Integer, Request> entry = iter.next();
			System.out.println(entry.getKey()+" :: "+entry.getKey().toString());
		}
		Request req = map.get(id);
		System.out.println(req.toString());
		return req;
	}

}
