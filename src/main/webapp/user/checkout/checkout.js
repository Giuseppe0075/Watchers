const request = new XMLHttpRequest();
const form = document.querySelector('form');
let quantities = document.getElementsByClassName('quantity');
for(let quantity of quantities) {
    quantity.addEventListener('change', function () {
        if (quantity.value < 1) {
            quantity.value = 1;
        } else if (quantity.value > quantity.max.value) {
            quantity.value = quantity.max.value;
        }

        let price = quantity.parentElement.parentElement.querySelector('.price');
        price.innerText = quantity.value * price.dataset.singlePrice + " €";
    });
}

function removeItem(watchId) {
    alert(`Removing watch ${watchId}`)
    document.getElementById(`watch${watchId}`).remove();
}