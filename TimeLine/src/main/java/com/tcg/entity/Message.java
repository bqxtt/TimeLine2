package com.tcg.entity;

import java.sql.Timestamp;

public class Message
{
	private String userName;
	private String content;
	private Timestamp uploadTime;
	private String image;
	
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
	}
	public Timestamp getUploadTime()
	{
		return uploadTime;
	}
	public void setUploadTime(Timestamp uploadTime)
	{
		this.uploadTime = uploadTime;
	}
	public String getImage()
	{
		return image;
	}
	public void setImage(String image)
	{
		this.image = image;
	}
	
}
