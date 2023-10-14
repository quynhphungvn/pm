<form method="post" action="/PM/class-spec/update-test-info"
	class="ui form" name="edit-testing-function-info">
	<input type="hidden" name="selected-testing-function-id" value="${selectedTestingFunction.id}">
	<div class="d-flex mb-3">
		<label>Name:</label> 
		<strong class="ms-2">${selectedTestingFunction.name }</strong>
	</div>
	<div class="field">
		<label>New Name</label> 
		<input type="text" name="new-name"
			placeholder="New Name">
	</div>
	<div class="field">
		<label>Test Requirement</label> 
		<input type="text" name="requirement"
			placeholder="Requirement"
			value="${selectedTestingFunction.testRequirement }">
	</div>
	<div class="field">
		<label>Params</label> 
		<input type="text" name="params"
			placeholder="Params"
			value="${selectedTestingFunction.functionParams }">
	</div>
	<div class="field">
		<label>Return</label> 
		<input type="text" name="return"
			placeholder="Return"
			value="${selectedTestingFunction.functionReturn }">
	</div>
	<div class="field">
		<label>Exceptions</label>
		<textarea rows="3" name="exceptions">${selectedTestingFunction.functionExceptions}</textarea>
	</div>
	<div class="field">
		<label>Logs</label>
		<textarea rows="3" name="logs">${selectedTestingFunction.functionLogs }</textarea>
	</div>
	<button class="ui button" type="submit">Update</button>
</form>