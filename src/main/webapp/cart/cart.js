const request = new XMLHttpRequest();
const form = document.querySelector('form');
let quantities = document.getElementsByClassName('quantity');
for(let quantity of quantities) {
    quantity.addEventListener('change', function () {
        let watch = quantity.parentElement.parentElement.parentElement.getElementsByClassName('watch')[0];
        if (quantity.value < 0) {
            quantity.value = 0;
        } else if (quantity.value > quantity.max.value) {
            quantity.value = quantity.max.value;
        }

        request.onreadystatechange = function () {
            if (request.status === 200 && request.readyState === 4) {
                console.log("Quantity updated successfully");
            }
        }

        request.open('GET', `../cart-servlet?watch=${watch.value}&action=update&quantity=${quantity.value}`, true);
        request.send();
    });
}

document.addEventListener('DOMContentLoaded', function () {
    updateCheckout();
});

function updateCheckout(){
    if(document.getElementsByClassName('watch').length === 0){
        document.getElementById('checkout').style.display = 'none';
        document.getElementById('empty').style.display = 'block';
    } else {
        document.getElementById('checkout').style.display = 'block';
        document.getElementById('empty').style.display = 'none';
    }
}

function removeItem(watchId) {
    request.onreadystatechange = function() {
        if (request.status === 200 && request.readyState === 4) {
            // Remove the watch element from the DOM
            document.getElementById(`watch${watchId}`).remove();
            updateCheckout();
        }
    };
    // Open the request with the correct URL and parameters
    request.open('GET', `../cart-servlet?watch=${watchId}&action=remove`, true);
    request.send();
}

form.addEventListener('submit', function (event) {
    event.preventDefault();
    for(let quantity of quantities){
        if(quantity.value === "0"){
            alert("Cannot checkout an out of stock watch.");
            return;
        }
    }

    form.submit();
});

