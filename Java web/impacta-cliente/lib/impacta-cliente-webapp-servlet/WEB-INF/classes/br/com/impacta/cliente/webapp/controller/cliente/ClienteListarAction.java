package br.com.impacta.cliente.webapp.controller.cliente;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cliente/listar")
public final class ClienteListarAction extends AbstractClienteAction {

	private static final long serialVersionUID = 990049358705857620L;

	/** {@inheritDoc} */
	@Override
	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Collection<?> rows = Collections.emptySet();
		c.log("Clientes encontratos: " + rows.size());

		request.setAttribute(CLIENTES, rows);
		request.getRequestDispatcher(FORWARD_LISTAR).forward(request, response);
	}
}
