package com.inits.expense.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void createExpenseTest() throws Exception {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("value", "Bought Shoes");
		params.add("reason", "I needed new shoes");
		params.add("date",  "2019-10-10");
		params.add("username", "support@inits.com");
		
		mockMvc.perform(post("/expense").params(params))
			.andExpect(MockMvcResultMatchers.content().string(containsString("\"status\":1")));
	}
	
}
