Feature: Usuario nuevo se registra en la aplicación

  Como usuario cliente quiero registrarme en la aplicación web para acceder a los servicios que ofrece

Scenario: El cliente introduce los datos requeridos
  Given el cliente está en la sección del inicio de sesión de la aplicación
  And ha presionado el botón de Registrarse
  And se le ha enviado a la sección de formulario de registro
  When   ingrese los datos correctamente
  And presione el botón de Enviar
  Then devolverá el mensaje Todo correcto

  Scenario: El cliente no introduce todos los datos
    Given el cliente está en la sección del inicio de sesión de la aplicación
    And ha presionado el botón de Registrarse
    And se le ha enviado a la sección de formulario de registro
    When   ingrese los datos incorrectamente
    And presione el botón de Enviar
    Then devolverá el mensaje Ingrese los datos correctamente