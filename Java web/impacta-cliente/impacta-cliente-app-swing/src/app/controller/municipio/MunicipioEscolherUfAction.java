package app.controller.municipio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.MainView;
import app.concurrent.CarregarMunicipiosThread;

public class MunicipioEscolherUfAction implements ActionListener {

    private MainView view;

    public MunicipioEscolherUfAction(MainView view) { // Composição
        super();

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        Thread t = new CarregarMunicipiosThread(view);

        t.start();
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.view = null;
    }
}
