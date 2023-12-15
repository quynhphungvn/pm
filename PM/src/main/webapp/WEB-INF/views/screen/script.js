$(document).ready(() => {
      setUpOffCanvas();
    });
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
        .modal('show')
        ;
    }
    function showModalImageWireframe() {
      $('.ui.modal.image-wireframe')
        .modal('show')
        ;
    }
    function openSideBar() {
      $('.ui.sidebar.diagram')
        .sidebar('setting', 'transition', 'overlay')
        .sidebar('toggle')
        ;
    }
function deleteScreen(id, name, domainId) {
	if (confirm("Delete screen: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "screen-id=" + id	
		}
		fetch("/PM/screen/delete", options)
			.then(() => {
				window.location.replace("/PM/screen?domain-id=" + domainId);
			});
	}
}
function testUsecaseDiagram(domainId, projectId) {
	let diagramText = document.querySelector("textarea[name='usecase-diagram']").value;
	let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body:"project-id=" + projectId
				+ "&domain-id=" + domainId
				+ "&usecase-diagram=" + encodeURIComponent(diagramText)	
		}
		fetch("/PM/screen/test-usecase", options)
			.then((res) => {
				return res.text();	
			})
			.then((data) => {
				let imgEl = document.querySelector("img.diagram-image");
				imgEl.setAttribute("src","/PM" + data + "?t=" + Date.now());
			});
}

function reloadDiagram() {
	window.location.reload();
}
