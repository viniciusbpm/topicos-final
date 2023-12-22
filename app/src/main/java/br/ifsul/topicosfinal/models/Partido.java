package br.ifsul.topicosfinal.models;

public class Partido {

    private Integer id;

    private String sigla;

    private String nome;

    private String urlLogo;

    public Partido() {
    }

    public Partido(Integer id, String sigla, String nome, String urlLogo) {
        this.id = id;
        this.sigla = sigla;
        this.nome = nome;
        this.urlLogo = urlLogo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUrlLogo() {
        return urlLogo;
    }

    public void setUrlLogo(String urlLogo) {
        this.urlLogo = urlLogo;
    }

    @Override
    public String toString() {
        return nome;
    }

    public void set(Partido partido) {
        id = partido.id;
        sigla = partido.sigla;
        nome = partido.nome;
        urlLogo = partido.urlLogo;
    }

}
