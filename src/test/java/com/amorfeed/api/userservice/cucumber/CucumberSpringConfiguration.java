package com.amorfeed.api.userservice.cucumber;
import com.amorfeed.api.userservice.UserServiceApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootContextLoader;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
@CucumberContextConfiguration
@SpringBootTest(classes = UserServiceApplication.class,
                webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = UserServiceApplication.class,loader = SpringBootContextLoader.class)
public class CucumberSpringConfiguration {

}
