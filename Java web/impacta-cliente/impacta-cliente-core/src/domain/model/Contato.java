package domain.model;

import java.util.Set;

import domain.Domain;
import domain.exception.ContatoException;

public class Contato extends Entidade<Integer> implements Comparable<Contato>, Domain {

	private ContatoVO tipo;
	private String valor;

	private Contato(Integer id, ContatoVO tipo, String valor) {
		super(id);
		this.tipo = tipo;
		this.valor = valor;
	}

	public static final Contato createInstance(ContatoVO tipo) {
		return new Contato(null, tipo, null);
	}

	public ContatoVO getTipo() {
		return tipo;
	}

	public void setTipo(ContatoVO tipo) {
		this.tipo = tipo;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public void validarTipo() throws ContatoException {
		if (tipo == null) {
			throw new ContatoException("Tipo de contato nulo!");
		}

		if (tipo.isNotSelecionado()) {
			throw new ContatoException("Por favor, selecione um tipo de contato!");
		}
	}

	public void validarValor() throws ContatoException {
		if (tipo != null) {
			tipo.validar(valor);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return new Contato(getId(), tipo, valor);
	}

	/** {@inheritDoc} */
	@Override
	protected void finalize() throws Throwable {
		setTipo(null);
		setValor(null);
		super.finalize();
	}

	/** {@inheritDoc} */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((valor == null) ? 0 : valor.hashCode());
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
		if (!(obj instanceof Contato)) {
			return false;
		}
		Contato other = (Contato) obj;
		if (tipo != other.tipo) {
			return false;
		}
		if (valor == null) {
			if (other.valor != null) {
				return false;
			}
		} else if (!valor.equals(other.valor)) {
			return false;
		}
		return true;
	}

	/** {@inheritDoc} */
	@Override
	public int compareTo(Contato outro) {
		int comp = tipo.compareTo(outro.tipo);

		if (comp == 0 && valor != null) {
			comp = valor.compareToIgnoreCase(outro.valor);
		}

		return comp;
	}

	/** {@inheritDoc} */
	@Override
	public String export() {
		return String.format("%s;%s;%s\n", getId(), tipo, valor);
	}

	/** {@inheritDoc} */
	@Override
	public Object[] toArray() {
		return new Object[] { tipo, valor, this };
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return valor;
	}

	public static boolean containsEMail(final Set<Contato> contatos) {
		for (Contato c : contatos) {
			if (ContatoVO.E_MAIL.equals(c.tipo)) {
				return true;
			}
		}

		return false;
	}
}
