package domain.model;

import domain.exception.UFException;

public enum UFVO {
    ESCOLHA, AC, AL, AM, AP, BA, CE, DF, ES, GO, MA, MG, MS, MT, PA, PB, PE, PI, PR, RJ, RN, RO, RR, RS, SC, SE, SP, TO;

    public boolean isSelecionado() {
        return !ESCOLHA.equals(this);
    }

    public boolean isNotSelecionado() {
        return !isSelecionado();
    }

    public static String verificar(String uf) {
        return uf == null ? ESCOLHA.toString() : uf;
    }

    public static Object verificar(Object uf) throws UFException {
        if (uf == null) {
            return ESCOLHA;
        }

        if (uf instanceof String) {
            return UFVO.valueOf((String) uf);
        }

        if (uf instanceof UFVO) {
            return uf;
        }

        throw new UFException("UF inválida!");
    }
}
