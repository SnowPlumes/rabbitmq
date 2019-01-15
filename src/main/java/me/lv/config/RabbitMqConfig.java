package me.lv.config;

import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lv
 */
@Configuration
public class RabbitMqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(new Jackson2JsonMessageConverter());
        return template;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        // 最大并发消费者数量
        factory.setMaxConcurrentConsumers(10);
        // 启动新消费者之间的时间间隔，单位为毫秒
        factory.setStartConsumerMinInterval(10000L);
        // 停止消费者的时间间隔, 由于最后一个消费者已经停止了，这时可以检测到空闲消费者
        factory.setStopConsumerMinInterval(60000L);
        // 消费者收到连续消息的最小数量，当考虑启动一个新的消费者，接收不会发生超时
        factory.setConsecutiveActiveTrigger(10);
        // 在考虑停止一个消费者，消费者必须经历的最小接收超时时间
        factory.setConsecutiveIdleTrigger(10);
        return factory;
    }
}
