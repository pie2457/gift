package dev.practice.gift.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;

@Configuration
public class AwsSqsConfig {
	@Value("${cloud.aws.secretKey}")
	private String awsAccessKey;

	@Value("${cloud.aws.secretKey}")
	private String awsSecretKey;

	@Bean
	public AmazonSQSAsync amazonSQSAsync() {
		AWSCredentialsProvider awsCredentialsProvider = new AWSStaticCredentialsProvider(
			new BasicAWSCredentials(awsAccessKey, awsSecretKey));

		return AmazonSQSAsyncClientBuilder
			.standard()
			.withRegion(Regions.AP_NORTHEAST_2)
			.withCredentials(awsCredentialsProvider)
			.build();
	}
}
