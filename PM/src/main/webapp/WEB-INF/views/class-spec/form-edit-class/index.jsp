<form method="post" action="/PM/class-spec/update-class-name"
                class="ui form" name="edit-class">
    <input type="hidden" name="selected-class-id" value="${selectedClass.id}">
    <input type="hidden" name="selected-test-function-id" value = "${selectedTestFunction.id}" >
    <div class="d-flex mb-3">
    	<label>Name:</label>
        <strong class="ms-2">${selectedClass.name}</strong>
    </div>
    <div class="field">
         <label>New Name</label>
         <input type="text" name="new-name" placeholder="New Name">
    </div>
    <button class="ui button" type="submit">Update</button>
</form>