<form method="post" action="/PM/class-spec/update-test-input"
	class="ui form" name="testcase-input">
	<input type="hidden" name="selected-testing-function-id" 
			value="${selectedTestingFunction.id}">
	<div class="field">
		<label>Input</label>
		<textarea class="mt-2" rows="20" readonly 
				name="plan-input">${selectedTestingFunction.testPlanInput}</textarea>
	</div>
	<div class="d-flex justify-content-end">
		<button class="ui button btn-update-input d-none" type="submit">Update</button>
		<button class="ui icon button ${selectedTestingFunction == null?'disabled':''}" 
				type="button" onclick="enableEditInputMode()">
			<i class="icon edit"></i>
		</button>
	</div>
</form>