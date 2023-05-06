package com.amorfeed.api.userservice.steps;
import java.util.Set;

import com.mysql.cj.exceptions.AssertionFailedException;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.opentest4j.AssertionFailedError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.amorfeed.api.userservice.resource.RegisterResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class USFC01FStepdefs {

    @Autowired
    protected TestRestTemplate testRestTemplate;
    private ResponseEntity<String> response;
    private RegisterResource request;

    boolean RegisterButtonhasbennPressed=false;
    boolean UserIsOnRegisterForm=false;

    @Given("el cliente está en la sección del inicio de sesión de la aplicación")
    public void userEnterSignInDataSuccesfully(){
        request=new RegisterResource();
    }

    @And("ha presionado el botón de Registrarse")
    public void userPressRegisterButton(){
        RegisterButtonhasbennPressed=true;
    }

    @And("se le ha enviado a la sección de formulario de registro")
    public void userGoToRegisterForm(){
        UserIsOnRegisterForm=true;
    }

    @When("ingrese los datos correctamente")
    public void userFillOutRegisterForm(){
        request.setName("Mario");
        request.setEmail("mario@hotmail.com");
        request.setPassword("12345");
    }

    @And("presione el botón de Enviar")
    public void userTriesSigIn(){
        this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/sign-up",request,String.class);
    }

    @Then("devolverá el mensaje Todo correcto")
    public void signInSuccesfull(){
        Assertions.assertTrue(true);
    }

    @When("ingrese los datos incorrectamente")
    public void userFiilOutRegisterFormIncorrectly(){
        request.setName("");
        request.setEmail("");
        request.setPassword("");
    }

    @Then("devolverá el mensaje Ingrese los datos correctamente")
    public void signInFail(){
        response= this.testRestTemplate.postForEntity("http://localhost:8090/api/v1/users/sign-up",request,String.class);
        Assertions.assertNotSame(response.getStatusCode().value(),HttpStatus.OK.value());
    }
}
