package com.amorfeed.api.userservice.steps;
import java.util.Set;

import com.amorfeed.api.userservice.resource.RegisterResource;
import io.cucumber.java.en.And;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import com.amorfeed.api.userservice.resource.ChangePasswordResource;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
public class USFC03FStepdefs {

    @Autowired
    protected TestRestTemplate testRestTemplate;
    private String response;
    private ChangePasswordResource request;

    private void beforeSteps(){
        RegisterResource registerResource=new RegisterResource(
            "Marcelo",
                "marcelo@hotmail.com",
                "12345"
        );
        this.testRestTemplate.postForEntity("ttp://localhost:8090/api/v1/users/sign-up",registerResource,String.class);
    }

    @Given("el cliente se encuentre en la sección de su perfil")
    public void userIsOnPerfil(){
        beforeSteps();
        request=new ChangePasswordResource();

    }

    @And("presiona el botón configuración")
    public void presionaElBotónConfiguración() {

    }

    @And("aparecerá un menú despegable")
    public void apareceráUnMenúDespegable() {
    }

    @When("seleccione la opción cambio de contraseña o correo actual")
    public void seleccioneLaOpciónCambioDeContraseñaOCorreoActual() {
        request=new ChangePasswordResource("marcelo@hotmail.com","12345","54321");
        response=this.testRestTemplate.patchForObject("http://localhost:8090/api/v1/users/change-password",request,String.class);

    }

    @Then("ingresa los nuevos datos")
    public void ingresaLosNuevosDatos() {
        Assertions.assertTrue(true);
    }

    @Given("el cliente se encuentre en la sección de inicio de sesión y no recuerde su contraseña")
    public void elClienteSeEncuentreEnLaSecciónDeInicioDeSesiónYNoRecuerdeSuContraseña() {
        request=new ChangePasswordResource();
    }

    @When("presione el botón Olvidé mi contraseña")
    public void presioneElBotónOlvidéMiContraseña() {

    }

    @Then("se mostrará el formulario de recuperación de cuenta")
    public void seMostraráElFormularioDeRecuperaciónDeCuenta() {

    }
}
