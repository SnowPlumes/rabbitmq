package me.lv;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lv
 */
@SpringBootApplication
public class RabbitApplication {

    private static Logger logger = LoggerFactory.getLogger(RabbitApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
        logger.info(">>>>>> RabbitApplication running ......");
    }
}
