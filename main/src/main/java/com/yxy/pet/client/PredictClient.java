package com.yxy.pet.client;
import com.yxy.pet.domain.entity.PredictionResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2024/1/22 20:06
 */
@Component
public class PredictClient {
    @Value("${translation.predict.url-file}")
    String clientUrlByFile;

    @Value("${translation.predict.url}")
    String clientUrl;


    public PredictionResult predictByFile(MultipartFile file) throws IOException {
        RestTemplate restTemplate = new RestTemplate();
        // 将 MultipartFile 转换为 byte[]
        byte[] fileContent = file.getBytes();

        // 创建请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        // 创建文件资源
        ByteArrayResource fileResource = new ByteArrayResource(fileContent) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename(); // 设置文件名
            }
        };

        // 添加文件资源到 MultiValueMap
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileResource);

        // 创建请求实体
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发送 POST 请求
        ResponseEntity<PredictionResult> responseEntity = restTemplate.exchange(clientUrlByFile, HttpMethod.POST, requestEntity, PredictionResult.class);

        // 返回响应体
        return responseEntity.getBody();
    }

    public PredictionResult predict(String url) throws IOException {
        RestTemplate restTemplate = new RestTemplate();

        // 构建包含查询参数的 URL
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(clientUrl)
                .queryParam("url", url);

        // 发送 GET 请求并获取响应体
        ResponseEntity<PredictionResult> responseEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.POST, null, PredictionResult.class);

        // 返回响应体
        return responseEntity.getBody();
    }

    public static void main(String[] args) throws IOException {

    }
}


