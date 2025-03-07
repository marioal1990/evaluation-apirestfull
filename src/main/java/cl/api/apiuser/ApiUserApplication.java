package cl.api.apiuser;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiUserApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(ApiUserApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        app.run(args);
    }

}
