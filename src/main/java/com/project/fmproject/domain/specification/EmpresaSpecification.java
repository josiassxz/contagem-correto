package com.project.fmproject.domain.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.project.fmproject.domain.model.PostoContagem;

public class EmpresaSpecification {

    public static Specification<PostoContagem> filtrarEmpresas(String nome, String cnpj, String razaoSocial){
        return (Root<PostoContagem> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null) {
                predicates.add(criteriaBuilder.like(root.get("nome"), "%" + nome + "%"));
            }
            
             if (razaoSocial != null) {
                predicates.add(criteriaBuilder.like(root.get("razaoSocial"), "%" + razaoSocial + "%"));
            }

             if (cnpj != null) {
                predicates.add(criteriaBuilder.like(root.get("cnpj"), "%" + cnpj + "%"));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    };
}
    
}
