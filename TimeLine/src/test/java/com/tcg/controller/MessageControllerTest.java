package com.tcg.controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.tcg.entity.Message;
import com.tcg.service.MessageService;

@RunWith(SpringRunner.class)
//@SpringBootTest
@WebMvcTest(controllers = MessageController.class)
class MessageControllerTest
{
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MessageService messageService;
	
	@Test
	public void should_return_submit_html() throws Exception
	{
		mockMvc.perform(get("/")).andExpect(status().isOk());
		mockMvc.perform(get("/submit")).andExpect(status().isOk());
	}
	
	@Test
	public void should_return_showResult_html() throws Exception
	{
		mockMvc.perform(get("/showResult")).andExpect(status().isOk());
	}
	
	@Test
	public void should_return_success_when_user_send() throws Exception
	{
		when(messageService.add(Mockito.any(Message.class))).thenReturn(1);
		
		MvcResult mvcResult = mockMvc.perform(post("/send").param("user", "test1").param("content", "this is a test").param("image", ""))
									 .andExpect(status().isOk())
									 .andDo(print())
									 .andReturn();
		
		verify(messageService,times(1)).add(Mockito.any(Message.class));
		assertEquals("success", mvcResult.getResponse().getContentAsString());
	}
	
	@Test
	public void should_return_JsonString_when_user_get() throws Exception
	{
		List<Message> messages = new ArrayList<Message>();

		when(messageService.findAllMessage()).thenReturn(messages);
		
		MvcResult mvcResult = mockMvc.perform(get("/messages"))
									 .andExpect(status().isOk())
									 .andDo(print())
									 .andReturn();
		//System.out.println(mvcResult.getResponse().getContentAsString());
		verify(messageService,times(1)).findAllMessage();
		assertNotNull(mvcResult.getResponse().getContentAsString());
	}
}
