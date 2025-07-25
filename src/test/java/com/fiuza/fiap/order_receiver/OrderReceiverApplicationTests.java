package com.fiuza.fiap.order_receiver;

import com.fiuza.fiap.order_receiver.core.usecases.CreateOrderUseCaseTest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("test")
@TestPropertySource(properties = {
		"spring.kafka.bootstrap-servers=localhost:9092",
		"spring.kafka.producer.bootstrap-servers=localhost:9092",
		"spring.kafka.listener.auto-startup=false",
		"spring.kafka.listener.missing-topics-fatal=false",
		"spring.cloud.compatibility-verifier.enabled=false",
		"springdoc.api-docs.enabled=false",
		"springdoc.swagger-ui.enabled=false"
})
class OrderReceiverApplicationTests {

	@MockBean
	private CreateOrderUseCaseTest getOrderByIdUseCase;

	@Test
	void contextLoads() {
	}

}
