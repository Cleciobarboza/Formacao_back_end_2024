package br.com.impacta.cliente.webapp.controller.logradouro;

import java.util.Collection;
import java.util.Collections;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/logradouro/listar")
public final class LogradouroListarAction extends AbstractLogradouroAction {

	private static final long serialVersionUID = -7470960112699661766L;

	/** {@inheritDoc} */
	@Override
	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Collection<?> rows = f.listarTodos();
		c.log("Logradouros encontratos: " + rows.size());

		request.setAttribute(LOGRADOUROS, Collections.emptySet());
		request.getRequestDispatcher(FORWARD_LISTAR).forward(request, response);
	}
}
