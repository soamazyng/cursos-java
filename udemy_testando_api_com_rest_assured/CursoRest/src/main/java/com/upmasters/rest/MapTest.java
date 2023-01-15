package com.upmasters.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MapTest {

  @Test
  void deveSalvarUsuarioUsandoMap() {

    Map<String, Object> params = new HashMap<>();
    // add elements dynamically
    params.put("name", "Lem");
    params.put("age", 46);
    params.put("salary", 10000L);

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(params)
        .when()
        .post("https://restapi.wcaquino.me/users")
        .then()
        .log().all()
        .statusCode(201)
        .body("id", is(notNullValue()))
        .body("name", equalTo(params.get("name")))
        .body("age", equalTo(params.get("age")))
        .body("salary", equalTo(Integer.parseInt(params.get("salary").toString())))

    ;

  }

  @Test
  void deveSalvarUsuarioUsandoObjeto() {

    User user = User.builder()
        .name("Meu nome")
        .age(37)
        .salary(10000L)
        .build();

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(user)
        .when()
        .post("https://restapi.wcaquino.me/users")
        .then()
        .log().all()
        .statusCode(201)
        .body("id", is(notNullValue()))
        .body("name", equalTo(user.getName()))
        .body("age", equalTo(user.getAge()))
        .body("salary", equalTo(Integer.parseInt(user.getSalary().toString())))
    ;

  }

  @Test
  void deveDeserializarObjetoAoSalvarUsuario() {

    User user = User.builder()
        .name("Meu nome")
        .age(37)
        .salary(10000L)
        .build();

    User response = given()
        .log().all() // coloca o log pois não podemos testar no browser
          .contentType(ContentType.JSON)
          .body(user)
        .when()
          .post("https://restapi.wcaquino.me/users")
        .then()
          .log().all()
          .statusCode(201)
          .extract().body().as(User.class);

    assertNotNull(response.getId());
    assertEquals(user.getName(), response.getName());
    assertEquals(user.getAge(), response.getAge());
    assertEquals(user.getSalary(), response.getSalary());

  }



}
