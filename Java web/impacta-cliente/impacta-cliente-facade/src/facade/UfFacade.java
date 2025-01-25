package facade;

import java.util.Arrays;
import java.util.Collection;

import domain.model.UFVO;

public class UfFacade {

    public static final String NAME = "UF_FACADE";
    public static final String FIELD = "uf";

    public Collection<?> listarUFs() {
        return Arrays.asList(UFVO.values());
    }

    public UFVO valueOf(String uf) {
        uf = UFVO.verificar(uf);
        return UFVO.valueOf(uf);
    }
}
