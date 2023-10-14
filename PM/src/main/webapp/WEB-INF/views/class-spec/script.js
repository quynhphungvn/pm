$(document).ready(() => {
	setUpOffCanvas();
});
$('.tabular.menu .item').tab();
$('.dropdown').dropdown({
	label: {
		duration: 0,
	},
	debug: true,
	performance: true,
});
function showModalPackageInfo(btnEl) {
	let info = btnEl.dataset.info;
	$('.ui.modal.package-info p.info').text(info);
	$('.ui.modal.package-info')
		.modal('show')
		;
}
function showModalAddClass() {
	$('.ui.modal.add-class')
		.modal('show')
		;
}
function showModalEditClass() {
	$('.ui.modal.edit-class')
		.modal('show')
		;
}

function deleteClass(id, name, packageId) {
	if (confirm("Delete Class: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "class-id=" + id
		}
		fetch("/PM/class-spec/delete-class", options)
			.then(() => {
				window.location.replace("/PM/class-spec?package-id=" + packageId);
			});
	}
}
function showModalAddPackage() {
	$('.ui.modal.add-package')
		.modal('show')
		;
}
function showModalEditPackage() {
	$('.ui.modal.edit-package')
		.modal('show')
		;
}
function deletePackage(id, name, domainId) {
	if (confirm("Delete Class: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "package-id=" + id
		}
		fetch("/PM/class-spec/delete-package", options)
			.then(() => {
				window.location.replace("/PM/class-spec?domain-id=" + domainId);
			});
	}
}
function showModalAddTestingFunction() {
	$('.ui.modal.add-testing-function')
		.modal('show')
		;
}
function showModalEditTestingFunction() {
	$('.ui.modal.edit-testing-function')
		.modal('show')
		;
}

function deleteTestingFunction(id, name, classId) {
	if (confirm("Delete Class: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "test-id=" + id
		}
		fetch("/PM/class-spec/delete-testing-function", options)
			.then(() => {
				window.location.replace("/PM/class-spec?id=" + classId + "&tab-view=test");
			});
	}
}
function enableEditMode() {
	$("form[name='class-spec'] textarea").attr("readonly", false);
	$("form[name='class-spec'] button.btn-update-spec").removeClass("d-none");
}
function enableEditInputMode() {
	$("form[name='testcase-input'] textarea").attr("readonly", false);
	$("form[name='testcase-input'] button.btn-update-input").removeClass("d-none");
}
function enableEditResultMode() {
	$("form[name='testcase-result'] textarea").attr("readonly", false);
	$("form[name='testcase-result'] button.btn-update-result").removeClass("d-none");
}
function deleteDiagram(id, name, domainId) {
	if (confirm("Delete diagram: " + name + "?") == true) {
		let options = {
			method: "POST",
			headers: {
				"Content-Type": "application/x-www-form-urlencoded"
			},
			body: "diagram-id=" + id
		}
		fetch("/PM/plan/delete", options)
			.then(() => {
				window.location.replace("/PM/plan?domain-id=" + domainId);
			});
	}
}
function testClassDiagram(id) {
	let diagramText = document.querySelector("textarea[name='class-diagram']").value;
	let options = {
		method: "POST",
		headers: {
			"Content-Type": "application/x-www-form-urlencoded"
		},
		body: "domain-id=" + id
			+ "&diagram-text=" + encodeURIComponent(diagramText)
	}
	fetch("/PM/domain/test-class", options)
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
