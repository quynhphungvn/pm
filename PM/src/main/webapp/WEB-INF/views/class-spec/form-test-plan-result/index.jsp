<form method="post" action="/PM/class-spec/update-test-result"
		class="ui form" name="testcase-result">
	<input type="hidden" name="selected-testing-function-id" value="${selectedTestingFunction.id}">
	<div class="field">
		<label>Result</label>
		<textarea rows="7" readonly 
				name="plan-result">${selectedTestingFunction.testPlanResult}</textarea>
	</div>
	<div class="d-flex justify-content-end">
		<button class="ui button btn-update-result d-none" type="submit">Update</button>
		<button class="ui icon button ${selectedTestingFunction == null?'disabled':''}" 
				type="button" onclick="enableEditResultMode()">
			<i class="icon edit"></i>
		</button>
	</div>
</form>