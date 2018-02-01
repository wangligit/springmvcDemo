<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>queen for html</title>
<script src="static/js/qunee-min.js"></script>
</head>
<body>
<div style="height: 500px;" id="canvas"/>
<script>
    var graph = new Q.Graph('canvas');
    graph.originAtCenter = false;

    function createText(host, name, x, y, anchorPosition, w, h, fontSize, fontColor, backgroundColor){
        var text = graph.createText(name, x, y);
        text.setStyle(Q.Styles.LABEL_BORDER, 0.5);
        text.setStyle(Q.Styles.LABEL_PADDING, 5);
        text.setStyle(Q.Styles.LABEL_BORDER_STYLE, "#1D4876");
        text.tooltipType = "text";
        if(host){
            text.host = text.parent = host;
        }
        if(anchorPosition){
            text.anchorPosition = anchorPosition;
            text.setStyle(Q.Styles.LABEL_ALIGN_POSITION, anchorPosition);
        }
        if(w && h){
            text.setStyle(Q.Styles.LABEL_SIZE, new Q.Size(w, h));
        }

        text.setStyle(Q.Styles.LABEL_FONT_SIZE, fontSize || 14);
        text.setStyle(Q.Styles.LABEL_COLOR, fontColor || "#555");
        text.setStyle(Q.Styles.LABEL_BACKGROUND_COLOR, backgroundColor || "#FFF");
//        text.setStyle(Q.Styles.LABEL_BACKGROUND_GRADIENT, new Q.Gradient(Q.Consts.GRADIENT_TYPE_LINEAR, ['#00d4f9', '#1ea6e6'], null, Math.PI/2));
        return text;
    }
/*     var hello = graph.createNode("Hello", -100, -50);
    var qunee = graph.createNode("Qunee", 100, 50);
    var edge = graph.createEdge("Hello\nQunee", hello, qunee); */
    var newAPIs = createText(null, "New APIs", 450, 71, Q.Position.CENTER_TOP, 255, 216, 20, "#2eaae6", "#ebebeb");
    createText(newAPIs, "File APIs\nDrag & Drop\nWeb Messaging\nBrowser history management\nWeb Storage\n", 450, 112, Q.Position.CENTER_TOP, 210, 160);
</script>
</body>
</html>