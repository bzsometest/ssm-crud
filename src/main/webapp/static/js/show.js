//加载员工信息，和分页信息
var webPath = $path;
var totalRecord;// 总记录数，主要用于新增数据后显示最后一页
var currentPage;//当前页码
$(function () {
    //加载第一页信息
    to_page(1);
    //加载部门信息
    getDepts();
});

function to_page(pn) {
    $.ajax({
        url: webPath + "/emps",
        data: "pn=" + pn,
        type: "GET",
        success: function (result) {
            console.log(result);
            // 1、解析并显示员工数据
            build_emps_table(result);
            // 2、解析并显示分页信息
            build_page_info(result);
            // 3、解析显示分页条数据
            build_page_nav(result);
        }
    });
}

function build_emps_table(result) {
    var emps = result.extend.pageInfo.list;
    // 清空table表格
    $("#emps_table tbody").empty();
    $.each(emps, function (index, item) {
        var empIdTd = $("<td></td>").append(item.emp_Id);
        var empNameTd = $("<td></td>").append(item.emp_name);
        var empGenderTd = $("<td></td>").append(
            item.gender == "M" ? "男" : "女");
        var empEmailTd = $("<td></td>").append(item.email);
        var empDeptNameTd = $("<td></td>").append(
            item.department.dept_name);

        var editBtn = $("<button></button>")
            .addClass("btn btn-primary btn-sm edit_btn")
            .append('<span class="glyphicon glyphicon-pencil"></span>编辑');
        //为编辑按钮添加一个自定义属性，来表示当前员工id
        editBtn.attr("edit-id", item.emp_Id);

        var delBtn = $("<button></button>")
            .addClass("btn btn-danger btn-sm delete_btn")
            .append('<span class="glyphicon glyphicon-trash"></span>删除');
        //为编辑按钮添加一个自定义属性，来表示当前员工id
        delBtn.attr("edit-id", item.emp_Id);

        var btnTd = $("<td></td>").append(editBtn).append(" ")
            .append(delBtn);

        var empTr = $("<tr></tr>").append(empIdTd).append(
            empNameTd).append(empGenderTd).append(
            empEmailTd).append(empDeptNameTd).append(
            empEmailTd).append(btnTd);

        $("#emps_table tbody").append(empTr);
    });
}

// 解析显示分页信息
function build_page_info(result) {
    $("#page_info_area").empty();
    var info = "当前" + result.extend.pageInfo.pageNum + "页, 总"
        + result.extend.pageInfo.pages + "页, 总"
        + result.extend.pageInfo.total + "记录";
    $("#page_info_area").append(info);
    totalRecord = result.extend.pageInfo.total;
    currentPage = result.extend.pageInfo.pageNum;
}

// 解析显示分页条，点击分页要能去下一页....
function build_page_nav(result) {
    // page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    // 构建元素
    var firstPageLi = $("<li></li>").append(
        $("<a></a>").append("首页").attr("href", "#"));

    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.pageInfo.hasPreviousPage == false) {
        firstPageLi.addClass("disabled");
        prePageLi.addClass("disabled");
    } else {
        // 为元素添加点击翻页的事件
        firstPageLi.click(function () {
            to_page(1);
        });
        prePageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum - 1);
        });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append(
        $("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.pageInfo.hasNextPage == false) {
        nextPageLi.addClass("disabled");
        lastPageLi.addClass("disabled");
    } else {
        nextPageLi.click(function () {
            to_page(result.extend.pageInfo.pageNum + 1);
        });
        lastPageLi.click(function () {
            to_page(result.extend.pageInfo.pages);
        });
    }

    // 添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);

    // 1,2，3遍历给ul中添加页码提示
    $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {

        var numLi = $("<li></li>").append($("<a></a>").append(item));
        if (result.extend.pageInfo.pageNum == item) {
            numLi.addClass("active");
        }
        numLi.click(function () {
            to_page(item);
        });
        ul.append(numLi);
    });

    // 添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    // 把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
}

// 获得部门列表
function getDepts() {
    $.ajax({
        url: webPath + "/depts",
        type: "GET",
        success: function (result) {
            console.log(result);
            // 显示部门信息到模态框中
            $(".depts_select").empty();
            $.each(result.extend.depts, function () {
                var optionEle = $("<option></option>").append(this.dept_name)
                    .attr("value", this.dept_Id)
                $(".depts_select").append(optionEle);
            });
        }
    });
}
