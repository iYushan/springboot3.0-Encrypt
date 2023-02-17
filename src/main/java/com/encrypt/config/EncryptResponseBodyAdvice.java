package com.encrypt.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Map;

@RestControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Method method=returnType.getMethod();
        if (method!=null){
            return method.isAnnotationPresent(EncryptBody.class);
        }
        return false;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
       if (body instanceof Map map){
           ObjectMapper mapper = new ObjectMapper();
           try {
               var str= mapper.writeValueAsString(map);
               return encrypt(str);
           } catch (Exception e) {
               throw new RuntimeException(e);
           }
       }
        try {
            return encrypt(body.toString());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 加密 内容
     *
     * @param sSrc 加密内容
     * @return 返回加密后的String
     * @throws Exception
     */
    private static String encrypt(String sSrc) throws Exception {
        byte[] raw = "wap816wap816wap8".getBytes(StandardCharsets.UTF_8);
        SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");// "算法/模式/补码方式"
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec);
        byte[] encrypted = cipher.doFinal(sSrc.getBytes(StandardCharsets.UTF_8));
        return Base64Utils.encodeToString(encrypted);
    }

}
