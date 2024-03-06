package com.yxy.pet.common.basic.utils;

import org.apache.http.entity.ContentType;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.mock.web.MockMultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.UUID;

/**
 * @author YeXingyi
 * @version 1.0
 * @date 2024/3/6 10:10
 */
public class Base64ToPngConverter {
    public static MultipartFile convertBase64ToMockMultipartFile(String base64Image) {
        String filename = UUID.randomUUID().toString() + ".png";
        byte[] imageBytes = Base64.getDecoder().decode(base64Image);
        return new MockMultipartFile(filename, filename, "image/png", imageBytes);
    }
}
