package br.com.impacta.cliente.webapp.controller.cliente;

import javax.servlet.ServletException;

import br.com.impacta.cliente.facade.ClienteFacade;
import br.com.impacta.cliente.webapp.controller.HttpServletAction;

abstract class AbstractClienteAction extends HttpServletAction {

	private static final long serialVersionUID = -5372321836623349334L;

	static final String CLIENTES = "clientes";
	static final String FORWARD_LISTAR = "/WEB-INF/view/cadastros/cliente/listar.jsp";

	ClienteFacade f;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		super.init();

		f = (ClienteFacade) c.getAttribute(ClienteFacade.NAME);
	}
}
