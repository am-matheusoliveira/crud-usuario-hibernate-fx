package com.matheus.dao;

import com.matheus.modelo.Usuario;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class UsuarioDAO {

    private EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("conexaoPrincipal");
    private EntityManager entityManager = entityManagerFactory.createEntityManager();

    // Método para listar todos os usuários
    public List<Usuario> listarUsuarios() {
        return entityManager.createQuery("FROM Usuario", Usuario.class).getResultList();
    }
    // Método para listar apenas um usuario pelo seu ID
    public Usuario buscar(Long id) {
        return entityManager.find(Usuario.class, id);
    }

    // Método para salvar um usuário
    public void salvar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(usuario); // Salva o novo usuário
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Desfaz a transação se houver erro
            }
            e.printStackTrace();
        }
    }

    // Método para atualizar m usuário
    public void atualizar(Usuario usuario) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.merge(usuario); // Atualiza o usuário existente
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Desfaz a transação se houver erro
            }
            e.printStackTrace();
        }
    }

    // Método para deletar um usuário
    public void deletar(Long id) {
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            Usuario usuario = entityManager.find(Usuario.class, id); // Busca o usuário pelo ID
            if (usuario != null) {
                entityManager.remove(usuario); // Remove o usuário
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback(); // Desfaz a transação se houver erro
            }
            e.printStackTrace();
        }
    }

    // Fechando a conexão com o Banco de Dados
    public void fechar() {
        entityManager.close();
        entityManagerFactory.close();
    }
}