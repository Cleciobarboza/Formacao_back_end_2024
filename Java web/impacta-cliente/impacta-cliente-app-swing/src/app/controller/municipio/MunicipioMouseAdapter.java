package app.controller.municipio;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPopupMenu;

public class MunicipioMouseAdapter extends MouseAdapter {

        private JPopupMenu menu;

        public MunicipioMouseAdapter(JPopupMenu menu) {
            super();
            this.menu = menu;
        }

        @Override
        public void mousePressed(MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }

        @Override
        public void mouseReleased(MouseEvent evt) {
            if (evt.isPopupTrigger()) {
                menu.show(evt.getComponent(), evt.getX(), evt.getY());
            }
        }
}
