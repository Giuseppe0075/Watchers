const filterForm = document.getElementById('filterForm');
const catalogue = document.getElementById('catalogue');
const filterFormGroup = filterForm.querySelectorAll('.filterGroup');


document.addEventListener("DOMContentLoaded", function() {
    const toggleButton = document.getElementById('toggleFilters');
    const filtersBar = document.getElementById('filters');
    const closeFiltersBtn = document.getElementById('closeFiltersBtn');

    toggleButton.addEventListener('click', function() {
        if (filtersBar.classList.contains('hidden')) {
            filtersBar.classList.remove('hidden');
            filtersBar.classList.add('visible');
            filtersBar.style.maxHeight = filtersBar.scrollHeight + "px"; // Aggiunge l'altezza massima dinamica
        } else {
            filtersBar.classList.add('hidden');
            filtersBar.classList.remove('visible');
            filtersBar.style.maxHeight = 0; // Riduce l'altezza massima a 0
        }
    });

    closeFiltersBtn.addEventListener('click', function() {
        filtersBar.classList.add('hidden');
        filtersBar.classList.remove('visible');
        filtersBar.style.maxHeight = 0; // Riduce l'altezza massima a 0
    });
});

window.onresize = function() {
    const filtersBar = document.getElementById('filters');
    const closeFiltersBtn = document.getElementById('closeFiltersBtn');

    if (!filtersBar.classList.contains('hidden')) {
        filtersBar.classList.add('hidden');
        filtersBar.classList.remove('visible');
        filtersBar.style.maxHeight = "0"; // Riduce l'altezza massima a 0
    }
};
//For the first load of the catalogue
document.addEventListener('DOMContentLoaded', () => {
    sendForm();
});

/* Filtering Catalogue */
// Add an event listener to all the filter form groups
filterFormGroup.forEach(formGroup => {
    formGroup.addEventListener('input', event => {
        event.preventDefault();
        console.log("Form changed")
        sendForm()
    });
});

//Sends the form via AJAX to the server
function sendForm() {
    const xhttp = new XMLHttpRequest();
    // Create a FormData object from the form
    const formData = new FormData(filterForm);

    xhttp.onreadystatechange = function() {
        if (xhttp.readyState === 4 && xhttp.status === 200) {
            // Clear the current catalog
            while (catalogue.firstChild) {
                catalogue.removeChild(catalogue.firstChild);
            }

            // Assume `watch` and `image` are objects with the necessary properties
            for (const watch of JSON.parse(this.responseText)) {
                createWatchCard(watch)
            }
        }
    }

    xhttp.open('POST', '../get-watches', true);
    xhttp.send(formData);
}

function changeFavourite(watchId, button){
    return function(){
        const xhttp = new XMLHttpRequest();
        let action = button.innerHTML.includes('fas fa-heart') ? 'remove' : 'add';
        let parameters = "watch=" + watchId + "&action=" + action;

        if(action === 'add'){
            button.innerHTML = '<i class="fas fa-heart"></i>';
            button.style.color = 'red';
        } else {
            button.innerHTML = '<i class="far fa-heart"></i>';
            button.style.color = 'black';
        }

        xhttp.open('GET', '../favourites-servlet?' + parameters, true);
        xhttp.send();
    }
}

//Creates a watch card for the catalogue
function createWatchCard(watch){
    // Create the outer div
    const watchElement = document.createElement('div');
    watchElement.className = 'watch';
    watchElement.style.position = 'relative';

    // Create the anchor element
    const anchorElement = document.createElement('a');
    anchorElement.href = `../watchpage/watch.jsp?id=${watch.id}`;

    // Create the img element
    const imgElement = document.createElement('img');
    imgElement.src = `../getImage?id=${watch.mainImageId}&watch=${watch.id}`;
    imgElement.alt = 'no images';
    anchorElement.appendChild(imgElement);

    // Create the h2 element
    const h2Element = document.createElement('h2');
    h2Element.textContent = watch.name;
    anchorElement.appendChild(h2Element);

    // Create the brand p element
    const brandElement = document.createElement('p');
    brandElement.textContent = `Brand: ${watch.brand}`;
    anchorElement.appendChild(brandElement);

    // Create the price p element
    const priceElement = document.createElement('p');
    priceElement.textContent = `${watch.price} $`;
    anchorElement.appendChild(priceElement);

    // Append the anchor element to the outer div
    watchElement.appendChild(anchorElement);

    // Create the button for favorite element
    const buttonElement = document.createElement('button');
    buttonElement.style.position = 'absolute';
    buttonElement.style.top = '2%';
    buttonElement.style.right = '2%';

    // Invia una richiesta GET per verificare se l'orologio è nei preferiti
    const xhttpFavourite = new XMLHttpRequest();
    xhttpFavourite.onreadystatechange = function() {
        if (this.readyState === 4 && this.status === 200) {
            // Se l'orologio è nei preferiti, mostra il cuore pieno
            if(this.responseText === 'true'){
                buttonElement.innerHTML = '<i class="fas fa-heart"></i>';
                buttonElement.style.color = 'red';
            } else {
                // Altrimenti mostra il cuore vuoto
                buttonElement.innerHTML = '<i class="far fa-heart"></i>';
                buttonElement.style.color = 'black';
            }
        }
    };

    xhttpFavourite.open('GET', '../favourites-servlet?watch='+watch.id+'&action=get', true);
    xhttpFavourite.send();
    buttonElement.addEventListener('click', changeFavourite(watch.id, buttonElement));

    watchElement.appendChild(buttonElement);

    // Append the watch element to the catalog
    catalogue.appendChild(watchElement);
}