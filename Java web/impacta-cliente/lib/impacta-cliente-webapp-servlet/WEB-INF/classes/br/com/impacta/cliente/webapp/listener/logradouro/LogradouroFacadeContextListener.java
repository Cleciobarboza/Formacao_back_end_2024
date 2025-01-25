package br.com.impacta.cliente.webapp.listener.logradouro;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.impacta.cliente.facade.LogradouroFacade;

@WebListener
public final class LogradouroFacadeContextListener implements ServletContextListener {

	/** {@inheritDoc} */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			context.setAttribute(LogradouroFacade.NAME, new LogradouroFacade());
			context.log("Fachada para logradouros criada!");
		} catch (Exception cause) {
			context.log(LogradouroFacade.NAME, cause);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			LogradouroFacade facade = (LogradouroFacade) context.getAttribute(LogradouroFacade.NAME);

			facade.close();
			context.log("Fachada para logradouros finalizada!");
		} catch (Exception cause) {
			context.log(LogradouroFacade.NAME, cause);
		}
	}
}
