package me.lv.receiver;

import me.lv.constant.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.stereotype.Component;

/**
 * @author lv
 */
@Component
public class Receiver {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @RabbitListener(
            bindings = @QueueBinding(
                    key = Constant.RECEIVER_KEY,
                    value = @Queue(value = "queue.test", durable = "true", autoDelete = "false"),
                    exchange = @Exchange(value = Constant.EXCHANGE_KEY, type = ExchangeTypes.TOPIC)
            )
    )
    public void receiver(String msg) {
        logger.info(">>>>>> receiver : {}", msg);
    }
}
