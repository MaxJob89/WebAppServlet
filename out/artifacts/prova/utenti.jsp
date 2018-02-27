<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
  <head>
    <title>List</title>
    <style>

      body
      {
        font-family: Gargi;
      }
      .Filtro
      {
        margin-left: 25%;
      }
      h1 {
        align-content: center;
        margin-left: 40%;
      }
      table{
        font-size: medium ;
        border-color: transparent;

        border-color: transparent;
        border: 5px;
      }

      th
      {

        font-weight: bold;
        font-size: 17px;

      }
      td{
        font-style: oblique;
        height: 36px;
        width: auto;
        padding-left: 15px;
        padding-right: 10px;
      }

      tr:nth-child(even)
      {
        background-color: aquamarine;
      }

      .Bottone
      {
        border-radius: 40px 40px 40px 40px;
        border: 0px;
        width: 40px;
        height: 40px;
        background-color: grey;
        color: white;
        font-size: 13px;
        font-weight: bold;

      }
      .pl
      {
        border-radius: 40px 40px 40px 40px;
        border: 0px;
        width: 40px;
        height: 40px;
        background-color: grey;
        color: white;
        font-size: 20px;
        font-weight: bold;

      }
      select
      {
        border-radius: 30px 30px 30px 30px;
        border: 0px;
        width: 80px;
        height: 20px;

      }

      input{
        border-radius: 30px 30px 30px 30px;
        width: 200px;
        background-color: lightgray;
        border: 0px;
        text-align:center;
      }
      .Fil{
        width: 100px;
        height: 20px;
        border: 0px;
      }

    </style>
  </head>

  <body>

  <H1>LIST</H1>

  <form name="filtro" method="post" action="/" class="Filtro">

    <input type="text" name="filtro"  >

    <td>
      Stato : <select name="statusFilter">
      <option>All</option>
      <c:forEach items="${requestScope.stat}" var="st">
      <option>${st.getName()}</option>
      </c:forEach>
    </select>
    </td>
    <input type="submit" name="inviaFiltro" value="Filtres" class="Fil">
  </form>

<table>


    <tr>
      <th>   Nome   </th>
      <th>   Cognome   </th>
      <th>   Stato    </th>
      <th>   Data  </th>
      <th>   Action  </th>

    </tr>

    <c:forEach items="${requestScope.result}" var="res">
      <tr>

        <td>${res.getFirstname()}</td>
        <td>${res.getLastname()}</td>
        <td>${res.getStatus()}</td>
        <td><fmt:formatDate pattern="yyyy-MM-dd" value="${res.getData()}"/></td>
        <td >
          <form action="/viewuser.do" method="post" >
            <input hidden name="id" value="${res.getId()}">
            <input type="submit"  value="Edit" class="Bottone">
          </form>
        </td>

        <td>
          <form action="/remove.do" method="post">
            <input hidden name="id" value="${res.getId()}">
            <input type="submit"  value="X" class="Bottone">
          </form>
        </td>
      </tr>
    </c:forEach>


  </table>

  <a href="/viewuser.do"><input type="button" value="+" class="pl"></a>


  </body>
</html>
