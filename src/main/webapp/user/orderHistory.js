const xhttp = new XMLHttpRequest();

function createInvoice(buttonElement){
    const order = buttonElement.parentElement;
    const table = order.querySelector("table");
    let data = [];

    //get the data from the table
    for(let i = 1; i < table.rows.length; i++){
        const row = table.rows[i];
        const rowData = {
            product: row.cells[0].innerText,
            quantity: row.cells[1].innerText,
            price: row.cells[2].innerText,
            total: row.cells[3].innerText
        };
        data.push(rowData);
    }

    const jsonData = JSON.stringify(data);

    xhttp.responseType = 'blob';
    xhttp.onreadystatechange = function() {
        if(this.readyState === 4 && this.status === 200){
            const blob = new Blob([this.response], { type: 'application/pdf' });
            const downloadUrl = URL.createObjectURL(blob);
            const a = document.createElement("a");
            a.href = downloadUrl;
            a.download = "fattura.pdf"; // Nome del file da scaricare
            document.body.appendChild(a); // Aggiunge l'elemento a al documento
            a.click(); // Simula un click sull'elemento a per scaricare il file
            document.body.removeChild(a); // Rimuove l'elemento a dal documento
            URL.revokeObjectURL(downloadUrl); // Rimuove l'URL temporaneo
        }
    }

    xhttp.open("POST", "/pdfGenerator-servlet", true);
    xhttp.send(jsonData);
}