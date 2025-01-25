package app.model;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class MunicipioTableRow {

    private LongProperty id;

    private StringProperty municipio;

    private StringProperty uf;

    public MunicipioTableRow(Object... values) {
        id = new SimpleLongProperty((Long) values[2]);
        municipio = new SimpleStringProperty(values[0].toString());
        uf = new SimpleStringProperty(values[1].toString());
    }

    public LongProperty getId() {
        return id;
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    public StringProperty getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio.set(municipio);
    }

    public StringProperty getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf.set(uf);
    }
}
