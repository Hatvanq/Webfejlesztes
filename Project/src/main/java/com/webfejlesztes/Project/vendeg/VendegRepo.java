package com.webfejlesztes.Project.vendeg;

import org.springframework.data.repository.CrudRepository;

public interface VendegRepo extends CrudRepository<Vendeg, Integer> {
    public Long countById(Integer id);
}
