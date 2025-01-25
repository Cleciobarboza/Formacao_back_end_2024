package domain.model;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

import domain.Domain;
import domain.exception.ClienteException;

public class Cliente extends Entidade<Long> implements Cloneable, Comparable<Cliente>, Domain {

	private String nome;
	private String sobrenome;
	private EstadoCivilVO estadoCivil;
	private SexoVO sexo;
	private Endereco enderecoResidencial;
	private final Set<Contato> contatos = new TreeSet<Contato>();

	public Cliente() {
		this(null, null, null);
	}

	public Cliente(Long id, String nome, String sobrenome) {
		this(id, nome, sobrenome, null, null, null);
	}

	public Cliente(Long id, String nome, String sobrenome, EstadoCivilVO estadoCivil, SexoVO sexo,
			Endereco enderecoResidencial) {
		super(id);
		this.nome = nome;
		this.sobrenome = sobrenome;
		this.estadoCivil = estadoCivil;
		this.sexo = sexo;
		this.enderecoResidencial = enderecoResidencial;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getNomeCompleto() {
		return String.format("%s %s", nome, sobrenome);
	}

	public EstadoCivilVO getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilVO estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public SexoVO getSexo() {
		return sexo;
	}

	public void setSexo(SexoVO sexo) {
		this.sexo = sexo;
	}

	public Endereco getEnderecoResidencial() {
		return enderecoResidencial;
	}

	public void setEnderecoResidencial(Endereco enderecoResidencial) {
		this.enderecoResidencial = enderecoResidencial;
	}

	public boolean add(Contato contato) {
		return contatos.add(contato);
	}

	public boolean addAll(Collection<Contato> contatos) {
		return this.contatos.addAll(contatos);
	}

	public boolean contains(Contato contato) {
		return contatos.contains(contato);
	}

	public boolean containsEMail() {
		return Contato.containsEMail(contatos);
	}

	public boolean remove(Contato contato) {
		return contatos.remove(contato);
	}

	public void clear() {
		contatos.clear();
	}

	public void validarNome() throws ClienteException {
		if (nome == null) {
			throw new ClienteException("Nome do cliente nulo!");
		}

		if (nome.isEmpty()) {
			throw new ClienteException("Por favor, informe o nome do cliente!");
		}

		if (!nome.matches("^[a-zA-Z .]+$")) {
			throw new ClienteException("Nome do cliente inv�lido!");
		}
	}

	public void validarSobrenome() throws ClienteException {
		if (sobrenome == null) {
			throw new ClienteException("Sobrenome do cliente nulo!");
		}

		if (sobrenome.isEmpty()) {
			throw new ClienteException("Por favor, informe o sobrenome do cliente!");
		}

		if (!sobrenome.matches("^[a-zA-Z .]+$")) {
			throw new ClienteException("Sobrenome do cliente inv�lido!");
		}
	}

	public void validarEstadoCivil() throws ClienteException {
		if (estadoCivil == null) {
			throw new ClienteException("Estado c�vil do cliente nulo!");
		}

		if (estadoCivil.isNotSelecionado()) {
			throw new ClienteException("Por favor, selecione o estado c�vil do cliente!");
		}
	}

	public void validarSexo() throws ClienteException {
		if (sexo == null) {
			throw new ClienteException("Sexo do cliente nulo!");
		}

		if (sexo.isNotSelecionado()) {
			throw new ClienteException("Por favor, selecione o sexo do cliente!");
		}
	}

	public void validarEnderecoResidencial() throws ClienteException {
		if (enderecoResidencial == null) {
			throw new ClienteException("Endere�o residencial do cliente nulo!");
		}
	}

	public void validarContatos() throws ClienteException {
		if (containsEMail()) {
			throw new ClienteException("Por favor, informe seu e-mail!");
		}
	}

	/** {@inheritDoc} */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Cliente domain;

		domain = new Cliente(getId(), nome, sobrenome, estadoCivil, sexo, (Endereco) enderecoResidencial.clone());
		domain.addAll(contatos);

		return domain;
	}

	/** {@inheritDoc} */
	@Override
	protected void finalize() throws Throwable {
		setNome(null);
		setSobrenome(null);
		setEstadoCivil(null);
		setSexo(null);
		setEnderecoResidencial(null);
		super.finalize();
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((estadoCivil == null) ? 0 : estadoCivil.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((sexo == null) ? 0 : sexo.hashCode());
		result = prime * result + ((sobrenome == null) ? 0 : sobrenome.hashCode());
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
		if (!(obj instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) obj;
		if (estadoCivil != other.estadoCivil) {
			return false;
		}
		if (nome == null) {
			if (other.nome != null) {
				return false;
			}
		} else if (!nome.equals(other.nome)) {
			return false;
		}
		if (sexo != other.sexo) {
			return false;
		}
		if (sobrenome == null) {
			if (other.sobrenome != null) {
				return false;
			}
		} else if (!sobrenome.equals(other.sobrenome)) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(Cliente outro) {
		int comp = enderecoResidencial.compareTo(outro.enderecoResidencial);

		if (comp == 0) {
			comp = nome.compareToIgnoreCase(outro.nome);
		}

		if (comp == 0) {
			comp = sobrenome.compareToIgnoreCase(outro.sobrenome);
		}

		return comp;
	}

	/** {@inheritDoc} */
	@Override
	public String export() {
		return String.format("%s;%s;%s;%s;%s\n", getId(), nome, sobrenome, estadoCivil, sexo);
	}

	/** {@inheritDoc} */
	@Override
	public Object[] toArray() {
		return new Object[] { this.getNomeCompleto(), enderecoResidencial.getEnderecoCompleto(), this.contatos, this };
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + ", sobrenome=" + sobrenome + ", estadoCivil=" + estadoCivil + ", sexo=" + sexo
				+ ", enderecoResidencial=" + enderecoResidencial + ", contatos=" + contatos.size() + "]";
	}
}
