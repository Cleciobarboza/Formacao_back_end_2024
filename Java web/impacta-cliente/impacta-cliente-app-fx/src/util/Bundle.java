package util;

public interface Bundle {

    interface Dir {
        String SOURCE = "dir.source";
    }

    interface Key {
        String TITLE = "key.title";

        interface Cliente {
            String TITLE = "key.cliente.title";
        }

        interface Logradouro {
            String TITLE = "key.logradouro.title";
        }

        interface Bairro {
            String TITLE = "key.bairro.title";
        }

        interface Municipio {
            String TITLE = "key.municipio.title";
        }
    }

    interface Error {
        String HEADER_TEXT = "key.exception.header";
    }

    interface Message {
        String CANCEL = "msg.cancel";
        String EXIT = "msg.exit";

        interface Municipio {
            String CANCEL = "msg.municipio.cancel";
        }
    }

    interface Validation {
        String FAIL = "validation.fail";
    }
}
