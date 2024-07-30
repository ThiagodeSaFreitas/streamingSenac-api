
package com.senac.streamingSenac.repositories;

import com.senac.streamingSenac.models.Analise;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnaliseRepository extends JpaRepository<Analise, Long> {
    
    List<Analise> findByUser_Id(Long id);
    
}
