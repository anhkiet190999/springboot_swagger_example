package security.springframework;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import security.springframework.controllers.ContactController;


@SpringBootApplication
public class SpringBootWebApplication {
    //private static final Logger logger = LogManager.getLogger(SpringBootWebApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebApplication.class, args);

//        logger.info("Info level log message");
//        logger.debug("Debug level log message");
//        logger.error("Error level log message");
    }
}
