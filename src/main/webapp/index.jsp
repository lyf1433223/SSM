<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<script type="application/javascript" src="js/jquery-3.3.1.js"></script>
<html>
<head>
    <title>Title</title>
</head>
<body>

<center>

   <a href="student">查询所有</a>

    <h1>添加</h1>
    <form method="post" action="add">
        姓名：<input name="sname" type="text">
        性别：<input name="ssex" type="text">
        年龄：<input name="sage" type="text">
        <button>提交</button>
    </form>


    <h1>修改</h1>
    <form method="post" name="a" action="update">
        编号：<input name="sid" type="text">
        姓名：<input name="sname" type="text">
        性别：<input name="ssex" type="text">
        年龄：<input name="sage" type="text">
        <button>提交</button>
    </form>


    <h1>删除</h1>
    <form method="post" name="a" action="delete">
        编号：<input name="sid" type="text">
        <button>提交</button>
    </form>


    <h1>ajax 查询单个</h1>
    <form name="ajax">
        编号：<input name="sid" type="text">
    </form>
    <button id="button">提交</button>

    <h1>文件</h1>
    <form action="file" enctype="multipart/form-data" method="post">
        <input type="file" multiple="true" name="files">
        <button>提交</button>
    </form>

</center>

</body>
</html>

<script>
  var from = $("[name='ajax']");
  var button = $("#button");
  button.click(function(){
     $.ajax({
        url:"selectOne",
        type:"post",
        data:{from:from.serialize()},
        success:function (result) {
            alert(result)
        }
     });
  });
  // $.ajax({
  //     type:"post",
  //     url:"selectOne",
  //     data:{},
  // })

</script>