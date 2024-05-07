package com.yxy.pet.common.oss.aliyun.service.impl;

import java.io.ByteArrayInputStream;
import com.aliyun.oss.OSS;
import com.yxy.pet.common.basic.utils.DateUtil;
import com.yxy.pet.common.oss.aliyun.service.AliYunOssService;
import com.yxy.pet.common.oss.aliyun.config.properties.AliYunOssProperties;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Desc:
 * @Author: yxy
 * @Time: 2022/1/26 10:39
 */
@Slf4j
@Service
@AllArgsConstructor
public class AliYunOssServiceImpl implements AliYunOssService {
    private final AliYunOssProperties ossProperties;
    private final OSS ossClient;

    @Override
    public String createBucketName(String bucketName) {
        // 存储空间
        final String bucketNames = bucketName;
        if (!ossClient.doesBucketExist(bucketName)) {
            // 创建存储空间
            Bucket bucket = ossClient.createBucket(bucketName);
            log.info("创建存储空间成功");
            return bucket.getName();
        }
        return bucketNames;
    }

    @Override
    public void deleteBucket(String bucketName) {
        ossClient.deleteBucket(bucketName);
        log.info("删除" + bucketName + "Bucket成功");
    }

    @Override
    public String createFolder(String bucketName, String folder) {
        // 文件夹名
        final String keySuffixWithSlash = folder;
        // 判断文件夹是否存在，不存在则创建
        if (!ossClient.doesObjectExist(bucketName, keySuffixWithSlash)) {
            // 创建文件夹
            ossClient.putObject(bucketName, keySuffixWithSlash, new ByteArrayInputStream(new byte[0]));
            log.info("创建文件夹成功");
            // 得到文件夹名
            OSSObject object = ossClient.getObject(bucketName, keySuffixWithSlash);
            String fileDir = object.getKey();
            return fileDir;
        }
        return keySuffixWithSlash;
    }

    @Override
    public void deleteFile(String bucketName, String folder, String key) {
        ossClient.deleteObject(bucketName, folder.concat(key));
        log.info("删除" + bucketName + "下的文件" + folder.concat(key) + "成功");
        log.info("执行成功[删除图片]key:{}", key);
    }

    @Override
    public String uploadObjectOSS(MultipartFile file) {
        String resultStr = null;
        String storePath = getStorePath();
        createFolder(ossProperties.getBucketName(), storePath);
        try {
            //获取上传文件的原始文件名
            String fileName = file.getOriginalFilename();
            //获取上传文件的大小
            Long fileSize = file.getSize();

            //创建一个ObjectMetadata对象，用于设置上传文件的元数据信息
            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentLength(file.getInputStream().available());
            // 指定该Object被下载时的网页的缓存行为
            metadata.setCacheControl("no-cache");
            // 指定该Object下设置Header
            metadata.setHeader("Pragma", "no-cache");
            // 指定该Object被下载时的内容编码格式
            metadata.setContentEncoding("utf-8");
            // 文件的MIME，定义文件的类型及网页编码，决定浏览器将以什么形式、什么编码读取文件。如果用户没有指定则根据Key或文件名的扩展名生成，
            // 如果没有扩展名则填默认值application/octet-stream
            metadata.setContentType(getContentType(fileName));
            // 指定该Object被下载时的名称（指示MINME用户代理如何显示附加的文件，打开或下载，及文件名称）
            metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
            // 上传文件 (上传文件流的形式)
            PutObjectResult putResult = ossClient.putObject(ossProperties.getBucketName(), storePath + fileName, file.getInputStream(), metadata);
            // 解析结果
            resultStr = storePath + fileName;
            //记录上传成功后的唯一MD5数字签名
            log.info("唯一MD5数字签名:" + putResult.getETag());
            log.info("执行成功[上传图片]key:{}", resultStr);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
        }
        //返回图片在OSS上的Url，通过ossProperties配置的URL前缀凭借上传成功后的文件路径
        return ossProperties.getUrlPrefix().concat(resultStr);
    }

    /**
     * oss目录归档
     * @return
     */
    private String getStorePath() {
        String currentTime = DateUtil.getCurrentTime();
        String storePath;
        storePath = "images/" + currentTime.substring(0, 4) + "/" + currentTime.substring(5, 7)
                + "/" +currentTime.substring(8, 10) + "/";
        return storePath;
    }

    /**
     * 通过文件名判断并获取OSS服务文件上传时文件的contentType
     * @param fileName
     * @return
     */
    public String getContentType(String fileName) {
        // 文件的后缀名
        String fileExtension = fileName.substring(fileName.lastIndexOf("."));
        if (".bmp".equalsIgnoreCase(fileExtension)) {
            return "image/bmp";
        }
        if (".gif".equalsIgnoreCase(fileExtension)) {
            return "image/gif";
        }
        if (".jpeg".equalsIgnoreCase(fileExtension) || ".jpg".equalsIgnoreCase(fileExtension)
                || ".png".equalsIgnoreCase(fileExtension)) {
            return "image/jpeg";
        }
        if (".png".equalsIgnoreCase(fileExtension)) {
            return "image/png";
        }
        if (".html".equalsIgnoreCase(fileExtension)) {
            return "text/html";
        }
        if (".txt".equalsIgnoreCase(fileExtension)) {
            return "text/plain";
        }
        if (".vsd".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.visio";
        }
        if (".ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        if (".doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) {
            return "application/msword";
        }
        if (".xml".equalsIgnoreCase(fileExtension)) {
            return "text/xml";
        }
        // 默认返回类型
        return "";
    }
}
