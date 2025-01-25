package app.controller.municipio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JTable;

import app.MainView;

public class MunicipioSelecionarTodosAction implements ActionListener {

    private MainView view;

    public MunicipioSelecionarTodosAction(MainView view) {
        super();

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        final JTable t = view.getMunicipiosjTable();
        final int selecionados = t.getSelectedRowCount();
        final JMenuItem mi = (JMenuItem) e.getSource();

        if (selecionados == 0) {
            t.selectAll();
            mi.setText("Limpar seleção");
        } else {
            t.clearSelection();
            mi.setText("Selecionar todos");
        }
    }

    @Override
    protected void finalize() throws Throwable {
        this.view = null;
        super.finalize();
    }
}
