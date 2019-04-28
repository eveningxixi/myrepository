package com.baidu.ai.aip;

import com.baidu.ai.aip.utils.HttpUtil;
import com.alibaba.fastjson.JSONObject;
/*import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baidu.ai.aip.utils.Base64Util;
import com.baidu.ai.aip.utils.FileUtil;*/
import com.baidu.ai.aip.auth.AuthService;
import com.baidu.ai.aip.utils.GsonUtils;

import java.util.*;

/**
 * 人脸检测与属性分析
 */
public class FaceDetect {

	/**
	 * 重要提示代码中所需工具类 FileUtil,Base64Util,HttpUtil,GsonUtils请从
	 * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
	 * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
	 * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
	 * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3 下载
	 */
	public static String detect(String img) {
		// 请求url
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
		try {
			Map<String, Object> map = new HashMap<>();
			// 读取图片，把图片用Base64编码成--》字符串
			// byte[] bytes = FileUtil.readFileByBytes("d:/wapp/z1.jpg");
			// Base64编码：请求的图片需经过Base64编码，图片的base64编码指将图片数据编码成一串字符串，
			// 使用该字符串代替图像地址
			/*
			 * String img = Base64Util.encode(bytes); 
			 */
			// 图片信息：base64编码后的字符串
    
			map.put("image", img);
			//设置检测人脸的数量
			map.put("max_face_num","5");
			//age,beauty,expression,face_shape,glasses,emotion,face_type信息
			map.put("liveness_control", "NONE");

			// 默认只返回face_token、人脸框、概率和旋转角度
			map.put("face_field", "age,beauty,glasses,face_type,face_shape,emotion,expression");
			//+age，+beauty
			//facetype:真实人脸/卡通人脸。++type,human: 真实人脸 cartoon: 卡通人脸。++probability：0-1。
			/*faceshape：脸型。square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
			 *expression：表情 。 none：无，smile：微笑，laugh：大笑
			 *glasses：是否戴眼镜。 none:无眼镜，common:普通眼镜，sun:墨镜 
			 *emotion：情绪。angry:愤怒 disgust:厌恶 fear:恐惧 happy:高兴 sad:伤心 surprise:惊讶 neutral:无情绪
			 * */
				
			

			// 图片类型：BASE64/URL/FACE_TOKEN:人脸图片的唯一标识码，同一张图片多次检测得到的FACE_TOKEN是同一个
			map.put("image_type", "BASE64");

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



/*	public static void main(String[] args) {
		String img = "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";
		String result = FaceDetect.detect(img);
		System.out.println(result);
		
		
		// 把json字符串转化为json对象
		JSONObject jsonObject = JSONObject.parseObject(result);

		// 从json对象中提取age和beauty的值。注意：有[] 要用getJSONArray --> getJSONArray("face_list")
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		String age = face.getString("age");
		String beauty = face.getString("beauty");
		System.out.println(age + "," + beauty);

	}*/
}