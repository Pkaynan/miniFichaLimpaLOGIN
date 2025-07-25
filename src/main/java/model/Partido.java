package model;

import java.util.ArrayList;
import java.util.List;

public class Partido {

    private Long id;
    private String nome;
    private String sigla;


    public Partido() {
        
    }
    
    private List<Politico> politicos = new ArrayList<>();

   	public void adicionarPolitico(Politico politico){
   		politicos.add(politico);
   	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}
