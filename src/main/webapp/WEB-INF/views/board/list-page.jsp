<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>

    <%@ include file="include/static-file.jsp" %>

    <link rel="stylesheet" href="/assets/css/list.css" />

    <style>
      .card-container .card .card-title-wrapper .time-view-wrapper > div.hit {
        background: yellow;
      }
    </style>
  </head>

  <body>
    <!-- header -->
    <%@ include file="include/header.jsp" %>

    <div id="wrap">
      <div class="main-title-wrapper">
        <h1 class="main-title">꾸러기 게시판</h1>
        <button class="add-btn">새 글 쓰기</button>
      </div>

      <div class="top-section">
        <!-- 검색창 영역 -->
        <div class="search">
          <form id="search-form">
            <select class="form-select" name="type" id="search-type">
              <option value="title">제목</option>
              <option value="content">내용</option>
              <option value="writer">작성자</option>
              <option value="tc">제목+내용</option>
            </select>

            <input type="text" class="form-control" name="keyword" />

            <button class="btn btn-primary" type="submit">
              <i class="fas fa-search"></i>
            </button>
          </form>
        </div>

        <div class="amount">
          <div><a href="#">6</a></div>
          <div><a href="#">18</a></div>
          <div><a href="#">30</a></div>
        </div>
      </div>

      <div class="card-container">
        <!-- <div class="card-wrapper">
                <section class="card" data-bno="1">
                  <div class="card-title-wrapper">
                    <h2 class="card-title">하하호호</h2>
                    <div class="time-view-wrapper">
                      <div class="time">
                        <i class="far fa-clock"></i>
                        2024-12-18
                      </div>



                      <div class="view">
                        <i class="fas fa-eye"></i>
                        <span class="view-count">0</span>
                      </div>
                    </div>
                  </div>
                  <div class="card-content">
                    가나다라마바사
                  </div>
                </section> -->

        <!-- 관리자이거나 본인이 쓴글에만 렌더링되도록 -->
        <!-- <div class="card-btn-group">
                  <button class="del-btn">
                    <i class="fas fa-times"></i>
                  </button>
                </div>

              </div> -->
        <!-- end div.card-wrapper -->
      </div>
      <!-- end div.card-container -->

      <!-- 게시글 목록 하단 영역 -->
      <div class="bottom-section">
        <!-- 페이지 버튼 영역 -->
        <nav aria-label="Page navigation example">
          <ul class="pagination pagination-lg pagination-custom"></ul>
        </nav>
      </div>
      <!-- end div.bottom-section -->
    </div>
    <!-- end div.wrap -->

    <!-- 모달 창 -->
    <div class="modal" id="modal">
      <div class="modal-content">
        <p>정말로 삭제할까요?</p>
        <div class="modal-buttons">
          <button class="confirm" id="confirmDelete">
            <i class="fas fa-check"></i> 예
          </button>
          <button class="cancel" id="cancelDelete">
            <i class="fas fa-times"></i> 아니오
          </button>
        </div>
      </div>
    </div>

    <script>
      const $cardContainer = document.querySelector(".card-container");

      const API_URL = "/api/v1/boards";

      async function getListData(item) {
        try {
          let url = API_URL;

          // 아이템(검색옵션)이 있으면 검색옵션 추가
          if (item) {
            url += `?searchOption=\${item.type}&keyword=\${item.keyword}`;
            console.log(url);
          }
          const res = await fetch(url);
          const data = await res.json();

          console.log("========Get data");

          console.log(data);

          if (res.status === 200) {
            rendData(data);
          } else {
            alert("error");
          }
        } catch (error) {
          console.error("get호출 에러", error);
          alert(error);
        }
      }

      async function delListItem(id) {
        try {
          const res = await fetch(API_URL + "/" + id, {
            method: "DELETE",
          });
          if (res.status === 200) {
            getListData();
          } else {
            alert("error");
          }
        } catch (error) {
          console.error("delete호출 에러", error);
          alert(error);
        }
      }

      async function countListItem(id) {
        try {
          const res = await fetch(API_URL + "/" + id, {
            method: "PUT",
            headers: {
              "Content-Type": "application/json", // JSON 데이터 전송
            },
          });
          if (res.status === 200) {
            getListData();
          } else {
            alert("error");
          }
        } catch (error) {
          console.error("put호출 에러", error);
          alert(error);
        }
      }

      getListData();

      function rendData(data) {
        $cardContainer.innerHTML = "";

        if (!data) {
          return;
        }
        data.forEach((element) => {
          // const now = element.regDateTime.split("T")[0];

          let newArticle = "";
          if(element.newArticle){
            newArticle = "new ";
          }

          $cardContainer.innerHTML += `
                  <div class="card-wrapper">
                  <section class="card" data-bno="\${element.bno}">
                        <div class="card-title-wrapper">
                          <h2 class="card-title">\${element.shortTitle}</h2>
                          <div class="time-view-wrapper">
                            <div class="time">
                              <i class="far fa-clock"></i>


                              \${newArticle}\${element.date}
                            </div>

                            <div class="view">
                              <i class="fas fa-eye"></i>
                              <span class="view-count">\${element.view}</span>
                            </div>
                          </div>
                        </div>
                        <div class="card-content">
                          \${element.shortContent}
                        </div>
                      </section>      
                      <div class="card-btn-group">
                        <button class="del-btn">
                          <i class="fas fa-times"></i>
                        </button>
                      </div>
                      </div>
      `;
        });
      }

      //================= 조회수 스크립트 =================//
      $cardContainer.addEventListener("click", (e) => {
        e.preventDefault();

        if (e.target.matches(".card-container *")) {
          const selectedItemId = e.target
            .closest(".card-wrapper")
            .querySelector(".card").dataset.bno;

          countListItem(selectedItemId);
          window.location.href = "/board/detail/" + selectedItemId;
        }
      });

      //================= 검색버튼 스크립트 =================//
      const $searchForm = document.getElementById("search-form");

      $searchForm.addEventListener("submit", (e) => {
        e.preventDefault();
        const searchForm = new FormData($searchForm);

        const item = Object.fromEntries(searchForm.entries());

        getListData(item);
      });

      //================= 삭제버튼 스크립트 =================//
      const modal = document.getElementById("modal"); // 모달창 얻기
      const confirmDelete = document.getElementById("confirmDelete"); // 모달 삭제 확인버튼
      const cancelDelete = document.getElementById("cancelDelete"); // 모달 삭제 취소 버튼

      $cardContainer.addEventListener("click", (e) => {
        // 삭제 버튼을 눌렀다면~
        if (e.target.matches(".card-btn-group *")) {
          console.log("삭제버튼 클릭");

          const selectedItemId = e.target
            .closest(".card-wrapper")
            .querySelector(".card").dataset.bno;

          modal.style.display = "flex"; // 모달 창 띄움

          // 확인 버튼 이벤트
          confirmDelete.onclick = (e) => {
            // 삭제 처리 로직
            delListItem(selectedItemId);
            modal.style.display = "none"; // 모달 창 닫기
          };

          // 취소 버튼 이벤트
          cancelDelete.onclick = (e) => {
            modal.style.display = "none"; // 모달 창 닫기
          };
        }
      });

      // 전역 이벤트로 모달창 닫기
      window.addEventListener("click", (e) => {
        if (e.target === modal) {
          modal.style.display = "none";
        }
      });

      //========== 게시물 목록 스크립트 ============//

      function removeDown(e) {
        if (!e.target.matches(".card-container *")) return;
        const $targetCard = e.target.closest(".card-wrapper");
        $targetCard?.removeAttribute("id", "card-down");
      }

      function removeHover(e) {
        if (!e.target.matches(".card-container *")) return;
        const $targetCard = e.target.closest(".card");
        $targetCard?.classList.remove("card-hover");

        const $delBtn = e.target
          .closest(".card-wrapper")
          ?.querySelector(".del-btn");
        if ($delBtn) $delBtn.style.opacity = "0";
      }

      $cardContainer.onmouseover = (e) => {
        if (!e.target.matches(".card-container *")) return;

        const $targetCard = e.target.closest(".card");
        $targetCard?.classList.add("card-hover");

        const $delBtn = e.target
          .closest(".card-wrapper")
          ?.querySelector(".del-btn");
        if ($delBtn) $delBtn.style.opacity = "1";
      };

      $cardContainer.onmousedown = (e) => {
        if (e.target.matches(".card-container .card-btn-group *")) return;

        const $targetCard = e.target.closest(".card-wrapper");
        $targetCard?.setAttribute("id", "card-down");
      };

      $cardContainer.onmouseup = removeDown;

      $cardContainer.addEventListener("mouseout", removeDown);
      $cardContainer.addEventListener("mouseout", removeHover);

      // write button event
      document.querySelector(".add-btn").onclick = (e) => {
        window.location.href = "/board/write";
      };
    </script>
  </body>
</html>
