package com.tcg.dao;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.tcg.entity.Message;

@Repository
public class MessageDaoImpl implements MessageDao
{
    @Autowired
    private JdbcTemplate jdbcTemplate;
	
	@Override
	public int add(Message message)
	{
		return jdbcTemplate.update("insert into message values(?,?,?,?)",
				message.getUserName(),
				message.getContent(),
				message.getUploadTime(),
				message.getImage());
	}

	@Override
	public int delete(String userName, Timestamp uploadTime)
	{
		return jdbcTemplate.update("delete from message where userName=?,uploadTime=?",
				userName,uploadTime);
	}

	@Override
	public List<Message> findMessageByUsername(String userName)
	{
		List<Message> messages = jdbcTemplate.query("select * from message where userName=?",
				new Object[] {userName},new BeanPropertyRowMapper(Message.class));
		return messages;
	}

	@Override
	public List<Message> findAllMessage()
	{
		List<Message> messages = jdbcTemplate.query("select * from message order by uploadTime desc",
				new Object[] {},new BeanPropertyRowMapper(Message.class));
		return messages;
	}
	
}
