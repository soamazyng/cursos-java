package com.upmasters.rest;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class AuthTest {

  @Test
  public void deveAcessarSWAPI() {

    given()
        .log().all()
        .when()
          .get("https://swapi.dev/api/people/1")
        .then()
          .log().all()
          .statusCode(HttpStatus.SC_OK)
          .body("name", is("Luke Skywalker"))
        ;

  }

  @Test
  public void deveObterClima(){

    given()
        .log().all()
        .when()
          .queryParam("q", "Sao Paulo,BR")
          .queryParam("apikey", "6d9ed0aef50823e49f2849d07ab0dde6")
          .queryParam("units", "metric")
          .get("http://api.openweathermap.org/data/2.5/weather")
        .then()
          .log().all()
          .statusCode(HttpStatus.SC_OK)
          .body("name", is("São Paulo"))
    ;

  }

}
