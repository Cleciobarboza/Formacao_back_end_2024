package app.controller.municipio;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import app.MainView;
import domain.model.Municipio;

public class MunicipioAlterarAction implements ActionListener {

    private MainView view;

    public MunicipioAlterarAction(MainView view) {
        super();

        this.view = view;
    }

    public void actionPerformed(ActionEvent e) {
        JTable t = view.getMunicipiosjTable();
        int selecionados = t.getSelectedRowCount();

        if (selecionados != 1) {
            JOptionPane.showMessageDialog(null, 
                    "Por favor, selecione apenas um município!");
            return;
        }

        int linha = t.getSelectedRow();
        Municipio municipio = (Municipio) t.getValueAt(linha, 0);
        JTextField nome = view.getMunicipiojTextField();

        nome.setText(municipio.getNome());

        view.setMunicipioAAlterar(municipio);
    }

    @Override
    protected void finalize() throws Throwable {
        this.view = null;
        super.finalize();
    }
}
