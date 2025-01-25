package app.controller.municipio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

import app.MainView;
import domain.model.Municipio;
import domain.model.UFVO;
import facade.MunicipioFacade;

public class MunicipioOkAction implements ActionListener {

    private MainView view;

    public MunicipioOkAction(MainView view) {
        super();

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        Object uf = view.getMunicipioUfjComboBox().getSelectedItem();
        String nome = view.getMunicipiojTextField().getText();
        MunicipioFacade f;
        Municipio domain;

        if (view.getMunicipioAAlterar() != null) {
            domain = (Municipio) view.getMunicipioAAlterar();
            view.setMunicipioAAlterar(null);
        } else {
            domain = new Municipio();
        }

        domain.setNome(nome);
        domain.setUf((UFVO) uf);

        f = view.getMunicipioFacade();

        try (PrintWriter log = new PrintWriter("municipioErro.log");) {
            try {
                f.validar(domain);
                f.salvar(domain);
                view.limparMunicipio();
                view.carregarMunicipios();
                view.getStatusMessageLabel().setText("Município salvo!");
            } catch (Exception cause) {
                cause.printStackTrace(log);
                JOptionPane.showMessageDialog(null, cause.getMessage());
            }
        } catch (Exception cause) {
            cause.printStackTrace();
            JOptionPane.showMessageDialog(null, cause.getMessage());
        }
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        this.view = null;
    }
}
