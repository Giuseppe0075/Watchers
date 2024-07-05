const xhttp = new XMLHttpRequest();

function createInvoice(){
    xhttp.responseTyoe = 'blob';
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
    xhttp.send();
}