package me.lv.controller;

import me.lv.constant.Constant;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author lv
 */
@RestController
public class SendController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/send")
    public String send() {
        String content = "Date: " + new Date();
        rabbitTemplate.convertAndSend(Constant.EXCHANGE_KEY, Constant.RECEIVER_KEY, content);
        return content;
    }


    @GetMapping("/multiSend")
    public String multiSend() {
        StringBuilder times = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            long time = System.nanoTime();
            rabbitTemplate.convertAndSend(Constant.EXCHANGE_KEY, Constant.RECEIVER_KEY, "第" + i + "次发送的时间：" + time);
            times.append(time + "<br>");
        }
        return times.toString();
    }

}
