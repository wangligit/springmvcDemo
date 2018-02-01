<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>jtopo</title>
<script src="static/js/jtopo-0.4.8-min.js"></script>
<!-- jQuery 2.1.4 -->
<script src="static/js/jquery-2.1.4.min.js"></script>
<script src="static/js/topo-tool.js"></script>
</head>
<body>
   <canvas width="800" height="500" id="canvas" style=" background-color:#EEEEEE; border:1px solid #444;">

   </canvas>
<script>
$(document).ready(function(){					
	var canvas = document.getElementById('canvas');
	var stage = new JTopo.Stage(canvas);
	var scene = new JTopo.Scene(stage);	
	var textNode = new JTopo.TextNode('资 源 关 系 图');
	textNode.font = '30px 微软雅黑';
	textNode.fontColor = '68, 68, 68';
	textNode.setLocation(300, 30);
	scene.add(textNode);
	
 	var from = newNode(scene, 350, 100, "号码管理\n4008306666\n4008306667\n4008306668\n40083066669","");//\n4008306666
	var to = newNode(scene , 350, 250, "落地号","");
	var link = newLink(scene, from, to, '1:N');
	
	/*var to2 = newNode(350, 400, "中继组","");
	var link = newLink(to, to2, '1:N');
	
	var to3 = newNode(100, 400, "线路信息","");//350+150+70
	var link = newLink(to2, to3, 'N:1');
	
	var to4 = newNode(600, 400, "计费信息","");//350-150-70
	var link = newLink(to2, to4, 'N:1');
	
	var to5 = newNode(100, 250, "网关信息","");
	var link = newLink(to3, to5, 'N:1');
	
	var to6 = newNode(600, 100,  "Telid","");
	var link = newLink(from, to6, '1:N'); */

});

 function test(){
	var rs = ['','1:N', '1:N', 'N:1', '1:1', 'N:1'
					, 'N:1'];
 	var ps = ['号码管理', 'Telid', '落地号', '中继组', '计费信息', '线路信息'
				, '网关信息'];
	var arr = [[450,100],[700,100],[450,250],[450,400],[700,400],[200,400],[200,250]];
	var from = newNode(scene, arr[0][0], arr[0][1], ps[0],"");	
	for(var i = 1; i<ps.length ; i++){
		var to = newNode(scene , arr[i][0], arr[i][1], ps[i],"");
		var link = newLink(scene, from, to, '1:N');
		if(i != 1 && i != 4){
			from = to;	
		}
	} 
} 


</script>
</body>
</html>