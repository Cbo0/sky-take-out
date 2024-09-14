package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.result.Result;
import com.sky.utils.AliOssUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

/**
 * Common interface
 */
@RestController
@RequestMapping("/admin/common")
@Api(tags = "Common interface")
@Slf4j
public class CommonController {

    @Autowired
    private AliOssUtil aliOssUtil;

    /**
     * Upload file
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @ApiOperation("Upload files")
    public Result<String> upload(MultipartFile file){
        log.info("upload file,{}", file);

        try {
            //Original file name
            String originalFilename = file.getOriginalFilename();
            //Intercept the suffix of the original file name
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String objectName = UUID.randomUUID().toString() + extension;

            String filePath = aliOssUtil.upload(file.getBytes(), objectName);
            return Result.success(filePath);
        } catch (IOException e) {
            log.error("Uploading file failed: {}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
