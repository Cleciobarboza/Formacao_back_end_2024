package br.com.impacta.cliente.webapp.controller.municipio;

import java.util.Collection;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/municipio/listar")
public final class MunicipioListarAction extends AbstractMunicipioAction {

	private static final long serialVersionUID = -2523913373429664261L;

	/** {@inheritDoc} */
	@Override
	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String uf = request.getParameter(UF);
		final Collection<?> rows = f.listar(uf);
		c.log("Municípios encontratos: " + rows.size());

		request.setAttribute(MUNICIPIOS, rows);
		request.setAttribute(UFS, f.listarUFs());
		request.setAttribute(UF, uf);
    	request.setAttribute(LISTAR, ON);
		request.getRequestDispatcher(FORWARD_LISTAR).forward(request, response);
	}
}
