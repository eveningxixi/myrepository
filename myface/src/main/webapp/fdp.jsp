<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>拍照识别</title>
<script src="https://cdn.staticfile.org/jquery/1.10.2/jquery.min.js"></script>

<style type="text/css">
table.gridtable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #666666;
	border-collapse: collapse;
}

table.gridtable th {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #dedede;
}

table.gridtable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #666666;
	background-color: #ffffff;
}
</style>

</head>

<body>
	<h1>人脸识别</h1>
	<div class="booth">
		<video id="video" width="640" height="480" autoplay></video>
		<button id="snap">拍照</button>
		<input type="button" id="btn" value="立即识别" />
		<canvas id="canvas" width="640" height="480"></canvas>

	</div>



	<div class="two" style="position: relative; left: 40%;">
		<table class="gridtable" id="result"
			style="display: none; text-align: center;">
			<tr>
				<th colspan="4">识别结果</th>

			</tr>
			<tr>
				<td colspan="2">年龄:</td>
				<td colspan="2" id="age"></td>
			</tr>

			<tr>
				<td colspan="2">颜值:</td>
				<td colspan="2" id="beauty"></td>
			</tr>

			<tr>
				<td>人物类型：</td>
				<td id="facetype"></td>
				<td>可信度：</td>
				<td id="pft"></td>
			</tr>

			<tr>
				<td>脸型：</td>
				<td id="faceshape"></td>
				<td>可信度：</td>
				<td id="pfs"></td>
			</tr>

			<tr>
				<td>表情：</td>
				<td id="expression"></td>
				<td>可信度：</td>
				<td id="pex"></td>
			</tr>

			<tr>
				<td>情绪：</td>
				<td id="emotion"></td>
				<td>可信度：</td>
				<td id="pem"></td>
			</tr>

			<tr>
				<td>戴眼镜：</td>
				<td id="glasses"></td>
				<td>可信度：</td>
				<td id="pg"></td>
			</tr>

		</table>
	</div>
	<p id="p"></p>


	<script>
		var aCanvas = document.getElementById('canvas');
		var ctx = aCanvas.getContext('2d');
		var dataURL;
		var dataUrl;

		//打开摄像头
		navigator.getUserMedia = navigator.getUserMedia
				|| navigator.webkitGetUserMedia || navigator.mozGetUserMedia
				|| navigator.msGetUserMedia; //获取媒体对象（这里指摄像头）

				
				
		//参数1获取用户打开权限；参数二成功打开后调用，并传一个视频流对象；参数三打开失败后调用，传错误信息
		navigator.getUserMedia({
			video : true
		}, gotStream, noStream);

		function gotStream(stream) {
			video.srcObject = stream;
			//video.src = window.URL.createObjectURL(stream);
			video.onerror = function() {
				stream.stop();
			};
			stream.onended = noStream;
			video.onloadedmetadata = function() {
				//alert('摄像头成功打开！');
			};
		}

		function noStream(err) {
			alert(err);
		}

		//截取图片
		var aVideo = document.getElementById('video');
		document.getElementById("snap").addEventListener("click", function() {
			$("#result")[0].style.display = "none";
			//将获取视频绘制在画布上
			ctx.drawImage(aVideo, 0, 0, 640, 480);
			//将截取的图片转化为dataURL形式:“data:image/png;base64,****”
			dataURL = canvas.toDataURL();

			//特别注意：提取图片用base64编码后的字符串
			dataUrl = dataURL.split(',')[1];
			//console.log(dataUrl);

			/*			特别注意：
			 * 			提取****，也就是图片用base64编码后的字符串。
			 * 			逗号之前都是一些说明性的文字，我们只需要逗号之后的就行了
						var a= dataURL.split(',')[1];
						console.log(a);*/

		});

		//ajax base64 字符串 （dataUrl）传给后端
		$(document).ready(function() {
			$("#btn").click(function() {
				//显示表格 注意：记得加 [0]
				$("#result")[0].style.display = "block";
				$.ajax({
					url : 'FaceDetectServlet',
					type : 'POST',
					data : {
						'dataUrl' : dataUrl
					},
					dataType : 'json',
					success : function(r) {
						//dataType -->  $("#ab").html(result);
						//alert(r); //[object Object]	
						console.log(r);

						//$("#p").html(rr);

						$("#age").html(r['age']);
						$("#beauty").html(r['beauty']);

						$("#facetype").html(r['ft0']);
						$("#pft").html(r['ft1']);

						$("#faceshape").html(r['fs0']);
						$("#pfs").html(r['fs1']);

						$("#expression").html(r['ex0']);
						$("#pex").html(r['ex1']);

						$("#emotion").html(r['em0']);
						$("#pem").html(r['em1']);

						$("#glasses").html(r['g0']);
						$("#pg").html(r['g1']);

					}
				});
			});
		})
	</script>

</body>

</html>