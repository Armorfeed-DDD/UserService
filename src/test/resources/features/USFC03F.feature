Feature: Usuario cambia su correo o contraseña
  Scenario: Recuerda su correo y contraseña
    Given el cliente se encuentre en la sección de su perfil
    And presiona el botón configuración
    And aparecerá un menú despegable
    When seleccione la opción cambio de contraseña o correo actual
    Then ingresa los nuevos datos
  Scenario: No recuerda su contraseña o correo
    Given el cliente se encuentre en la sección de inicio de sesión y no recuerde su contraseña
    When presione el botón Olvidé mi contraseña
    Then se mostrará el formulario de recuperación de cuenta