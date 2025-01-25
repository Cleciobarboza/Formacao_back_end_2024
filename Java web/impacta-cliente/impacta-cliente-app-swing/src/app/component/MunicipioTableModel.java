package app.component;

import java.util.Collection;

import javax.swing.table.DefaultTableModel;

public class MunicipioTableModel extends DefaultTableModel {

	private static final long serialVersionUID = 5918740727634328731L;

	public MunicipioTableModel(final Collection<?> domains) {
		super();

		setColumnIdentifiers(new Object[] { "Município", "UF" });

		for (Object domain : domains) {
			addRow((Object[]) domain);
		}
	}
}
