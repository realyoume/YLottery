package com.yumi.media.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
public class MinioProperties {

    /**
     * minio地址+端口号
     */
    @Value("${minio.url}")
    private String url;

    /**
     * minio用户名
     */
    @Value("${minio.accessKey}")
    private String accessKey;

    /**
     * minio密码
     */
    @Value("${minio.secretKey}")
    private String secretKey;

    /**
     * 文件桶的名称
     */
    @Value("${minio.bucketName}")
    private String bucketName;

}
