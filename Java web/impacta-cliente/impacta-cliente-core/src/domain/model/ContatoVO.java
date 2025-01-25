package domain.model;

import domain.exception.ContatoException;

public enum ContatoVO {
	ESCOLHA("ESCOLHA", ""), 
	CELULAR("Celular", ""), 
	E_MAIL("E-Mail", ""), 
	FONE_COMERCIAL("Fone Comercial", ""), 
	FONE_RESIDENCIAL("Fone Residencial", "");

	private String descricao;
	private String regex;

	private ContatoVO(String descricao, String regex) {
		this.descricao = descricao;
		this.regex = regex;
	}

	public boolean isSelecionado() {
		return !ESCOLHA.equals(this);
	}

	public boolean isNotSelecionado() {
		return !isSelecionado();
	}

	public void validar(final String valor) throws ContatoException {
		if (valor == null) {
			throw new ContatoException(String.format("%s nulo!", this));
		}

		if (!valor.matches(regex)) {
			throw new ContatoException(String.format("%s inv�lido: %s", this, valor));
		}
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return descricao;
	}
}
