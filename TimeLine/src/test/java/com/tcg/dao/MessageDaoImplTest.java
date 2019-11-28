package com.tcg.dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import com.tcg.entity.Message;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
class MessageDaoImplTest
{
	@Autowired
	private MessageDao messageDao;
	
	private Message message;
	
	@Test
	public void should_add_a_message()
	{
		message = new Message();
		
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String userName = "test1";
		String content = "this is a test";
		Timestamp uploadTime = Timestamp.valueOf(sdf1.format(date));
		String image = "";
		
		message.setUserName(userName);
		message.setContent(content);
		message.setUploadTime(uploadTime);
		message.setImage(image);
		
		int t = messageDao.add(message);
		assertEquals(t, 1);
		
	}
	
	@Test
	public void should_find_all_message()
	{
		message = new Message();
		
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String userName = "test1";
		String content = "this is a test";
		Timestamp uploadTime = Timestamp.valueOf(sdf1.format(date));
		String image = "";
		
		message.setUserName(userName);
		message.setContent(content);
		message.setUploadTime(uploadTime);
		message.setImage(image);
		
		messageDao.add(message);
		
		List<Message> messages = messageDao.findAllMessage();
		
		int count = 0;
		for(Message message : messages)
		{
			assertEquals(userName, message.getUserName());
			assertEquals(content, message.getContent());
			assertEquals(uploadTime, message.getUploadTime());
			assertEquals(image, message.getImage());
			count++;
		}
		assertEquals(count, 1);
	}

}
