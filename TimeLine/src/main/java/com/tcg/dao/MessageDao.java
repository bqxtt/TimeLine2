package com.tcg.dao;

import java.sql.Timestamp;
import java.util.List;

import com.tcg.entity.Message;

public interface MessageDao
{
	int add(Message message);
	
	int delete(String userName,Timestamp uploadTime);
	
	List<Message> findMessageByUsername(String userName);
	
	List<Message> findAllMessage();
}
