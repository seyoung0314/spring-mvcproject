<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="ko">
  <head>
    
    <%@ include file="include/static-file.jsp" %>

    <!-- 토스트 css -->
    <link
      href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css"
      rel="stylesheet"
    />

    <!-- ck editor -->
    <link
      rel="stylesheet"
      href="https://cdn.ckeditor.com/ckeditor5/44.1.0/ckeditor5.css"
    />
    <script src="https://cdn.ckeditor.com/ckeditor5/44.1.0/ckeditor5.umd.js"></script>

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
        background-color: #4caf50;
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
      .ck-editor__editable {
        height: 300px;
      }
      .ck-editor__editable p {
        margin: 0;
      }

      /* Toastr error 배경색과 글자 색상 */
      .toast-error {
        background-color: #e74c3c !important; /* 빨간색 배경 */
        color: white !important; /* 하얀색 글자 */
      }

      .toast-top-center {
        top: 50% !important; /* 수직으로 중앙 정렬 */
        transform: translateY(-50%) !important; /* 정확한 중앙 위치 맞추기 */
      }
    </style>
  </head>

  <body>
    <!-- header -->
    <%@ include file="include/header.jsp" %>

    <div id="wrap" class="form-container">
      <h1>꾸러기 게시판 글쓰기</h1>
      <form id="board-form" novalidate>
        <label for="title">작성자</label>
        <input type="text" id="writer" name="writer" value="익명" readonly />
        <label for="title">제목</label>
        <input type="text" id="title" name="title" required />
        <label for="content">내용</label>
        <textarea
          id="content"
          name="content"
          maxlength="200"
          required
        ></textarea>
        <div class="buttons">
          <button
            class="list-btn"
            type="button"
            onclick="window.location.href='/board/list'"
          >
            목록
          </button>
          <button type="submit">글쓰기</button>
        </div>
      </form>
    </div>
    <script>
      // CKEDITOR ...
      let editor;
      const { ClassicEditor, Essentials, Bold, Italic, Font, Paragraph } =
        CKEDITOR;

      ClassicEditor.create(document.querySelector("#content"), {
        licenseKey:
          "eyJhbGciOiJFUzI1NiJ9.eyJleHAiOjE3NjYxMDIzOTksImp0aSI6IjEzODIxZWU2LTY0OWItNGE1OC04ZTA2LThlNzNhM2RlMTg4NiIsInVzYWdlRW5kcG9pbnQiOiJodHRwczovL3Byb3h5LWV2ZW50LmNrZWRpdG9yLmNvbSIsImRpc3RyaWJ1dGlvbkNoYW5uZWwiOlsiY2xvdWQiLCJkcnVwYWwiXSwiZmVhdHVyZXMiOlsiRFJVUCIsIkJPWCJdLCJ2YyI6IjM4MTM3YjhjIn0.Vy670BGfDF08y8-WPxehiMxEdzZwv99XOYbmPweVv1NOisMc-GE3PnTEY6pwz6pmeooWe5lArtch1r9iykDmfQ",
        plugins: [Essentials, Bold, Italic, Font, Paragraph],
        toolbar: [
          "undo",
          "redo",
          "|",
          "bold",
          "italic",
          "|",
          "fontSize",
          "fontFamily",
          "fontColor",
          "fontBackgroundColor",
        ],
      })
        .then((newEditor) => {
          // editor = newEditor;
        })
        .catch((err) => console.error(err));
    </script>

    <!-- 토스트 -->
    <script src="https://code.jquery.com/jquery-latest.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.1.4/toastr.min.js"></script>

    <!-- custom script -->
    <script>
      const $form = document.getElementById("board-form");

      const API_URL = "/api/v1/boards";

      $form.addEventListener("submit", (e) => {
        e.preventDefault();
        const formData = new FormData($form);

        const item = Object.fromEntries(formData.entries());

        // 게시판 라이브러리가 p태그 포함해서 줘서 제거하고 서버로 보냄
        // (이거 스타일 적용때문에 그런거였음..)
        // const content = formData.get("content").replace(/<[^>]*>/g, "");
        // item.content = content;

        console.log("item");
        console.log(item);

        postDataItem(item);
      });

      async function postDataItem(item) {
        try {
          const res = await fetch(API_URL, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(item),
          });
          if (res.status === 200) {
            window.location.href = "/board/list";
          } else if (res.status === 400) {
            errorMessase(res);
          } else {
            alert("error");
          }
        } catch (error) {
          console.error("post호출 에러", error);
          alert(error);
        }
      }

      async function errorMessase(res) {
        const error = await res.json();
        for (key in error) {
          console.log("=========================");
          console.log(key);
          console.log(error[key]);
          if (key !== "errorCount") {
            toastShow(key, error[key], 3000);
          }
        }
      }

      function toastShow(title, content, time) {
        toastr.options = {
          closeButton: true,
          debug: false,
          positionClass: "toast-top-center",
          showDuration: "300",
          hideDuration: "1000",
          timeOut: time,
          extendedTimeOut: "1000",
          showEasing: "swing",
          hideEasing: "linear",
          showMethod: "fadeIn",
          hideMethod: "fadeOut",
          // 색상 추가
          backgroundColor: "#e74c3c", // error 색상: 빨간색
          textColor: "white", // 글자색: 하얀색
        };
        toastr["error"](content);
        // toastr.info(title, content, { timeOut: time });
      }
    </script>
  </body>
</html>
