// .offcanvas
function setupPage() {
    setUpOffCanvas();
}
function setUpOffCanvas() {
    let canvases = document.querySelectorAll(".offcanvas");
    for (let i = 0; i < canvases.length; i++) {
        let canvasEl = canvases[i];
        let width = canvasEl.dataset.width;
        let location = canvasEl.dataset.location;
        canvasEl.style.width = width;
        if (location == 'left') {
            canvasEl.style.left = "-" + width;
            canvasEl.style.transition = "left 0.4s";
        } else if (location == "right" || location == false) {
            canvasEl.style.right = "-" + width;
            canvasEl.style.transition = "right 0.4s";
        }
        canvasEl.classList.remove("d-none");
    }
}
function showOffCanvas(offCanvasId) {
    let canvasEl = document.querySelector(offCanvasId);
    let location = canvasEl.dataset.location;
    if (location == "left")
        canvasEl.style.left = 0;
    else if (location == "right")
        canvasEl.style.right = 0;
}
function hideOffCanvas(offCanvasId) {
    let canvasEl = document.querySelector(offCanvasId);
    let width = canvasEl.dataset.width;
    let location = canvasEl.dataset.location;
    if (location == "left") {
        canvasEl.style.left = "-" + width;
    } else if (location == "right" || location == false) {
        canvasEl.style.right = "-" + width;
    }
}