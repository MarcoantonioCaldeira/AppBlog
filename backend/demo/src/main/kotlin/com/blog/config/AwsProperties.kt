package com.blog.com.blog.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "aws")
class AwsProperties {
    lateinit var region: String
    lateinit var s3: S3
    class S3 {
        lateinit var bucket: String
    }
}