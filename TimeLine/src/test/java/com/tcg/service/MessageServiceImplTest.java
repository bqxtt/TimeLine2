package com.tcg.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.tcg.dao.MessageDao;
import com.tcg.entity.Message;

@RunWith(SpringRunner.class)
@SpringBootTest
class MessageServiceImplTest
{
	@Autowired
	private MessageServiceImpl messsageService;

	@MockBean
	private MessageDao messageDao;

	private Message message;

	@Test
	public void should_find_all_messages_by_dao()
	{

		message = new Message();

		List<Message> messages = new ArrayList<Message>();
		messages.add(message);

		when(messageDao.findAllMessage()).thenReturn(messages);
		List<Message> messagesList = messsageService.findAllMessage();

		System.out.println(messagesList.size());
		verify(messageDao, times(1)).findAllMessage();
	}
	
	@Test
	public void should_add_message_by_dao()
	{
		message = new Message();
		
		when(messageDao.add(Mockito.any(Message.class))).thenReturn(1);
		
		int t = messsageService.add(message);
		
		verify(messageDao,times(1)).add(Mockito.any(Message.class));
		assertEquals(t,1);
	}

}
