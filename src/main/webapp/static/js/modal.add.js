$("#emp_add_modal_btn").click(function() {
	// 发送ajax请求，查出部门信息，显示在下拉列表中
	getDepts();
	// 弹出模态框
	$("#empAddModal").modal({
		backdrop : "static"
	});
	// 重新调用验证方法
	$("#empName_add_input").change();
	$("#email_add_input").change();
});

$("#emp_save_btn").click(function() {
	// 先对要提交的内容进行格式校验
	if (!validate_add_form()) {
		return false;
	}

	// 提交之前的ajax验证信息
	if ($(this).attr("ajax-va") != "success") {
		return false;
	}

	// 发送ajax请求保存员工信息
	var emp = $("#empAddModal form").serialize();
	console.log(emp);
	$.ajax({
		url : webPath + "/emp",
		type : "POST",
		data : emp,
		success : function(result) {
			console.log(result);
			if (result.code == 100) {
				// 1.员工保存成功，关闭模态框
				$("#empAddModal").modal("hide");
				// 2.来到最后一页，显示最后一页的数据
				// 发送ajax请求
				to_page(totalRecord);
			} else {
				// 显示保存失败信息
				show_save_error(result);
			}

		}
	});
});
function show_save_error(result) {
	if (undefined != result.extend.errorFileds.emp_name) {
		// 显示用员工名错误信息
		show_validate_msg("#empName_add_input", "error",
				result.extend.errorFileds.emp_name);
	}
	if (undefined != result.extend.errorFileds.email) {
		// 显示用email错误信息
		show_validate_msg("#email_add_input", "error",
				result.extend.errorFileds.email);
	}
}

function validate_add_form() {
	// 1.拿到要校验的数据
	var empName = $("#empName_add_input").val();
	var regName = /(^[a-zA-Z0-9_-]{5,16}$)|(^[\u4e00-\u9fa5]{2,5})/;
	if (!regName.test(empName)) {
		// alert("用户名必须是5-16位英文和数字的组合或者2-5位中文！");
		show_validate_msg("#empName_add_input", "error",
				"用户名必须是2-5位中文或者5-16位英文和数字的组合!");
		return false;
	} else {
		show_validate_msg("#empName_add_input", "success", "");
	}
	// 2.校验邮箱信息
	var email = $("#email_add_input").val();
	var regEmail = /^[A-Za-z\d]+([-_.][A-Za-z\d]+)*@([A-Za-z\d]+[-.])+[A-Za-z\d]{2,4}$/;
	if (!regEmail.test(email)) {
		// alert("邮箱输入错误！");
		show_validate_msg("#email_add_input", "error", "邮箱格式错误！");
		return false;
	} else {
		show_validate_msg("#email_add_input", "success", "");
	}
	return true;
}

function show_validate_msg(ele, status, msg) {
	// 清除当前元素状态
	$(ele).parent().removeClass("has-success has-error");
	$(ele).next(".help-block").text(msg);
	if ("success" == status) {
		$(ele).parent().addClass("has-success");
	} else {
		$(ele).parent().addClass("has-error");
	}
}

// 检验用户名是否可用
$("#empName_add_input").change(function() {
	var emp_name = this.value;
	if (emp_name.length == 0) {
		return;
	}
	// 发送ajax请求校验用户名是否可用
	$.ajax({
		url : webPath + "/checkuser",
		data : "emp_name=" + emp_name,
		type : "POST",
		success : function(result) {
			if (result.code == 100) {
				show_validate_msg("#empName_add_input", "success", "用户名可用");
				$("#emp_save_btn").attr("ajax-va", "success");
			} else {
				show_validate_msg("#empName_add_input", "error", "用户名已存在！");
				$("#emp_save_btn").attr("ajax-va", "error");
			}
		}
	});
});

// 检验邮箱是否可用
$("#email_add_input").change(function() {

	var email = this.value;
	return;
	$.ajax({
		url : webPath + "/checkemail",
		data : "email=" + email,
		type : "POST",
		success : function(result) {
			if (result.code == 100) {
				show_validate_msg("#empName_add_input", "success", "邮箱可用");
			} else {
				show_validate_msg("#empName_add_input", "error", "邮箱已存在！");
			}
		}
	});
});

