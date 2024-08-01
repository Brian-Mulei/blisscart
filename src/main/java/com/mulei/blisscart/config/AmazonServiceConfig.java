package com.mulei.blisscart.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

 public class AmazonServiceConfig {

    @Value("${aws.access.id}")
    String awsAccessKey;

    @Value("${aws.secret.key}")
    String awsSecretKey;

 }
