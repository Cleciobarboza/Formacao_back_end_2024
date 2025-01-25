package app.controller.municipio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import app.MainView;
import facade.MunicipioFacade;

public class MunicipioApagarAction implements ActionListener {

    private MainView view;

    public MunicipioApagarAction(MainView view) {
        super();

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        JTable t;
        int selecionados;

        t = view.getMunicipiosjTable();
        selecionados = t.getSelectedRowCount();

        if (selecionados == 0) {
            JOptionPane.showMessageDialog(null, "Por favor, selecione municípios a apagar!");
            return;
        }

        if (JOptionPane.showConfirmDialog(null, "Confirma apagar?") == JOptionPane.YES_OPTION) {
            MunicipioFacade f = view.getMunicipioFacade();

            try {
                Object municipio;
                int[] linhas = t.getSelectedRows();

                for (int i : linhas) {
                    municipio = t.getValueAt(i, 0);

                    f.apagar(municipio);
                }

                view.limparMunicipio();
                view.carregarMunicipios();
            } catch (Exception cause) {
                cause.printStackTrace();
                JOptionPane.showMessageDialog(null, cause.getMessage());
            }
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.view = null;
        super.finalize();
    }
}
