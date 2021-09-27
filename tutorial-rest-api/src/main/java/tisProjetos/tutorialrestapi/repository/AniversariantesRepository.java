package tisProjetos.tutorialrestapi.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tisProjetos.tutorialrestapi.model.AniversariantesModel;

import javax.persistence.NamedNativeQuery;
import java.util.List;

@Repository
public interface AniversariantesRepository extends JpaRepository<AniversariantesModel, Integer> {
    AniversariantesModel findByNome(String nome);
    List<AniversariantesModel> findByNomeContains(String nome);
}
