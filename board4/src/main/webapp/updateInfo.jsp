<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html><html><head><meta charset="UTF-8">
<title><spring:message code="message.user.login.updateBtn"/></title>
<script>
function chk(){
	var pass1=document.forms[0].password.value;
	var pass2=document.forms[0].password2.value;
	if(pass1!=pass2){
		alert('비밀번호확인값이 서로 다릅니다.');
		document.forms[0].password.value='';
		document.forms[0].password2.value='';
		document.forms[0].password.focus();
		return false;
	}
}
function deleteUser(id){
	var yesno =confirm(id+"회원님 정말 탈퇴하시겠습니까?");
	if(yesno){
		location.href='deleteUser.do?id='+id;//GET
	}else{
		return;
	}
}
</script>
</head>
<body>
<center>
  <h1><spring:message code="message.user.login.updateBtn"/> </h1>
  
  <a href="?lang=en&id=${user.id}&password=${user.password}">
  <spring:message code="message.user.login.language.en"/>
  </a>&nbsp;&nbsp;
  <a href="?lang=ko&id=${user.id}&password=${user.password}">
  <spring:message code="message.user.login.language.ko"/>
  </a>
  
  <hr>
  <form action="updateInfo.do" method="post" onsubmit="return chk()">
     <table border="1" cellpadding="0" cellspacing="0">
       <tr>
        <td bgcolor="orange"><spring:message code="message.user.login.id"/></td>
        <td><input type="text" name="id" required="required"  value="${user.id}" readonly="readonly"></td>
       </tr>
        <tr>
         <td bgcolor="orange"><spring:message code="message.user.login.password"/></td>
         <td><input type="password" name="password" required="required"></td>
       </tr>
       <tr>
         <td bgcolor="orange"><spring:message code="message.user.login.passwordCofirm"/></td>
         <td><input type="password" name="password2" required="required"></td>
       </tr>
      <tr>
         <td bgcolor="orange"><spring:message code="message.user.login.name"/></td>
         <td><input type="text" name="name" value="${user.name}" required="required"></td>
       </tr>
       <tr>
         <td bgcolor="orange"><spring:message code="message.user.login.role"/></td>
         <td>
         <select name="role">
          <c:forEach var="role" items="${roleMap}">
             <option value="${role.key}" <c:if test="${role.key==user.role}">selected</c:if>> ${role.value}</option>
          </c:forEach>
         </select>                  
         </td>
       </tr>
       <tr>
       	<td colspan="2" align="center">
       		<input type="submit" value='<spring:message code="message.user.login.updateBtn"/>'/>
       		<input type="button" value='<spring:message code="message.user.login.deleteBtn"/>' 
       		            onclick="deleteUser('${user.id}')"/> 
       	</td>
       </tr>
     </table>
  
  </form>

</center>
</body>
</html>