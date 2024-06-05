function manageDiscount() {
    console.log("Hey, this is function manageDiscount!");
    let priceP = document.getElementById("price");

    console.log(priceP);

    let price = priceP.getAttribute("data-price");
    let discount = priceP.getAttribute("data-discount");

    console.log(price);
    console.log(discount);

    if(discount != null && discount > 0) {
        let discountedPrice = price*discount;
        priceP.innerHTML = "<div class='text-center'><span class='text-decoration-line-through text-dark-emphasis'>"
            + price + "$</span> </br> <span class='text-danger-emphasis'>" + discountedPrice + "</span> </div>"
    } else {
        priceP.innerHTML = "<span class='text-dark-emphasis'>" +  price + "$</span>";
    }
}
