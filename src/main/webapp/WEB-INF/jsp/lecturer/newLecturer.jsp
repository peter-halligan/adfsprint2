<%@ include file="/WEB-INF/jsp/include.jsp"%>

<form:form method="POST" enctype="multipart/form-data" data-ajax="false" action="addNew" modelAttribute="lecturer">

<form:errors path="*" cssClass="errorblock" element="div" />

 <div class="ui-field-contain">
 <form:label path="firstName">First name</form:label>
 <form:input path="firstName" placeholder="firstName" value=""/>
 <form:errors path="firstName" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="lastName">Last name</form:label>
 <form:input path="lastName" placeholder="lastName"  value=""/>
 <form:errors path="lastName" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="email">Email</form:label>
 <form:input path="email" placeholder="email"   value=""/>
 <form:errors path="email" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="roomNumber">Room number</form:label>
 <form:input path="roomNumber" placeholder="roomNumber"   value=""/>
 <form:errors path="roomNumber" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="phoneNumber">Phone number</form:label>
 <form:input path="phoneNumber" placeholder="phoneNumber"   value=""/>
 <form:errors path="phoneNumber" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <div class="ui-field-contain">
 <form:label path="idManagedProgram">Id Managed Program</form:label>
 <form:input path="idManagedProgram" placeholder="idManagedProgram"   value=""/>
 <form:errors path="idManagedProgram" cssClass="error" class="notification error" style="display:block"></form:errors>
 </div>
 
 <input data-theme="b" type="submit" data-icon="check" value="Save" />
  </form:form> 
