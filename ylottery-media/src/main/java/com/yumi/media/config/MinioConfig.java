package com.yumi.media.config;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;


/**
 * description: 获取配置文件信息
 *
 * @author: weirx
 * @time: 2021/8/25 9:50
 */
@Configuration
public class MinioConfig {

    @Autowired
    private MinioProperties minioProperties;

    /**
     * 初始化 MinIO 客户端
     */
    //@Bean
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioProperties.getUrl())
                .credentials(minioProperties.getAccessKey(), minioProperties.getSecretKey())
                .build();
        return minioClient;
    }
}


    