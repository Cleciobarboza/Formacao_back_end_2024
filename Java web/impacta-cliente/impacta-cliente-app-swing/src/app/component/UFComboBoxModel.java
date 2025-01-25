package app.component;

import javax.swing.DefaultComboBoxModel;

import domain.model.UFVO;

public class UFComboBoxModel extends DefaultComboBoxModel<Object> {

    private static final long serialVersionUID = 7060849720926524792L;

    public UFComboBoxModel() {
        super(UFVO.values());
    }
}
