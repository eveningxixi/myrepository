package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.HttpUtil;
import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.GsonUtils;

import java.util.*;

/**
* ����ɾ��
* ɾ���û���ĳһ��������������û�ֻ��һ������ͼƬ����ͬʱɾ���û���
*/
public class FaceDelete {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String delete() {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/faceset/user/delete";
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("face_token", "5b259a4e4c4d410ef19f8c3fbc147265");
            map.put("group_id", "myforest");
           // map.put("user_id", "leslie");
            
           
            
/*            map.put("liveness_control", "NORMAL");
             map.put("user_info", "cba");
            map.put("quality_control", "LOW");*/

            String param = GsonUtils.toJson(map);

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
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
        FaceDelete.delete();
    }
}