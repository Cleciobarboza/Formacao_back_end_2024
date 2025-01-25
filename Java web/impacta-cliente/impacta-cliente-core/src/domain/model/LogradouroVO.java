package domain.model;

public enum LogradouroVO {
	ESCOLHA("ESCOLHA"), AVENIDA("Avenida"), PRACA("Pra�a"), RUA("Rua");

	private String descricao;

	private LogradouroVO(String descricao) {
		this.descricao = descricao;
	}

	public boolean isSelecionado() {
		return !ESCOLHA.equals(this);
	}

	public boolean isNotSelecionado() {
		return !isSelecionado();
	}

	@Override
	public String toString() {
		return descricao;
	}

	public static LogradouroVO valueOf(final int ordinal) {
		for (LogradouroVO vo : values()) {
			if (vo.ordinal() == ordinal) {
				return vo;
			}
		}

		return null;
	}
}
