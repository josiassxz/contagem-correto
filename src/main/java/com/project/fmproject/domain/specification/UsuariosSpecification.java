package com.project.fmproject.domain.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.project.fmproject.domain.model.Usuario;

public class UsuariosSpecification {
    
    public static Specification<Usuario> filtrarUsuarios(String nome, String email, String tipo){
        return (Root<Usuario> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null) {
                predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
            }
            
             if (email != null) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + email + "%"));
            }

             if (tipo != null) {
                predicates.add(criteriaBuilder.like(root.get("tipo"), "%" + tipo + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
}

}
