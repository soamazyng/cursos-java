package com.upmasters.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsString;

public class EnvioDadosTest {


  @Test
  void deveEnviarValorViaQuery(){

    given()
          .log().all() // coloca o log pois não podemos testar no browser
        .when()
          .get("https://restapi.wcaquino.me/v2/users?format=json")
        .then()
          .log().all()
          .statusCode(200)
          .contentType(ContentType.JSON)
          .contentType(containsString("utf-8"))
    ;
  }

  @Test
  void deveEnviarValorViaParams(){

    given()
        .log().all() // coloca o log pois não podemos testar no browser
          .queryParam("format", "json")
        .when()
          .get("https://restapi.wcaquino.me/v2/users")
        .then()
          .log().all()
          .statusCode(200)
          .contentType(ContentType.JSON)
          .contentType(containsString("utf-8"))
    ;
  }


  @Test
  void deveEnviarValorViaHeader(){

    given()
          .log().all() // coloca o log pois não podemos testar no browser
          .accept(ContentType.JSON)
        .when()
          .get("https://restapi.wcaquino.me/v2/users")
        .then()
          .log().all()
          .statusCode(200)
          .contentType(ContentType.JSON)
          .contentType(containsString("utf-8"))
    ;

  }


}
