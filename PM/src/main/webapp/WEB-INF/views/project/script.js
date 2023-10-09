$('.dropdown').dropdown({
	label: {
		duration: 0,
	},
	debug: true,
	performance: true,
});

function showModalAdd() {
	$('.ui.modal.add')
		.modal('show')
		;
}
function showModalEdit() {
	$('.ui.modal.edit')
		.modal({
			
		})
		.modal('show')
		;
}
function deleteProject(id, name) {
	if (confirm("Delete project: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "project-id=" + id
		}
		fetch("/PM/project/delete", options)
			.then(() => {
				window.location.replace("/PM/project");
			});
	}
}