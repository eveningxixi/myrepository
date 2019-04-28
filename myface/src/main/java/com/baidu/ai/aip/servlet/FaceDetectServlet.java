package com.baidu.ai.aip.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baidu.ai.aip.FaceDetect;
import com.baidu.ai.aip.dao.ABDao;
import com.baidu.ai.aip.utils.GsonUtils;

/**
 * Servlet implementation class FaceDetectServlet
 */
@WebServlet("/FaceDetectServlet")
public class FaceDetectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FaceDetectServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset = utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String dataUrl = request.getParameter("dataUrl");
		//System.out.println(dataUrl);
		String result = FaceDetect.detect(dataUrl);
		
		ABDao ab = new ABDao();
		String facenum = ab.getFaceNum(result);
		
		
		
		String age = ab.getAge(result);
		String beauty = ab.getBeauty(result);
		String g[] = ab.getGlasses(result);
		String ft[] = ab.getFaceType(result);
		String fs[] = ab.getFaceShape(result);
		String em[] = ab.getEmotion(result);
		String ex[] = ab.getExpression(result);
		
		//dataType = 'json'时的响应格式
		Map<String, Object> map = new HashMap<>();
		map.put("age", age);
		map.put("beauty",beauty);
		
		map.put("g0",g[0]);
		map.put("g1",g[1]);
		
		map.put("ft0",ft[0]);
		map.put("ft1",ft[1]);
		
		map.put("fs0",fs[0]);
		map.put("fs1",fs[1]);
		
		map.put("em0",em[0]);
		map.put("em1",em[1]);
		
		map.put("ex0",ex[0]);
		map.put("ex1",ex[1]);
		
		//System.out.println("map="+map);
		//map={ft1=0.99, fs0=椭圆, ft0=真人, beauty=44.09, fs1=0.41, em1=0.95, g0=是, em0=无情绪, g1=1, ex0=严肃, age=22, ex1=1}
		
		//把map转为json字符串格式
		String param = GsonUtils.toJson(map);
		
		//System.out.println("param="+param);
		//param={"ft1":"0.99","fs0":"椭圆","ft0":"真人","beauty":"44.09","fs1":"0.41","em1":"0.95","g0:"是","em0":"无情绪","g1":"1","ex0":"严肃","age":"22","ex1":"1"}
		
		//(ajax接收)r={ft1: "1", fs0: "椭圆", ft0: "真人", beauty: "48.65", fs1: "0.39", …} 
		// r为对象 ，通过r['ft1'],获取ft1的值。（//对象的键不加双引号，“字符串的键”都加了双引号）
		response.getWriter().print(param);
	

		//response.getWriter().println("检测到的人脸数量为："+facenum+"<br>");
		//dataType='html' 时的响应格式
		/*response.getWriter().print("年龄："+age+"<br>");
		response.getWriter().print("颜值："+beauty+"<br>");
		response.getWriter().print("是否佩戴眼镜："+g[0]+"  ,  可信度："+g[1]+"<br>");
		response.getWriter().print("人物类型："+ft[0]+"  ,  可信度："+ft[1]+"<br>");
		response.getWriter().print("脸型："+fs[0]+"  ,  可信度："+fs[1]+"<br>");
		response.getWriter().print("情绪："+em[0]+"  ,  可信度："+em[1]+"<br>");
		response.getWriter().print("表情："+ex[0]+"  ,  可信度："+ex[1]+"<br>");*/
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
