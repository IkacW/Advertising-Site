function postLoad() {
    setUpPageNavigation();
    setOrUpdateCardDescriptionHeight()
}

function whenResized() {
    setOrUpdateCardDescriptionHeight()
}

window.addEventListener('load', () => {
    postLoad();
});

window.addEventListener('resize', () => {
    whenResized();
})
