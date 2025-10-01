package com.blog.com.blog.config
import org.springframework.context.annotation.Bean
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.s3.S3Client
import org.springframework.context.annotation.Configuration

@Configuration
class AwsConfig(private val awsProperties: AwsProperties) {

    @Bean
    fun s3Client(): S3Client {
        return S3Client.builder()
            .region(Region.of(awsProperties.region))
            .build()
    }
}