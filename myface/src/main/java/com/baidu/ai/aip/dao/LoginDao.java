package com.baidu.ai.aip.dao;

import com.alibaba.fastjson.JSONObject;

public class LoginDao {
		//��ȡ score
		public String getScore(String result) {
			// �Ȱ�json�ַ���ת��Ϊjson����
			JSONObject jsonObject = JSONObject.parseObject(result);
			JSONObject face = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
			String score = face.getString("score");
			return score;
		}
		
		//��ȡ��id
		
		//��ȡ �û���
}
