<form method="post" action="/PM/class-spec/update-package-info"
                  class="ui form" name="edit-package">
    <input type="hidden" name="selected-testing-function-id" value="${selectedTestingFunction.id}">
	<input type="hidden" name="selected-class-id" value="${selectedClass.id}">
	<input type="hidden" name="selected-package-id" value="${selectedPackage.id}">
	<div class="d-flex mb-3">
		<label>Name:</label>
		<strong class="ms-2">${selectedPackage.name}</strong>
	</div>
	<div class="field">
		<label>New Name</label>
		<input type="text" name="new-name" placeholder="New Name">
	</div>
	<div class="field">
		<label>Info</label>
		<textarea rows="10" name="info">${selectedPackage.info}</textarea>
	</div>
	<button class="ui button" type="submit">Update</button>
</form>