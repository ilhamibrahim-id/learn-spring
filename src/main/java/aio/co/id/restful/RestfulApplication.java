package aio.co.id.restful;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RestfulApplication {

	private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(RestfulApplication.class);

	public static void main(String[] args) {
		logger.info("Starting Restful Application...");
		SpringApplication.run(RestfulApplication.class, args);
	}

}
