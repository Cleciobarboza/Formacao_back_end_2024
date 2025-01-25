package br.com.impacta.cliente.webapp.listener.cliente;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.impacta.cliente.facade.ClienteFacade;

@WebListener
public final class ClienteFacadeContextListener implements ServletContextListener {

	/** {@inheritDoc} */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			context.setAttribute(ClienteFacade.NAME, new ClienteFacade());
			context.log("Fachada para clientes criada!");
		} catch (Exception cause) {
			context.log(ClienteFacade.NAME, cause);
		}
	}

	/** {@inheritDoc} */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		final ServletContext context = event.getServletContext();

		try {
			ClienteFacade facade = (ClienteFacade) context.getAttribute(ClienteFacade.NAME);

			facade.close();
			context.log("Fachada para clientes finalizada!");
		} catch (Exception cause) {
			context.log(ClienteFacade.NAME, cause);
		}
	}
}
