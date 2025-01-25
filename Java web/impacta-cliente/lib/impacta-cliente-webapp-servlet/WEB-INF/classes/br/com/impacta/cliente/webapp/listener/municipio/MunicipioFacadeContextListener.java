package br.com.impacta.cliente.webapp.listener.municipio;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.impacta.cliente.facade.MunicipioFacade;

@WebListener
public final class MunicipioFacadeContextListener implements ServletContextListener {

	/** {@inheritDoc} */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			context.setAttribute(MunicipioFacade.NAME, new MunicipioFacade());
			context.log("Fachada para municípios criada!");
		} catch (Exception cause) {
			context.log(MunicipioFacade.NAME, cause);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			MunicipioFacade facade = (MunicipioFacade) context.getAttribute(MunicipioFacade.NAME);

			facade.close();
			context.log("Fachada para municípios finalizada!");
		} catch (Exception cause) {
			context.log(MunicipioFacade.NAME, cause);
		}
	}
}
