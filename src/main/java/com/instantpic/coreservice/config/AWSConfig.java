package com.instantpic.coreservice.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@Configuration
public class AWSConfig {

    private String iamAccessKey;
    private String iamSecretKey;
    private String region = "ap-northeast-2"; // Bucket Region

    FileInputStream propsInput;

    {
        try {
            propsInput = new FileInputStream("resources/aws.properties");
            Properties prop = new Properties();
            prop.load(propsInput);
            iamAccessKey = prop.getProperty("aws_access_key_id"); // IAM Access Key
            iamSecretKey = prop.getProperty("aws_secret_access_key");; // IAM Secret Key
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
;

    @Bean
    public AmazonS3Client amazonS3Client() {
        BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(iamAccessKey, iamSecretKey);
        return (AmazonS3Client) AmazonS3ClientBuilder.standard()
                .withRegion(region)
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .build();
    }
}
