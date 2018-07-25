//使用此方法，也能为后来的元素绑定事件
$(document).on("click", ".delete_btn", function () {
    var emp_id = $(this).attr("edit-id");
    var empName = $(this).parents("tr").find("td:eq(1)").text();
    if (confirm("确认删除员工【" + empName + "】？")) {
        //发送删除员工信息请求
        delete_emp_ajax(emp_id);
    }
});

function delete_emp_ajax(id) {
    $.ajax({
        url: webPath + "/emp/" + id,
        type: "DELETE",
        success: function (result) {
            console.log(result);
            if (result.code == 100) {
                alert("删除成功！")
                to_page(currentPage);
            }else {
                alert("删除失败！")
            }
        }
    });
}