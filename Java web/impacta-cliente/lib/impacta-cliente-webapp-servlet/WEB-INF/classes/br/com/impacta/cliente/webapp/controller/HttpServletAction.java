package br.com.impacta.cliente.webapp.controller;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class HttpServletAction extends HttpServlet {

	private static final long serialVersionUID = 3146324713374728232L;

	protected static final String MSG = "message";
	protected static final String ESPACO = " ";

	protected static final String INSERIR = "inserir";
	protected static final String ALTERAR = "alterar";
	protected static final String LISTAR = "listar";
	protected static final String ON = "on";

	protected ServletContext c;

	protected HttpServletAction() {
		super();
	}

	protected abstract void doProccess(HttpServletRequest request, HttpServletResponse response) throws Exception;

	/** {@inheritDoc} */
	@Override
	public void init() throws ServletException {
		c = getServletContext();

		super.init();
	}

	/** {@inheritDoc} */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			doProccess(request, response);
		} catch (Exception rootCause) {
			c.log("SERVIÇO INDIPONÍVEL!", rootCause);
			throw new ServletException(rootCause);
		}
	}

	/** {@inheritDoc} */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		try {
			doProccess(request, response);
		} catch (Exception rootCause) {
			c.log("SERVIÇO INDIPONÍVEL!", rootCause);
			throw new ServletException(rootCause);
		}
	}
}
