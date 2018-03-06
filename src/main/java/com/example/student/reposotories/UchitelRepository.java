package com.example.student.reposotories;


import com.example.student.models.Uchitel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UchitelRepository extends CrudRepository<Uchitel, Long> {
    Optional<Uchitel> findById(Long id);
}
