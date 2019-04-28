package com.baidu.ai.aip.dao;

import com.alibaba.fastjson.JSONObject;

public class LoginDao {
		//提取 score
		public String getScore(String result) {
			// 先把json字符串转化为json对象
			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject face = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
			String score = face.getString("score");
			return score;
		}
		
		//提取组id
		
		//提取 用户名
}
