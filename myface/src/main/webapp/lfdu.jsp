<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>上传图片识别</title>
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
	<h1>上传图片登录</h1>

	<div id="uploader">
		<input type="file" onchange="changeFile(event);" /> <input
			type="button" id="btn" value="立即登录" />
	</div>

	<div id="show">
		<img id="showImg" src="" />
	</div>


	<div class="two" style="position: absolute; left: 40%;top:120px;">
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
		var dataURL;
		var dataUrl;
		function changeFile(event) {
			$("#result")[0].style.display = "none";
			file = event.target.files[0];
			//console.log(file)

			var fr = new FileReader();

			//先读取
			fr.readAsDataURL(file);

			//FileReader.onload事件在读取完成后触发。
			fr.onload = function(e) {
				dataURL = e.target.result;

				//特别注意：提取图片用base64编码后的字符串
				dataUrl = dataURL.split(',')[1];
				//console.log(dataUrl)
				$('#showImg').attr('src', dataURL);
			}
		}

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
						console.log(r);

						$("#score").html(r['score']);
						$("#resultmsg").html(r['resultmsg']);
						$("#userid").html(r['userid']);
					}
				});
			});
		})
	</script>

</body>

</html>