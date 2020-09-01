package br.com.alexandria.autores.model.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.alexandria.autores.dao.AutorDAO;
import br.com.alexandria.autores.model.Autor;

@ManagedBean
@RequestScoped
public class AutorBean {

    
    private Integer id;

    private String nome;

    private String nacionalidade;

    private String anoNascimento;

    private String notas;

    private Integer rating;
    
    private Autor autor = new Autor();
    
    private AutorDAO autorDAO = new AutorDAO();

    private List<Autor> autores;
        
    
    public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(String nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getAnoNascimento() {
		return anoNascimento;
	}

	public void setAnoNascimento(String anoNascimento) {
		this.anoNascimento = anoNascimento;
	}

	public String getNotas() {
		return notas;
	}

	public void setNotas(String notas) {
		this.notas = notas;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	
    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    @PostConstruct
    public void init() {
    	System.out.println("iniciando...");
        autores = autorDAO.getAllAutor();
        System.out.println("lista de autores na base de dados carregada");
    }

    public String addAutor() {
    	autor.setId(null);
    	autor.setNome(nome);
    	autor.setNotas(notas);
    	autor.setAnoNascimento(anoNascimento);
    	autor.setRating(rating);
    	autor.setNacionalidade(nacionalidade);
    	System.out.println("to adicionando");
        autorDAO.addAutor(autor);
        System.out.println("adicionado!");
        return "success";
    }

    public String updateAutor(int id) {
    	System.out.println("atualizando...");
    	autor.setId(id);
    	autor.setNome(nome);
    	autor.setNotas(notas);
    	autor.setAnoNascimento(anoNascimento);
    	autor.setRating(rating);
    	autor.setNacionalidade(nacionalidade);
        autorDAO.updateAutor(autor);
        System.out.println("atualizado!");
        return "success";
    }

    public String deleteAutor(int id) {
    	System.out.println("removendo..."+id);
        autorDAO.removeAutor(id);
        System.out.println("removido!");
        return "success";
    }
    
}