//全局Ajax错误处理
//全局Ajax错误处理
$(document).ajaxError(function(event, xhr, options, exc) {
	/* 错误信息处理 */
	/* 弹出jqXHR对象的信息 */
	console.log("Ajax request error!");
	console.log("status: " + xhr.status);
	console.log("statusText: " + xhr.statusText);
	console.log("readyState: " + xhr.readyState);
	console.log("responseText: " + xhr.responseText);
	console.log("exc" + exc);
});
$(document).ajaxSend(function(event, xhr, options) {
	console.log("url: " + options.url);
	console.log("data: " + options.data);
});