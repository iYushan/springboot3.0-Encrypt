package com.encrypt;

import com.encrypt.config.EncryptBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @GetMapping("/encryption")
    @EncryptBody
    public Map<String,Object> testAPI(){
        return Map.of("key1","value1","key2","value2","key3","value3");
        //访问 http://localhost:8080/encryption
        //不加密输出:{"key3":"value3","key2":"value2","key1":"value1"}
        //加密输出:"P1ngBpuTZ4c5Qf/Dal5xDhOeEPbrwx+wHVRlc7xSv+MPgt3SaAcgXlVZL5zteJE+n+01C9Qpys58uSrmzooInw=="
    }
    /**
     * 项目说明
     *
     *  本项目实际应用于Android app 数据交互.app 使用的Kotlin编写的解密
     *
     */

 /*   fun decrypt(sSrc: String?): String {
        val raw = "wap816wap816wap8".toByteArray(StandardCharsets.UTF_8)
        val skeySpec = SecretKeySpec(raw, "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec)
        val encrypted1 = Base64.decode(sSrc,0) // 先用base64解密
        val original = cipher.doFinal(encrypted1)
        return String(original, StandardCharsets.UTF_8)
    }*/
}
