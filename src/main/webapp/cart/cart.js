const quantity = document.getElementById('quantity');
const watch = document.getElementById('watch');
let request = new XMLHttpRequest();
quantity.addEventListener('change', function() {

    if(quantity.value < 1) {
        quantity.value = 1;
    }
    if(quantity.value > 99){
        quantity.value = 99;
    }

    request.onreadystatechange = function() {
        if(request.status === 200 && request.readyState === 4) {
            console.log("Quantity updated successfully");
        }
    }

    request.open('GET', `../cart-servlet?watch=${watch.value}&action=update&quantity=${quantity.value}`, true);
    request.send();
});

function removeItem(watchId) {
    request.onreadystatechange = function() {
        if(request.status === 200 && request.readyState === 4) {
            document.getElementById(`watch`+watchId).remove();
        }
    }
    request.open('GET', `../cart-servlet?watch=${watch.value}&action=remove`, true);
    request.send();
}