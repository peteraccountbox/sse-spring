package com.engagebay.ssedemo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
public class SSEController {

    @RequestMapping(value = "/hello")
    public @ResponseBody  String sayHello(){
        return "hello";
    }

    @RequestMapping(value = "/sse-emitter")
    public SseEmitter streamSseMvc(@RequestParam("data") String data, @RequestParam("name")String name) {
        SseEmitter emitter = new SseEmitter();
        ExecutorService sseMvcExecutor = Executors.newSingleThreadExecutor();
        sseMvcExecutor.execute(() -> {
            try {
                for (int i = 0; i < data.toCharArray().length; i++) {
                    SseEmitter.SseEventBuilder event = SseEmitter.event()
                            .data(data.charAt(i))
                            .id(String.valueOf(i))
                            .name(name);
                    emitter.send(data.charAt(i));
                    Thread.sleep(1000);
                }
                emitter.complete();
            } catch (Exception ex) {
                emitter.completeWithError(ex);
            }

        });
       emitter.onCompletion(() -> {
           sseMvcExecutor.shutdown();
       });
        return emitter;
    }

    @RequestMapping("/index")
    public String getIndex()
    {
        return "index";
    }
}
