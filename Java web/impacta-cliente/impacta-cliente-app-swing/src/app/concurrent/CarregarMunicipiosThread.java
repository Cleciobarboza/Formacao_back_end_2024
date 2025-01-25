package app.concurrent;

import app.MainView;

public class CarregarMunicipiosThread extends Thread {

    private MainView view;

    public CarregarMunicipiosThread(MainView view) {
        this.view = view;
    }

    @Override
    public void run() {
        try {
            view.carregarMunicipios();
        } catch (Exception cause) {
            cause.printStackTrace();
        }
    }
}
