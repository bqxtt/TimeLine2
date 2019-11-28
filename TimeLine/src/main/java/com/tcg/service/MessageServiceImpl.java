package com.tcg.service;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcg.dao.MessageDao;
import com.tcg.entity.Message;

@Service
public class MessageServiceImpl implements MessageService
{
	@Autowired
	MessageDao messageDao;
	
	
	@Override
	public int add(Message message)
	{
		return messageDao.add(message);
	}

	@Override
	public int delete(String userName, Timestamp uploadTime)
	{
		return messageDao.delete(userName, uploadTime);
	}

	@Override
	public List<Message> findMessageByUsername(String userName)
	{
		return messageDao.findMessageByUsername(userName);
	}

	@Override
	public List<Message> findAllMessage()
	{
		return messageDao.findAllMessage();
	}

}
