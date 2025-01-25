package br.com.impacta.cliente.webapp.controller.municipio;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/municipio/salvar")
public final class MunicipioSalvarAction extends AbstractMunicipioAction {

	private static final long serialVersionUID = -2523913373429664261L;

	@Override
	protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
		final String id = request.getParameter(ID);
		final String uf = request.getParameter(UF);
		final String municipio = request.getParameter(MUNICIPIO);

		try {
			f.validar(id, municipio, uf);
			f.salvar(id, municipio, uf);

			response.sendRedirect(ACTION_LISTAR);
		} catch (Exception cause) {
			String msg = cause.getMessage();
			request.setAttribute(MSG, msg.substring(msg.indexOf(ESPACO)));
			request.setAttribute(ID, id);
			request.setAttribute(MUNICIPIO, municipio);
			request.setAttribute(UF, uf);

			c.log(cause.getMessage(), cause);
			request.getRequestDispatcher(id != null ? DISPATCHER_ALTERAR : DISPATCHER_INSERIR).forward(request,
					response);
		}
	}
}
