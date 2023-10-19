package com.project.fmproject.domain.infrastructure;

import com.project.fmproject.domain.model.Usuario;
import lombok.var;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class usuarioRepositoryImpl {

    @PersistenceContext
    private EntityManager manager;

    public List<Usuario> buscarPorNomeEmail(String nome, String email) {
        var jpql = "FROM Usuario WHERE 1=1";
        Map<String, Object> parametros = new HashMap<>();

        if (nome != null) {
            jpql += " AND nome LIKE :nome";
            parametros.put("nome", "%" + nome + "%");
        }

        if (email != null) {
            jpql += " AND email LIKE :email";
            parametros.put("email", "%" + email + "%");
        }

        TypedQuery<Usuario> query = manager.createQuery(jpql, Usuario.class);
        for (Map.Entry<String, Object> parametro : parametros.entrySet()) {
            query.setParameter(parametro.getKey(), parametro.getValue());
        }

        return query.getResultList();
    }



}
