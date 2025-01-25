package br.com.impacta.cliente.webapp.controller.municipio;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/municipio/apagar")
public final class MunicipioApagarAction extends AbstractMunicipioAction {

	private static final long serialVersionUID = -2523913373429664261L;

	@Override
	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String id = request.getParameter(ID);
		final String uf = request.getParameter(UF);
		final String municipio = request.getParameter(MUNICIPIO);

		f.apagarPorId(id);

		request.setAttribute(MSG, String.format("Apagado com sucesso: %s / %s", municipio, uf));
		request.getRequestDispatcher(DISPATCHER_LISTAR).forward(request, response);
	}
}
