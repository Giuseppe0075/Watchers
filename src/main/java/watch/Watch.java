package watch;

import java.util.List;

public class Watch {
    private final String id;
    private final String nome;
    private final Double prezzo;
    private final String descrizione;
    private final List<String> images;

    public Watch(String id, String nome, Double prezzo, String descrizione, List<String> images) {
        this.id = id;
        this.nome = nome;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.images = images;
    }

    public String getId(){
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public List<String> getImages() {
        return images;
    }
}
