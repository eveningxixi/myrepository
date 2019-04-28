package com.baidu.ai.aip.dao;

import com.alibaba.fastjson.JSONObject;

public class ABDao {
	//1 从FaceDetect中返回的json字符串result中提取age
	public String getAge(String result) {
		// 先把json字符串转化为json对象
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		String age = face.getString("age");
		return age;
	}

	//2 从result提取beauty
	public String getBeauty(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		String beauty = face.getString("beauty");
		return beauty;
	}

	//3 获取人脸的数量
	public String getFaceNum(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject res = jsonObject.getJSONObject("result");
		String facenum = res.getString("face_num");
		return facenum;
	}

	// 戴眼镜了没有
	/*
	 * 比较两个字符串是否相等要用equals，用== 不行。 public static String yn(String type) {
	 * if(type.equals("common")) { return type = "是"; } if(type.equals("none")) {
	 * return type = "否"; } return "fail"; }
	 */
	public String[] getGlasses(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("glasses");
		String type = facet.getString("type");
		if (type.equals("common")) {
			type = "是";
		}
		if (type.equals("none")) {
			type = "否";
		}                            
		String pro = facet.getString("probability");
		String[] g = {type, pro};
		return g;
	}
	
	//4人脸类型 真人，卡通
	public String[] getFaceType(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("face_type");
		String type = facet.getString("type");
		
		if (type.equals("human")) {
			type = "真人";
		}
		if (type.equals("cartoon")) {
			type = "卡通";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	//5脸型 square: 正方形 triangle:三角形 oval: 椭圆 heart: 心形 round: 圆形
	public String[] getFaceShape(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("face_shape");
		String type = facet.getString("type");
		
		if (type.equals("square")) {
			type = "正方形";
		}
		if (type.equals("triangle")) {
			type = "三角形";
		}
		if(type.equals("oval")) {
			type = "椭圆";
		}
		if(type.equals("heart")) {
			type = "心形";
		}
		if(type.equals("round")) {
			type = "圆形";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	//6情绪	angry:愤怒  disgust:厌恶  fear:恐惧 happy:高兴 sad:伤心 surprise:惊讶 neutral:无情绪
	public String[] getEmotion(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("emotion");
		String type = facet.getString("type");
		
		if (type.equals("angry")) {
			type = "愤怒";
		}
		if (type.equals("disgust")) {
			type = "厌恶";
		}
		if (type.equals("fear")) {
			type = "恐惧";
		}
		if (type.equals("happy")) {
			type = "高兴";
		}
		if (type.equals("sad")) {
			type = "悲伤";
		}
		if (type.equals("surprise")) {
			type = "惊喜";
		}
		if (type.equals("neutral")) {
			type = "无情绪";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	//7表情 none:不笑(严肃)；smile:微笑；laugh:大笑
	public String[] getExpression(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("expression");
		String type = facet.getString("type");
		
		if (type.equals("none")) {
			type = "严肃";
		}
		if (type.equals("smile")) {
			type = "微笑";
		}
		if (type.equals("laugh")) {
			type = "大笑";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	
	//提取 score
			public String getScore(String result) {
				// 先把json字符串转化为json对象
				JSONObject jsonObject = JSONObject.parseObject(result);
				JSONObject face = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
				String score = face.getString("score");
				return score;
			}
			
			public String getUserId(String result) {
				// 先把json字符串转化为json对象
				JSONObject jsonObject = JSONObject.parseObject(result);
				JSONObject face = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
				String userid = face.getString("user_id");
				return userid;
			}

}
