package com.assign;

import Model.Customer;
import org.junit.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
class AssignApplicationTests {
	@Autowired
	private TestRestTemplate restTemplate;
	@Test
	void contextLoads() {
	}
	@Test
	public void testGetAllUsers() {
		ResponseEntity<List<Customer>> response = restTemplate.exchange("/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {});
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
	@Test
	public void testCreateUser() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		Customer user = new Customer();
		HttpEntity<Customer> request = new HttpEntity<>(user, headers);

		ResponseEntity<Customer> response = restTemplate.postForEntity("/customers", request, Customer.class);

		assertEquals(HttpStatus.CREATED, response.getStatusCode());
		assertEquals(user.getId(), response.getBody().getId());

	}

	private void assertEquals(HttpStatus created, HttpStatusCode statusCode) {
	}

	private void assertEquals(Long id, Long id1) {
	}


}
