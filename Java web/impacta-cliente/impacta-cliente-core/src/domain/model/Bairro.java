package domain.model;

import domain.Domain;
import domain.exception.BairroException;

public class Bairro extends Entidade<Integer> implements Cloneable, Comparable<Bairro>, Domain {

	private String nome;
	private Municipio municipio;

	/** Construtor padr�o */
	public Bairro() {
		this((String) null, null, null);
	}

	public Bairro(Integer id) {
		this(id, null, null);
	}

	public Bairro(String nome, String municipio, String uf) {
		this(null, nome, new Municipio(municipio, uf));
	}

	public Bairro(Integer id, String nome, String municipio, String uf) {
		this(id, nome, new Municipio(municipio, uf));
	}

	public Bairro(Integer id, String nome, Municipio municipio) {
		super(id);
		this.nome = nome;
		this.municipio = municipio;
	}

	public static Bairro createInstance() {
		return new Bairro();
	}

	public static Bairro createInstance(Integer id) {
		return new Bairro(id);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Municipio getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipio municipio) {
		this.municipio = municipio;
	}

	public String getMunicipioNome() {
		return municipio.getNome();
	}

	public UFVO getUf() {
		return municipio.getUf();
	}

	public void validarNome() throws BairroException {
		if (nome == null) {
			throw new BairroException("Nome do bairro nulo!");
		}

		if (nome.isEmpty()) {
			throw new BairroException("Por favor, informe o nome do bairro!");
		}

		if (!nome.matches("^[a-zA-Z .]+$")) {
			throw new BairroException("Nome do bairro inv�lido!");
		}
	}

	public void validarMunicipio() throws BairroException {
		if (municipio == null) {
			throw new BairroException("Munic�pio do bairro nulo!");
		}
	}

	public void validarUf() throws BairroException {
		validarMunicipio();

		try {
			municipio.validarUf();
		} catch (Exception cause) {
			throw new BairroException(cause);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Bairro(getId(), nome, (Municipio) municipio.clone());
	}

	/** {@inheritDoc} */
	@Override
	protected void finalize() throws Throwable {
		setNome(null);
		setMunicipio(null);
		super.finalize();
	}

	/** {@inheritDoc} */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Bairro)) {
			return false;
		}
		Bairro other = (Bairro) obj;
		if (municipio == null) {
			if (other.municipio != null) {
				return false;
			}
		} else if (!municipio.equals(other.municipio)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((municipio == null) ? 0 : municipio.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(Bairro outro) {
		int comp = municipio.compareTo(outro.municipio);

		if (comp == 0) {
			comp = nome.compareToIgnoreCase(outro.nome);
		}

		return comp;
	}

	/** {@inheritDoc} */
	@Override
	public String export() {
		return String.format("%s;%s;%s;%s\n", getId(), nome, getMunicipioNome(), getUf());
	}

	/** {@inheritDoc} */
	@Override
	public Object[] toArray() {
		return new Object[] { this.nome, municipio.getNome(), getUf(), this };
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Bairro [nome=" + nome + ", municipio=" + municipio + "]";
	}
}
