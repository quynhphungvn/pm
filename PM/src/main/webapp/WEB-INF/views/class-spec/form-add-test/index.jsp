<form method="post" action="/PM/class-spec/add-test"
	class="ui form" name="add-unit-test"> 
	<input type="hidden" name="selected-testing-function-id" value="${selectedTestingFunction.id}">
	<input type="hidden" name="selected-class-id" value="${selectedClass.id }">
	<div class="field">
		<label>Function Name</label> 
		<input type="text" name="name"
			placeholder="Function name">
	</div>
	<div class="field">
		<label>Test Requirement</label> 
		<input type="text" name="requirement"
			placeholder="Requirement">
	</div>
	<div class="field">
		<label>Params</label> 
		<input type="text" name="params"
			placeholder="Params">
	</div>
	<div class="field">
		<label>Return</label> 
		<input type="text" name="return"
			placeholder="Return">
	</div>
	<div class="field">
		<label>Exceptions</label>
		<textarea rows="3" name="exceptions"></textarea>
	</div>
	<div class="field">
		<label>Logs</label>
		<textarea rows="3" name="logs"></textarea>
	</div>
	<button class="ui button" type="submit">Add</button>
</form>