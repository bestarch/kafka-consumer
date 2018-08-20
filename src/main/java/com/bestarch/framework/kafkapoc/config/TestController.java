package com.bestarch.framework.kafkapoc.config;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.bestarch.framework.kafkapoc.bean.Greeting;
import com.bestarch.framework.kafkapoc.bean.HelloMessage;
import com.bestarch.framework.kafkapoc.bean.Request;

@Controller
public class TestController {


    /*@MessageMapping("/requestObject")
    @SendTo("/topic/processedRequest")
    public Greeting getProcessedRequest(HelloMessage message) throws Exception {
        Thread.sleep(1000); 
        return new Greeting("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }
    
    @SendTo("/topic/processedRequest")
    public Request getProcessedRequest(Request req) throws InterruptedException {
        Thread.sleep(1000); 
        // May process other things here
        return req;
    }*/

}
