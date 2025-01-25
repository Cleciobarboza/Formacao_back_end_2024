package domain.model;

import domain.Domain;
import domain.exception.MunicipioException;

public class Municipio extends Entidade<Long> implements Cloneable, Comparable<Municipio>, Domain {

    private String nome;
    private UFVO uf;

    /** Contrutor padrão. */
    public Municipio() {
        this(null, null, (String) null);
    }

    public Municipio(Long id) {
        this(id, null, (String) null);
    }

    public Municipio(UFVO uf) {
        this(null, uf);
    }

    public Municipio(String nome, String uf) {
        this(null, nome, UFVO.valueOf(uf));
    }

    public Municipio(String nome, UFVO uf) {
        this(null, nome, uf);
    }

    public Municipio(Long id, String nome, String uf) {
    	this(id, nome, uf != null ? UFVO.valueOf(uf) : null);
    }

    public Municipio(Long id, String nome, UFVO uf) {
        super(id);
        this.nome = nome;
        this.uf = uf;
    }

	public static Municipio createInstance(long id) {
		return new Municipio(id);
	}

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UFVO getUf() {
        return uf;
    }

    public void setUf(UFVO uf) {
        this.uf = uf;
    }

    public void validarNome() throws MunicipioException {
        if (nome == null) {
            throw new MunicipioException("municipio.nulo");
        }

        if (nome.isEmpty()) {
            throw new MunicipioException("municipio.vazio");
        }

        if (!nome.matches("^[a-zA-Z .]+$")) {
            throw new MunicipioException("municipio.invalido");
        }
    }

    public void validarUf() throws MunicipioException {
        if (uf == null) {
            throw new MunicipioException("uf.nula");
        }

        if (uf.isNotSelecionado()) {
            throw new MunicipioException("uf.vazia");
        }
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nome == null) ? 0 : nome.hashCode());
        result = prime * result + ((uf == null) ? 0 : uf.hashCode());
        return result;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Municipio other = (Municipio) obj;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (uf == null) {
            if (other.uf != null)
                return false;
        } else if (!uf.equals(other.uf))
            return false;
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Municipio(getId(), nome, uf);
    }

    /** {@inheritDoc} */
    @Override
    protected void finalize() throws Throwable {
        setNome(null);
        setUf(null);
        super.finalize();
    }

    /** {@inheritDoc} */
    @Override
    public int compareTo(Municipio outro) {
        int comp = uf.compareTo(outro.uf);

        if (comp == 0) {
            comp = nome.compareToIgnoreCase(outro.nome);
        }

        return comp;
    }

    /** {@inheritDoc} */
    @Override
    public String export() {
        return String.format("%02d;%s;%s", getId(), nome, uf);
    }

    /** {@inheritDoc} */
    @Override
    public Object[] toArray() {
        return new Object[] { this, this.uf, this.getId() };
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return nome;
    }
}
