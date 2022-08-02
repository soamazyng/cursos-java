package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import com.example.carros.domain.dto.CarroDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.*;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CarrosApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarroAPITest {
    @Autowired
    protected TestRestTemplate rest;
    @Autowired
    private CarrosService service;

    private ResponseEntity<CarroDto> getCarro(String url) {
        return
                rest.getForEntity(url, CarroDto.class);
    }

    private ResponseEntity<List<CarroDto>> getCarros(String url) {
        return rest.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<CarroDto>>() {
                });
    }


    @Test
    public void testSave() {

        Carro carro = new Carro();
        carro.setName("Porshe");
        carro.setType("esportivos");

        // Insert
        ResponseEntity response = rest.postForEntity("/api/v1/carros", carro, null);
        System.out.println(response);

        // Verifica se criou
        assertEquals(HttpStatus.CREATED, response.getStatusCode());

        // Buscar o objeto
        String location = response.getHeaders().get("location").get(0);
        CarroDto c = getCarro(location).getBody();

        assertNotNull(c);
        assertEquals("Porshe", c.getName());
        assertEquals("esportivos", c.getType());

        // Deletar o objeto
        rest.delete(location);

        // Verificar se deletou
        assertEquals(HttpStatus.NOT_FOUND, getCarro(location).getStatusCode());
    }

    @Test
    public void testLista() {
        List<CarroDto> carros = getCarros("/api/v1/carros").getBody();
        assertNotNull(carros);
        assertEquals(30, carros.size());
    }

    @Test
    public void testListaPorTipo() {

        assertEquals(10, getCarros("/api/v1/carros/tipo/classicos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/esportivos").getBody().size());
        assertEquals(10, getCarros("/api/v1/carros/tipo/luxo").getBody().size());

        assertEquals(HttpStatus.NO_CONTENT, getCarros("/api/v1/carros/tipo/xxx").getStatusCode());
    }

    @Test
    public void testGetOk() {

        ResponseEntity<CarroDto> response = getCarro("/api/v1/carros/11");
        assertEquals(response.getStatusCode(), HttpStatus.OK);

        CarroDto c = response.getBody();
        assertEquals("Ferrari FF", c.getName());
    }

    @Test
    public void testGetNotFound() {

        ResponseEntity response = getCarro("/api/v1/carros/1100");

        assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

}
