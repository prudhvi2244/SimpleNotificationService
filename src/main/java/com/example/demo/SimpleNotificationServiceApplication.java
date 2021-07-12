package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.SubscribeRequest;

@SpringBootApplication
@RestController
public class SimpleNotificationServiceApplication {

	@Autowired
	private AmazonSNSClient snsClient;
	
	private static final String TOPIC_ARN="arn:aws:sns:ap-south-1:941516478045:Lockdown";
	
	@GetMapping(value = "/subscription/{email}")
	public String addSubscription(@PathVariable String email)
	{
		SubscribeRequest request=new SubscribeRequest(TOPIC_ARN,"email",email);
		snsClient.subscribe(request);
		return "Please check your mail and click on confirm subscription to activate";
	}
	
	@GetMapping(value = "/publishMessage")
	public String publishMessage()
	{
		PublishRequest request=new PublishRequest(TOPIC_ARN,"Welcome To CTS In Lockdown","Welcome Intern");
		snsClient.publish(request);
		return "Message Published Successfully!";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SimpleNotificationServiceApplication.class, args);
	}

}
