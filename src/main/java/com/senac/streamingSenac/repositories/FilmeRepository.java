
package com.senac.streamingSenac.repositories;

import com.senac.streamingSenac.models.Filme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    
    
    
}
