package com.upmasters.rest;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserJsonTest {

  @Test
  public void deveVerificarPrimeiroNivel(){

    given()
        .when()
        . get("https://restapi.wcaquino.me/users/1")
        .then()
        .assertThat()
          .statusCode(200)
          .body("id", is(1))
          .body("name", containsString("Silva"))
          .body("age", greaterThan(18))
    ;

  }

  @Test
  public void deveVerificarPrimeiroNivelOutrasFormas(){

    Response response = RestAssured.get("https://restapi.wcaquino.me/users/1");

    var path = response.path("id");

    assertEquals(1, path);

  }

  @Test
  public void deveVerificarSegundoNivel(){

    given()
        .when()
        . get("https://restapi.wcaquino.me/users/2")
        .then()
        .assertThat()
        .statusCode(200)
        .body("id", is(2))
        .body("name", containsString("Joaquina"))
        .body("age", greaterThan(18))
        .body("endereco.rua", is("Rua dos bobos"))
    ;

  }

}
