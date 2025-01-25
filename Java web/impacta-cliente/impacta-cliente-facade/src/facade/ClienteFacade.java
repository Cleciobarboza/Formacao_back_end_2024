package facade;

import java.util.Collection;
import java.util.Collections;

import domain.model.Cliente;

public class ClienteFacade {

	public static final String NAME = "CLIENTE_FACADE";

    public Collection<Cliente> listarTodos() {
        return Collections.<Cliente> emptySet();
    }

	public void close() {
		
	}
}
