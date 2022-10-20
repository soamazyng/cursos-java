package com.upmasters.rest;


import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class OlaMundoTest {

  @Test
  public void testOlaMundo(){

    Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/ola");
    Assertions.assertEquals(200, response.statusCode());

  }

  @Test
  public void devoConhecerOutrasFormas(){
    given()
        .when().get("http://restapi.wcaquino.me/ola")
        .then()
        .assertThat()
        .statusCode(200);
  }

  @Test
  public void devoConhecerMathcersHamcrest(){
    assertThat(true, is(true));
  }

  @Test
  public void devoValidarBody(){

    given()
        .when()
          .get("http://restapi.wcaquino.me/ola")
        .then()
          .assertThat()
            .statusCode(200)
            .body(is("Ola Mundo!"))
            .body(containsString("Mundo"))
            .body(is(not(nullValue())))
    ;
  }

}
