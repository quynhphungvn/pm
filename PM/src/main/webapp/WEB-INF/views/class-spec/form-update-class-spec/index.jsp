<form method="post" action="/PM/class-spec/update-class-spec"
	class="ui form" name="class-spec">
	<input type="hidden" name="selected-class-id" value="${selectedClass.id}">
	<input type="hidden" name="selected-testing-function-id" value="${selectedTestingFunction.id}">
	<div class="field">
		<textarea rows="38" readonly name="content">${selectedClass.detailContent}</textarea>
	</div>
	<div class="d-flex justify-content-end">
		<button class="ui button d-none btn-update-spec" type="submit">Update</button>
		<button type="button" class="ui icon button"
			onclick="enableEditMode()">
			<i class="edit icon"></i>
		</button>
	</div>
</form>
