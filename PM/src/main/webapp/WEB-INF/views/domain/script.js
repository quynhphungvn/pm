$('.dropdown').dropdown({
	label: {
		duration: 0,
	},
	debug: true,
	performance: true,
});
$('.modal').modal({
	duration: 200	
});
function showDomainNote() {
	$('.ui.modal.note')
		.modal({
			closable: false
		})
		.modal('show')
		;
}
function showModalAdd() {
	$('.ui.modal.add')
		.modal('show')
		;
}
function showModalEdit() {
	$('.ui.modal.edit')
		.modal('show')
		;
}
function deleteDomain(id, name, projectId) {
      if (confirm("Delete Domain: " + name + "?") == true) {
       let options = {
    	 method: "POST",
         headers: {
             "Content-Type": "application/x-www-form-urlencoded"
         },
         body: "domain-id=" + id
	   	}
	    fetch("/PM/domain/delete", options)
		.then(() => {
			window.location.replace("/PM/domain/info?project-id=" + projectId);
		}); 
    }
}