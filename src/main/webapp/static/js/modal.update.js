//使用此方法，也能为后来的元素绑定事件
$(document).on("click", ".edit_btn", function() {
	// 查出当前员工信息
	getEmp($(this).attr("edit-id"));
	// 把员工的id传递给模态框的更新按钮
	$("#emp_update_btn").attr("edit-id", $(this).attr("edit-id"));
	// 弹出模态框
	$("#empUpdateModal").modal({
		backdrop : "static"
	});
});

// 查询员工信息
function getEmp(id) {
	$.ajax({
		url : webPath + "/emp/" + id,
		type : "GET",
		success : function(result) {
			console.log(result);
			var empData = result.extend.emp;
			$("#empName_update_static").text(empData.emp_name);
			$("#email_update_input").val(empData.email);
			$("#empUpdateModal input[name=gender]").val([ empData.gender ]);
			$("#empUpdateModal select").val([ empData.dept_id ]);
		}
	});
}

// 点击更新按钮，更新员工信息
$("#emp_update_btn").click(function() {
	// 验证邮箱是否合法
	if (!validate_update_form()) {
		return false;
	}
	update_emp_ajax();

});

function validate_update_form() {
	// 2.校验邮箱信息
	var email = $("#email_update_input").val();
	var regEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
	if (!regEmail.test(email)) {
		// alert("邮箱输入错误！");
		show_validate_msg("#email_update_input", "error", "邮箱格式错误！");
		return false;
	} else {
		show_validate_msg("#email_update_input", "success", "");
	}
	return true;
}

// 发送更新员工信息请求
function update_emp_ajax() {
	var id = $("#emp_update_btn").attr("edit-id");
	$.ajax({
		url : webPath + "/emp/" + id,
		type : "PUT",
		data : $("#empUpdateModal form").serialize(),
		// data : $("#empUpdateModal form").serialize()+"&_method=PUT",
		success : function(result) {
			console.log(result);
			if (result.code == 100) {
				$("#empUpdateModal").modal("hide");
				to_page(currentPage);
			} else {
				alert("更新失败");
			}
		}
	});
}