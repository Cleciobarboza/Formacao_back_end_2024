package domain.model;

public enum SexoVO {
	ESCOLHA(' ', "ESCOLHA"), FEMININO('F', "Feminino"), MASCULINO('M', "Masculino");

	private char id;
	private String descricao;

	private SexoVO(char id, String descricao) {
		this.id = id;
		this.descricao = descricao;
	}

	public boolean isSelecionado() {
		return !ESCOLHA.equals(this);
	}

	public boolean isNotSelecionado() {
		return !isSelecionado();
	}

	public char getId() {
		return id;
	}

	public static SexoVO valueOf(final char id) {
		for (SexoVO vo : values()) {
			if (vo.getId() == id) {
				return vo;
			}
		}

		return null;
	}

	/** {@inheritDoc} */
	@Override
	public String toString() {
		return descricao;
	}
}
