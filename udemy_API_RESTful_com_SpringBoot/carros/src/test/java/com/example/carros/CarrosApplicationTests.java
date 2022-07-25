package com.example.carros;

import com.example.carros.domain.Carro;
import com.example.carros.domain.CarrosService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static junit.framework.TestCase.*;

@SpringBootTest
class CarrosApplicationTests {

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
		var opt = carrosService.getCarroById(id);
		assertTrue(opt.isPresent());

		carroDto = opt.get();

		assertEquals(carroDto.getName(), "Meu carro Teste");
		assertEquals(carroDto.getType(), "Esportivo");

		// deleta o dado
		carrosService.deleteCarro(id);

		assertFalse(carrosService.getCarroById(id).isPresent());

	}

	@Test
	public void TestListCarros(){
		var listCarros = carrosService.getCarros();



	}

}
