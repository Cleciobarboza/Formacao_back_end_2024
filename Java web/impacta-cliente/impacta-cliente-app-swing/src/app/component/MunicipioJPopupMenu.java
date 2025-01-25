package app.component;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;

import app.MainView;
import app.controller.municipio.MunicipioAlterarAction;
import app.controller.municipio.MunicipioApagarAction;
import app.controller.municipio.MunicipioSelecionarTodosAction;

public class MunicipioJPopupMenu extends JPopupMenu {

    private static final long serialVersionUID = 8201759039795977257L;

    private MainView view;

    public MunicipioJPopupMenu(MainView view) {
        super();

        this.view = view;

        initComponents();
    }

    private void initComponents() {
        JMenuItem item;

        item = new JMenuItem("Alterar");
        item.addActionListener(new MunicipioAlterarAction(view));
        this.add(item);

        item = new JMenuItem("Apagar");
        item.addActionListener(new MunicipioApagarAction(view));
        this.add(item);

        this.add(new JSeparator());

        item = new JMenuItem("Selecionar todos");
        item.addActionListener(new MunicipioSelecionarTodosAction(view));
        this.add(item);
    }

    @Override
    protected void finalize() throws Throwable {
        this.view = null;
        super.finalize();
    }
}
