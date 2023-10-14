<form method="post" action="/PM/class-spec/add-class" class="ui form"
	name="add-class">
	<input type="hidden" name="selected-package-id" value="${selectedPackage.id}"> 
	<input type="hidden" name="selected-class-id" value="${selectedClass.id}"> 
	<input type="hidden" name="selected-test-function-id" value="${selectedTestFunction.id}">
	<div class="field">
		<label>Name</label> 
		<input type="text" name="name" placeholder="Class Name">
	</div>
	<button class="ui button" type="submit">Add</button>
</form>