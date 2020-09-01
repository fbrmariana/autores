package br.com.alexandria.autores.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.alexandria.autores.model.Autor;

public class AutorDAO {
    
    public AutorDAO() {
        
    }
    
    private EntityManager createEntityManager() {
    	EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("dev");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        return entityManager;
    }


    
    public void addAutor(Autor autor) {
    	EntityManager entityManager = this.createEntityManager();
    	System.out.println("DAO "+autor.getNome());
    	entityManager.persist(autor);
    	entityManager.getTransaction().commit();
    	System.out.println("Salvo DAO "+autor.getNome());
    }

    
    public Autor getAutor(int id) {
        return getAllAutor().get(id);
    }

    
    public void removeAutor(int id) {
    	EntityManager em = this.createEntityManager();
    	System.out.println("DAO removendo... "+id);
    	Autor autor = findObjectById(id);
    	em.remove(em.getReference(Autor.class, autor.getId()));
    	em.getTransaction().commit();
        System.out.println("DAO removido! "+id);
    }
    
    public Autor findObjectById(int id) {
        System.out.println("Buscando por id");
        EntityManager em = this.createEntityManager();
        Autor autor = em.find(Autor.class, id);
        System.out.println("Achou o autor de id "+autor.getId());
        return autor;
    }

   
    public void updateAutor(Autor autor) {
    	EntityManager em = this.createEntityManager();
    	System.out.println("DAO atualizando... "+autor.getId());
    	Autor newAutor = findObjectById(autor.getId());
    	newAutor.setAnoNascimento(autor.getAnoNascimento());
    	newAutor.setNacionalidade(autor.getNacionalidade());
    	newAutor.setNome(autor.getNome());
    	newAutor.setNotas(autor.getNotas());
    	newAutor.setRating(autor.getRating());
    	System.out.println("DAO atualizando... "+newAutor.getNome());
        em.merge(newAutor);
        em.getTransaction().commit();
        System.out.println("DAO atualizado! "+autor.getId());
    }


    public List<Autor> getAllAutor() {
    	EntityManager em = this.createEntityManager();
        return em.createQuery("SELECT autor FROM Autor autor", Autor.class).getResultList();
    }
}
