package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sns.AmazonSNSClient;
import com.amazonaws.services.sns.AmazonSNSClientBuilder;

@Configuration
public class AwsSnsConfig {

	String accessKey="";
	String secretKey="";
	
	@Bean
	@Primary
	public AmazonSNSClient getSnsClient()
	{
		return (AmazonSNSClient)AmazonSNSClientBuilder.standard()
				                     .withRegion(Regions.AP_SOUTH_1)
				                     .withCredentials(
				                    		 new AWSStaticCredentialsProvider(
				                    				 new BasicAWSCredentials(accessKey, secretKey))).build();
	}

}
