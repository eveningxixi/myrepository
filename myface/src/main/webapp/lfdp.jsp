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
	<h1>人脸登录</h1>
	<div class="booth">
		<video id="video" width="640" height="480" autoplay></video>
		<button id="snap">拍照</button>
		<input type="button" id="btn" value="立即登录" />
		<canvas id="canvas" width="640" height="480"></canvas>

	</div>



	<div class="two" style="position: relative;left: 40%;">
		<table class="gridtable" id="result" style="display: none; text-align: center;">
			<tr>
				<th colspan="4">登录信息</th>

			</tr>
			<tr>
				<td>相似程度：</td>
				<td id="score"></td>
			</tr>

			<tr>
				<td>登录结果：</td>
				<td id="resultmsg"></td>
			</tr>

			<tr>
				<td>用户名：</td>
				<td id="userid"></td>
			</tr>

		</table>
	</div>

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

		});

		//ajax base64 字符串 （dataUrl）传给后端
		$(document).ready(function() {
			$("#btn").click(function() {
				//显示表格 注意：记得加 [0]
				$("#result")[0].style.display = "block";
				$.ajax({
					url : 'FaceSearchServlet',
					type : 'POST',
					data : {
						'dataUrl' : dataUrl
					},
					dataType : 'json',
					success : function(r) {
						//dataType ='html'时， $("#ab").html(result);
						//alert(r); //[object Object]	
						console.log(r);

						$("#score").html(r['score']);
						$("#resultmsg").html(r['resultmsg']);
						$("#userid").html(r['userid']);

					}
				});
				//显示表格 注意：记得加 [0]
				//$("#result")[0].style.display = "block";
			});
			
		})
	</script>

</body>

</html>