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