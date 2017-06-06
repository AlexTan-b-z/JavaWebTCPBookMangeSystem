$('#idfind').hide();
$('#namefind').hide();
$('#authorfind').hide();
$('#pricefind').hide();
$('#idBT')[0].onclick=function(){
	$('#idBT').hide();
	$('#booknameBT').hide();
	$('#authorBT').hide();
	$('#priceBT').hide();
	$('#back').hide();
	$('#idfind').show();
}
$('#booknameBT')[0].onclick=function(){
	$('#idBT').hide();
	$('#booknameBT').hide();
	$('#authorBT').hide();
	$('#priceBT').hide();
	$('#back').hide();
	$('#namefind').show();
}
$('#authorBT')[0].onclick=function(){
	$('#idBT').hide();
	$('#booknameBT').hide();
	$('#authorBT').hide();
	$('#priceBT').hide();
	$('#back').hide();
	$('#authorfind').show();
}
$('#priceBT')[0].onclick=function(){
	$('#idBT').hide();
	$('#booknameBT').hide();
	$('#authorBT').hide();
	$('#priceBT').hide();
	$('#back').hide();
	$('#pricefind').show();
}

//给下载和预览按钮绑定id属性
var alltd = $('.success');
for(i=0; i<alltd.length;i++){
	var id = alltd[i].children[0].innerText;
	alltd[i].children[4].children[0].setAttribute("id", id);
	alltd[i].children[5].children[0].setAttribute("id", id);
	
	alltd[i].children[5].children[0].setAttribute("class", "download");
	alltd[i].children[4].children[0].setAttribute("class", "view");
}
//点击下载按钮
$('.download').click(function() {
	var id = $(this)[0].id;
	console.log(id);
	window.location.href = "downloadServlet?id="+id;
});

//点击预览按钮
$('.view').click(function() {
	var id = $(this)[0].id;
	console.log(id);
	window.location.href = "viewServlet?id="+id;
});
