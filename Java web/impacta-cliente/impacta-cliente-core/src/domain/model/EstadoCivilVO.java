package domain.model;

public enum EstadoCivilVO {
	ESCOLHA("ESCOLHA"), CASADO("Casado"), SEPARADO("Separado"), SOLTEIRO("Solteiro"), VIUVO("Vi�vo");

	private String descricao;

	private EstadoCivilVO(String descricao) {
		this.descricao = descricao;
	}

	public boolean isSelecionado() {
		return !ESCOLHA.equals(this);
	}

	public boolean isNotSelecionado() {
		return !isSelecionado();
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return descricao;
	}
}
