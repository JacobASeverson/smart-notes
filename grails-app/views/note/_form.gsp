<%@ page import="com.objectpartners.note.Note" %>



<div class="fieldcontain ${hasErrors(bean: noteInstance, field: 'name', 'error')} ">
	<label for="name">
		<g:message code="note.name.label" default="Name" />
		
	</label>
	<g:textField name="name" value="${noteInstance?.name}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: noteInstance, field: 'content', 'error')} ">
	<label for="content">
		<g:message code="note.content.label" default="Content" />
		
	</label>
	<g:textField name="content" value="${noteInstance?.content}"/>
</div>

