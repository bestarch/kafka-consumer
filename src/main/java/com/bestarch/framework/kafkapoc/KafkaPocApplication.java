package com.bestarch.framework.kafkapoc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bestarch.framework.kafkapoc.bean.Request;
import com.bestarch.framework.kafkapoc.service.DummyService;
import com.hazelcast.core.HazelcastInstance;

/**
 * 
 * @author bestarch
 *
 */
@SpringBootApplication
@RestController
public class KafkaPocApplication { //implements CommandLineRunner {
	
	@Autowired
    private HazelcastInstance instance; 
	
	@Autowired
	DummyService service;

	public static void main(String[] args) {
		SpringApplication.run(KafkaPocApplication.class, args);
	}
	
	
	/*@Override
	public void run(String... args) throws Exception {
		System.out.println("Inside run method...");
		Map<Integer,Request> map = instance.getMap("request");
		Request request = null;
		for (int i=0;i<4;i++) {
			request = new Request(i, "requestName"+i, "details"+i, i*10.0+1, "ser"+i+"00", "miscDetails"+i, i+1);
			map.put(i, request);
		}
		
	}*/
	
	@GetMapping("/request")
	public ResponseEntity<Request> getRequest(@RequestParam int id) {
		long t1 = System.currentTimeMillis();
		System.out.println("Inside getRequest() controller method");
		Request request = service.getRequest(id);
		ResponseEntity<Request> res = new ResponseEntity<Request>(request, HttpStatus.OK);
		System.out.println("********* Time taken to get request object from Hazelcast ********* "+request.getRequestId()+": "+(System.currentTimeMillis()-t1));
		return res;
	}
	
	@PostMapping("/request")
	public ResponseEntity<Request> postRequest(@RequestBody Request request) {
		long t1 = System.currentTimeMillis();
		System.out.println("Inside postRequest() controller method");
		service.postToHazelcast(request);
		ResponseEntity<Request> res = new ResponseEntity<Request>(request, HttpStatus.CREATED);
		System.out.println("********* Time taken to send request directly to Hazelcast from Client ********* "+request.getRequestId()+": "+(System.currentTimeMillis()-t1));
		return res;
	}


}
