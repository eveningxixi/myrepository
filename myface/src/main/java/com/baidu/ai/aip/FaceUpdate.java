package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;
import com.baidu.ai.aip.utils.GsonUtils;

import java.util.*;

/**
* 人脸更新
* 说明：针对一个user_id执行更新操作，新上传的人脸图像将覆盖此user_id原有所有图像。
*/
public class FaceUpdate {

    /**
    * 重要提示代码中所需工具类
    * FileUtil,Base64Util,HttpUtil,GsonUtils请从
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * 下载
    */
    public static String update() {
        // 请求url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/update";
        try {
            Map<String, Object> map = new HashMap<>();
            byte[] bytes = FileUtil.readFileByBytes("d:/wapp/z2.jpg");
            //"5b259a4e4c4d410ef19f8c3fbc147265"
            String img = Base64Util.encode(bytes);
            
            map.put("image", img);
            map.put("image_type", "BASE64");
            map.put("group_id", "myforest");
            map.put("user_id", "leslie");
           
            
/*            map.put("liveness_control", "NORMAL");
             map.put("user_info", "cba");
            map.put("quality_control", "LOW");*/

            String param = GsonUtils.toJson(map);

            // 注意这里仅为了简化编码每一次请求都去获取access_token，线上环境access_token有过期时间， 客户端可自行缓存，过期后重新获取。
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceUpdate.update();
    }
}