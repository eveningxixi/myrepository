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
	<h1>人脸识别</h1>

	<div id="uploader">
		<input type="file" onchange="changeFile(event);" /> <input
			type="button" id="btn" value="立即识别" />
	</div>

	<div id="show">
		<img id="showImg" src="" />
	</div>


	<div class="two" style="position: absolute; left: 40%;top:120px;">
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

						//$("#p").html(r);

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