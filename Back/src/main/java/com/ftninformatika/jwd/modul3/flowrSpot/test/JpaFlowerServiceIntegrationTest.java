package com.ftninformatika.jwd.modul3.flowrSpot.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.ftninformatika.jwd.modul3.flowrSpot.model.Flower;
import com.ftninformatika.jwd.modul3.flowrSpot.repository.FlowerRepository;
import com.ftninformatika.jwd.modul3.flowrSpot.service.FlowerService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JpaFlowerServiceIntegrationTest {
	
	@Autowired
	private FlowerService flowerService;

	@MockBean
	private FlowerRepository flowerRepository;

	@Before
	public void setUp() {
		
		Flower flower1 = new Flower();
		flower1.setName("Sunflower");
		flower1.setLatinName("Helianthus annuus");

		// Corrected the method name to findByNameAndLatinName
		Mockito.when(flowerRepository.findByNameAndLatinName(flower1.getName(), flower1.getLatinName()))
				.thenReturn(flower1);
	}

	@Test
	public void whenValidNameAndLatinName_thenFlowerShouldBeFound() {
		
		String name = "Sunflower";
		String latinName = "Helianthus annuus";

		Flower found = flowerService.findByNameAndLatinName(name, latinName);

		assertThat(found).isNotNull();
		assertThat(found.getName()).isEqualTo(name);
		assertThat(found.getLatinName()).isEqualTo(latinName);
	}
	
}
