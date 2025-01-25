package br.com.impacta.cliente.webapp.listener.bairro;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.impacta.cliente.facade.BairroFacade;

@WebListener
public final class BairroFacadeContextListener implements ServletContextListener {

	/** {@inheritDoc} */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			context.setAttribute(BairroFacade.NAME, new BairroFacade());
			context.log("Fachada para bairros criada!");
		} catch (Exception cause) {
			context.log(BairroFacade.NAME, cause);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			BairroFacade facade = (BairroFacade) context.getAttribute(BairroFacade.NAME);

			facade.close();
			context.log("Fachada para bairros finalizada!");
		} catch (Exception cause) {
			context.log(BairroFacade.NAME, cause);
		}
	}
}
