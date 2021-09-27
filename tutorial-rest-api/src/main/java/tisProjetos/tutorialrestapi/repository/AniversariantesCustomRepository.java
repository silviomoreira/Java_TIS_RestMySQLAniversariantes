package tisProjetos.tutorialrestapi.repository;

import org.springframework.stereotype.Repository;
import tisProjetos.tutorialrestapi.model.AniversariantesModel;

import javax.persistence.EntityManager;
import javax.persistence.Query;
//import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AniversariantesCustomRepository {
    private final EntityManager em;

    public AniversariantesCustomRepository(EntityManager em) {
        this.em = em;
    }

    public List<AniversariantesModel> findByDiaEMes(Integer dia, Integer mes) {

        String query = "select id, nome, dia, mes from Aniversariantes as A ";
        String condicao = "where";

        if(dia != null) {
            query += condicao + " A.dia = :dia";
            condicao = " and ";
        }

        if(mes != null) {
            query += condicao + " A.mes = :mes";
        }

        Query q = em.createQuery(query, AniversariantesModel.class);

        if(dia != null) {
            q.setParameter("dia", dia);
        }

        if(mes != null) {
            q.setParameter("mes", mes);
        }

        return q.getResultList();
    }

    /*public List<AniversariantesModel> findByMes(Integer mes) {

        String query = "select id, nome, dia, mes from Aniversariantes as A ";
        String condicao = "where";

        if(mes != null) {
            query += condicao + " A.mes = :mes";
        }

        Query q = em.createQuery(query, AniversariantesModel.class);

         if(mes != null) {
            q.setParameter("mes", mes);
        }

        return q.getResultList();
    }*/

    public List<AniversariantesModel> listaAniversariantesOrdPorNome() {

        String query = "select * from Aniversariantes order by nome";
        Query q = em.createQuery(query, AniversariantesModel.class);
        return q.getResultList();
    }
    public List<AniversariantesModel> listaAniversariantesOrdPorMes() {

        String query = "select * from Aniversariantes order by mes";
        Query q = em.createQuery(query, AniversariantesModel.class);
        return q.getResultList();
    }

    //findByMes
    //listaAniversariantesOrdPorNome
    //listaAniversariantesOrdPorMes
}

