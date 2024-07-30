
package com.senac.streamingSenac.controllers;

import com.senac.streamingSenac.models.Analise;
import com.senac.streamingSenac.services.AnaliseService;
import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/analise")
public class AnaliseController {
    
    @Autowired
    private AnaliseService analiseService;
    
    
    @GetMapping("/{id}")
    public ResponseEntity<Analise> findById(@PathVariable Long id){
        Analise obj = this.analiseService.findById(id);
        return ResponseEntity.ok(obj);
    }
    
    
    @GetMapping("/filme/{filmeId}")
    public ResponseEntity<List<Analise>> findAllByFilmeId(@PathVariable Long filmeId){
      List<Analise> objs = this.analiseService.findAllByFilmeId(filmeId);
      return ResponseEntity.ok().body(objs);
    }
    
    
    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Analise obj) {
        this.analiseService.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Analise obj, @PathVariable Long id){
        obj.setId(id);
        this.analiseService.update(obj);
        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.analiseService.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
