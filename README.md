# springboot3.0-Encrypt
基于springboot3.0的API接口加密解密

内容很简单,无第三方包, 涉及两个文件,一个是注解用的,一个是加密拦截用的
复制即用.
关于解密 附 Android 开发 Kotlin编写的解密

fun decrypt(sSrc: String?): String {
        val raw = "wap816wap816wap8".toByteArray(StandardCharsets.UTF_8)
        val skeySpec = SecretKeySpec(raw, "AES")
        val cipher = Cipher.getInstance("AES/ECB/PKCS5Padding")
        cipher.init(Cipher.DECRYPT_MODE, skeySpec)
        val encrypted1 = Base64.decode(sSrc,0) // 先用base64解密
        val original = cipher.doFinal(encrypted1)
        return String(original, StandardCharsets.UTF_8)
    }
