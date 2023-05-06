Feature:Usuario inicia sesión con correo y contraseña

  Como usuario cliente quiero iniciar sesión con mi cuenta y contraseña para ingresar a la aplicación

  Scenario: El usuario ingresa bien su correo y su contraseña y cuenta con conexión a internet
    Given el usuario se encuentre en la sección de inicio de sesión e ingresa su correo y contraseña ya registradas correctamente
    When presione el botón Iniciar sesión
    Then ingresará a su cuenta exitosamente

  Scenario: El usuario ingresa mal su correo y contraseña y cuenta con conexión a internet.
    Given el usuario está en la sección de inicio de sesión e ingresa mal su correo o contraseña
    When presione el botón Iniciar Sesión
    Then no podrá iniciar sesión y le pedirá que ingrese su contraseña o la recupere
