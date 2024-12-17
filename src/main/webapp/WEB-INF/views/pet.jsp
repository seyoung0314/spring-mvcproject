<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8" />
    <title>Insert title here</title>
  </head>
  <body>
    <h1>Pet.jsp 파일입니다~~</h1>
    <p id="content"></p>

    <script>
      const $p = document.getElementById("content");
      fetch("/products")
        .then((res) => res.json())
        .then((data) => {
          data.forEach((d) => {
            console.log(d);
            
            $p.innerHTML += `
        <div>
        제품명 : \${d.name},
        가격 : \${d.price}
        </div>`;
          });
        });
    </script>
  </body>
</html>
