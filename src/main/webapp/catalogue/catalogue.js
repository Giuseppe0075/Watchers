const filterForm = document.getElementById('filterForm');
const catalogue = document.getElementById('catalogue');
const filterFormGroup = filterForm.querySelectorAll('.filterGroup');
const xhttp = new XMLHttpRequest();

filterFormGroup.forEach(formGroup => {
    formGroup.addEventListener('change', event => {
        event.preventDefault();
        sendForm()
    });
});


//For the first load of the catalogue
document.addEventListener('DOMContentLoaded', () => {
    sendForm();
});


function sendForm() {
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

    xhttp.open('POST', '/get-watches', true);
    xhttp.send(formData);
}


function createWatchCard(watch){
    // Create the outer div
    const watchElement = document.createElement('div');
    watchElement.className = 'watch';

    // Create the anchor element
    const anchorElement = document.createElement('a');
    anchorElement.href = `../watchpage/watch.jsp?id=${watch.id}`;

    // Create the img element
    const imgElement = document.createElement('img');
    imgElement.src = `/getImage?id=1&watch=${watch.id}`;
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

    // Create the description p element
    const descriptionElement = document.createElement('p');
    descriptionElement.textContent = `Descrizione: ${watch.description}`;
    anchorElement.appendChild(descriptionElement);

    // Create the first form
    const form1Element = document.createElement('form');
    form1Element.method = 'post';
    form1Element.action = `../cart-servlet`;

    const input1Element = document.createElement('input');
    input1Element.type = 'hidden';
    input1Element.name = 'quantity';
    input1Element.value = '1';
    form1Element.appendChild(input1Element);

    const input2Element = document.createElement('input');
    input2Element.type = 'hidden';
    input2Element.name = 'watch';
    input2Element.value = watch.id;
    form1Element.appendChild(input2Element);

    if (watch.stock === 0) {
        const pElement = document.createElement('p');
        pElement.textContent = 'Out of stock';
        form1Element.appendChild(pElement);
    } else {
        const buttonElement = document.createElement('button');
        buttonElement.type = 'submit';
        buttonElement.name = 'action';
        buttonElement.value = 'add';
        buttonElement.textContent = 'Aggiungi a Carrello';
        form1Element.appendChild(buttonElement);
    }

    anchorElement.appendChild(form1Element);

    // Create the second form
    const form2Element = document.createElement('form');
    form2Element.method = 'post';
    form2Element.action = `../favourites-servlet`;

    const input3Element = document.createElement('input');
    input3Element.type = 'hidden';
    input3Element.name = 'url';
    input3Element.value = `../catalogue/catalogue.jsp`;
    form2Element.appendChild(input3Element);

    const input4Element = document.createElement('input');
    input4Element.type = 'hidden';
    input4Element.name = 'watch';
    input4Element.value = watch.id;
    form2Element.appendChild(input4Element);

    const button2Element = document.createElement('button');
    button2Element.type = 'submit';
    button2Element.name = 'action';
    button2Element.value = 'add';
    button2Element.textContent = 'Aggiungi a Preferiti';
    form2Element.appendChild(button2Element);

    anchorElement.appendChild(form2Element);

    // Append the anchor element to the outer div
    watchElement.appendChild(anchorElement);

    // Append the watch element to the catalog
    catalogue.appendChild(watchElement);
}