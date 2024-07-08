const xhttp = new XMLHttpRequest();

function createInvoice(buttonElement){
    const order = buttonElement.parentElement;
    const table = order.querySelector("table");
    const orderId = order.querySelector("h2").innerText.split(" ")[1];
    const hiddenDataDiv = document.getElementById(`hidden-data-for-${orderId}`);
    let data = {
        tableData: [],
        clientData: {}
    }

    //get the data from the table
    for(let i = 1; i < table.rows.length; i++){
        const row = table.rows[i];
        const rowData = {
            product: row.cells[0].innerText,
            quantity: row.cells[1].innerText,
            price: row.cells[2].innerText,
            iva: row.cells[3].innerText,
            total: row.cells[4].innerText
        };
        data.tableData.push(rowData);
    }

    // Get the additional data from the hidden div
    data.clientData = {
        date: hiddenDataDiv.querySelector(".date").innerText,
        userName: hiddenDataDiv.querySelector(".user-name").innerText,
        userSurname: hiddenDataDiv.querySelector(".user-surname").innerText,
        userAddress: hiddenDataDiv.querySelector(".user-address").innerText
    };

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