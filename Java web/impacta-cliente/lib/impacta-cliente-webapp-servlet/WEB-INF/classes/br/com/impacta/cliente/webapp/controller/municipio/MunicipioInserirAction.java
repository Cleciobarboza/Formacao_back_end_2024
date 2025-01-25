package br.com.impacta.cliente.webapp.controller.municipio;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/municipio/inserir")
public final class MunicipioInserirAction extends AbstractMunicipioAction {

    private static final long serialVersionUID = -2523913373429664261L;

    @Override
    protected void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception {
    	request.setAttribute(UFS, f.listarUFs());
    	request.setAttribute(INSERIR, ON);
        request.getRequestDispatcher(FORWARD_INSERIR).forward(request, response);
    }
}
