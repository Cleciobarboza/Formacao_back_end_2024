package app.controller.municipio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import app.MainView;

public class MunicipioCancelarAction implements ActionListener {

    private MainView view;

    public MunicipioCancelarAction(MainView view) {
        super();

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        view.limparMunicipio();
    }

    @Override
    protected void finalize() throws Throwable {
        this.view = null;
        super.finalize();
    }
}
