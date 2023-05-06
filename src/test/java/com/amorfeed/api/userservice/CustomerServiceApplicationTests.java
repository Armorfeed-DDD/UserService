package com.amorfeed.api.userservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@SpringBootTest
@CucumberOptions(
        plugin = { "pretty"},
        features = { "src/test/resources/features"},
        glue = { "com.amorfeed.api.userservice.steps" }
)
class CustomerServiceApplicationTests {

    @Test
    void contextLoads() {
    }

}
