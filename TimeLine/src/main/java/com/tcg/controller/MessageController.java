package com.tcg.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tcg.entity.Message;
import com.tcg.service.MessageService;

import net.sf.json.JSONArray;

@Controller
public class MessageController
{
	@Autowired
	MessageService messageService;
	
	final String rootPath = "src/main/resources/images/";
	
	@GetMapping(value = {"/","/submit"})
	public String submit()
	{	
		return "submit";
	}
	
	@PostMapping("/send")
	@ResponseBody
	public String userSend(@RequestParam(value = "user") String userName, @RequestParam(value = "content") String content,
			@RequestParam(value = "image") String image/*, HttpServletResponse response*/)
	{
		//response.setHeader("Access-Control-Allow-Origin", "*");
		Date date = new Date();
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy_MM_dd_HH_MM_SS");
		
		String time = sdf1.format(date);
		
		String dataUrl = image;
		
		if(!image.equals(""))
		{
			image = rootPath + sdf2.format(date) + ".txt";
			File fileSaver = new File(image);
			
			System.out.println(image);
			FileWriter writer = null;
			
			try
			{
				fileSaver.createNewFile();
		        writer = new FileWriter(fileSaver, true);
		        writer.append(dataUrl);
		        writer.flush();
		        writer.close();
			} catch (IOException e)
			{
				e.printStackTrace();
				return "fail";
			}
		}

		Timestamp uploadTime = Timestamp.valueOf(time);
		
		Message message = new Message();
		message.setUserName(userName);
		message.setContent(content);
		message.setUploadTime(uploadTime);
		message.setImage(image);
		
		int t = messageService.add(message);
		
		if(t == 1)
			return "success";
		else 
			return "fail";
	}
	
	@GetMapping("/messages")
	@ResponseBody
	public String userGet()
	{
		List<Message> messages = messageService.findAllMessage();
		try
		{
			for(Message message : messages)
			{
				if(message.getImage().equals(""))continue;
				File imageFile = new File(message.getImage());
				if(imageFile.exists())
				{
					FileInputStream fis = new FileInputStream(imageFile);
		            int iAvail = fis.available();
		            byte[] bytes = new byte[iAvail];
		            fis.read(bytes);
					message.setImage(new String(bytes));
					fis.close();
				}
				else 
				{
					message.setImage("");
				}
				//System.out.println(message.getUploadTime());
			}
		}catch (Exception e)
		{
			e.printStackTrace();
		}

		//System.out.println(JSONArray.fromObject(messages).toString());
		
		return JSONArray.fromObject(messages).toString();
	}
	
	@GetMapping("/showResult")
	public String showResult()
	{
		return "showResult";
	}
}
