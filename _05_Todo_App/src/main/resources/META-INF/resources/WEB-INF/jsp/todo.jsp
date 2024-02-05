<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <h1>Enter Todo Details</h1>

    <form:form method="post" modelAttribute="todo">
        <div class="mb-3">
            <fieldset class="form-group">
                <form:label path="description" class="form-label">Description</form:label>
                <form:input type="text" path="description" class="form-control" placeholder="Enter description"
                    required="required" />
                <form:errors path="description" cssClass="text-danger" />
            </fieldset>
        </div>
        <div class="mb-3">
            <fieldset class="form-group">
                <form:label path="targetDate" class="form-label">Target Date</form:label>
                <form:input type="text" path="targetDate" class="form-control" required="required" />
                <form:errors path="targetDate" cssClass="text-danger" />
            </fieldset>
        </div>
        <div class="mb-3">
            <input type="submit" class="btn btn-primary" />
        </div>
    </form:form>
</div>

<%@ include file="common/footer.jspf" %>

<script type="text/javascript">
    $('#targetDate').datepicker({
        format: 'yyyy-mm-dd'
    });
</script>