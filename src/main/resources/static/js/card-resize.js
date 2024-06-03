function shortenDescription(description) {
    let charNumber = 0;
    let currentWidth = document.body.clientWidth;

    if (currentWidth > 992) {
        charNumber = 350;
    } else if (currentWidth > 768) {
        charNumber = 260;
    } else if (currentWidth > 576) {
        charNumber = 170;
    } else if (currentWidth <= 576) {
        charNumber = 120;
    }
    if (description.length > charNumber) {
        description = description.substring(0, charNumber);
        description = description.substring(0, Math.min(description.length, description.lastIndexOf(" ")));
        description = description + "...";
    }

    return description;
}

function setEqualHeight() {
    const cards = document.querySelectorAll('.card');
    let maxHeight = 0;

    // Reset heights
    cards.forEach(card => {
        card.style.height = 'auto';
    });

    // Find the maximum height
    cards.forEach(card => {
        const cardHeight = card.offsetHeight;
        if (cardHeight > maxHeight) {
            maxHeight = cardHeight;
        }
    });

    // Set all cards to the maximum height
    cards.forEach(card => {
        card.style.height = maxHeight + 'px';
    });
}

function limitDescriptionLength() {
    // this works fine because the querySelector returns
    // elements as they appear in the document.
    const cards = document.querySelectorAll('.card'); // Get all cards

    cards.forEach(card => { // for each given card
        // Get the description data holder within that card
        let descriptionDataHolder = card.querySelector('.description-data');
        // Extract the description from the data-description attribute of the paragraph
        let description = descriptionDataHolder.getAttribute('data-description');
        // Set the innerHTML of the card description to the shortened description
        card.querySelector('.card-description').innerHTML = shortenDescription(description);
    });
}

function setOrUpdateCardDescriptionHeight() {
    limitDescriptionLength();
    setEqualHeight();
}

// // Run the function on page load
// window.addEventListener('load', () => {
//     setOrUpdateCardDescriptionHeight();
// });
//
// // Run the function on window resize
// window.addEventListener('resize', () => {
//     setOrUpdateCardDescriptionHeight();
// })
