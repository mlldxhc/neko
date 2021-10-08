let sign = 0;

function tableAjax(page, totalPage, url) {
    $.ajax({
        url: url + 'userAjax',
        data: {"page": page},
        success: function (users) {
            if (page > 1) {
                $("#up").show();
            } else {
                $("#up").hide();
            }
            if (page >= totalPage) {
                $("#down").hide();
            } else {
                $("#down").show();
            }
            $("#totalPage").text("共" + totalPage + "页");
            let html = null;
            for (let i = 0; i < users.length; i++) {
                let cats;
                 if (users[i].cats == null) {
                     cats = '未领养';
                 } else {
                     cats = users[i].cats;
                 }

                html += "<tr>" +
                    "<td>" + users[i].id + "</td>" +
                    "<td>" + users[i].name + "</td>" +
                    "<td>" + users[i].phone + "</td>" +
                    "<td>" + users[i].username + "</td>" +
                    "<td>" + users[i].password + "</td>" +
                    "<td>" + cats + "</td>" +
                    "<td>" + users[i].perms + "</td>" +
                    "<td>" +
                    "<a class='btn btn-sm btn-primary' href='" + url + "toUpdate/" + page + "/" + users[i].id + "'>编辑</a>&nbsp;" +
                    "<a class='btn btn-sm btn-danger' href='" + url + "delete/" + page + "/" + users[i].id + "' onclick='return deleteOr()'>删除</a>" +
                    "</td>" +
                    "</tr>";
            }
            $("#table").html(html);
            $("#page").text(page);
        }
    });
}

function nameAjax(url) {
    let username = $("#username").val();
    $.ajax({
        url: url + "nameAjax",
        data: {"username": username},
        success: function (data) {
            if (data === "\"0\"") {
                sign = 0;
                $("#exit").show();
            } else {
                sign = 1;
                $("#exit").hide();
            }
        }
    })
}

function a() {
    if (sign === 0) {
        alert("用户名已存在");
        return false;
    }
    if ($("#username").val() == null) {
        alert("请输入用户名");
        return false;
    }
}

function catAjax(page, totalPage, url, method) {
    $.ajax({
        url: url + 'catAjax',
        data: {"page": page},
        success: function (cats) {
            let html = null;
            for (let i = 0; i < cats.length; i++) {
                if (method === 0) {
                    if (page > 1) {
                        $("#up").show();
                    } else {
                        $("#up").hide();
                    }
                    if (page >= totalPage) {
                        $("#down").hide();
                    } else {
                        $("#down").show();
                    }
                    $("#totalPage").text("共" + totalPage + "页");
                    let username;
                    let adoptDate;
                    if (cats[i].username == null||cats[i].username == "") {
                        username = '未被领养';
                        adoptDate = '无';
                    } else {
                        username = cats[i].username;
                        adoptDate = cats[i].adoptDate;
                    }
                    html += "<tr>" +
                        "<td>" + cats[i].id + "</td>" +
                        "<td>" + cats[i].area + "</td>" +
                        "<td>" + username + "</td>" +
                        "<td>" + adoptDate + "</td>" +
                        "<td>" +
                        "<a class='btn btn-sm btn-primary' href='" + url + "toUpdateCat/" + page + "/" + cats[i].id + "'>编辑</a>&nbsp;" +
                        "<a class='btn btn-sm btn-danger' href='" + url + "deleteCat/" + page + "/" + cats[i].id + "' onclick='return deleteOr()'>删除</a>" +
                        "</td>" +
                        "</tr>";
                    $("#table").html(html);
                } else {
                    if (page > 1) {
                        $("#up_all").show();
                    } else {
                        $("#up_all").hide();
                    }
                    if (page >= totalPage) {
                        $("#down_all").hide();
                    } else {
                        $("#down_all").show();
                    }
                    $("#totalPage_all").text("共" + totalPage + "页");
                    html += "<tr>" +
                        "<td>" + cats[i].id + "</td>" +
                        "<td>" + cats[i].area + "</td>" +
                        "<td>" +
                        "<a class='btn btn-sm btn-primary' href=" + url + "catImg/cat" + cats[i].id+".png" +
                        ">详细信息</a>" +
                        "</td>" +
                        "</tr>";
                    $("#table_all").html(html);
                }
            }
            $("#page_all").text(page);
        }
    });
}

function adoptCat() {
    return confirm("是否领养");
}

function adoptAjax(page, totalPage, url) {
    $.ajax({
        url: url + 'adoptAjax',
        data: {"page": page},
        success: function (cats) {
            if (page > 1) {
                $("#up").show();
            } else {
                $("#up").hide();
            }
            if (page >= totalPage) {
                $("#down").hide();
            } else {
                $("#down").show();
            }
            if (cats.length === 0) {
                $("#table").html("<h2>您暂未领养猫</h2>");
                return;
            }
            $("#totalPage").text("共" + totalPage + "页");
            let html = null;
            for (let i = 0; i < cats.length; i++) {
                html += "<tr>" +
                    "<td>" + cats[i].id + "</td>" +
                    "<td>" + cats[i].area + "</td>" +
                    "<td>" + cats[i].adoptDate + "</td>" +
                    "<td>" +
                    "<a class='btn btn-sm btn-primary' href='" + url + "discCat/" + page + "/" + cats[i].id +
                    "' onclick='return deleteOr()'>弃养</a>" +
                    "</td>" +
                    "</tr>";
            }
            $("#table").html(html);
            $("#page").text(page);
        }
    });
}

function canAdoptAjax(page, totalPage, url) {
    $.ajax({
        url: url + 'canAdoptAjax',
        data: {"page": page},
        success: function (cats) {
            if (page > 1) {
                $("#up").show();
            } else {
                $("#up").hide();
            }
            if (page >= totalPage) {
                $("#down").hide();
            } else {
                $("#down").show();
            }
            if (cats.length === 0) {
                $("#table").html("<h2>暂未有可领养的猫</h2>");
                return;
            }
            $("#totalPage").text("共" + totalPage + "页");
            let html = null;
            for (let i = 0; i < cats.length; i++) {
                html += "<tr>" +
                    "<td>" + cats[i].id + "</td>" +
                    "<td>" + cats[i].area + "</td>" +
                    "<td>" +
                    "<a class='btn btn-sm btn-primary' href="+url+"catImg/cat"+cats[i].id+".png"+
                    ">详细信息</a>" +
                    "</td>" +
                    "<td>" +
                    "<a class='btn btn-sm btn-primary' href='" + url + "adoptCat/" + page + "/" + cats[i].id +
                    "' onclick='return adoptCat()'>领养</a>" +
                    "</td>" +
                    "</tr>";
            }
            $("#table").html(html);
            $("#page").text(page);
        }
    });
}
