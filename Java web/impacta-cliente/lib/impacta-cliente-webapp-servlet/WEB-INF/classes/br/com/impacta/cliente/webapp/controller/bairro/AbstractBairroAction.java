package br.com.impacta.cliente.webapp.controller.bairro;

import javax.servlet.ServletException;

import br.com.impacta.cliente.facade.BairroFacade;
import br.com.impacta.cliente.webapp.controller.HttpServletAction;

abstract class AbstractBairroAction extends HttpServletAction {

	private static final long serialVersionUID = 5331565279228091955L;

	static final String BAIRROS = "bairros";
	static final String FORWARD_LISTAR = "/WEB-INF/view/cadastros/bairro/listar.jsp";

	BairroFacade f;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		super.init();

		f = (BairroFacade) c.getAttribute(BairroFacade.NAME);
	}
}
