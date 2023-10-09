$(document).ready(()=> {
      setUpOffCanvas();
    });
    $('.dropdown').dropdown({
      label: {
        duration: 0,
      },
      debug: true,
      performance: true,
    });
    $('.ui.accordion').accordion();

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
    function openSideBar() {
      $('.ui.sidebar.diagram')
        .sidebar('setting', 'transition', 'overlay')
        .sidebar('toggle')
      ;
    }
    function enableEditMode() {
      $("form[name='sql-content'] textarea").attr("readonly", false);
      $("form[name='sql-content'] button.js-update-content").removeClass("d-none");
    }
function deleteQuery(id, name, domainId) {
	if (confirm("Delete query: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "query-id=" + id	
		}
		fetch("/PM/sqlquery/delete", options)
			.then(() => {
				window.location.replace("/PM/sqlquery?domain-id=" + domainId);
			});
	}
}
