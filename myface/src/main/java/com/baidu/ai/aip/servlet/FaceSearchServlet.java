package com.baidu.ai.aip.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.ai.aip.FaceSearch;
import com.baidu.ai.aip.dao.ABDao;
import com.baidu.ai.aip.utils.GsonUtils;

/**
 * Servlet implementation class FaceDetectServlet
 */
@WebServlet("/FaceSearchServlet")
public class FaceSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * Default constructor.
	 */
	public FaceSearchServlet() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");

		String dataUrl = request.getParameter("dataUrl");
		// System.out.println(dataUrl);
		String result = FaceSearch.search(dataUrl);

		ABDao ab = new ABDao();
		String score = ab.getScore(result);
		String userid = ab.getUserId(result);
		// String 转 float
		float fscore = Float.parseFloat(score);

		String resultmsg;
		if (fscore > 75) {
			resultmsg = "登录成功";
		} else {
			resultmsg = "登录失败";
		}
		
		// dataType = 'json'时的响应格式
		Map<String, Object> map = new HashMap<>();
		map.put("score", score);
		map.put("resultmsg", resultmsg);
		map.put("userid", userid);

		// 把map转为json字符串格式
		String param = GsonUtils.toJson(map);

		response.getWriter().print(param);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
