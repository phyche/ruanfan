<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>用户管理</title>
    <%@ include file="inc/css.jsp" %>
</head>
<style>
    .textAling {
        text-align: center;
    }
</style>
<body>

<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">管理人员</h1>
                <h4 style="margin-left: 10px;" id="showH">——新增权限</h4>
            </div>
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <form id="productForm" method="post" action="backend/roles/save"
                              class="form-horizontal nice-validator n-default" role="form" novalidate="novalidate">
                            <input type="hidden" id="id" name="id" value="${roles.id}"/>

                            <div class="form-group">
                                <label class="col-sm-1 control-label">角色名称:</label>

                                <div class="col-sm-4">
                                    <input type="text" class="form-control" id="name" name="name" maxlength="50"
                                           data-rule="required" value="${roles.name}" placeholder="请输入角色名称"/>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-sm-1 control-label">权限名称:</label>

                                <div class="col-sm-11">
                                    <table border="1" style="line-height: 30px;">
                                        <tr width="100px">
                                            <th width="6%;" class="textAling">序号</th>
                                            <th width="12%;" class="textAling">功能名称</th>
                                            <th width="80%">

                                            </th>
                                        </tr>
                                        <tr>
                                            <td class="textAling">1</td>
                                            <td class="textAling">用户管理</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理设计师
                                                    <input type="checkbox" name="roles" value="roles"/>管理商户
                                                    <input type="checkbox" name="roles" value="roles"/>管理会员
                                                    <input type="checkbox" name="roles" value="roles"/>管理管理人员
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="textAling">2</td>
                                            <td class="textAling">商品管理</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理商品单品
                                                    <input type="checkbox" name="roles" value="roles"/>管理商品套餐
                                                    <input type="checkbox" name="roles" value="roles"/>管理秒杀商品
                                                    <input type="checkbox" name="roles" value="roles"/>管理订单
                                                    <input type="checkbox" name="roles" value="roles"/>管理评价
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="textAling">3</td>
                                            <td class="textAling">灵感集管理</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理灵感图集
                                                    <input type="checkbox" name="roles" value="roles"/>管理虚拟体验
                                                    <input type="checkbox" name="roles" value="roles"/>管理杂志
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="textAling">4</td>
                                            <td class="textAling">分类管理</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理商品种类
                                                    <input type="checkbox" name="roles" value="roles"/>管理品牌分类
                                                    <input type="checkbox" name="roles" value="roles"/>管理风格分类
                                                    <input type="checkbox" name="roles" value="roles"/>管理灵感图区域分类
                                                    <input type="checkbox" name="roles" value="roles"/>管理虚拟体验分类
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="textAling">5</td>
                                            <td class="textAling">站长工具</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理广告banner
                                                    <input type="checkbox" name="roles" value="roles"/>管理饭票
                                                    <input type="checkbox" name="roles" value="roles"/>管理预约
                                                    <input type="checkbox" name="roles" value="roles"/>管理反馈
                                                    <input type="checkbox" name="roles" value="roles"/>管理收入
                                                    <input type="checkbox" name="roles" value="roles"/>管理操作日志
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="textAling">6</td>
                                            <td class="textAling">消息管理</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理消息
                                                </div>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="textAling">7</td>
                                            <td class="textAling">日志管理</td>
                                            <td>
                                                <div>
                                                    <input type="checkbox" name="roles" value="roles"/>管理日志
                                                </div>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-6" style="text-align: center">
                                    <a href="javascript:void(0)" type="button" class="btn btn-primary" onclick="roles.fn.subInfo()">确定添加</a>
                                    <a href="backend/roles/index" type="button" class="btn btn-primary">返回</a>
                                </div>
                            </div>
                        </form>
                    </div>
                    <!-- /.panel-body -->

                </div>
                <!-- /.panel -->
            </div>
        </div>

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>

</body>

<script type="text/javascript">
    var roles = {
        v: {
            id: "roles",
            list: [],
            dTable: null,
            types: ""
        },
        fn: {
            init: function () {
                $.ajaxSetup({
                    async: false
                });

                if ($("#id").val() != "") {
                    $("#showH").text("——编辑消息");
                }

                $('#sendto_alls').click(function () {
                    if (this.checked) {
                        $(":checkbox").each(function () {
                            this.checked = true;
                        });
                    } else {
                        $(":checkbox").each(function () {
                            this.checked = false;
                        });
                    }
                });

                roles.fn.loadData();
            },
            loadData: function () {
                // 界面加载时，选中复选框
                var id = $('#id').val();
                if (null != id && id != '') {
                    // 回选复选框
                    var tempType = $('#tempType').val();
                    var array = tempType.split(',');
                    for (var i = 0; i < array.length; i++) {
                        $('input:checkbox[name="roles"]').each(function () {
                            if ($(this).val() == array[i]) {
                                $(this).prop("checked", true);
                            }
                        });
                    }
                }
            },
            checkData: function () {
                var flag = true;
                var result = false;
                var name = $('#name').val();

                if (null == name || name == '') {
                    $sixmac.notify('角色名称不能为空', "error");
                    flag = false;
                    return;
                }

                $('input:checkbox[name="roles"]').each(function () {
                    if ($(this).prop("checked")) {
                        result = true;
                        return;
                    }
                });

                if (!result) {
                    $sixmac.notify('权限不能为空', "error");
                    return;
                }

                // 到此处的时候，说明前面的校验都通过，此时保存需要提交的复选框的值
                $('input:checkbox[name="roles"]:checked').each(function () {
                    roles.v.types += $(this).val() + ',';
                });

                return flag;
            },
            subInfo: function () {
                // 所有的验证通过后，执行新增操作
                if (roles.fn.checkData()) {
                    $.post(_basePath + "backend/roles/save",
                            {
                                "id": $('#id').val(),
                                "name": $('#name').val(),
                                "types": roles.v.types
                            },
                            function (data) {
                                if (data > 0) {
                                    window.location.href = _basePath + "backend/roles/index";
                                } else {
                                    $sixmac.notify("操作失败", "error");
                                }
                            });
                }
            },
            checkChkBox: function (data) {
                if (data.checked == false) {
                    document.getElementsByName('roles')[0].checked = false;
                }
                var arrChk = document.getElementsByName('roles');
                var flag = true;
                for (var i = 0; i < arrChk.length; i++) {
                    if (i == 0) {
                        continue;
                    } else {
                        if (arrChk[i].checked == false) {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    document.getElementsByName('roles')[0].checked = flag;
                }
            },
            goBack: function () {
                window.location.href = "backend/roles/index";
            }
        }
    }

    $(document).ready(function () {
        roles.fn.init();
    });

</script>

</html>