<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="inc/taglibs.jsp" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <%@ include file="inc/meta.jsp" %>
    <meta name="description" content="">
    <meta name="author" content="">
    <title>活动公告列表</title>
    <%@ include file="inc/css.jsp" %>
</head>

<body>
<div id="posts" class="wrapper">

    <%@ include file="inc/nav.jsp" %>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">活动公告</h1>
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
                                </colgroup>
                                <thead>
                                <tr>
                                    <th>标题</th>
                                    <th>描述</th>
                                    <th>发布时间</th>
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

</div>
<!-- /#wrapper -->

<%@ include file="inc/footer.jsp" %>
</body>

<script type="text/javascript">

    var noticeList = {
        v: {
            id: "noticeList",
            list: [],
            dTable: null
        },
        fn: {
            init: function () {
                noticeList.fn.dataTableInit();
            },
            dataTableInit: function () {
                noticeList.v.dTable = $sixmac.dataTable($('#dataTables'), {
                    "processing": true,
                    "serverSide": true,
                    "searching": false,
                    "ordering": false,
                    "ajax": {
                        "url": "merchant/message/list",
                        "type": "POST"
                    },
                    "columns": [
                        {"data": "title"},
                        {"data": "des"},
                        {"data": "createTime"},
                        {"data": ""}
                    ],
                    "columnDefs": [
                        {
                            "data": null,
                            "defaultContent": "<a title='查看' class='btn btn-info btn-circle eye'>" +
                            "<i class='fa fa-eye'></i>" +
                            "</a>",
                            "targets": -1
                        }
                    ],
                    "createdRow": function (row, data, index) {
                        noticeList.v.list.push(data);

                        if (data.title.length > 10) {
                            $('td', row).eq(0).html(data.title.substring(0, 10) + '...');
                        } else {
                            $('td', row).eq(0).html(data.title);
                        }

                        if (data.des.length > 10) {
                            $('td', row).eq(1).html(data.des.substring(0, 10) + '...');
                        } else {
                            $('td', row).eq(1).html(data.des);
                        }
                    },
                    rowCallback: function (row, data) {
                        $('td', row).last().find(".eye").click(function () {
                            window.location.href = 'merchant/message/detail?messageId=' + data.id;
                        });
                    },
                    "fnServerParams": function (aoData) {
                    },
                    "fnDrawCallback": function (row) {
                        $sixmac.uiform();
                    }
                });
            }
        }
    }

    $(document).ready(function () {
        noticeList.fn.init();
    });

</script>

</html>