package tisProjetos.tutorialrestapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tisProjetos.tutorialrestapi.model.AniversariantesModel;
import tisProjetos.tutorialrestapi.repository.AniversariantesCustomRepository;
import tisProjetos.tutorialrestapi.repository.AniversariantesRepository;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AniversariantesController {

    private final AniversariantesRepository repository;
    private final AniversariantesCustomRepository customRepository;

    public AniversariantesController(AniversariantesRepository repository, AniversariantesCustomRepository customRepository) {
        this.repository = repository;
        this.customRepository = customRepository;
    }
    /*@Autowired
    private AniversariantesRepository repository;
    @Autowired
    private AniversariantesCustomRepository customRepository;*/

    @GetMapping(path = "/api/aniversariantes/{id}")
    public ResponseEntity consultar(@PathVariable("id") Integer id) {
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    // 1) Cadastrar pessoa na agenda de aniversariantes (nome, dia e mês do aniversário)
    @PostMapping(path = "/api/aniversariantes/salvar")
    public AniversariantesModel salvar(@RequestBody AniversariantesModel aniversariante) {
        return repository.save(aniversariante);
    }

    @GetMapping(path = "/api/aniversariantes/filtronomeexato")
    public AniversariantesModel acharAniversariantePeloNome(@RequestParam("nome") String nome) {
        return this.repository.findByNome(nome);
    }

    // 2) Excluir pessoa a partir do nome
    @DeleteMapping(path = "/api/aniversariantes/excluir")
    public ResponseEntity<Long> excluirPeloNome(@RequestParam("nome") String nome) {
        AniversariantesModel aniversariante = this.repository.findByNome(nome);
        this.repository.delete(aniversariante);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping(path = "/api/aniversariantes/alterardia")
    public AniversariantesModel alterarDiaPeloNome(@RequestParam("nome") String nome, @RequestParam("dia") Integer dia) {
        AniversariantesModel aniversariante = this.repository.findByNome(nome);
        aniversariante.setDia(dia);
        return repository.save(aniversariante);
    }

    // 3) Alterar dia ou mês a partir do nome
    @PostMapping(path = "/api/aniversariantes/alterardiames")
    public AniversariantesModel alterarDiaMesPeloNome(@RequestParam("nome") String nome,
                                                      @RequestParam(value="dia", required = false) Integer dia,
                                                      @RequestParam(value="mes", required = false) Integer mes) {
        AniversariantesModel aniversariante = this.repository.findByNome(nome);
        if (dia != null){
            aniversariante.setDia(dia);
        }
        if (mes != null){
            aniversariante.setMes(mes);
        }
        return repository.save(aniversariante);
    }

    // 4) Consultar aniversariantes de uma data(dia e mês)
    @GetMapping("/api/aniversariantes/filtrodiames")
    public List<AniversariantesModel> buscarAniversariantesPeloDiaEMes(@RequestParam(value="dia", required = false) Integer dia,
                                                                       @RequestParam(value="mes", required = false) Integer mes) {
       return this.customRepository.findByDiaEMes(dia, mes)
                .stream()
                .map(AniversariantesModel::converter)
                .collect(Collectors.toList());
    }

    // 5) Consultar aniversariantes por mês
/*    @GetMapping(path = "/api/aniversariantes/filtromes")
    public AniversariantesModel buscarAniversariantePeloMes(@RequestParam("mes") Integer mes) {
        return this.customRepository.findByMes(mes)
                .stream()
                .map(AniversariantesModel::converter)
                .collect(Collectors.toList());
    }*/

    // 6) Consultar aniversariantes pela letra inicial do nome
    @GetMapping(path = "/api/aniversariantes/filtronome")
    public List<AniversariantesModel> buscarAniversariantesPeloNomeLike(@RequestParam("nome") String nome) {
        return this.repository.findByNomeContains(nome)
                .stream()
                .map(AniversariantesModel::converter)
                .collect(Collectors.toList());
    }

    // 7) Mostrar toda a agenda ordenada pelo nome
    @GetMapping(path = "/api/aniversariantes/ordenadapornome")
    public List<AniversariantesModel> listaAniversariantesOrdPorNome() {
        return this.customRepository.listaAniversariantesOrdPorNome()
                .stream()
                .map(AniversariantesModel::converter)
                .collect(Collectors.toList());
    }

    // 8) Mostrar toda a agenda ordenada por mês
    @GetMapping(path = "/api/aniversariantes/ordenadapormes")
    public List<AniversariantesModel> listaAniversariantesOrdPorMes() {
        return this.customRepository.listaAniversariantesOrdPorMes()
                .stream()
                .map(AniversariantesModel::converter)
                .collect(Collectors.toList());
    }
}

