package com.webfejlesztes.Project.vendeg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VendegServ {
    @Autowired private VendegRepo repo;

    public List<Vendeg> listAll() {
        return (List<Vendeg>) repo.findAll();
    }

    public void save(Vendeg vendeg) {
        repo.save(vendeg);
    }

    public Vendeg get(Integer id) throws UserNotFoundExeption {
        Optional<Vendeg> result = repo.findById(id);
        if (result.isPresent()) {
            return result.get();
        }
        throw new UserNotFoundExeption("Nincs ilyen ID" + id);
    }

    public void delete(Integer id) throws UserNotFoundExeption {
        Long count = repo.countById(id);
        if (count == null || count == 0) {
            throw new UserNotFoundExeption("Nincs ilyen ID" + id);
        }
        repo.deleteById(id);
    }
}
