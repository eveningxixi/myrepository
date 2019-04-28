package com.baidu.ai.aip.dao;

import com.alibaba.fastjson.JSONObject;

public class ABDao {
	//1 ��FaceDetect�з��ص�json�ַ���result����ȡage
	public String getAge(String result) {
		// �Ȱ�json�ַ���ת��Ϊjson����
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		String age = face.getString("age");
		return age;
	}

	//2 ��result��ȡbeauty
	public String getBeauty(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		String beauty = face.getString("beauty");
		return beauty;
	}

	//3 ��ȡ����������
	public String getFaceNum(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject res = jsonObject.getJSONObject("result");
		String facenum = res.getString("face_num");
		return facenum;
	}

	// ���۾���û��
	/*
	 * �Ƚ������ַ����Ƿ����Ҫ��equals����== ���С� public static String yn(String type) {
	 * if(type.equals("common")) { return type = "��"; } if(type.equals("none")) {
	 * return type = "��"; } return "fail"; }
	 */
	public String[] getGlasses(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("glasses");
		String type = facet.getString("type");
		if (type.equals("common")) {
			type = "��";
		}
		if (type.equals("none")) {
			type = "��";
		}                            
		String pro = facet.getString("probability");
		String[] g = {type, pro};
		return g;
	}
	
	//4�������� ���ˣ���ͨ
	public String[] getFaceType(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("face_type");
		String type = facet.getString("type");
		
		if (type.equals("human")) {
			type = "����";
		}
		if (type.equals("cartoon")) {
			type = "��ͨ";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	//5���� square: ������ triangle:������ oval: ��Բ heart: ���� round: Բ��
	public String[] getFaceShape(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("face_shape");
		String type = facet.getString("type");
		
		if (type.equals("square")) {
			type = "������";
		}
		if (type.equals("triangle")) {
			type = "������";
		}
		if(type.equals("oval")) {
			type = "��Բ";
		}
		if(type.equals("heart")) {
			type = "����";
		}
		if(type.equals("round")) {
			type = "Բ��";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	//6����	angry:��ŭ  disgust:���  fear:�־� happy:���� sad:���� surprise:���� neutral:������
	public String[] getEmotion(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("emotion");
		String type = facet.getString("type");
		
		if (type.equals("angry")) {
			type = "��ŭ";
		}
		if (type.equals("disgust")) {
			type = "���";
		}
		if (type.equals("fear")) {
			type = "�־�";
		}
		if (type.equals("happy")) {
			type = "����";
		}
		if (type.equals("sad")) {
			type = "����";
		}
		if (type.equals("surprise")) {
			type = "��ϲ";
		}
		if (type.equals("neutral")) {
			type = "������";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	//7���� none:��Ц(����)��smile:΢Ц��laugh:��Ц
	public String[] getExpression(String result) {
		JSONObject jsonObject = JSONObject.parseObject(result);
		JSONObject face = jsonObject.getJSONObject("result").getJSONArray("face_list").getJSONObject(0);
		JSONObject facet = face.getJSONObject("expression");
		String type = facet.getString("type");
		
		if (type.equals("none")) {
			type = "����";
		}
		if (type.equals("smile")) {
			type = "΢Ц";
		}
		if (type.equals("laugh")) {
			type = "��Ц";
		}
		String pro = facet.getString("probability");
		String[] g = { type, pro };
		return g;
	}
	
	
	//��ȡ score
			public String getScore(String result) {
				// �Ȱ�json�ַ���ת��Ϊjson����
				JSONObject jsonObject = JSONObject.parseObject(result);
				JSONObject face = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
				String score = face.getString("score");
				return score;
			}
			
			public String getUserId(String result) {
				// �Ȱ�json�ַ���ת��Ϊjson����
				JSONObject jsonObject = JSONObject.parseObject(result);
				JSONObject face = jsonObject.getJSONObject("result").getJSONArray("user_list").getJSONObject(0);
				String userid = face.getString("user_id");
				return userid;
			}

}
