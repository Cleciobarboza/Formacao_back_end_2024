package domain.model;

public abstract class Entidade<PK> extends Object {

	private PK id;

	protected Entidade(PK id) {
		super();
		this.id = id;
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public boolean isNotNullId() {
		return !isNullId();
	}

	public boolean isNullId() {
		return id == null;
	}
	
	@Override
	protected void finalize() throws Throwable {
		setId(null);
		super.finalize();
	}
}
