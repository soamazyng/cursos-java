package com.upmasters.rest;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class VerbosTest {

  @Test
  void deveSalvarUsuario(){

    Map<String, Object> person =
        new HashMap<>();

    // add elements dynamically
    person.put("name", "Lem");
    person.put("age", 46);
    person.put("salary", 10000L);

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(person)
        .when()
        .post("https://restapi.wcaquino.me/users")
        .then()
        .log().all()
        .statusCode(201)
        .body("id", is(notNullValue()));

  }

  @Test
  void naoDeveSalvarUsuarioSemNome(){

    Map<String, Object> person =
        new HashMap<>();

    // add elements dynamically
    person.put("age", 46);
    person.put("salary", 10000L);

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(person)
        .when()
        .post("https://restapi.wcaquino.me/users")
        .then()
        .log().all()
        .statusCode(400)
        .body("id", is(nullValue()))
        .body("error", is("Name é um atributo obrigatório"))
        ;

  }


  @Test
  void deveAlterarUsuario(){

    Map<String, Object> person =
        new HashMap<>();

    // add elements dynamically
    person.put("name", "Lem123");
    person.put("age", 56);
    person.put("salary", 20000L);

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(person)
        .when()
        .put("https://restapi.wcaquino.me/users/1")
        .then()
        .log().all()
        .statusCode(200)
        .body("name", is(person.get("name")))
        .body("age", is(person.get("age")))
    ;

  }

  @Test
  void deveCustomizarUrl(){

    Map<String, Object> person =
        new HashMap<>();

    // add elements dynamically
    person.put("name", "Lem123");
    person.put("age", 56);
    person.put("salary", 20000L);

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(person)
        .when()
        .put("https://restapi.wcaquino.me/{entidade}/{userId}", "users", "1")
        .then()
        .log().all()
        .statusCode(200)
        .body("name", equalTo(person.get("name")))
        .body("age", equalTo(person.get("age")))
        .body("salary", equalTo(Integer.parseInt(person.get("salary").toString())))
    ;

  }

  @Test
  void deveCustomizarUrlParte2(){

    Map<String, Object> person =
        new HashMap<>();

    // add elements dynamically
    person.put("name", "Lem123");
    person.put("age", 56);
    person.put("salary", 20000L);

    given()
        .log().all() // coloca o log pois não podemos testar no browser
        .contentType(ContentType.JSON)
        .body(person)
        .pathParam("entidade", "users")
        .pathParam("userId", "1")
        .when()
          .put("https://restapi.wcaquino.me/{entidade}/{userId}")
        .then()
          .log().all()
        .assertThat()
          .statusCode(200)
          .body("name", is(person.get("name")))
          .body("age", is(person.get("age")))
          .body("salary", equalTo(Integer.parseInt(person.get("salary").toString())))
    ;

  }

  @Test
  void deveRemoverUsuario(){
    given()
        .log().all() // coloca o log pois não podemos testar no browser
          .pathParam("entidade", "users")
          .pathParam("userId", "1")
        .when()
          .delete("https://restapi.wcaquino.me/{entidade}/{userId}")
        .then()
          .log().all()
        .assertThat()
          .statusCode(204)
    ;

  }

  @Test
  void naoDeveRemoverUsuarioNaoExistente(){
    given()
        .log().all() // coloca o log pois não podemos testar no browser
          .pathParam("entidade", "users")
          .pathParam("userId", "1000")
        .when()
          .delete("https://restapi.wcaquino.me/{entidade}/{userId}")
        .then()
          .log().all()
        .assertThat()
          .statusCode(400)
          .body("error", is("Registro inexistente"))
    ;

  }





}
