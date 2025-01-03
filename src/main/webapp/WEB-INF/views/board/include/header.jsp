<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" %> <%@ taglib prefix="c"
uri="http://java.sun.com/jsp/jstl/core" %>

<header>
  <div class="inner-header">
    <h1 class="logo">
      <a href="/board/list">
        <img src="/assets/img/logo.png" alt="로고이미지" />
      </a>
    </h1>

    <div class="profile-box"></div>

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