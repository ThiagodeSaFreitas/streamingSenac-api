
package com.senac.streamingSenac.services;

import com.senac.streamingSenac.models.Filme;
import com.senac.streamingSenac.repositories.FilmeRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FilmeService {
    
    
    @Autowired
    private FilmeRepository filmeRepository;
    
   
    
    public Filme findById(Long id) {
        Optional<Filme> filme = this.filmeRepository.findById(id);
        return filme.orElseThrow(() -> new RuntimeException(
        "Filme não encontrado. Id:" + id + ", Tipo: " + Filme.class.getName()));
    }
    
    
    public List<Filme> findAll() {
        return filmeRepository.findAll();
    }
    
    @Transactional
    public Filme create(Filme obj){
        obj.setId(null);
        obj = this.filmeRepository.save(obj);
        return obj;
    }
    
    @Transactional
    public Filme update(Filme obj){
        Filme newObj = findById(obj.getId());
        newObj.setTitulo(obj.getTitulo());
        newObj.setSinopse(obj.getSinopse());
        newObj.setGenero(obj.getGenero());
        newObj.setLancamento(obj.getLancamento());
        return this.filmeRepository.save(newObj);
    } 
    
    public void delete(Long id){
        findById(id);
        
        try {
            this.filmeRepository.deleteById(id);
        } catch (Exception e){
            throw new RuntimeException("Não foi possivel excluir pois não há entidade relacionada.");
        }
        
    }    
}
