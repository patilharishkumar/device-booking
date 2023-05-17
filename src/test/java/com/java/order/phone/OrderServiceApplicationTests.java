package com.java.order.phone;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.order.phone.api.model.BookingResponse;
import com.java.order.phone.service.OrderService;
import okhttp3.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@EnableWebMvc
class OrderServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private OrderService phoneOrderService;

	@MockBean
	private RestTemplate restTemplate;

	@BeforeEach
	void setMockOutput() {

		List<BookingResponse> bookingResponses = new ArrayList<>();
		bookingResponses.add(new BookingResponse("1","Samsung Galaxy S9","hari", LocalDateTime.now(), "Yes","booked"));
		when(phoneOrderService.getAllBookings()).thenReturn(bookingResponses);

	}


	/**
	 *   context load test
	 */
	@Test
	void contextLoads() {
	}

	/**
	 * test success
	 *  when GET phones
	 *  then Returns 200OK
	 */

	@Test
	public void test_success_when_GET_phones_thenReturns200OK() throws Exception {
		mockMvc.perform(get("/api/v1/devices")).andExpect(status().isOk());
	}

	@Test
	public void test_invalid_when_GET_phones_thenReturns400Error() throws Exception {
		mockMvc.perform(get("/api/v1/devicesa")).andExpect(status().is4xxClientError());
	}

	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void list_phones_whenPost_thenReturns200OK() throws Exception {
		List<BookingResponse> bookingResponses = new ArrayList<>();
		bookingResponses.add(new BookingResponse("1","Samsung Galaxy S9","hari", LocalDateTime.now(), "Yes","booked"));
		mockMvc.perform(get("/api/v1/devices")).andExpect(status().isOk());
	}


	/**
	 *
	 * @throws Exception
	 */
	@Test
	public void get_phone_Nokia_whenGET_thenReturns200OK() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/v1/device?name=Nokia 3310")).andExpect(status().isOk()).andDo(print()).andReturn();
		String content = result.getResponse().getContentAsString();
		System.out.println(content);
	}

	/**
	 *  Get phone details for samsung galaxy phone details.
	 * @throws Exception
	 */
	@Test
	public void get_phone_Samsung_whenGET_thenReturns200OK() throws Exception {
		MvcResult result = mockMvc.perform(get("/api/v1/device?name=Samsung Galaxy S9")).andExpect(status().isOk()).andDo(print()).andReturn();
		String content = result.getResponse().getContentAsString();

	}

	@Test
	public void test_book_device_thenReturns200OK() throws Exception {
		String requestBody = "{\"phoneName\":\"Samsung Galaxy S9\",\"bookedBy\":\"Bob\"}";
		mockMvc.perform(post("/api/v1/book").contentType("application/json")
				.content(requestBody))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@Test
	public void test_book_device_thenReturns200() throws Exception {
		String requestBody = "{\"phoneName\":\"Samsung Galaxy S8\",\"bookedBy\":\"Bobw\"}";
		mockMvc.perform(post("/api/v1/book").contentType("application/json")
				.content(requestBody))
				.andExpect(status().is2xxSuccessful())
				.andDo(print());
	}

}
