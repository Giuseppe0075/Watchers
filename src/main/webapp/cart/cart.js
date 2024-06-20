const request = new XMLHttpRequest();
const form = document.querySelector('form');
let quantities = document.getElementsByClassName('quantity');
for(let quantity of quantities) {
    quantity.addEventListener('change', function () {
        let watch = quantity.parentElement.parentElement.parentElement.getElementsByClassName('watch')[0];
        if (quantity.value < 1) {
            quantity.value = 1;
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

function removeItem(watchId) {
    request.onreadystatechange = function() {
        if (request.status === 200 && request.readyState === 4) {
            // Remove the watch element from the DOM
            document.getElementById(`watch${watchId}`).remove();
        }
    };
    // Open the request with the correct URL and parameters
    request.open('GET', `../cart-servlet?watch=${watchId}&action=remove`, true);
    request.send();
}

