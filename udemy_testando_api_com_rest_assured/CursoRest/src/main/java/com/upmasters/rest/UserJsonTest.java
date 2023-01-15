package com.upmasters.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserJsonTest {

  public static RequestSpecification reqSpec;
  public static ResponseSpecification resSpec;

  @BeforeAll
  public static void setup() {
    RestAssured.baseURI = "https://restapi.wcaquino.me";

    RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
    reqBuilder.log(LogDetail.ALL);
    reqSpec = reqBuilder.build();

    ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
    resBuilder.expectStatusCode(200);
    resSpec = resBuilder.build();

    // insere as specs em todos os testes
//    RestAssured.requestSpecification = reqSpec;
//    RestAssured.responseSpecification = resSpec;

  }

  @Test
  public void deveVerificarPrimeiroNivel() {

    given()
        .spec(reqSpec)
        .when()
        .get("/users/1")
        .then()
          .assertThat()
        .spec(resSpec)
            .body("id", is(1))
            .body("name", containsString("Silva"))
            .body("age", greaterThan(18))
    ;

  }

  @Test
  public void deveVerificarPrimeiroNivelOutrasFormas() {

    Response response = RestAssured.get("/users/1");

    var path = response.path("id");

    assertEquals(1, path);

  }

  @Test
  public void deveVerificarSegundoNivel() {

    given()
        .when()
          .get("/users/2")
        .then()
        .assertThat()
          .statusCode(200)
          .body("id", is(2))
          .body("name", containsString("Joaquina"))
          .body("age", greaterThan(18))
          .body("endereco.rua", is("Rua dos bobos"));

  }

  @Test
  public void deveVerificarLista() {

    given()
        .when()
          .get("/users/3")
        .then()
          .assertThat()
            .statusCode(200)
            .body("name", containsString("Ana"))
            .body("filhos", hasSize(2))
            .body("filhos[0].name", is("Zezinho"))
            .body("filhos[1].name", is("Luizinho"))
            .body("filhos.name", hasItems("Zezinho"))
            .body("filhos.name", hasItems("Zezinho", "Luizinho"))
    ;

  }

  @Test
  public void deveRetornarErroUsuarioNaoExiste() {

    given()
        .when()
        .get("/users/4")
        .then()
        .assertThat()
        .statusCode(404)
        .body("error", is("Usuário inexistente"))

    ;

  }

  @Test
  public void deveVerificarListaRaiz() {
    given()
        .when()
        .get("/users")
        .then()
        .assertThat().statusCode(200)
        .body("$", hasSize(3))
        .body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
        .body("age[1]", is(25))
        .body("filhos.name", hasItems(List.of("Zezinho", "Luizinho")))
        .body("salary", contains(1234.5678f, 2500, null))

    ;
  }

  @Test
  public void devoFazerVerificacoesAvancadas() {
    given()
        .when()
          .get("/users")
        .then()
        .assertThat()
          .statusCode(200)
          .body("$", hasSize(3))
          .body("age.findAll{it <= 25}.size()", is(2))
          .body("age.findAll{it <= 25 && it > 20}.size()", is(1))
          .body("findAll{it.age <= 25 && it.age > 20}.name", hasItem("Maria Joaquina"))
          .body("findAll{it.age <= 25}[0].name", is("Maria Joaquina"))
          .body("findAll{it.age <= 25}[-1].name", is("Ana Júlia"))
          .body("find{it.age <= 25}.name", is("Maria Joaquina"))
          .body("findAll{it.name.contains('n')}.name", hasItems("Maria Joaquina", "Ana Júlia"))
          .body("findAll{it.name.length()>10}.name", hasItems("João da Silva", "Maria Joaquina"))
          .body("name.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
          .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}", hasItem("MARIA JOAQUINA"))
          .body("name.findAll{it.startsWith('Maria')}.collect{it.toUpperCase()}.toArray()", allOf(arrayContaining("MARIA JOAQUINA"), arrayWithSize(1)))
          .body("id.max()", is(3))
          .body("salary.min()", is(1234.5678f))
          .body("salary.findAll{it != null}.sum()", is(closeTo(3734.5678f, 0.001)))
          .body("salary.findAll{it != null}.sum()", allOf(greaterThan(3000d), lessThan(5000d)));
  }

  @Test
  public void devoUnirJsonPathComJAVA() {
    ArrayList<String> names = given()
        .when()
        .get("/users")
        .then()
        .assertThat()
        .statusCode(200)
        .extract()
        .path("name.findAll{it.startsWith('Maria')}");

    assertEquals(1, names.size());
    assertTrue(names.get(0).equalsIgnoreCase("Maria Joaquina"));
    assertEquals(names.get(0).toUpperCase(), "Maria Joaquina".toUpperCase());

  }


}
