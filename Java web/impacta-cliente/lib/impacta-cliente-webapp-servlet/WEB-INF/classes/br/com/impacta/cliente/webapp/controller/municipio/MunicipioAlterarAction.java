package br.com.impacta.cliente.webapp.controller.municipio;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/municipio/alterar")
public final class MunicipioAlterarAction extends AbstractMunicipioAction {

    private static final long serialVersionUID = -2523913373429664261L;

    @Override
    protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	request.setAttribute(ID, request.getParameter(ID));
    	request.setAttribute(MUNICIPIO, request.getParameter(MUNICIPIO));
    	request.setAttribute(UF, request.getParameter(UF));
    	request.setAttribute(UFS, f.listarUFs());
    	request.setAttribute(ALTERAR, ON);
        request.getRequestDispatcher(FORWARD_ALTERAR).forward(request, response);
    }
}
