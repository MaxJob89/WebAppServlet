<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<html>
<head>
    <title>User</title>
    <style>

        body
        {
            font-family: Gargi;
        }
        .Filtro
        {
            margin-left: 20%;
        }
        h1 {
            align-content: center;
            margin-left: 40%;
        }
        table{
            font-size: medium ;
            border-color: transparent;
            border: 5px;
            width: 500px;
        }

        th
        {

            font-weight: bold;
            font-size: 17px;

        }
        td{
            font-style: oblique;
            height: 36px;
            width: 73px;
            padding-left: 15px;
            padding-right: 10px;
        }
        tr/*:nth-child(even)*/
        {
            background-color: lightgray;
        }

        .Bottone
        {
            border-radius: 40px 40px 40px 40px;
            border: 0px;
            width: 50px;
            height: 40px;
            background-color: grey;
            color: white;
            font-size: 13px;
            font-weight: bold;

        }

        .se
        {
            border-radius: 30px 30px 30px 30px;
            border: 0px;
            width: 100px;
            height: 20px;
            background-color: white;

        }
        .sl
        {
            width: 100px;
            height: 100px;
            background-color: white;
            border: 0px;
        }
        input{
            border-radius: 40px 40px 40px 40px;
            border: 0px;
            text-align:center;
        }

    </style>
    <%--SCRIPT--%>
    <script type="text/javascript" language="JavaScript">

        var add=new Array();
        function Add() {
            var sk=document.getElementById("sk");
            var skadd = document.getElementById("skadd");

            var selezione=sk.selectedOptions;
            for (var i=0;i<selezione.length;i++)
                add.push(selezione[i]);



            for (var i=0;i<add.length;i++)
            {
                skadd.add(add[i]);
            }
            add=[];

        }//end function Add()


        function Sub() {

            var sk=document.getElementById("sk");
            var skadd = document.getElementById("skadd");

            var selezione=skadd.selectedOptions;
            for (var i=0;i<selezione.length;i++)
                add.push(selezione[i]);


            for (var i=0;i<add.length;i++)
            {
                sk.add(add[i]);
            }

            add=[];
        }// end function Less()

        function SelectSkill() {

            var sel=document.getElementById("skadd");
            for (var i = 0; i < sel.length; i++) {
                sel[i].selected = true;
            }
        }


    </script>

</head>

<body>
<H1>User</H1>


<TABLE>

    <form action="/insert.do" method="post" onsubmit="SelectSkill()" name="Register">
        <tr>
            <td>
                Nome : <input type="text" name="nome"  value="${us.getFirstname()}">
            </td>
        </tr>
        <tr>
            <td>
                Cognome : <input type="text" name="cognome" value="${us.getLastname()}">
            </td>
        </tr>
        <tr>
            <td>
                <fmt:formatDate pattern="dd/MM/yyyy" value="${us.getData()}" var="dt"/>
               Data :  <input type="text" name="data" value="${dt}" >
            </td>
        </tr>
        <tr>
            <td>
                Stato : <select name="statusFilter" class="se">
                <c:forEach items="${stat}" var="st">
                   <option >${st}</option>
            </c:forEach>
                <c:if test="${us.getStatus() != null}">
                    <option selected>${us.getStatus()}</option>
                </c:if>
            </select>
            </td>
        </tr>
        <tr>

                <td>
                Default skill <select multiple="multiple" name="sk"  id="sk" class="sl">
                <c:forEach items="${requestScope.ski}" var="skill">

                    <option value="${skill.getDescription()}">${skill.getDescription()}</option>

                </c:forEach>  </select>

                    <input type="button" value="+" onclick="Add()" class="Bottone">
                    <input type="button" value="-" onclick="Sub()" class="Bottone">

                    <select multiple="multiple" name="skadd" id="skadd" class=" sl">
                    <c:forEach items="${us.getSkills()}" var="su">

                         <option value="${su.getDescription()}">${su.getDescription()}</option>


                    </c:forEach>
                    </select> skill add

         </td>

        </tr>

        <tr>
            <td>
                <input hidden name="idn" value="${us.getId()}">
                <input type="submit"  value="Send" class="Bottone">
            </td>
        </tr>

    </form>

</TABLE>




</body>
</html>



