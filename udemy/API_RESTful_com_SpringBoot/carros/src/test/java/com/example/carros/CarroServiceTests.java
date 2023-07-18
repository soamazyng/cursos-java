package com.example.carros;

import com.example.carros.api.exception.ObjectNotFoundException;
import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.*;

@SpringBootTest
class CarroServiceTests {

	@Autowired
	private CarrosService carrosService;

	@Test
	public void TestInsertCarros(){

		Carro carro = new Carro();
		carro.setName("Meu carro Teste");
		carro.setType("Esportivo");

		var carroDto = carrosService.saveCarro(carro);

		assertNotNull(carroDto);

		Long id = carroDto.getId();

		assertNotNull(id);

		// busca o objeto
		carroDto = carrosService.getCarroById(id);
		assertNotNull(carroDto);

		assertEquals(carroDto.getName(), "Meu carro Teste");
		assertEquals(carroDto.getType(), "Esportivo");

		// deleta o dado
		carrosService.deleteCarro(id);

		try{
			carrosService.getCarroById(id);
			fail();
		}
		catch (ObjectNotFoundException ex){
		// ok
		}
	}

	@Test
	public void TestListCarros(){
		var listCarros = carrosService.getCarros();

		assertEquals(30, listCarros.size());

	}

	@Test
	public void TestListCarrosPorTipo(){

		assertEquals(10, carrosService.getCarroByType("esportivos").size());
		assertEquals(10, carrosService.getCarroByType("classicos").size());
		assertEquals(10, carrosService.getCarroByType("luxo").size());
		assertEquals(0, carrosService.getCarroByType("x").size());

	}

	@Test
	public void TestGet(){
		var carroDto = carrosService.getCarroById(11L);

		assertNotNull(carroDto);

		assertEquals("Ferrari FF", carroDto.getName());
	}

}
