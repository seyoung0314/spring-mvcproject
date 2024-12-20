<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- reset css -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

    <!-- bootstrap css -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


    <!-- bootstrap js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

    <!-- jquery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>


    <style>
        li {
            list-style: none;
            margin: 0;
            padding: 0;
            font-size: 1.4em;
        }

        section.score-main {
            width: 30%;
            margin: 0 auto 150px;
            padding: 20px;
            border: 2px solid orange;
            border-radius: 10px;
            box-shadow: 2px 2px 5px orangered;
            transform: translateY(200px);
        }

        a {
            display: block;
            width: fit-content;
            text-decoration: none;
            border-radius: 5px;
            border: 1px solid white;
            padding: 5px;
            margin-right: 10px;
        }

        a.list-btn {
            background: rgb(83, 189, 83);
            color: white;
            box-shadow: 1px 1px 2px rgb(146, 228, 146);
        }
        a.mod-btn {
            background: rgb(228, 248, 49);
            color: #333;
            box-shadow: 1px 1px 2px rgb(250, 240, 105);
            border-radius: 5px;
        }
    </style>

</head>

<body>

    <div class="wrap">
        <section class="score-main">
            <h1><span id="fullName">김철수</span>님 성적 정보</h1>
            <ul>
                <li># 국어: <span id="kor">100점</span></li>
                <li># 영어: <span id="eng">100점</span></li>
                <li># 수학: <span id="math">100점</span></li>
                <li># 총점: <span id="total">100점</span></li>
                <li># 평균: <span id="avg">100점</span></li>
                <li># 석차: <span id="rank">1</span> / <span id="totalCount">4</span></li>
            </ul>
            <div class="btn-group">
                <a class="list-btn" href="/score/page">목록</a>
                <a class="mod-btn" href="#">수정</a>
            </div>
        </section>

    </div>

    <script>
      fetchDetail();
      async function fetchDetail() {
        // const pathName= window.location.pathname;
        // console.log(pathName);
        
        const res = await fetch(`/api/v1/scores/${id}`); // id는 model 객체에서 받아옴
        const data = await res.json();
        console.log(data);
        
        // 화면에 랜더링
        for(const property in data){
          // console.log(property);
          // console.log(data[property]);
          const $target = document.getElementById(property);
          if(!$target){
            $target.textContent = data[property];
          }
        }

      }

    </script>
</body>

</html>