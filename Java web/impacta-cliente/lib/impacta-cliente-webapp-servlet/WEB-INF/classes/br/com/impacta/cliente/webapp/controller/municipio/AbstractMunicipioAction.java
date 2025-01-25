package br.com.impacta.cliente.webapp.controller.municipio;

import javax.servlet.ServletException;

import br.com.impacta.cliente.facade.MunicipioFacade;
import br.com.impacta.cliente.webapp.controller.HttpServletAction;

abstract class AbstractMunicipioAction extends HttpServletAction {

	private static final long serialVersionUID = -404444212273357671L;

	static final String MUNICIPIOS = "municipios";
	static final String ID = "id";
	static final String MUNICIPIO = "municipio";
	static final String UFS = "ufs";
	static final String UF = "uf";
	static final String ACTION_INSERIR = "/impacta/municipio/inserir";
	static final String ACTION_ALTERAR = "/impacta/municipio/alterar";
	static final String ACTION_APAGAR = "/impacta/municipio/apagar";
	static final String ACTION_LISTAR = "/impacta/municipio/listar";
	static final String DISPATCHER_INSERIR = "/municipio/inserir";
	static final String DISPATCHER_ALTERAR = "/municipio/alterar";
	static final String DISPATCHER_LISTAR = "/municipio/listar";
	static final String FORWARD_INSERIR = "/WEB-INF/view/cadastros/municipio/inserir.jsp";
	static final String FORWARD_ALTERAR = "/WEB-INF/view/cadastros/municipio/alterar.jsp";
	static final String FORWARD_APAGAR = "/WEB-INF/view/cadastros/municipio/apagar.jsp";
	static final String FORWARD_LISTAR = "/WEB-INF/view/cadastros/municipio/listar.jsp";

	protected MunicipioFacade f;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		super.init();

		f = (MunicipioFacade) c.getAttribute(MunicipioFacade.NAME);
	}
}
