<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@include file="../layouts/header.jsp"%>
<h1 class="page-header my-4"><i class="far fa-edit"></i> 글 수정</h1>
<div>
  <form role="form" method="post" >
    <input type="hidden" name="no" value="${board.no}">
    <div >
      <label>제목</label>
      <input name="title" class="form-control" value="${board.title}">
    </div>
    <div >
      <label>작성자</label>
      <input name="writer" class="form-control" value="${board.writer}">
    </div>
    <div >
      <label>내용</label>
      <textarea class="form-control" name="content" rows="10">${board.content}</textarea>
    </div>
    <div class="mt-4">
      <button type="submit" class="btn btn-primary"><i class="fas fa-check"></i> 확인</button>
      <button type="reset" class="btn btn-primary"><i class="fas fa-undo"></i> 취소</button>
      <a href="get?no=${board.no}" class="btn btn-primary"><i class="fas fa-file-alt"></i> 돌아가기</a>
    </div>
  </form>
</div>
<div class="mt-4">
  <a href="list" class="btn btn-primary"><i class="fas fa-list"></i> 목록</a>
  <a href="update?no=${board.no}" class="btn btn-primary"><i class="far fa-edit"></i> 수정</a>
  <a href="#" class="btn btn-primary delete"><i class="fas fa-trash-alt"></i> 삭제</a>
</div>
<form action="delete" method="post" id="deleteForm">
  <input type="hidden" name="no" value="${board.no}"/>
</form>
<script src="/resources/js/board.js"></script>
<%@include file="../layouts/footer.jsp"%>