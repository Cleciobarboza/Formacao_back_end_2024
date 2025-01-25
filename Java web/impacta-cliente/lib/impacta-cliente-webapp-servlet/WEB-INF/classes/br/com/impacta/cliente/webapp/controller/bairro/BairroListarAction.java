package br.com.impacta.cliente.webapp.controller.bairro;

import java.util.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/bairro/listar")
public final class BairroListarAction extends AbstractBairroAction {

	private static final long serialVersionUID = 6921495070741944097L;

	/** {@inheritDoc} */
	@Override
	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final Collection<?> rows = f.listarTodos();
		c.log("Bairros encontratos: " + rows.size());

		request.setAttribute(BAIRROS, rows);
		request.getRequestDispatcher(FORWARD_LISTAR).forward(request, response);
	}
}
