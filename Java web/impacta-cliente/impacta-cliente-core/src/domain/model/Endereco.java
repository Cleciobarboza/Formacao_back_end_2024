package domain.model;

public class Endereco extends Entidade<Long> implements Cloneable, Comparable<Endereco> {

	private String numero;
	private String cep;
	private Logradouro logradouro;

	public Endereco() {
		this(null, null, null, null);
	}

	public Endereco(Long id, Logradouro logradouro, String numero, String cep) {
		super(id);
		this.numero = numero;
		this.cep = cep;
		this.logradouro = logradouro;
	}

	public String getEnderecoCompleto() {
		return String.format("%s, %s, %s - %s - %s - %s",  
				logradouro.getNomeCompleto(), numero, cep, 
				logradouro.getBairroNome(),
				logradouro.getMunicipioNome(),
				logradouro.getUf());
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public Bairro getBairro() {
		return logradouro.getBairro();
	}

	public Municipio getMunicipio() {
		return logradouro.getMunicipio();
	}

	public UFVO getUf() {
		return logradouro.getUf();
	}

	/** {@inheritDoc} */
	@Override
	protected void finalize() throws Throwable {
		setLogradouro(null);
		setNumero(null);
		setCep(null);
		super.finalize();
	}

	/** {@inheritDoc} */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Endereco(getId(), (Logradouro) logradouro.clone(), numero, cep);
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cep == null) ? 0 : cep.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		result = prime * result + ((numero == null) ? 0 : numero.hashCode());
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
		if (!(obj instanceof Endereco)) {
			return false;
		}
		Endereco other = (Endereco) obj;
		if (cep == null) {
			if (other.cep != null) {
				return false;
			}
		} else if (!cep.equals(other.cep)) {
			return false;
		}
		if (logradouro == null) {
			if (other.logradouro != null) {
				return false;
			}
		} else if (!logradouro.equals(other.logradouro)) {
			return false;
		}
		if (numero == null) {
			if (other.numero != null) {
				return false;
			}
		} else if (!numero.equals(other.numero)) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(Endereco outro) {
		int comp = logradouro.compareTo(outro.logradouro);

		if (comp == 0 && numero != null) {
			comp = numero.compareToIgnoreCase(outro.numero);
		}

		if (comp == 0 && cep != null) {
			comp = cep.compareToIgnoreCase(outro.cep);
		}

		return comp;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Endereco [numero=" + numero + ", cep=" + cep + ", logradouro=" + logradouro + "]";
	}
}
