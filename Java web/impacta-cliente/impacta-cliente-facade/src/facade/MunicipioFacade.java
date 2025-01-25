package facade;

import java.util.Collection;
import java.util.Vector;

import domain.Closeable;
import domain.exception.MunicipioException;
import domain.model.Municipio;
import domain.model.UFVO;
import facade.exception.DomainException;
import service.MunicipioService;
import service.impl.DefaultMunicipioService;

public class MunicipioFacade {

    public static final String NAME = "MUNICIPIO_FACADE";
    public static final String SOURCE = "impacta_source";

    private MunicipioService service;

    public MunicipioFacade(String source) throws Exception {
        super();

        this.service = new DefaultMunicipioService(source);
    }

    public void validar(Object municipio) throws Exception {
        try {
            service.validar((Municipio) municipio);
        } catch (ClassCastException cause) {
            throw new MunicipioException(cause);
        } catch (Exception cause) {
            throw new DomainException(cause);
        }
    }

    public void validar(String municipio, String uf) throws Exception {
        validar((Long) null, municipio, uf);
    }

    public void validar(String id, String municipio, String uf) throws Exception {
        validar(id == null || id.isEmpty() ? null : Long.valueOf(id), municipio, uf);
    }

    public void validar(Long id, String municipio, String uf) throws Exception {
        validar(new Municipio(id, municipio, uf));
    }

    public void salvar(String municipio, String uf) throws Exception {
        salvar((Long) null, municipio, uf);
    }

    public void salvar(String id, String municipio, String uf) throws Exception {
        salvar(id == null || id.isEmpty() ? null : Long.valueOf(id), municipio, uf);
    }

    public void salvar(Long id, String municipio, String uf) throws Exception {
        salvar(new Municipio(id, municipio, uf));
    }

    public void salvar(Object municipio) throws Exception {
        try {
            service.salvar((Municipio) municipio);
        } catch (ClassCastException cause) {
            throw new MunicipioException(cause);
        } catch (Exception cause) {
            throw new DomainException(cause);
        }
    }

    public void apagar(Object domain) throws Exception {
        service.apagar((Municipio) domain);
    }

    public void apagarPorId(String id) throws Exception {
        apagarPorId(Long.valueOf(id));
    }

    public void apagarPorId(Long id) throws Exception {
        apagar(new Municipio(id));
    }

    public Collection<Object[]> listar(Object uf) throws Exception {
        UFVO vo = (UFVO) UFVO.verificar(uf);
        final Collection<Object[]> domains;
        final Collection<Municipio> municipios;

        domains = new Vector<Object[]>();
        municipios = vo.isNotSelecionado() ? service.listarTodos() : service.listar(vo);

        for (Municipio municipio : municipios) {
            domains.add(municipio.toArray());
        }

        return domains;
    }

    public Integer contarTodos() throws Exception {
        return service.contarTodos();
    }

    public void close() throws Exception {
        ((Closeable) service).close();
    }
}
