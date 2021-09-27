package tisProjetos.tutorialrestapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
//import javax.persistence.GeneratedValue;//novo, coloc. por causa do get()
//import javax.persistence.GenerationType;//novo, coloc. por causa do get()

@Entity(name = "aniversariantes")
public class AniversariantesModel {

    @Id
    public Integer id;

    @Column(nullable = false, length = 100)
    public String nome;

    @Column(nullable = false)
    public Integer dia;

    @Column(nullable = false)
    public Integer mes;

    public Integer getId() {
        return id;
    }

    public static AniversariantesModel converter(AniversariantesModel a) {
        AniversariantesModel aniversariantesModel = new AniversariantesModel();
        aniversariantesModel.setNome(a.getNome());
        aniversariantesModel.setDia(a.getDia());
        aniversariantesModel.setMes(a.getMes());
        return a;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDia() {
        return dia;
    }

    public void setDia(Integer dia) {
        this.dia = dia;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }
}
