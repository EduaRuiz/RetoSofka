package com.eduarruiz.retosofka;

import com.eduarruiz.retosofka.model.Player;
import com.eduarruiz.retosofka.serviceimpl.PlayerServiceImpl;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RetosofkaApplicationTests {

	@Test
	void contextLoads() {

		PlayerServiceImpl a = new PlayerServiceImpl();
		for (Player b:a.getAll()){
			System.out.println(b.getUsername());
		}
	}

}
