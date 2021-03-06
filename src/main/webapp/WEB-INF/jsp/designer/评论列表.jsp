<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>系统消息列表</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>
<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">用户评论</h1>
                <h4 style="margin-left: 10px;">——评论列表</h4>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
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
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>用户名</th>
                                    <th>评论对象类型</th>
                                    <th>评论时间</th>
                                    <th>评论内容</th>
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


    </div>
    <!-- /#page-wrapper -->

    <!-- Modal -->
    <div class="modal fade" id="delModal" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel"
         aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">删除提示</h4>
                </div>
                <div class="modal-body">
                    确定删除该评论？删除该评论后，该评论下的所有回复将一同删除。
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" onclick="commentList.fn.subDelInfo()" class="btn btn-primary">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>
    <!-- Modal end -->

    <div class="modal fade" id="infoModal" tabindex="-1" role="dialog" aria-labelledby="pwdModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    <h4 class="modal-title">回复评论</h4>
                </div>
                <div class="modal-body">
                    <form id="infoForm" method="post" action="backend/productType/save" class="form-horizontal" role="form" enctype="multipart/form-data">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">回复内容:</label>
                            <div class="col-sm-8">
                                <textarea cols="20" class="form-control" style="resize: none" rows="6" id="replyInfo" maxlength="50" placeholder="请输入回复内容，最多50个字"></textarea>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    <button type="button" onclick="commentList.fn.subInfo()" class="btn btn-primary">确定</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <!-- /.modal-dialog -->
    </div>

</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>
</body>

<script type="text/javascript">

    var commentList = {
        v: {
            id: "commentList",
            list: [],
            dTable: null,
            commentId: 0
        },
        fn: {
            init: function () {
                commentList.fn.dataTableInit();

                // 查询
                $("#c_search").click(function () {
                    commentList.v.dTable.ajax.reload();
                });
            },
            dataTableInit: function () {
                commentList.v.dTable = $sixmac.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ordering": false,
                    "ajax": {
                        "url": "designer/comment/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "user.mobile"},
                        {"data": "objectType"},
                        {"data": "createTime"},
                        {"data": "content"},
                        {"data": ""}
                    ],
                    "columnDefs": [
                        {
                            "data": null,
                            "defaultContent": "<a title='回复' class='btn btn-primary btn-circle edit'>" +
                            "<i class='fa fa-edit'></i>" +
                            "</a>" +
                            "&nbsp;&nbsp;" +
                            "<button type='button' title='删除' class='btn btn-danger btn-circle delete'>" +
                            "<i class='fa fa-remove'></i>" +
                            "</button>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        commentList.v.list.push(data);

                        if (data.objectType == 1) {
                            $('td', row).eq(1).html("设计师");
                        } else if (data.objectType == 2) {
                            $('td', row).eq(1).html("设计作品");
                        } else if (data.objectType == 3) {
                            $('td', row).eq(1).html("灵感集");
                        }

                        if (data.content.length > 10) {
                            $('td', row).eq(3).html(data.content.substring(0, 10) + '...');
                        } else {
                            $('td', row).eq(3).html(data.content);
                        }
                    },
                    rowCallback: function (row, data) {
                        $('td', row).last().find(".edit").click(function () {
                            commentList.v.commentId = data.id;
                            commentList.fn.addInfo();
                        });

                        $('td', row).last().find(".delete").click(function () {
                            // 删除
                            commentList.v.commentId = data.id;
                            commentList.fn.delInfo();
                        });
                    },
                    "fnServerParams": function (aoData) {
                    },
                    "fnDrawCallback": function (row) {
                        $sixmac.uiform();
                    }
                });
            },
            addInfo: function () {
                $sixmac.clearForm($('#infoForm'));

                $("#infoModal").modal("show");
            },
            delInfo: function () {
                $("#delModal").modal("show");
            },
            subDelInfo: function () {
                $sixmac.ajax("designer/comment/delete", {
                    "id": commentList.v.commentId
                }, function (result) {
                    if (result == 1) {
                        $sixmac.notify("操作成功", "success");
                        $("#delModal").modal("hide");
                        commentList.v.dTable.ajax.reload(null, false);
                    } else {
                        $sixmac.notify("操作失败", "error");
                    }
                });
            },
            subInfo: function () {
                var flag = true;
                var replyInfo = $('#replyInfo').val();

                if (null == replyInfo || replyInfo == '') {
                    $sixmac.notify("请输入回复内容", "error");
                    flag = false;
                    return;
                }

                // 所有的验证通过后，执行新增操作
                if (flag) {
                    $("#infoForm").ajaxSubmit({
                        url: _basePath + "designer/comment/addReply",
                        dataType: "json",
                        data: {
                            "commentId": commentList.v.commentId,
                            "replyInfo": replyInfo
                        },
                        success: function (result) {
                            if (result > 0) {
                                $sixmac.notify("操作成功", "success");
                                $("#infoModal").modal("hide");
                                commentList.v.dTable.ajax.reload(null, false);
                            } else {
                                $sixmac.notify("操作失败", "error");
                            }
                        }
                    });
                }
            }
        }
    }

    $(document).ready(function () {
        commentList.fn.init();
    });

</script>

</html>