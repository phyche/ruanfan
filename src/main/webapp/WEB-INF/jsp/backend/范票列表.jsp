<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>范票列表</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>
<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">管理范票</h1>
                <h4 style="margin-left: 10px;">——范票列表</h4>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="backend/coupon/add" class="btn btn-outline btn-primary btn-lg" role="button">新增范票</a>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover" id="dataTables">
                                <colgroup>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                    <col class="gradeA odd"/>
                                    <col class="gradeA even"/>
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>优惠券码</th>
                                    <th>名称</th>
                                    <th>封面</th>
                                    <th>面值</th>
                                    <th>类型</th>
                                    <th>来源</th>
                                    <th>审核状态</th>
                                    <th>添加时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                </tbody>
                            </table>
                        </div>

                    </div>
                    <!-- /.panel-body -->

                </div>
                <!-- /.panel -->
            </div>
        </div>

        <!-- Modal -->
        <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                        <h4 class="modal-title">删除提示</h4>
                    </div>
                    <div class="modal-body">
                        <input type="hidden" id="couponId"/>
                        确定删除该范票信息？
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                        <button type="button" onclick="couponList.fn.subInfo()" class="btn btn-primary">确定</button>
                    </div>
                </div>
                <!-- /.modal-content -->
            </div>
            <!-- /.modal-dialog -->
        </div>
        <!-- Modal end -->

    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>
</body>

<script type="text/javascript">

    var couponList = {
        v: {
            id: "couponList",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                couponList.fn.dataTableInit();
            },
            dataTableInit: function () {
                couponList.v.dTable = $sixmac.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ordering": false,
                    "ajax": {
                        "url": "backend/coupon/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "couponNum"},
                        {"data": "name"},
                        {"data": null},
                        {"data": "money"},
                        {"data": "type"},
                        {"data": "sourceName"},
                        {"data": "isCheck"},
                        {"data": "createTime"},
                        {"data": ""}
                    ],
                    "columnDefs": [
                        {
                            "data": null,
                            "defaultContent": "<a title='编辑' class='btn btn-primary btn-circle edit'>" +
                            "<i class='fa fa-edit'></i>" +
                            "</a>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button' title='删除' class='btn btn-danger btn-circle delete'>" +
                            "<i class='fa fa-remove'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button' title='审核通过' style='display: none' class='btn btn-success btn-circle checkyes'>" +
                            "<i class='fa fa-check'></i>" +
                            "</button>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button' title='审核不通过' style='display: none' class='btn btn-danger btn-circle checkno'>" +
                            "<i class='fa fa-minus'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        couponList.v.list.push(data);

                        if (data.type == 1) {
                            $('td', row).eq(4).html("满减");
                        } else {
                            $('td', row).eq(4).html("无限制");
                        }

                        if (data.sourceName == '') {
                            $('td', row).eq(5).html("软范后台");
                        }

                        if (data.isCheck == 0) {
                            $('td', row).eq(6).html("待审核");
                        } else if (data.isCheck == 1) {
                            $('td', row).eq(6).html("审核通过");
                        } else {
                            $('td', row).eq(6).html("审核不通过");
                        }

                        if (data.cover == '') {
                            $('td', row).eq(2).html("暂无");
                        } else {
                            $('td', row).eq(2).html("<img src='" + data.cover + "' width='100px' height='100px' />");
                        }

                        $('td', row).eq(0).css('line-height', '102px');
                        $('td', row).eq(1).css('line-height', '102px');
                        $('td', row).eq(2).css('line-height', '102px');
                        $('td', row).eq(3).css('line-height', '102px');
                        $('td', row).eq(4).css('line-height', '102px');
                        $('td', row).eq(5).css('line-height', '102px');
                        $('td', row).eq(6).css('line-height', '102px');
                        $('td', row).eq(7).css('line-height', '102px');
                        $('td', row).eq(8).css('line-height', '102px');
                    },
                    rowCallback: function (row, data) {
                        if (data.isCheck == 0) {
                            // 未审核时，显示审核按钮
                            $('td', row).last().find(".checkyes").css("display", '');
                            $('td', row).last().find(".checkno").css("display", '');
                        }

                        $('td', row).last().find(".edit").attr("href", 'backend/coupon/add?id=' + data.id);

                        $('td', row).last().find(".delete").click(function () {
                            // 删除
                            couponList.fn.delInfo(data.id);
                        });

                        $('td', row).last().find(".checkyes").click(function () {
                            // 审核为通过
                            couponList.fn.changeCheck(data.id, 1);
                        });

                        $('td', row).last().find(".checkno").click(function () {
                            // 审核为不通过
                            couponList.fn.changeCheck(data.id, 2);
                        });
                    },
                    "fnServerParams": function (aoData) {

                    },
                    "fnDrawCallback": function (row) {
                        $sixmac.uiform();
                    }
                });
            },
            changeCheck: function (id, isCheck) {
                $sixmac.ajax("backend/coupon/changeCheck", {
                    "couponId": id,
                    "isCheck": isCheck
                }, function (result) {
                    if (result == 1) {
                        $sixmac.notify("操作成功", "success");
                        couponList.v.dTable.ajax.reload(null, false);
                    } else {
                        $sixmac.notify("操作失败", "error");
                    }
                });
            },
            delInfo: function (id) {
                $('#couponId').val(id);
                $("#delModal").modal("show");
            },
            subInfo: function () {
                var couponId = $('#couponId').val();

                $sixmac.ajax("backend/coupon/delete", {
                    "couponId": couponId,
                }, function (result) {
                    if (result == 1) {
                        $sixmac.notify("操作成功", "success");
                        $("#delModal").modal("hide");
                        couponList.v.dTable.ajax.reload(null, false);
                    } else if (result == -1) {
                        $sixmac.notify("该优惠券还有用户未使用，暂时无法删除，操作失败", "error");
                        $("#delModal").modal("hide");
                    } else {
                        $sixmac.notify("操作失败", "error");
                    }
                });
            }
        }
    }

    $(document).ready(function () {
        couponList.fn.init();
    });

</script>

</html>