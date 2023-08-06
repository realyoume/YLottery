package com.yumi.media.service.impl;

import com.yumi.media.service.FileService;
import com.yumi.media.utils.MinioUtil;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

/**
 * @version 1.0
 * @author: xk
 * @description TODO
 * @date: 2023/8/6 15:53
 */


public class FileServiceImpl implements FileService {
    @Autowired
    private MinioUtil minioUtil;


    @Override
    public boolean upload(String html) {
        try {
            FileInputStream input = new FileInputStream(html);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // 使用工具上传文件


        return false;
    }
}


    