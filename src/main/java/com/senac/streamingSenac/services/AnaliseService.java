
package com.senac.streamingSenac.services;

import com.senac.streamingSenac.models.Analise;
import com.senac.streamingSenac.models.Filme;
import com.senac.streamingSenac.repositories.AnaliseRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnaliseService {
    
    @Autowired
    private FilmeService filmeService;
    
    @Autowired
    private AnaliseRepository analiseRepository;
    
    public Analise findById(Long id){
        Optional<Analise> analise = this.analiseRepository.findById(id);
        return analise.orElseThrow(() -> new RuntimeException(
        "Analise não encontrado. Id:" + id + ", Tipo: " + Analise.class.getName()));
    }
    
    public List<Analise> findAllByFilmeId(Long filmeId){
      List<Analise> analises = this.analiseRepository.findByFilme_Id(filmeId);
      return analises;
    }
    
    
    @Transactional
    public Analise create(Analise obj){
        Filme filme = this.filmeService.findById(obj.getFilme().getId());
        obj.setId(null);
        obj = this.analiseRepository.save(obj);
        return obj;
    }
    
    @Transactional
    public Analise update(Analise obj){
        Analise newObj = findById(obj.getId());
        newObj.setOpniao(obj.getOpniao());
        return this.analiseRepository.save(newObj);
    }
    
    public void delete(Long id){
        findById(id);
        try{
            this.analiseRepository.deleteById(id);
        } catch (Exception e) {
           throw new RuntimeException("Não foi possivel excluir pois não há entidade relacionada.");

        }
        
    }
    
    
    
    
    
}
