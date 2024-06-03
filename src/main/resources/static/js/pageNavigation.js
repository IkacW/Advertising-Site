function setUpPageNavigation() {

    let pagination = document.querySelector(".pagination");
    let pageLinks = pagination.querySelectorAll(".page-link");

    let pageNo = parseInt(pagination.getAttribute("data-page-no")) + 1;
    let totalPages = parseInt(pagination.getAttribute("data-total-pages"))

    if(pageNo === 1) {
        document.getElementById('prev').classList.add("disabled");
    } else if (pageNo === totalPages) {
        document.getElementById("next").classList.add("disabled");
    }

    pageLinks.forEach(link => {
        if(link.innerHTML == pageNo) {
            link.classList.add("disabled");
            // find a way to break forEach
        }
    });

    if(pageNo <= 2) {
        document.getElementById("hide-under-condition-1").hidden = true;
    } else if(pageNo >= totalPages - 1) {
        document.getElementById("hide-under-condition-2").hidden = true;
    }
}

// window.addEventListener("DOMContentLoaded", () => {
//     setUpPageNavigation();
// });