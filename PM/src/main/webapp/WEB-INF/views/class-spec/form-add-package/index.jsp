<form method="post" action="/PM/class-spec/add-package"
                class="ui form" name="add-package">
	<input type="hidden" name="selected-domain-id" value="${selectedDomain.id}">
	<input type="hidden" name="selected-package-id" value="${selectedPackage.id }">
	<input type="hidden" name="selected-class-id" value="${selectedClass.id}">
	<input type="hidden" name="selected-test-function-id" value="${selectedTestingFunction.id}">
	<div class="field">
		<label>Name</label>
		<input type="text" name="name" placeholder="Package Name">
	</div>
	<div class="field">
		<label>Info</label>
		<textarea rows="10" name="info"></textarea>
	</div>
	<button class="ui button" type="submit">Add</button>
</form>