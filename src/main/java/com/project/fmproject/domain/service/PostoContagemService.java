package com.project.fmproject.domain.service;


import com.project.fmproject.domain.exception.EntidadeNaoEncontradaException;
import com.project.fmproject.domain.model.PostoContagem;
import com.project.fmproject.domain.model.Usuario;
import com.project.fmproject.domain.repository.PostoContagemRepository;
import com.project.fmproject.domain.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostoContagemService {
    @Autowired
    private  PostoContagemRepository postoContagemRepository;
    @Autowired
    private  UsuarioRepository usuarioRepository;

//    public PostoContagemService(PostoContagemRepository postoContagemRepository, UsuarioRepository usuarioRepository) {
//        this.postoContagemRepository = postoContagemRepository;
//        this.usuarioRepository = usuarioRepository;
//    }

    public Page<PostoContagem> listarEmpresas(Pageable pageable) {
        return postoContagemRepository.findAll(pageable);
    }

    public List<PostoContagem> listPostoContagem() {
        return postoContagemRepository.findAll();
    }

    public PostoContagem buscarPostoContagemPorId(Long id) {
        return postoContagemRepository.findById(id)
                .orElseThrow(() -> new EntidadeNaoEncontradaException("Posto de contagem não encontrada"));
    }


    public PostoContagem salvarEmpresa(PostoContagem empresa) {
        List<Usuario> usuarios = new ArrayList<>();
        if (empresa.getUsuarios() != null) {
            for (Usuario usuario : empresa.getUsuarios()) {
                Usuario usuarioExistente = usuarioRepository.findById(usuario.getId())
                        .orElseThrow(() -> new EntidadeNaoEncontradaException("Usuário não encontrado com o ID informado: " + usuario.getId()));
                usuarios.add(usuarioExistente);
            }
        }
        empresa.setUsuarios(usuarios);
        return postoContagemRepository.save(empresa);
    }



    public PostoContagem atualizarEmpresa(Long id, PostoContagem empresaAtualizada) {
        PostoContagem empresaExistente = buscarPostoContagemPorId(id);
        empresaExistente.setLocal(empresaAtualizada.getLocal());
        empresaExistente.setKm(empresaAtualizada.getKm());
        empresaExistente.setSentido(empresaAtualizada.getSentido());
        empresaExistente.setDataContagem(empresaAtualizada.getDataContagem());
        return postoContagemRepository.save(empresaExistente);
    }

    public void deletarEmpresa(Long id) {
        postoContagemRepository.deleteById(id);
    }


}
