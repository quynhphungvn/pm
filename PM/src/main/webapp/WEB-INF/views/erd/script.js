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
    function showModalEdit() {
      $('.ui.modal.edit')
        .modal('show')
        ;
    }
    function deleteWbs(id, name) {
      if (confirm("Delete Wbs " + name + "?") == true) {
        alert("Deleted");
      }
    }
    function openSideBar() {
      $('.ui.sidebar.diagram')
        .sidebar('setting', 'transition', 'overlay')
        .sidebar('toggle')
      ;
    }
function enableEditRequirement() {
    let textareaEL = document.querySelector("textarea[name='requirement']");
    textareaEL.readOnly = false;
    let actionEl = document.querySelector("#canvas-requirement .actions");
    actionEl.classList.remove("d-none");
}
function testErd(id) {
	let diagramText = document.querySelector("textarea[name='erd']").value;
	let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "domain-id=" + id
				+ "&diagram-text=" + encodeURIComponent(diagramText)	
		}
		fetch("/PM/domain/test-erd", options)
			.then((res) => {
				return res.text();	
			})
			.then((data) => {
				let imgEl = document.querySelector("img.diagram-image");
				imgEl.setAttribute("src", data + "?t=" + Date.now());
			});
}

function reloadDiagram() {
	window.location.reload();
}
