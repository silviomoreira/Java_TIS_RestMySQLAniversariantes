package tisProjetos.tutorialrestapi.repository;

import org.springframework.data.repository.CrudRepository;
import tisProjetos.tutorialrestapi.model.UsuarioModel;

public interface UsuarioRepository extends CrudRepository<UsuarioModel, Integer> {
}
