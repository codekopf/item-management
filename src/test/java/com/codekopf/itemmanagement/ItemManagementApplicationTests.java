package com.codekopf.itemmanagement;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@Tag("integration")
// TODO probably remove - default test which only start the app and check the context - no value here if PostgreSQL
@SpringBootTest
public class ItemManagementApplicationTests {

	@Test
	void contextLoads() {
	}

}
