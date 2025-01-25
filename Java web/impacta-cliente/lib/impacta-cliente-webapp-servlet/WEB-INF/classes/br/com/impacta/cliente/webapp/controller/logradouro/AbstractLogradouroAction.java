package br.com.impacta.cliente.webapp.controller.logradouro;

import javax.servlet.ServletException;

import br.com.impacta.cliente.facade.LogradouroFacade;
import br.com.impacta.cliente.webapp.controller.HttpServletAction;

abstract class AbstractLogradouroAction extends HttpServletAction {

	private static final long serialVersionUID = 5331565279228091955L;

	static final String LOGRADOUROS = "logradouros";
	static final String FORWARD_LISTAR = "/WEB-INF/view/cadastros/logradouro/listar.jsp";

	LogradouroFacade f;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		super.init();

		f = (LogradouroFacade) c.getAttribute(LogradouroFacade.NAME);
	}
}
