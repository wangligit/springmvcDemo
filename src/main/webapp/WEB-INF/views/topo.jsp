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
	
	var from = newNode(350, 100, "号码管理\n4008306666\n4008306667\n4008306668\n40083066669");//\n4008306666
	var to = newNode(350, 250, "落地号");
	var link = newLink(from, to, '1:N');
	
	var to2 = newNode(350, 400, "中继组");
	var link = newLink(to, to2, '1:N');
	
	var to3 = newNode(100, 400, "线路信息");//350+150+70
	var link = newLink(to2, to3, 'N:1');
	
	var to4 = newNode(600, 400, "计费信息");//350-150-70
	var link = newLink(to2, to4, 'N:1');
	
	var to5 = newNode(100, 250, "网关信息");
	var link = newLink(to3, to5, 'N:1');
	
	var to6 = newNode(600, 100,  "Telid");
	var link = newLink(from, to6, '1:N');

	
	// 节点
	function newNode(x, y, text){
		var node = new JTopo.Node(text);
		node.setLocation(x, y);
		node.fillColor = '204, 222, 184';  
		//node.setSize(width, height);
		node.borderRadius = 5; // 圆角
		node.borderWidth = 2; // 边框的宽度
		node.setSize(150, 80);  // 尺寸
		node.textPosition = 'Middle_Center';// 文字居中
		node.font = 'bold 14px 微软雅黑'; // 字体
		node.fontColor = '68,68,68';
		scene.add(node);
		return node;
	}
	
	// 简单连线
	function newLink(nodeA, nodeZ, text, dashedPattern){
		var link = new JTopo.Link(nodeA, nodeZ, text);		
		//link.lineWidth = 3; // 线宽
		//link.dashedPattern = dashedPattern; // 虚线
		//link.bundleOffset = 60; // 折线拐角处的长度
		link.bundleGap = 20; // 线条之间的间隔
		link.textOffsetX = 15;
		link.textOffsetY = 5; // 文本偏移量（向下3个像素）
		link.strokeColor = '93, 91, 91';//连线的颜色0,200,255
		link.fontColor = '68,68,68';  
		scene.add(link);
		return link;
	}
	
	from.click(function(event){

		window.location.href="http://www.baidu.com";
	});
	
	from.paintText = function(a){
		var b = this.text;
		if (null != b && "" != b) {
			a.beginPath(),
			a.font = this.font;
			var c = a.measureText(b).width,
			d = a.measureText("田").width;
			a.fillStyle = "rgba(" + this.fontColor + ", " + this.alpha + ")";
			var e = this.getTextPostion(this.textPosition, c, d);
			a.wrapText(b, e.x, e.y),
					a.closePath()
		}
	}
	
	CanvasRenderingContext2D.prototype.wrapText = function(str,x,y){
		var textArray = str.split('\n');
		if(textArray==undefined||textArray==null)return false;

		var rowCnt = textArray.length;
		var i = 0,imax = rowCnt,maxLength = 0;maxText = textArray[0];
		var lineHeight = this.measureText("元").width;
		x-= lineHeight*2;
		y-= lineHeight*(textArray.length-1)/2;
		for(var j= 0;j<textArray.length;j++){
			var words = textArray[j];
			var maxWidth = this.measureText(words).width; 
			this.fillText(words,-(maxWidth/2),y-textArray.length*lineHeight/100);
			this.font = '12px 微软雅黑';
			y+= lineHeight;
		}
	};

});

function test(){
	var ps = ['号码管理', '落地号', '中继组', '线路信息', '计费信息'
				, '网关信息', 'Telid'];
	var rs = ['1:N', '1:N', 'N:1', 'N:1', 'N:1'
				, 'N:1', '1:N'];
	var x = "350";
	var y = "100";
	var from = newNode(350, 100, 100, 60, ps[0]);
	for (var i= 0; i< 3; i++) {
		
		for(var j = 0;j<3;j++){
			var text = ps[i];
			var linkText = rs[i-1];
			var to = newNode(350, 250, 100, 60, text);
			var link = newLink(from, to, linkText);
			from = to;
		}

	}
}


</script>
</body>
</html>