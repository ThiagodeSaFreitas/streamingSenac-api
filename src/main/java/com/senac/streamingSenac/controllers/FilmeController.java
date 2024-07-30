
package com.senac.streamingSenac.controllers;

import com.senac.streamingSenac.models.Filme;
import com.senac.streamingSenac.services.FilmeService;
import jakarta.validation.Valid;
import java.net.URI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
@RequestMapping("/filme")
@Validated
public class FilmeController {
    
    @Autowired
    private FilmeService filmeservice;
    
    @GetMapping("/{id}")
    public ResponseEntity<Filme> findById(@PathVariable Long id){
        Filme obj = this.filmeservice.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    
    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody Filme obj){
        this.filmeservice.create(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Filme obj, @PathVariable Long id){
        obj.setId(id);
        this.filmeservice.update(obj);
        return ResponseEntity.noContent().build();
        
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.filmeservice.delete(id);
        return ResponseEntity.noContent().build();
    }
    
}
