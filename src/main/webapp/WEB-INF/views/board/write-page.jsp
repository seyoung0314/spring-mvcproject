<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="ko">

        <head>

           <meta charset="UTF-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <title>스프링 연습프로젝트 사이트</title>

      <link rel="preconnect" href="https://fonts.googleapis.com">
      <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
      <link href="https://fonts.googleapis.com/css2?family=Single+Day&display=swap" rel="stylesheet">

      <!-- reset -->
      <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@5.0.1/reset.min.css">

      <!-- fontawesome css: https://fontawesome.com -->
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css">

      <!-- https://linearicons.com/free#cdn -->
      <link rel="stylesheet" href="https://cdn.linearicons.com/free/1.0.0/icon-font.min.css">

      <!-- bootstrap css -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">


      <link rel="stylesheet" href="/assets/css/main.css">

      <!-- bootstrap js -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" defer></script>

      <!-- side menu event js -->
      <script src="/assets/js/side-menu.js" defer></script>
     

                <!-- ck editor -->
                <script src="https://cdn.ckeditor.com/4.17.2/standard/ckeditor.js"></script>
                <style>
                    .form-container {
                        width: 500px;
                        margin: auto;
                        padding: 20px;
                        background-image: linear-gradient(135deg, #a1c4fd, #fbc2eb);
                        box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
                        border-radius: 4px;
                        font-size: 18px;
                    }

                    .form-container h1 {
                        font-size: 40px;
                        font-weight: 700;
                        letter-spacing: 10px;
                        text-align: center;
                        margin-bottom: 20px;
                        color: #ffffff;
                    }

                    label {
                        display: block;
                        margin-bottom: 5px;
                        font-size: 20px;
                    }

                    input[type="text"],
                    textarea {
                        font-size: 18px;
                        width: 100%;
                        padding: 8px;
                        box-sizing: border-box;
                        border: 2px solid #ffffff;
                        border-radius: 8px;
                        margin-bottom: 10px;
                        background-color: rgba(255, 255, 255, 0.8);
                    }

                    textarea {
                        resize: none;
                        height: 200px;
                    }

                    .buttons {
                        display: flex;
                        justify-content: flex-end;
                        margin-top: 20px;
                    }

                    button {
                        font-size: 20px;
                        padding: 10px 20px;
                        border: none;
                        margin-right: 10px;
                        background-color: #4CAF50;
                        color: white;
                        cursor: pointer;
                        border-radius: 4px;
                        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                        transition: background-color 0.3s;
                    }

                    button.list-btn {
                        background: #e61e8c;
                    }

                    button:hover {
                        background-color: #3d8b40;
                    }

                    button.list-btn:hover {
                        background: #e61e8c93;
                    }
                </style>
        </head>

        <body>

          <!-- header -->
      <header>
        <div class="inner-header">
          <h1 class="logo">
            <a href="/board/list">
              <img src="/assets/img/logo.png" alt="로고이미지">
            </a>
          </h1>

          <div class="profile-box">
            
          </div>

          <h2 class="intro-text">Welcome</h2>
          <a href="#" class="menu-open">
            <span class="menu-txt">MENU</span>
            <span class="lnr lnr-menu"></span>
          </a>
        </div>

        <nav class="gnb">
          <a href="#" class="close">
            <span class="lnr lnr-cross"></span>
          </a>
          <ul>
            <li><a href="/">Home</a></li>
            <li><a href="#">About</a></li>
            <li><a href="/board/list">Board</a></li>
            <li><a href="#">Contact</a></li>
            <li><a href="/members/sign-up">Sign Up</a></li>
            <li><a href="/members/sign-in">Sign In</a></li>
          </ul>
        </nav>

      </header>


                <div id="wrap" class="form-container">
                    <h1>꾸러기 게시판 글쓰기</h1>
                    <form>
                        <label for="title">작성자</label>
                        <input type="text" id="writer" name="writer" value="익명" readonly>
                        <label for="title">제목</label>
                        <input type="text" id="title" name="title" required>
                        <label for="content">내용</label>
                        <textarea id="content" name="content" maxlength="200" required></textarea>
                        <div class="buttons">
                            <button class="list-btn" type="button"
                                onclick="window.location.href='/board/list'">목록</button>
                            <button type="submit">글쓰기</button>
                        </div>
                    </form>
                </div>
                <script>
                    CKEDITOR.replace('content');
                </script>
        </body>

        </html>


        </body>

        </html>