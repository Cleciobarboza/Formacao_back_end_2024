package domain.model;

import domain.Domain;
import domain.exception.LogradouroException;

public class Logradouro extends Entidade<Integer> implements Cloneable, Comparable<Logradouro>, Domain {

	private LogradouroVO tipo;
	private String nome;
	private Bairro bairro;

	public Logradouro() {
		this(null, null, null, null);
	}

	public Logradouro(Integer id) {
		this(id, null, null, null);
	}

	public Logradouro(Integer id, Integer tipo, String logradouro, String bairro, String municipio, String uf) {
		this(id, LogradouroVO.valueOf(tipo), logradouro, new Bairro(bairro, municipio, uf));
	}

	public Logradouro(Integer id, LogradouroVO tipo, String nome, Bairro bairro) {
		super(id);
		this.tipo = tipo;
		this.nome = nome;
		this.bairro = bairro;
	}

	public static Logradouro createInstance() {
		return new Logradouro();
	}

	public LogradouroVO getTipo() {
		return tipo;
	}

	public void setTipo(LogradouroVO tipo) {
		this.tipo = tipo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNomeCompleto() {
		return String.format("%s %s", this.tipo, this.nome);
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getBairroNome() {
		return bairro.getNome();
	}

	public Municipio getMunicipio() {
		return bairro.getMunicipio();
	}

	public String getMunicipioNome() {
		return bairro.getMunicipioNome();
	}

	public UFVO getUf() {
		return bairro.getUf();
	}

	public void validarTipo() throws LogradouroException {
		if (tipo == null) {
			throw new LogradouroException("Tipo do logradouro nulo!");
		}

		if (tipo.isNotSelecionado()) {
			throw new LogradouroException("Por favor, informe o tipo de logradouro!");
		}
	}

	public void validarNome() throws LogradouroException {
		if (nome == null) {
			throw new LogradouroException("Nome do logradouro nulo!");
		}

		if (nome.isEmpty()) {
			throw new LogradouroException("Por favor, informe o nome do logradouro!");
		}

		if (!nome.matches("^[a-zA-Z .]+$")) {
			throw new LogradouroException("Nome do logradouro inv�lido!");
		}
	}

	public void validarBairro() throws LogradouroException {
		if (bairro == null) {
			throw new LogradouroException("Bairro do logradouro nulo!");
		}
	}

	public void validarMunicipio() throws LogradouroException {
		validarBairro();

		try {
			bairro.validarMunicipio();
		} catch (Exception cause) {
			throw new LogradouroException(cause);
		}
	}

	public void validarUf() throws LogradouroException {
		validarMunicipio();

		try {
			bairro.validarUf();
		} catch (Exception cause) {
			throw new LogradouroException(cause);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Logradouro(getId(), tipo, nome, (Bairro) bairro.clone());
	}

	/** {@inheritDoc} */
	@Override
	protected void finalize() throws Throwable {
		setTipo(null);
		setNome(null);
		setBairro(null);
		super.finalize();
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bairro == null) ? 0 : bairro.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		return result;
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
		if (!(obj instanceof Logradouro)) {
			return false;
		}
		Logradouro other = (Logradouro) obj;
		if (bairro == null) {
			if (other.bairro != null) {
				return false;
			}
		} else if (!bairro.equals(other.bairro)) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (tipo != other.tipo) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(Logradouro outro) {
		int comp = bairro.compareTo(outro.bairro);

		if (comp == 0) {
			comp = tipo.compareTo(outro.tipo);
		}

		if (comp == 0) {
			comp = nome.compareToIgnoreCase(outro.nome);
		}

		return comp;
	}

	/** {@inheritDoc} */
	@Override
	public String export() {
		return String.format("%s;%s;%s;%s;%s;%s\n", getId(), tipo, nome, getBairroNome(), getMunicipioNome(), getUf());
	}

	/** {@inheritDoc} */
	@Override
	public Object[] toArray() {
		return new Object[] { this.getNomeCompleto(), bairro.getNome(), getMunicipio().getNome(), getUf(), this };
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Logradouro [tipo=" + tipo + ", nome=" + nome + ", bairro=" + bairro + "]";
	}
}
