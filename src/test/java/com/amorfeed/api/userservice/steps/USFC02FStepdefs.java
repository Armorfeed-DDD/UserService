package com.amorfeed.api.userservice.steps;

import java.util.Set;

import com.amorfeed.api.userservice.resource.RegisterResource;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amorfeed.api.userservice.resource.LogInResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
public class USFC02FStepdefs {

    @Autowired
    protected TestRestTemplate testRestTemplate;
    private ResponseEntity<String> response;
    private LogInResource request;

    private void beforeSteps() {
        RegisterResource registerRequest = new RegisterResource(
                "miguel",
                "miguel@hotmail.com",
                "12345");
        this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/sign-up", registerRequest, String.class);
    }

    @Given("el usuario se encuentre en la sección de inicio de sesión e ingresa su correo y contraseña ya registradas correctamente")
    public void userEnterLogInDataSuccessfully() {
        beforeSteps();
        request = new LogInResource(
                "miguel@hotmail.com",
                "12345"
        );
    }
    @When("presione el botón Iniciar sesión")
    public void userTriesToLogIn() {
        response = this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/auth/sign-in", request, String.class);
    }
    @Then("ingresará a su cuenta exitosamente")
    public void userLoggedInSuccessfully() {
        Assertions.assertEquals(response.getStatusCode().value(), HttpStatus.OK.value());
    }

    @Given("el usuario está en la sección de inicio de sesión e ingresa mal su correo o contraseña")
    public void userEnterLogInDataIncorrectly(){
        request=new LogInResource("wrongEmail","wrongPassword");
    }
    @When("presione el botón Iniciar Sesión")
    public void userTriesToLoginWithWrongRequest(){
        response = this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/auth/sign-in", request, String.class);
    }

    @Then("no podrá iniciar sesión y le pedirá que ingrese su contraseña o la recupere")
    public void userLoggedInIncorrectly(){
        Assertions.assertNotSame(response.getStatusCode().value(), HttpStatus.OK.value());
    }
}
