$(document).ready(() => {
      setUpOffCanvas();
    });
    $('.menu .item')
      .tab()
      ;
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
function deleteUsecase(id, name, domainId) {
	if (confirm("Delete Usecase: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "usecase-id=" + id	
		}
		fetch("/PM/usecase/delete", options)
			.then(() => {
				window.location.replace("/PM/usecase?domain-id=" + domainId);
			});
	}
}
function testActivityDiagram(id) {
	let diagramText = document.querySelector("textarea[name='activity-diagram']").value;
	let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "usecase-id=" + id
				+ "&activity-diagram=" + encodeURIComponent(diagramText)	
		}
		fetch("/PM/usecase/test-activity", options)
			.then((res) => {
				return res.text();	
			})
			.then((data) => {
				let imgEl = document.querySelector("img.activity-image");
				imgEl.setAttribute("src", data + "?t=" + Date.now());
			});
}
function testSequenceDiagram(id) {
	let diagramText = document.querySelector("textarea[name='sequence-diagram']").value;
	let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "usecase-id=" + id
				+ "&sequence-diagram=" + encodeURIComponent(diagramText)	
		}
		fetch("/PM/usecase/test-sequence", options)
			.then((res) => {
				return res.text();	
			})
			.then((data) => {
				let imgEl = document.querySelector("img.sequence-image");
				imgEl.setAttribute("src", data + "?t=" + Date.now());
			});
}
function reloadDiagram() {
	window.location.reload();
}
